package client.view;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;


@SuppressWarnings("serial")
public class SignUp extends JFrame{
	private JLabel title = new JLabel("Registration");
    private JLabel userNameField = new JLabel("Username");
    private JLabel passwordField = new JLabel("Password");
    private JLabel confirmField = new JLabel("Confirm");
    private JLabel nameField = new JLabel("Name");
    private JLabel emailField = new JLabel("Email");
    private JLabel roleLabel = new JLabel("Account Type");
    
    private JTextField Email = new JTextField(10);
    private JTextField name = new JTextField(10);
	private JTextField userName = new JTextField(10);
    private JTextField password = new JTextField(10);
    private JTextField confirm = new JTextField(10);
    
	String[] menu = {"--choose one--", "Landlord","Registered Renter"};
	DefaultComboBoxModel<String> dropDown = new DefaultComboBoxModel<String>(menu);
	
	private JPanel titlePanel = new JPanel();
	private JPanel emailPanel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel userPanel = new JPanel();
	private JPanel passwordPanel = new JPanel();
	private JPanel confirmPanel = new JPanel();
	private JPanel rolePanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	public JButton submit = new JButton("Submit");
	private JPanel submitPanel = new JPanel();
    public JComboBox<String> dropDownCombo = new JComboBox<String>(dropDown);
    
    private void labels() {
      title.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
      title.setForeground(Color.WHITE);
      titlePanel.setBackground(Color.RED);
      titlePanel.add(title);
      northPanel.add("North", titlePanel);
      
      name.setPreferredSize(new Dimension(255, 35));
      name.setMinimumSize(new Dimension(255, 25));
      name.setMaximumSize(new Dimension(225,25));
      name.setFont(new Font("Sans", Font.PLAIN, 20));
      
      Email.setPreferredSize(new Dimension(255, 35));
      Email.setMinimumSize(new Dimension(255, 25));
      Email.setMaximumSize(new Dimension(225,25));
      Email.setFont(new Font("Sans", Font.PLAIN, 20));
      
      userName.setPreferredSize(new Dimension(255, 35));
      userName.setMinimumSize(new Dimension(255, 25));
      userName.setMaximumSize(new Dimension(225,25));
      userName.setFont(new Font("Sans", Font.PLAIN, 20));
      
      password.setPreferredSize(new Dimension(255, 35));
      password.setMinimumSize(new Dimension(255, 25));
      password.setMaximumSize(new Dimension(225,25));
      password.setFont(new Font("Sans", Font.PLAIN, 20));
      
      confirm.setPreferredSize(new Dimension(255, 35));
      confirm.setMinimumSize(new Dimension(255, 25));
      confirm.setMaximumSize(new Dimension(225,25));
      confirm.setFont(new Font("Sans", Font.PLAIN, 20));
      
      dropDownCombo.setPreferredSize(new Dimension(163,25));
      dropDownCombo.setMinimumSize(new Dimension(255,25));
      dropDownCombo.setMaximumSize(new Dimension(255,25));
      dropDownCombo.setFont(new Font("Sans", Font.PLAIN, 15));
     
      submit.setPreferredSize(new Dimension(100,25));
      submit.setMinimumSize(new Dimension(150,25));
      submit.setMaximumSize(new Dimension(150,25));
      submit.setFont(new Font("Sans", Font.PLAIN, 15));
//      private JLabel houseTypeLabel = new JLabel("Sign up");
//      private JLabel userNameField = new JLabel("Username");
//      private JLabel passwordField = new JLabel("Password");
//      private JLabel confirmField = new JLabel("Confirm");
//      private JLabel nameField = new JLabel("Name");
//      private JLabel emailField = new JLabel("Email");
//      
      emailPanel.setLayout(new GridLayout(0, 2, 0, 0));
      emailPanel.add(emailField);
      emailPanel.add(Email);

      
      namePanel.setLayout(new GridLayout(0, 2, 0, 0));
      namePanel.add(nameField);
      namePanel.add(name);

      
      userPanel.setLayout(new GridLayout(0, 2, 0, 0));
      userPanel.add(userNameField);
      userPanel.add(userName);

      
      passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
      passwordPanel.add(passwordField);
      passwordPanel.add(password);

      
      confirmPanel.setLayout(new GridLayout(0, 2, 0, 0));
      confirmPanel.add(confirmField);
      confirmPanel.add(confirm);
      
      rolePanel.setLayout(new GridLayout(0, 2, 0, 0));
      rolePanel.add(roleLabel);
      rolePanel.add(dropDownCombo);
      
      submitPanel.add(submit);
      submitPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
      
    }
    public SignUp() {
    	this.setTitle("Sign Up");
   	 	this.setSize(350, 380);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        
        labels();
        
        mainPanel.add(northPanel);
        mainPanel.add(namePanel);
        mainPanel.add(emailPanel);
        mainPanel.add(userPanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(confirmPanel);
        mainPanel.add(rolePanel);
        mainPanel.add(submitPanel);
        getContentPane().add("Center", mainPanel);      
    }
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
	public void clear() {
		Email.setText("");
	    name.setText("");
		userName.setText("");
	    password.setText("");
	    confirm.setText("");
	}
}