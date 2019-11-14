package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ListingsController.MyListener;
import view.*;

public class LandlordController {
	
	private LandlordAddView landlordAddView;
	private LandlordEmailView landlordEmailView;
	private LandlordView landlordView;
	private PropertyDatabaseController propertyDatabase;
	private UserDatabaseController userDatabase;
	private MyListener listener;
	private int landlordID;
	
	public LandlordController(Client c) {
		propertyDatabase = c.propertyDatabase;
        userDatabase = c.userDatabase;
        landlordAddView = c.landlordAddView;
        landlordEmailView = c.landlordEmailView;
        landlordView = c.landlordView;
        
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
