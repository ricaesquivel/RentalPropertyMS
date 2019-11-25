package client.view;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	
	JLabel SelectUserLabel = new JLabel("Please Select User Type");
	JPanel buttonPanel = new JPanel();
	JPanel submitPanel = new JPanel();
	JPanel topTitle = new JPanel();
	JPanel userPanel = new JPanel();
	JPanel imgPanel = new JPanel();
	public JButton managerButton = new JButton("Manager");
	public JButton renterButton = new JButton("Renter");
	public JButton landlordButton = new JButton("Landlord");
	public JButton newUser = new JButton("Sign Up");
	public JButton regRenterButton = new JButton("<html><center>"+"Registered"+"<br>"+"Renter"+"</center></html>");
	public JButton submitButton = new JButton("Ok");
	
	private final Component verticalStrut = Box.createVerticalStrut(31);
	private final Component verticalStrut_1 = Box.createVerticalStrut(64);

	public LoginView() {
		ImageIcon image = new ImageIcon("img\\image.jpg");
		Image i = image.getImage();
		Image i1 = i.getScaledInstance(360, 300, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(i1);
	    JLabel imagelabel = new JLabel(image);
	    imgPanel.add(imagelabel);
	    
	    
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        this.setBackground(Color.WHITE);
        this.setSize(800, 560);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout());
		
		SelectUserLabel.setFont(new Font("Sans", Font.BOLD, 18));
		topTitle.setLayout(new BorderLayout());
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		
		topTitle.add(imagelabel, BorderLayout.NORTH);
		topTitle.setBackground(Color.WHITE);
//		topTitle.add(verticalStrut_1, BorderLayout.SOUTH);
		
		SelectUserLabel.setHorizontalAlignment(JLabel.CENTER);
		SelectUserLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        topTitle.add("Center", SelectUserLabel);
       
        userPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        userPanel.add(newUser);
        setButtonFontSize(20);
        Color c = new Color(236, 236, 236);
        submitButton.setEnabled(false);
        submitPanel.add(submitButton);
		buttonPanel.setLayout(new FlowLayout());
		landlordButton.setPreferredSize(new Dimension(150, 60));
		landlordButton.setBackground(c);
		buttonPanel.add(landlordButton);
		renterButton.setPreferredSize(new Dimension(150, 60));
		renterButton.setBackground(c);
		buttonPanel.add(renterButton);
		regRenterButton.setPreferredSize(new Dimension(150, 60));
		regRenterButton.setBackground(c);
		buttonPanel.add(regRenterButton);
		managerButton.setPreferredSize(new Dimension(150, 60));
		managerButton.setBackground(c);
		buttonPanel.add(managerButton);
		buttonPanel.setBackground(Color.WHITE);
		userPanel.setBackground(Color.WHITE);
		newUser.setBackground(c);
		getContentPane().add("North", topTitle);
		getContentPane().add("Center", buttonPanel);
        getContentPane().add("South", userPanel);
       
        setVisible(false);
	}
	
	public void setButtonFontSize(int fontSize){
		managerButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
		regRenterButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        renterButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        submitButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        landlordButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        newUser.setFont(new Font("Sans", Font.PLAIN, fontSize));
       
        
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
			LoginView window = new LoginView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
