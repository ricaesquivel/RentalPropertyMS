package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	
	JLabel SelectUserLabel = new JLabel("Please Select User Type");
	JPanel buttonPanel = new JPanel();
	JPanel submitPanel = new JPanel();
	JPanel topTitle = new JPanel();
	
	public JButton managerButton = new JButton("Manager");
	public JButton renterButton = new JButton("Renter");
	public JButton landlordButton = new JButton("landlord");
	public JButton newUser = new JButton("Sign Up");
	public JButton regRenterButton = new JButton("<html><center>"+"Registered"+"<br>"+"Renter"+"</center></html>");
	public JButton submitButton = new JButton("Ok");
	
	private final Component verticalStrut = Box.createVerticalStrut(31);
	private final Component verticalStrut_1 = Box.createVerticalStrut(64);
	
	public LoginView() {
		
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        this.setBackground(Color.WHITE);
        this.setSize(700, 400);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		
		SelectUserLabel.setFont(new Font("Sans", Font.BOLD, 18));
		topTitle.setLayout(new BorderLayout());
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		topTitle.add(verticalStrut, BorderLayout.NORTH);
//		topTitle.add(verticalStrut_1, BorderLayout.SOUTH);
		topTitle.add(newUser, BorderLayout.SOUTH);
		SelectUserLabel.setHorizontalAlignment(JLabel.CENTER);
        topTitle.add("Center", SelectUserLabel);
		
        setButtonFontSize(20);
        submitButton.setEnabled(false);
        submitPanel.add(submitButton);
		buttonPanel.setLayout(new FlowLayout());
		landlordButton.setPreferredSize(new Dimension(150, 100));
		buttonPanel.add(landlordButton);
		renterButton.setPreferredSize(new Dimension(150, 100));
		buttonPanel.add(renterButton);
		regRenterButton.setPreferredSize(new Dimension(150, 100));
		buttonPanel.add(regRenterButton);
		managerButton.setPreferredSize(new Dimension(150, 100));
		buttonPanel.add(managerButton);
		
		
		getContentPane().add("Center", buttonPanel);
        getContentPane().add("North", topTitle);
        
        setVisible(false);
	}
	
	public void setButtonFontSize(int fontSize){
		managerButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
		regRenterButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        renterButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        submitButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        landlordButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
    }
	
	public void addUserTypeListener(ActionListener a){
        managerButton.addActionListener(a);
        renterButton.addActionListener(a);
        regRenterButton.addActionListener(a);
        submitButton.addActionListener(a);
        landlordButton.addActionListener(a);
        newUser.addActionListener(a);
    }

	public void errorMessage(String text){
        JOptionPane.showMessageDialog(this, text);
    }
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//LoginView window = new LoginView();
			SignUp window = new SignUp();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
