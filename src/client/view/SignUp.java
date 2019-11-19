package client.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class SignUp extends JFrame{
    private JLabel houseTypeLabel = new JLabel("Sign up");
    private JLabel userNameField = new JLabel("Username");
    private JLabel passwordField = new JLabel("Password");
    private JLabel confirmField = new JLabel("Confirm");
    private JLabel nameField = new JLabel("Name");
    private JLabel emailField = new JLabel("Email");
    
    private JTextField Email = new JTextField(10);
    private JTextField name = new JTextField(10);
	private JTextField userName = new JTextField(10);
    private JTextField password = new JTextField(10);
    private JTextField confirm = new JTextField(10);
    
	String[] menu = {"--choose one--", "Landlord","Registered Renter"};
	DefaultComboBoxModel<String> dropDown = new DefaultComboBoxModel<String>(menu);
	
	private JPanel text = new JPanel();
	private JPanel panel = new JPanel();
	
	public JButton submit = new JButton("Submit");
	
	
	
    public JComboBox dropDownCombo = new JComboBox(dropDown);
    
    public String getUserName() {
    	return userName.getText();
    }
    
    public String getPassword() {
    	return password.getText();
    }
    
    public String getConfirm() {
    	return confirm.getText();
    }
    
    public String getName() {
    	return name.getText();
    }
    
    public String getEmail() {
    	return Email.getText();
    }
    
	public void addDropdownListener(ItemListener a) {
		dropDownCombo.addItemListener(a);
	}
	
    

    

	
	
	private void init() {
        name.setPreferredSize(new Dimension(255, 25));
        name.setMinimumSize(new Dimension(255, 25));
        name.setMaximumSize(new Dimension(225,25));
        
        Email.setPreferredSize(new Dimension(255, 25));
        Email.setMinimumSize(new Dimension(255, 25));
        Email.setMaximumSize(new Dimension(225,25));
		
        userName.setPreferredSize(new Dimension(255, 25));
        userName.setMinimumSize(new Dimension(255, 25));
        userName.setMaximumSize(new Dimension(225,25));
        
        password.setPreferredSize(new Dimension(255, 25));
        password.setMinimumSize(new Dimension(255, 25));
        password.setMaximumSize(new Dimension(225,25));
        
        confirm.setPreferredSize(new Dimension(255, 25));
        confirm.setMinimumSize(new Dimension(255, 25));
        confirm.setMaximumSize(new Dimension(225,25));
        
        dropDownCombo.setPreferredSize(new Dimension(255,25));
        dropDownCombo.setMinimumSize(new Dimension(255,25));
        dropDownCombo.setMaximumSize(new Dimension(255,25));
        
        submit.setPreferredSize(new Dimension(150,25));
        submit.setMinimumSize(new Dimension(150,25));
        submit.setMaximumSize(new Dimension(150,25));
	}
	
	public SignUp() {
		this.setTitle("Enter Signup");
   	 	this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        
       text.setLayout(new BoxLayout(text, BoxLayout.X_AXIS));
       setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
       
        
        init();
        
        add("West",houseTypeLabel);
        add("West",emailField);
        add("West",Email);
        add("West", nameField);
        add("West",name);
        add("West",userNameField);
        add("West",userName);
        add("West",passwordField);
        add("West",password);
        add("West",confirmField);
        add("West",confirm);
        add("West",dropDownCombo);
        add("West",submit);
        
        //getContentPane().add("West",panel);
       // getContentPane().add("West",text);
        setVisible(false);
	}
	
	
	public void addSubmitListener(ActionListener a) {
		submit.addActionListener(a);
	}
	
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	
	public static void main(String[] args) {
		try {
			SignUp window = new SignUp();
			window.setVisible(true);
		}catch(Exception a){
			a.printStackTrace();			
		}
	}
}