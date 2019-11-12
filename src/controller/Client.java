package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.*;

/**
 * has all the different views
 */
public class Client {
	
	LoginView loginView;
	LoginPasswordView passwordView;
	SearchCriteriaView searchView;
	ListingsView listings;
	
	private Connection myConn;
	PropertyDatabaseController propertyDatabase;
	UserDatabaseController userDatabase;
	
	public Client() {
		try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalpropertyms?user=root","root", "799228002");
        } catch(SQLException a){
        	a.printStackTrace();
            System.err.println("Error connecting to database");
        }
        System.out.println("<< database Server is Running >>");
	}
	
	public static void main(String[] args) {
		
		Client c = new Client(); 
		
		PropertyDatabaseController propertyDatabase = new PropertyDatabaseController(c.myConn); 
		
		LoginView view = new LoginView();
		LoginPasswordView passwordView = new LoginPasswordView();
		
		ListingsView listings = new ListingsView();
		SearchCriteriaView searchView = new SearchCriteriaView();
		
		c.propertyDatabase = propertyDatabase;
		c.loginView = view;
		c.passwordView = passwordView;
		c.searchView = searchView;
		c.listings = listings;
		
		LoginController loginController = new LoginController(c);
		ListingsController listingsController = new ListingsController(c);
//		c.loginView.setVisible(true);
//		c.searchView.setVisible(true);		//TODO comment this
		c.listings.setVisible(true);		//TODO comment this
	}
	
}
