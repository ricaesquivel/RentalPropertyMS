package client.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.PropertyDatabaseController;
import server.UserDatabaseController;
import client.view.*;

/**
 * has all the different views
 */
public class Client {
	
	ClientCommunicator communicator;
	ChangeStatusPopUp changeView;
	ChangeStatusPopUp changeView2;

	LoginView loginView;
	LoginPasswordView passwordView;
	SearchCriteriaView searchView;
	ListingsView listings;
	ManagerView managerView;
	EmailView emailView;
	LandlordAddView landlordAddView;
	LandlordEmailView landlordEmailView;
	LandlordView landlordView;
	SignUp signUpView;
	SubscriptionsView subView;
	
	private Connection myConn;
	PropertyDatabaseController propertyDatabase;
	UserDatabaseController userDatabase;
	
	public Client() {
		try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental?user=root","root", "8002");
        } catch(SQLException a){
        	a.printStackTrace();
            System.err.println("Error connecting to database");
        }
        System.out.println("<< database Server is Running >>");
	}
	
	public static void main(String[] args) {
		
		ClientCommunicator communicator = new ClientCommunicator("localhost", 9091);
		
		Client c = new Client(); 
		
//		PropertyDatabaseController propertyDatabase = new PropertyDatabaseController(c.myConn); 
		PropertyDatabaseController propertyDatabase = PropertyDatabaseController.getOnlyInstance(c.myConn);
//		UserDatabaseController userDatabase = new UserDatabaseController(c.myConn);
		UserDatabaseController userDatabase = UserDatabaseController.getOnlyInstance(c.myConn);
		
		LoginView view = new LoginView();
		LoginPasswordView passwordView = new LoginPasswordView();
		ManagerView managerView = new ManagerView();
		EmailView emailView = new EmailView();
		LandlordAddView landlordAddView = new LandlordAddView();
		LandlordEmailView landlordEmailView = new LandlordEmailView();
		LandlordView landlordView = new LandlordView(); 
		SignUp signUpView = new SignUp();
		SubscriptionsView subView = new SubscriptionsView();
		ChangeStatusPopUp changeView = new ChangeStatusPopUp();
		ChangeStatusPopUp changeView2 = new ChangeStatusPopUp();
		
		ListingsView listings = new ListingsView();
		SearchCriteriaView searchView = new SearchCriteriaView();

		c.changeView = changeView;		
		c.changeView2 = changeView2;
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
		c.signUpView = signUpView;
		c.subView = subView;
		c.communicator = communicator;
		
		LoginController loginController = new LoginController(c);
		ListingsController listingsController = new ListingsController(c);
		ManagerController managerController = new ManagerController(c);
		LandlordController landlordController = new LandlordController(c);
		SignUpController signUpController = new SignUpController(c);
		
		c.loginView.setVisible(true);
		//c.signUpView.setVisible(true);
	    //c.landlordView.setVisible(true);
//		c.searchView.setVisible(true);		//TODO comment this
//		c.listings.setVisible(true);		//TODO comment this
	}
}
