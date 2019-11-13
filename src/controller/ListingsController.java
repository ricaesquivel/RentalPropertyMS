package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.LoginController.MyListener;

public class ListingsController {
	
	private ListingsView listings;
	private MyListener listener;
	private LoginView login;
	private SearchCriteriaView searchView;
	private PropertyDatabaseController database;
	
	private String quadChoice = "";
	private String furnishChoice = "";
	private String houseTypeChoice = "";
	
	
	public ListingsController(Client c) {

		database = c.propertyDatabase;
		listings = c.listings;
        searchView = c.searchView;
        login = c.loginView;
        
        listener = new MyListener();
        addListeners();
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.updateButton) {
					listings.clear();
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished"});
					String result = database.listAll("active");
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						listings.addElementTextBox(row);
					}
					listings.autoColWidth();
				} 
				if(event.getSource() == listings.searchButton) {
					searchView.setVisible(true);
				} 
				else if(event.getSource() == searchView.submitButton) {
					listings.clear();
					String bedrooms = searchView.getBedrooms();
					String bathrooms = searchView.getBathrooms();
					int beds = Integer.MAX_VALUE; int baths = Integer.MAX_VALUE;
					
					try{
						if(!bedrooms.equals(""))
							beds = Integer.parseInt(bedrooms);
						if(!bathrooms.equals(""))
							baths = Integer.parseInt(bathrooms);
						if(beds < 1 || baths < 1)
							throw new NumberFormatException();
                    }catch(NumberFormatException a){
                        searchView.errorMessage("Please enter a valid bathroom and bedroom number");
                        return;
                    }
					
					String result = database.searchProperty(houseTypeChoice, furnishChoice, quadChoice, beds, baths ,"active");
					if(result.equals("") || result.contentEquals("\n")) {
						searchView.errorMessage("No results, try changing filters");
						return;
					}
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished"});
					String[] arr = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						listings.addElementTextBox(row);
					}
					listings.autoColWidth();
					searchView.clearText();
					searchView.setVisible(false);
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("Error in ListingsController");
			}
		}
		
		
	}
	private void addListeners() {
		searchView.addSubmitListener(listener);
		listings.addListener(listener);
		
		searchView.addQuadDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		quadChoice = (String)e.getItem();
            	}
            }
        });
		searchView.addHouseDropdownListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					houseTypeChoice = (String)e.getItem();
				}
			}
		});
		searchView.addfurnishDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		furnishChoice = (String)e.getItem();
            	}
            }
        });
		searchView.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                searchView.clearText();
                searchView.setVisible(false);
            }
        });
		listings.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                searchView.clearText();
                listings.setVisible(false);
                login.setVisible(true);
            }
        });
	}
}
