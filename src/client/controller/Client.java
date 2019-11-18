package client.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import client.view.EmailView;
import client.view.LandlordAddView;
import client.view.LandlordEmailView;
import client.view.LandlordView;
import client.view.ListingsView;
import client.view.LoginPasswordView;
import client.view.LoginView;
import client.view.ManagerView;
import client.view.SearchCriteriaView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;
import client.view.*;

/**
 * has all the different views
 */
public class Client {
	
	ClientCommunicator communicator;
	
	LoginView loginView;
	LoginPasswordView passwordView;
	SearchCriteriaView searchView;
	ListingsView listings;
	ManagerView managerView;
	EmailView emailView;
	LandlordAddView landlordAddView;
	LandlordEmailView landlordEmailView;
	LandlordView landlordView;
	
	private Connection myConn;
	PropertyDatabaseController propertyDatabase;
	UserDatabaseController userDatabase;
	
	public Client() {
		try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental?user=rahman","rahman", "8002");
        } catch(SQLException a){
        	a.printStackTrace();
            System.err.println("Error connecting to database");
        }
        System.out.println("<< database Server is Running >>");
	}
	
	public static void main(String[] args) {
		
		ClientCommunicator communicator = new ClientCommunicator("localhost", 9091);
		
		Client c = new Client(); 
		
		PropertyDatabaseController propertyDatabase = new PropertyDatabaseController(c.myConn); 
		UserDatabaseController userDatabase = new UserDatabaseController(c.myConn);
		
		LoginView view = new LoginView();
		LoginPasswordView passwordView = new LoginPasswordView();
		ManagerView managerView = new ManagerView();
		EmailView emailView = new EmailView();
		LandlordAddView landlordAddView = new LandlordAddView();
		LandlordEmailView landlordEmailView = new LandlordEmailView();
		LandlordView landlordView = new LandlordView(); 
		
		ListingsView listings = new ListingsView();
		SearchCriteriaView searchView = new SearchCriteriaView();
		
		c.propertyDatabase = propertyDatabase;
		c.userDatabase = userDatabase;
		c.managerView = managerView;
		c.loginView = view;
		c.passwordView = passwordView;
		c.searchView = searchView;
		c.listings = listings;
		c.emailView = emailView;
		c.landlordAddView = landlordAddView;
		c.landlordEmailView = landlordEmailView;
		c.landlordView = landlordView;
		
		LoginController loginController = new LoginController(c);
		ListingsController listingsController = new ListingsController(c);
		ManagerController managerController = new ManagerController(c);
		LandlordController landlordController = new LandlordController(c);
		c.loginView.setVisible(true);
//		c.searchView.setVisible(true);		//TODO comment this
//		c.listings.setVisible(true);		//TODO comment this
	}
	
}
