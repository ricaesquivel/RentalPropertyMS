package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import client.view.LandlordView;
import client.view.ListingsView;
import client.view.LoginPasswordView;
import client.view.LoginView;
import client.view.ManagerView;
import server.UserDatabaseController;
import client.view.*;

public class LoginController {
	
	private PrintWriter socketOut;
    private BufferedReader socketIn;
	private MyListener listener;
	private ListingsView listings;
	private LoginView view;
	private LoginPasswordView passwordView;
	private UserDatabaseController database;
	private ManagerView managerView; 
	private LandlordView landlordView; 
	private LoginEnum loginType;
	
	public LoginController(Client c) {
		
        loginType = LoginEnum.DEFAULT;
        
        socketIn = c.communicator.getInSocket();
        socketOut = c.communicator.getOutSocket();
        view = c.loginView;
        listings = c.listings;
        passwordView = c.passwordView;
        database = c.userDatabase;
        managerView = c.managerView;
        landlordView = c.landlordView;
        listener = new MyListener();
        addListeners();
	}
	
	private void addListeners() {
		view.addUserTypeListener(listener);
		passwordView.addLoginListener(listener);
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == view.managerButton) {
					loginType = LoginEnum.MANAGER;
					view.setVisible(false);
					passwordView.setVisible(true);
					
				}
				else if(event.getSource() == view.landlordButton) {
					loginType = LoginEnum.LANDLORD;
					view.setVisible(false);
					passwordView.setVisible(true);
				}
				else if(event.getSource() == view.renterButton) {
					listings.setVisible(true);
					listings.updateButton.doClick();
					view.setVisible(false);
				}
				else if(event.getSource() == view.regRenterButton) {
					loginType = LoginEnum.REG_RENTER;
					view.setVisible(false);
					passwordView.setVisible(true);
				}
				else if(event.getSource() == passwordView.loginButton) {
					String username = passwordView.getUserName();
                    String pass = passwordView.getPassword();
                    
					if(username.equals("") || pass.equals("")){
                        System.out.println("error");
                        passwordView.errorMessage("Please Enter Username and Password");
                        return;
                    }
					if(!database.validateUser(username, pass, loginType)) {
						passwordView.errorMessage("username or password incorrect, please try again");
						return;
					}
					if(loginType == LoginEnum.MANAGER) {
						managerView.setVisible(true);
						managerView.listPropertiesBtn.doClick();
						passwordView.clearText();
						passwordView.setVisible(false);
					}
					if(loginType == LoginEnum.LANDLORD) {
						landlordView.setVisible(true);
						landlordView.setUsername(username);
						landlordView.showPropertiesBtn.doClick();
						passwordView.clearText();
						passwordView.setVisible(false);
					}
					//TODO else: give the appropriate GUI for who ever logged in
				}
				else if(event.getSource() == passwordView.backButton) {
					passwordView.setVisible(false);
					passwordView.clearText();
					view.setVisible(true);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error in LoginController");
			}
		}
	}
}
