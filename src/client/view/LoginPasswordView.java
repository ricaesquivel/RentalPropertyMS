package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LoginPasswordView extends JFrame {

	private JTextField userName = new JTextField(10);
    private JTextField password = new JTextField(10);
    private JLabel nameLabel = new JLabel("Username");
    private JLabel passwordLabel = new JLabel("Password");
    
    private JPanel loginPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel backButtonPanel = new JPanel();
    private JPanel userLabelPanel = new JPanel();
    private JPanel passLabelPanel = new JPanel();
    public JButton loginButton = new JButton("Login");
    public JButton backButton = new JButton("<< Back");
    private final Component verticalStrut = Box.createVerticalStrut(79);
    private final Component verticalStrut_1 = Box.createVerticalStrut(71);
    private final Component horizontalStrut = Box.createHorizontalStrut(101);
    private final Component horizontalStrut_1 = Box.createHorizontalStrut(101);
    
    public LoginPasswordView() {
    	 this.setTitle("Enter Details");
    	 this.setSize(700, 400);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(false);
         this.setBackground(Color.WHITE);
         this.setLocationRelativeTo(null);
         getContentPane().setLayout(new BorderLayout());
         
         nameLabel.setFont(new Font("Sans", Font.PLAIN, 20));
         passwordLabel.setFont(new Font("Sans", Font.PLAIN, 20));
         userName.setFont(new Font("Sans", Font.PLAIN, 20));
         password.setFont(new Font("Sans", Font.PLAIN, 20));
         loginButton.setFont(new Font("Sans", Font.BOLD, 20));
         backButton.setFont(new Font("Sans", Font.BOLD, 20));
         
         nameLabel.setBackground(Color.WHITE);
         loginPanel.setBackground(Color.WHITE);        
         
         backButton.setContentAreaFilled(false);			//all this is for button style
         backButton.setBorderPainted(false);
         backButton.setForeground(Color.BLACK);
         backButton.setBackground(Color.WHITE);
         Border line = new LineBorder(Color.WHITE);
         Border margin = new EmptyBorder(5, 15, 5, 15);
         Border compound = new CompoundBorder(line, margin);
         backButton.setBorder(compound);
         loginButton.setBorder(new EmptyBorder(10, 10, 10, 10));
         backButton.setBorder(new EmptyBorder(10, 10, 10, 10));
         buttonPanel.setLayout(new FlowLayout());
         backButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
         buttonPanel.setBackground(Color.WHITE);
         backButtonPanel.add(backButton);
         buttonPanel.add(loginButton);
         
         userLabelPanel.setLayout(new FlowLayout());
         passLabelPanel.setLayout(new FlowLayout());
         userLabelPanel.setBackground(Color.WHITE);
         passLabelPanel.setBackground(Color.WHITE);
         userLabelPanel.add(nameLabel);
         passLabelPanel.add(passwordLabel);

         loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
         
         loginPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
         loginPanel.add(userLabelPanel);
         loginPanel.add(userName);
         loginPanel.add(passLabelPanel);
         loginPanel.add(password);
         loginPanel.add(buttonPanel);

         getContentPane().add("Center", loginPanel);
         getContentPane().add(backButtonPanel, BorderLayout.NORTH);
         getContentPane().add(verticalStrut_1, BorderLayout.SOUTH);
         getContentPane().add(horizontalStrut, BorderLayout.WEST);
         getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
         
         setVisible(false);
    }
    
    public void addLoginListener(ActionListener a){
    	loginButton.addActionListener(a);
        backButton.addActionListener(a);
    }
    public String getUserName() {
        return userName.getText();
    }
    public String getPassword() {
    	return password.getText();
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
			LoginPasswordView window = new LoginPasswordView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearText() {
		userName.setText(null);
		password.setText(null);
	}

}
