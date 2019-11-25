package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.controller.ListingsController.MyListener;
import client.view.ChangeStatusPopUp;
import client.view.LoginView;
import client.view.ManagerView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;

public class ManagerController {
	
	private ClientCommunicator comms;
	private MyListener listener;
	private ManagerView managerView;
	private PropertyDatabaseController propDatabase;
	private UserDatabaseController userDatabase;
	private LoginView loginView;
	private ChangeStatusPopUp changeView;
	private String stateChoice;
	private String selectedID=" ";
	
	public ManagerController(Client c) {
		changeView = c.changeView2;
		comms = c.communicator;
		propDatabase = c.propertyDatabase;
		userDatabase = c.userDatabase;
        loginView = c.loginView;
        managerView = c.managerView;
        
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
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource()==managerView.requestSummary) {
					System.out.println("requesting summary");
					writeSocket("20");
					String s = readSocket();
					System.out.println(s);
				}
				if(e.getSource()==changeView.submit) {
					System.out.println(stateChoice);
					System.out.println(selectedID);	
					if(!stateChoice.equals(" ") && !stateChoice.equals("--choose one--")) {
						String s = stateChoice+"~"+selectedID;
	
						writeSocket("13");
						writeSocket(s);
						changeView.setVisible(false);
						changeView.errorMessage("State has been changed");
						managerView.changeStatusBtn.setEnabled(false);
						managerView.listPropertiesBtn.doClick();
					}
					
				}
				if(e.getSource()==managerView.changeStatusBtn) {
					changeView.setVisible(true);
				}
				
				if(e.getSource() == managerView.listLandlordBtn) {
					managerView.clear();
					managerView.setCols(new String[] {"username", "password", "email", "name"});
					
					writeSocket("5");
					String result = readSocket();
					
//					String result = userDatabase.listUsers("landlord");
					
					String arr[] = result.split("é");
					for (String string : arr) {
						String[] row = string.split("~");
						managerView.addElementTextBox(row);
					}
					managerView.autoColWidth();
				}
				if(e.getSource() == managerView.listPropertiesBtn) {
					managerView.clear();
					managerView.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished","Listing State" ,"Landlord name", "email"});
					String result = propDatabase.listPropertiesAndLandlords();
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						managerView.addElementTextBox(row);
					}
					managerView.autoColWidth();
				}
				if(e.getSource() == managerView.listRentersBtn) {
					managerView.clear();
					managerView.setCols(new String[] {"username", "password", "email", "name"});
					String result = userDatabase.listUsers("regrenter");
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						managerView.addElementTextBox(row);
					}
					managerView.autoColWidth();
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
				System.err.println("error in manager controller");
			}
		}
	}
	
	private void addListeners() {
		managerView.addListener(listener);
		changeView.addListener(listener);

		changeView.addDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		stateChoice = (String)e.getItem();
            	}
            }
		});
		

		
		managerView.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	managerView.setVisible(false);
                loginView.setVisible(true);
            }
        });
		managerView.addSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()&&managerView.textBox.getSelectedRow() !=-1) {
					selectedID = managerView.textBox.getModel().getValueAt(managerView.textBox.getSelectedRow(),0).toString();
					managerView.changeStatusBtn.setEnabled(true);
				}
			}
		});
		
	}
	
}
