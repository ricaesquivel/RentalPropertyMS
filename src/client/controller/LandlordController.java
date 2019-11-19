package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.controller.ListingsController.MyListener;
import client.view.LandlordAddView;
import client.view.LandlordEmailView;
import client.view.LandlordView;
import server.PropertyDatabaseController;
import server.UserDatabaseController;
import client.view.*;
import java.sql.*;
public class LandlordController {
	
	private LandlordAddView landlordAddView;
	private LandlordEmailView landlordEmailView;
	private LandlordView landlordView;
	private PropertyDatabaseController propertyDatabase;
	private UserDatabaseController userDatabase;
	private MyListener listener;
	private int landlordID;
	private Connection myConn;
	public LandlordController(Client c, Connection cMyConn) {
		propertyDatabase = c.propertyDatabase;
        userDatabase = c.userDatabase;
        landlordAddView = c.landlordAddView;
        landlordEmailView = c.landlordEmailView;
        landlordView = c.landlordView;
        myConn = cMyConn;
        listener = new MyListener();
        addListeners();
	}
	
	private void autoSetlandlordID() {
		landlordID = userDatabase.getlandlordID(landlordView.getUsername());
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				if(e.getSource() == landlordView.showPropertiesBtn) {
					autoSetlandlordID();
					landlordView.clear();
					landlordView.setCols(new String[] {"id", "type", "bedrooms", "bathrooms", "quadrant", "furnished", "state"});
					
					String result = propertyDatabase.getLandlordProperties(landlordID);
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						landlordView.addElementTextBox(row);
					}
					landlordView.autoColWidth();
//					landlordView.buttonState(false);
					
				}
				if(e.getSource() == landlordView.viewEmailButton) {
					
					landlordEmailView.clear();
					landlordEmailView.setCols(new String[] {"From", "Message"});
					String result = userDatabase.getLandlordEmails(landlordID);
					if(result.equals("none")) {
						landlordView.errorMessage("No Emails yet");
						return;
					}
					String arr[] = result.split("\n");
					for (String string : arr) {
						String[] row = string.split("~");
						landlordEmailView.addElementTextBox(row);
					}
					
					landlordEmailView.setVisible(true);
				}
				if(e.getSource() == landlordView.addPropertyBtn){
					landlordAddView.setVisible(true);
					MyListener listenerAdd = new MyListener();
					String query;
					PreparedStatement preStmt;
					landlordAddView.addListener(listenerAdd);
					if(e.getSource() == landlordAddView.submitButton){
						System.out.println("pressed");
						landlordAddView.setVisible(false);
						  try {
					            query =  "INSERT INTO `rental` (`id`,`type`, `bedrooms`, `bathrooms`, `quadrants`, `furnished`, `landlordID`, `state`)"
					            + "VALUES(?,?,?,?,?,?,?)";
					            preStmt = myConn.prepareStatement(query);

					            preStmt.setInt(1, '1');
					            preStmt.setString(2, "apartment");
					            preStmt.setInt(3, '5');
					            preStmt.setInt(4, '6');
					            preStmt.setString(5, "NW");
					            preStmt.setInt(6, '0');
					            preStmt.setString(4, "1001");
					            preStmt.execute();

					        } catch (SQLException a) {
					            System.err.println("SQL Error in Add Property to database");
					            a.printStackTrace();
					        }   
					}
					 
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("error in Landlord controller");
			}
		}
		
	}
	private void addListeners() {
		landlordView.addListener(listener);
		landlordView.addCloseListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
	}
}
