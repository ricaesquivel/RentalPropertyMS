package client.view;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;


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
	
	private JPanel emailPanel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel userPanel = new JPanel();
	private JPanel passwordPanel = new JPanel();
	private JPanel confirmPanel = new JPanel();
	
	private JPanel mainPanel = new JPanel();
	
	public JButton submit = new JButton("Submit");
	
	
	
    public JComboBox<String> dropDownCombo = new JComboBox<String>(dropDown);
    
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
        
        emailPanel.setLayout(new FlowLayout());
        namePanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        userPanel.setLayout(new FlowLayout());
        confirmPanel.setLayout(new FlowLayout());
        
        mainPanel.setLayout(new SpringLayout());
        
	}
	
	public SignUp() {
		this.setTitle("Enter Signup");
   	 	this.setSize(300, 350);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        
        
        init();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        

        
        

        
        getContentPane().add(Email);
        getContentPane().add(name);
        getContentPane().add(userName);
        getContentPane().add(password);
        getContentPane().add(confirm);
        getContentPane().add(dropDownCombo);
        getContentPane().add(submit)
    /*
        getContentPane().add(emailPanel);
        getContentPane().add(namePanel);
        getContentPane().add(userPanel);
        getContentPane().add(passwordPanel);
        getContentPane().add(confirmPanel);
        getContentPane().add(dropDownCombo);
        getContentPane().add(submit);
       */
;        setVisible(false);
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