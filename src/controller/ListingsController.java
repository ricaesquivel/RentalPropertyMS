package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.LoginController.MyListener;

public class ListingsController {
	
	private ListingsView listings;
	private MyListener listener;
	private SearchCriteriaView searchView;
	
	private String quadChoice;
	private String furnishChoice;
	private String houseTypeChoice;
	
	
	public ListingsController(Client c) {

		listings = c.listings;
        searchView = c.searchView;
        
        listener = new MyListener();
        addListeners();
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				
				if(event.getSource() == listings.searchButton) {
					searchView.setVisible(true);
				} else if(event.getSource() == searchView.submitButton) {
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("Error in ListingsController");
			}
		}
		
		
	}
	private void addListeners() {
		searchView.addSubmitListener(listener);
		listings.addListener(listener);
		
		searchView.addQuadDropdownListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		quadChoice = (String)e.getItem();
            		
            	}
//                if(e.getStateChange() == ItemEvent.SELECTED){
//                    choice = (String)e.getItem();
//                    if(!choice.equals("--choose one--")){
//                        c.search.buttonState(true);
//                    } else{
//                        c.search.buttonState(false);
//                    }
//                }
            }
        });
	}
}
