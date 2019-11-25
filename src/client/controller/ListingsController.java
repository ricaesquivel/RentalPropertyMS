package client.controller;

import client.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import client.view.EmailView;
import client.view.ListingsView;
import client.view.LoginView;
import client.view.SearchCriteriaView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;

public class ListingsController{
	
	private ClientCommunicator comms;
	private ListingsView listings;
	private EmailView emailView;
	private MyListener listener;
	private LoginView login;
	private SearchCriteriaView searchView;
	private SubscriptionsView subView;
	
	private String quadChoice = "";
	private String furnishChoice = "";
	private String houseTypeChoice = "";
	private String selected = "";
	private String selectedSubType = "";
	private String selectedSubFurnish = "";
	private String selectedSubBeds = "";
	private String selectedSubBaths = "";
	private String selectedSubQuadrant = "";
	private int rowNumber;
	
	public ListingsController(Client c) {
		
		comms = c.communicator;
		listings = c.listings;
        searchView = c.searchView;
        login = c.loginView;
        emailView = c.emailView;
        subView = c.subView;
        
        listener = new MyListener();
        addListeners();
	}
	
	private void writeSocket(String s) {
		comms.socketOut.println(s);	
		comms.socketOut.flush();
	}
	
	private String readSocket() {
		try {
			return comms.socketIn.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error in readSocket");
		}
		return null;
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.updateButton) {
					listings.clear();
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "LandlordID"});
					
					writeSocket("1");
					
//					String result = database.listAll("active");
					String result = readSocket();
					
					String arr[] = result.split("é");
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
					searchView.setSubscribeButtonState(listings.registered);
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
					
					writeSocket("3");
					writeSocket(selected + "é" + contactText + "é" + emailText);
//					userDatabase.sendEmail(Integer.parseInt(selected), contactText, emailText);
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
					
					writeSocket("2");
					writeSocket(houseTypeChoice + "é" + furnishChoice + "é" + quadChoice + "é" + beds + "é" + baths + "é" + "active");
//					String result = database.searchProperty(houseTypeChoice, furnishChoice, quadChoice, beds, baths ,"active");
					String result = readSocket();
					if(result.equals("") || result.contentEquals("\n")) {
						searchView.errorMessage("No results, try changing filters");
						return;
					}
					listings.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "LandlordID"});
					String[] arr = result.split("é");
					for (String string : arr) {
						String[] row = string.split("~");
						listings.addElementTextBox(row);
					}
					listings.autoColWidth();
					listings.hideLandlordCol();
					searchView.clearText();
					searchView.setVisible(false);
				}
				
				else if(event.getSource() == searchView.subscribeButton ) {
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
					writeSocket("17");
					writeSocket(listings.username + "é" +  houseTypeChoice + "é" + furnishChoice + "é" + beds + "é" +  baths + "é" + quadChoice);
					searchView.errorMessage("You have subscribed to this search!");
					
				}
				else if(event.getSource() == listings.subscriptionsButton) {
					
					writeSocket("11");
					writeSocket(listings.username);
					String result = readSocket();
					if(result.equals("none")) {
						listings.errorMessage("You have no subscriptions");
						return;
					}
					
					subView.setCols(new String[] {"type", "furnished", "bedrooms", "bathrooms", "quadrant"});
					String[] arr = result.split("é");
					for (String string : arr) {
						String[] row = string.split("~");
						if(row[2].equals("-1")) {
							row[2] = "any";
						}
						if(row[3].equals("-1")) {
							row[3] = "any";
						}
						subView.addElementTextBox(row);
					}
					subView.autoColWidth();
					subView.setVisible(true);
				}
				else if(event.getSource() == subView.deleteBtn) {
					writeSocket("12");
					if(selectedSubBaths.equals("any")) {
						selectedSubBaths = "-1";
					}
					if(selectedSubBeds.equals("any")) {
						selectedSubBeds = "-1";
					}
					writeSocket(listings.username + "é" + selectedSubType + "é" +selectedSubFurnish + "é" +selectedSubBeds + "é" +selectedSubBaths + "é" +selectedSubQuadrant);
					subView.deleteRow(rowNumber);
//					listings.errorMessage("deleted");
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
		subView.addListener(listener);
		
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
		subView.addSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!e.getValueIsAdjusting() && subView.textBox.getSelectedRow() != -1){
//	        		 {"type", "furnished", "bedrooms", "bathrooms", "quadrant"});
	        		selectedSubType = subView.textBox.getModel().getValueAt(subView.textBox.getSelectedRow(),0).toString();
	        		selectedSubFurnish = subView.textBox.getModel().getValueAt(subView.textBox.getSelectedRow(),1).toString();
	        		selectedSubBeds = subView.textBox.getModel().getValueAt(subView.textBox.getSelectedRow(),2).toString();
	        		selectedSubBaths = subView.textBox.getModel().getValueAt(subView.textBox.getSelectedRow(),3).toString();
	        		selectedSubQuadrant = subView.textBox.getModel().getValueAt(subView.textBox.getSelectedRow(),4).toString();
	        		subView.deleteBtn.setEnabled(true);
	        		rowNumber = subView.textBox.getSelectedRow();
	        	}
	        }
	    });
		
	}
}
