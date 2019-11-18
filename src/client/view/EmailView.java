package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class EmailView extends JFrame{

//	JLabel title = new JLabel("Rental Emailing Service" + "\u2122");
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();

    JPanel southPanel = new JPanel();

    private JTextArea emailArea = new JTextArea(300, 20);
    private JTextField contactArea = new JTextField(300);
	
    JLabel landlordLabel = new JLabel("Contact Landlord");
    JPanel landlordPanel = new JPanel();
    JLabel contactLabel = new JLabel("Your Phone Number or Email");
    JPanel contactPanel = new JPanel();

    public JButton sendBtn = new JButton("Send Email");
    public JButton cancelBtn = new JButton("Cancel");
	
    public void styler() {
    	
    	this.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        bottomButtonPanel.setBackground(Color.WHITE);
        landlordPanel.setBackground(Color.WHITE);
        contactPanel.setBackground(Color.WHITE);
        southPanel.setBackground(Color.WHITE);
        
        emailArea.setFont(new Font("Arial", Font.PLAIN, 20));
        contactArea.setFont(new Font("Arial", Font.PLAIN, 20));
        emailArea.setBorder(new LineBorder(Color.BLACK));
        
        emailArea.setPreferredSize(new Dimension(300, 5));
        setButtonFontSize(20);
        
        landlordLabel.setFont(new Font("Arial", Font.BOLD, 26));
        landlordPanel.setLayout(new FlowLayout());
        landlordPanel.add(landlordLabel);
        landlordPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        contactPanel.setLayout(new FlowLayout());
        contactPanel.add(contactLabel);
        contactPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(cancelBtn);
        southPanel.add(sendBtn);
        southPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
    }
    
	private void setButtonFontSize(int fontSize) {
        sendBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
        cancelBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
	}

	public EmailView() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
        this.setTitle("Send Email");
        this.setBackground(Color.WHITE);
        this.setSize(500, 600);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
        styler();
        buttonState(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(emailArea);
        
        contactArea.setPreferredSize(new Dimension(3306, 35));
        contactArea.setMinimumSize(new Dimension(6, 35));
        contactArea.setMaximumSize(new Dimension(2147483647, 35));
        mainPanel.add(contactPanel);
        mainPanel.add(contactArea);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        getContentPane().add("North", landlordPanel);
        getContentPane().add("Center", mainPanel);
        getContentPane().add("South", southPanel);
        
        buttonState(true);
		setVisible(false);
	}
	public void addCloseListener(WindowAdapter a){
        this.addWindowListener(a);
    }
	public void buttonState(boolean state) {
		sendBtn.setEnabled(state);
	}
	public String getContact() {
		return contactArea.getText();
	}
	public String getEmail() {
		return emailArea.getText();
	}
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		sendBtn.addActionListener(a);
		cancelBtn.addActionListener(a);
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		    EmailView window = new EmailView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		contactArea.setText(null);
		emailArea.setText(null);
	}
}
