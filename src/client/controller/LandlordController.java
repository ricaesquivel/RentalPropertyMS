package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.controller.ListingsController.MyListener;
import client.view.LandlordAddView;
import client.view.LandlordEmailView;
import client.view.LandlordView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;
import client.view.*;
import java.sql.*;

public class LandlordController {
	
	private LandlordAddView landlordAddView;
	private LandlordEmailView landlordEmailView;
	private LandlordView landlordView;
	private PropertyDatabaseController propertyDatabase;
	private UserDatabaseController userDatabase;
	private MyListener listener;
	private int landlordID;
	private ClientCommunicator comms; 
	private String houseTypeChoice;
	private String quadChoice;
	private String furnishChoice;
	private String selected;
	private String selected2;
	private int rowNum;
	

	public LandlordController(Client c) {
		propertyDatabase = c.propertyDatabase;
        userDatabase = c.userDatabase;
        landlordAddView = c.landlordAddView;
        landlordEmailView = c.landlordEmailView;
        landlordView = c.landlordView;
        comms = c.communicator;
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
		}catch(Exception e) {
			System.err.println("error in read socket");
		}
		return null;
	}
	private void autoSetlandlordID() {
		landlordID = userDatabase.getlandlordID(landlordView.getUsername());
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource() == landlordView.showPropertiesBtn) {
					autoSetlandlordID();
					landlordView.clear();
					landlordView.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "state"});
					
					String result = propertyDatabase.getLandlordProperties(landlordID);
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						landlordView.addElementTextBox(row);
					}
					landlordView.autoColWidth();
				}
				
				else if(e.getSource() == landlordView.viewEmailButton) {

					landlordEmailView.clear();
					landlordEmailView.setCols(new String[] {"From", "Message"});
					String result = userDatabase.getLandlordEmails(landlordID);
					if(result.equals("none")) {
						landlordView.errorMessage("No Emails yet");
						return;
					}
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						landlordEmailView.addElementTextBox(row);
					}
					
					landlordEmailView.setVisible(true);
				}
				
				else if(e.getSource() == landlordEmailView.deleteBtn){
					writeSocket("10");
					writeSocket(landlordID + "é" + selected + "é" + selected2);
					landlordEmailView.deleteRow(rowNum);
				}
				
				else if(e.getSource() == landlordEmailView.openBtn) {
					landlordEmailView.displayEmail(selected2);
				}
			
				else if(e.getSource() == landlordView.addPropertyBtn){
					landlordAddView.setVisible(true);
				}
				else if(e.getSource()== landlordAddView.submitButton){
					String bathroom = landlordAddView.getBathrooms();
					String bedroom = landlordAddView.getBedrooms();
					int beds = -1;
					int baths = -1;
					try{
						if(!bedroom.equals(""))
							beds = Integer.parseInt(bedroom);
						if(!bathroom.equals(""))
							baths = Integer.parseInt(bathroom);
						if(beds < 1 || baths < 1)
							throw new NumberFormatException();
                    }catch(NumberFormatException a){
                    	landlordAddView.errorMessage("Please enter a valid bathroom and bedroom number");
                        return;
                    }
					if(!quadChoice.equals("--choose one--") && !houseTypeChoice.equals("--choose one--") && !furnishChoice.equals("--choose one--")){
						int id = getNewPropertyID();
						id++;
						String status = "active";
						writeSocket("9");
						writeSocket(id + "é" + houseTypeChoice + "é" + bedroom + "é" + bathroom + "é" + quadChoice +"é" + furnishChoice + "é" + landlordID + "é" + status);
						landlordAddView.errorMessage("Property Added");
						landlordAddView.setVisible(false);
					}
				}				
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("error in Landlord controller");
			}
		}
	}	
	
	private int getNewPropertyID() {
		writeSocket("8");
		String result = readSocket();
		int currMax = Integer.parseInt(result);
		return currMax;
	}
	
	
	private void addListeners() {
		landlordEmailView.addListener(listener);
		landlordAddView.addSubmitListener(listener);
		landlordAddView.addHouseDropdownListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					houseTypeChoice = (String)e.getItem();
				}
			}
		});

		landlordAddView.addQuadDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		quadChoice = (String)e.getItem();
            	}
            }
        });
		landlordAddView.addfurnishDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		furnishChoice = (String)e.getItem();
            	}
            }
        });
		landlordView.addListener(listener);
		landlordView.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
		landlordEmailView.addSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!e.getValueIsAdjusting() && landlordEmailView.textBox.getSelectedRow() != -1){
	        		selected = landlordEmailView.textBox.getModel().getValueAt(landlordEmailView.textBox.getSelectedRow(),0).toString();
	        		selected2 = landlordEmailView.textBox.getModel().getValueAt(landlordEmailView.textBox.getSelectedRow(),1).toString();
	        		rowNum = landlordEmailView.textBox.getSelectedRow();
	        	}
	        }
	    });

	}
  }

