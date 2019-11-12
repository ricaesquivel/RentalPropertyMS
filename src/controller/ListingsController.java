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
	private SearchCriteriaView searchView;
	private PropertyDatabaseController database;
	
	private String quadChoice = "";
	private String furnishChoice = "";
	private String houseTypeChoice = "";
	
	
	public ListingsController(Client c) {

		database = c.propertyDatabase;
		listings = c.listings;
        searchView = c.searchView;
        
        listener = new MyListener();
        addListeners();
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.searchButton) {
					searchView.setVisible(true);
				} 
				else if(event.getSource() == searchView.submitButton) {
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
					
					String result = database.searchProperty(houseTypeChoice, furnishChoice, quadChoice, beds, baths);
					String[] arr = result.split("\n");
					for (String string : arr) {
						searchView.errorMessage(string);
						listings.addElementTextBox(string);
					}
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
	}
}
