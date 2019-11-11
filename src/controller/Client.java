package controller;

import view.*;

/**
 * has all the different views
 */
public class Client {
	
	LoginView loginView;
	LoginPasswordView passwordView;
	UserDatabaseController database;
	SearchCriteriaView searchView;
	ListingsView listings;
	
	public Client() {	}
	
	public static void main(String[] args) {
		
		LoginView view = new LoginView();
		LoginPasswordView passwordView = new LoginPasswordView();
		
		ListingsView listings = new ListingsView();
		SearchCriteriaView searchView = new SearchCriteriaView();
		
		Client c = new Client();
		c.loginView = view;
		c.passwordView = passwordView;
		c.searchView = searchView;
		c.listings = listings;
		
		LoginController loginController = new LoginController(c);
		ListingsController listingsController = new ListingsController(c);
		c.loginView.setVisible(true);
	}
	
}
