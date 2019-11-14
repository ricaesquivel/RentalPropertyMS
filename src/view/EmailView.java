package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class EmailView extends JFrame{

	JLabel title = new JLabel("Rental Emailing Service" + "\u2122");
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();

    JPanel southPanel = new JPanel();

    private JTextField emailArea = new JTextField(300);
    private JTextField subjectArea = new JTextField(300);
	
	public DefaultListModel<String> model = new DefaultListModel<>();
    public JList<String> textBox = new JList<>(model);


    public JButton sendBtn = new JButton("Send Email");
    public JButton cancelBtn = new JButton("Cancel");
	
    public void styler() {
    	this.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        bottomButtonPanel.setBackground(Color.WHITE);
        
        emailArea.setPreferredSize(new Dimension(300, 5));
        setButtonFontSize(20);

        southPanel.setLayout(new FlowLayout());
        southPanel.add(cancelBtn);
        southPanel.add(sendBtn);
        southPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
    }
    
	private void setButtonFontSize(int fontSize) {
        sendBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
        cancelBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
	}

	public EmailView() {
		
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Property Listings");
        this.setBackground(Color.WHITE);
        this.setSize(850, 600);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
        styler();
        buttonState(false);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", emailArea);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        add("South",southPanel);
        add("North", subjectArea);
        add("Center", mainPanel);
        
		setVisible(false);
	}
	public void addCloseListener(WindowAdapter a){
        this.addWindowListener(a);
    }
	public void buttonState(boolean state) {
	}
	public void addElementTextBox(String value){
        model.addElement(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
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
}
