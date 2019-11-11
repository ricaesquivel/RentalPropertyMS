package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.LoginController.MyListener;

public class ListingsController {
	
	private ListingsView listings;
	private MyListener listener;
	private SearchCriteriaView searchView;
	
	public ListingsController(Client c) {

		listings = c.listings;
        searchView = c.searchView;
        
        listener = new MyListener();
        addListeners();
	}

	private void addListeners() {
		searchView.addSubmitListener(listener);
		listings.addListener(listener);
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.searchButton) {
					
				} else if(true) {
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("Error in ListingsController");
			}
		}
		
		
	}
	
}
