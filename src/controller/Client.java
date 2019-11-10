package controller;

import view.*;

/**
 * has all the different views
 */
public class Client {
	
	LoginView loginView;
	LoginPasswordView passwordView;
	UserDatabaseController database;
	
	public Client() {
		
	}
	
	public static void main(String[] args) {
		
		LoginView view = new LoginView();
		LoginPasswordView passwordView = new LoginPasswordView();
		
		Client c = new Client();
		c.loginView = view;
		c.passwordView = passwordView;
		
		LoginController loginController = new LoginController(c);
		c.loginView.setVisible(true);
	}
	
}
