package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import client.view.SignUp;
import server.UserDatabaseController;

public class SignUpController {
	private SignUp view;
	private MyListener listener;
	private String choice;
	private UserDatabaseController base;
	
	public SignUpController(Client c) {
		view = c.signUpView;
		base = c.userDatabase;
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
	
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==view.submit) {
				String userName = view.getName() ;
				String email = view.getEmail();
				String password = view.getPassword();
				String name = view.getName();
				String confirm = view.getConfirm(); 
				if(!password.equals(confirm)) {
					view.errorMessage("Password does not match");
					return;
				}
				
				if (password.equals(confirm) && !base.userExists(userName) ) {
					base.addUser(userName,password,email,name,"regrenter");
				}
				else {
					view.errorMessage("Username has been taken, please try another one");
					return;
				}
			}
			
			
		}
		
	}
	
	
	

}