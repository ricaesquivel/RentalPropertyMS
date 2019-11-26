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
	SummaryView summary;
	

	public Client() {
        System.out.println("<< Client is connected >>");
	}
	
	public static void main(String[] args) {
		
		ClientCommunicator communicator = new ClientCommunicator("localhost", 9091);
		
		Client c = new Client(); 
		LoginView view = new LoginView();
		SummaryView summary = new SummaryView();
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
		c.summary = summary;
		
		
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
