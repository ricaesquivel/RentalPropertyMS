package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.controller.ListingsController.MyListener;
import client.view.LoginView;
import client.view.ManagerView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;

public class ManagerController {
	
	private MyListener listener;
	private ManagerView managerView;
	private PropertyDatabaseController propDatabase;
	private UserDatabaseController userDatabase;
	private LoginView loginView;
	
	public ManagerController(Client c) {

		propDatabase = c.propertyDatabase;
		userDatabase = c.userDatabase;
        loginView = c.loginView;
        managerView = c.managerView;
        
        listener = new MyListener();
        addListeners();
	}
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				if(e.getSource() == managerView.listLandlordBtn) {
					managerView.clear();
					managerView.setCols(new String[] {"username", "password", "email", "name"});
					String result = userDatabase.listUsers("landlord");
					String arr[] = result.split("\n");
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
		
		managerView.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	managerView.setVisible(false);
                loginView.setVisible(true);
            }
        });
	}
}
