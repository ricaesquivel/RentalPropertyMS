package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListingsController {
	
	private ListingsView listings;
	private EmailView emailView;
	private MyListener listener;
	private LoginView login;
	private SearchCriteriaView searchView;
	private PropertyDatabaseController database;
	private UserDatabaseController userDatabase;
	
	private String quadChoice = "";
	private String furnishChoice = "";
	private String houseTypeChoice = "";
	private String selected = "";
	
	public ListingsController(Client c) {

		database = c.propertyDatabase;
		listings = c.listings;
        searchView = c.searchView;
        login = c.loginView;
        emailView = c.emailView;
        userDatabase = c.userDatabase;
        
        listener = new MyListener();
        addListeners();
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.updateButton) {
					listings.clear();
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "LandlordID"});
					String result = database.listAll("active");
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						listings.addElementTextBox(row);
					}
					listings.autoColWidth();
					listings.hideLandlordCol();
					listings.buttonState(false);
				} 
				else if(event.getSource() == listings.emailButton) {
					emailView.clear();
					emailView.setVisible(true);
				} 
				else if(event.getSource() == listings.searchButton) {
					searchView.setVisible(true);
				} 
				else if(event.getSource() == emailView.cancelBtn) {
					emailView.setVisible(false);
					emailView.clear();
				} 
				else if(event.getSource() == emailView.sendBtn) {
					String contactText = ""; String emailText = "";
					
					contactText = emailView.getContact();
					emailText = emailView.getEmail();
					
					if(contactText.length() < 5) {
						emailView.errorMessage("Please enter a valid phone number or email address");
						return;
					}
					if(emailText.length() < 5) {
						emailView.errorMessage("Please write a decent email");
						return;
					}
					
					userDatabase.sendEmail(Integer.parseInt(selected), contactText, emailText);
					emailView.errorMessage("email sent!");
					
					emailView.clear();
					emailView.setVisible(false);
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
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "LandlordID"});
					String[] arr = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						listings.addElementTextBox(row);
					}
					listings.autoColWidth();
					listings.hideLandlordCol();
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
		emailView.addListener(listener);
		
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
                System.exit(0);
//                login.setVisible(true);
            }
        });
		listings.addSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!e.getValueIsAdjusting() && listings.textBox.getSelectedRow() != -1){
	        		selected = listings.textBox.getModel().getValueAt(listings.textBox.getSelectedRow(),6).toString();
	        		listings.emailButton.setEnabled(true);
	        	}
	        }
	    });
	}
}
