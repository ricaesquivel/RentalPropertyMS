package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.view.LoginView;
import client.view.SignUp;
import server.UserDatabaseController;

public class SignUpController {
	
	private ClientCommunicator comms;
	private SignUp view;
	private MyListener listener;
	private String choice = "";
	private LoginView loginView;
	
	public SignUpController(Client c) {
		comms = c.communicator;
		view = c.signUpView;
		loginView = c.loginView;
		listener = new MyListener();
		addListners();
	}
	
	private void addListners() {
		view.addSubmitListener(listener);
		view.addDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		choice = (String)e.getItem();
            	}
            }
	});
}
	private void writeSocket(String s) {
		comms.socketOut.println(s);
		comms.socketOut.flush();
	}
	
	private String readSocket() {
		try {
			return comms.socketIn.readLine();
		} catch (IOException e) {
			System.err.println("error in readSocket");
		}
		return null;
	}
	
	private int getNewLandLordID(){
		System.out.println("in signup");
		int id = -3;
		writeSocket("7");
			try {
				String s = readSocket();
				id = Integer.parseInt(s);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		id-=-1;
		return id;
	}
	
	
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("in addUsers");
			
			if(e.getSource()==view.submit) {
				System.out.println("in addUsers");
				String userName = view.getUserName() ;
				String email = view.getEmail();
				String password = view.getPassword();
				String name = view.getName();
				String confirm = view.getConfirm(); 
				if(userName.equals("")||email.equals("")||password.equals("")||name.equals("")||confirm.equals("")) {
					view.errorMessage("all fields must be filled in");
					return;
				}
				if(choice.equals("")||choice.equals("--choose one--")) {
					view.errorMessage("select a user type");
					return;
				}
				if(userName.contains(" ")) {
					view.errorMessage("username field does not accept spaces");
					return;
				}
				if(!password.equals(confirm)) {
					view.errorMessage("Password does not match");
					return;
				}
				
				if (password.equals(confirm)) {
					writeSocket("21");
					writeSocket(userName);
					
					String b = readSocket();
					
					boolean exists = Boolean.getBoolean(b);
					System.err.println(exists + "yuhhh ");
					
					System.out.print(b + "  >><<<   yuhhh");
					
					if(b.equals("false")) {
					
					String s="";
					if(choice.equals("Registered Renter")) {
						s = userName+"~"+password+"~"+email+"~"+name+"~"+"regrenter";
					}
					else if(choice.equals("Landlord")) {
						s = userName+"~"+password+"~"+email+"~"+name+"~"+"Landlord"+"~"+getNewLandLordID();
					}
					
					writeSocket("6");
					writeSocket(s);					
					view.errorMessage("Sign up complete!");
					view.setVisible(false);
					
					view.clear();
					
					loginView.setVisible(true);
					}else{
						view.errorMessage("Username has been taken, please try another one");
					}
				} 
			}	
		}	
	}
}