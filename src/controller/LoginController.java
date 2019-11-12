package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;

public class LoginController {
	
	private MyListener listener;
	private ListingsView listings;
	private LoginView view;
	private LoginPasswordView passwordView;
	private UserDatabaseController database;
	private LoginEnum loginType;
	
	public LoginController(Client c) {
		
        loginType = LoginEnum.DEFAULT;
        
        view = c.loginView;
        listings = c.listings;
        passwordView = c.passwordView;
        database = c.userDatabase;
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
					passwordView.errorMessage("SUCCESS");
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
