package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
public class ManagerView extends JFrame{

	JLabel title = new JLabel("Manager Page" + "\u2122");
	JPanel topButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();

    JPanel southPanel = new JPanel();
	
	public DefaultListModel<String> model = new DefaultListModel<>();
    public JList<String> textBox = new JList<>(model);
    
    public JButton listLandlordBtn = new JButton("List landlords");
    public JButton listRentersBtn = new JButton("List Renters");
    public JButton updateButton = new JButton("Clear Filters and Update");

    public JButton requestSummary = new JButton("Create Summary");
	
    public void styler() {
    	this.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        topButtonPanel.setBackground(Color.WHITE);
        bottomButtonPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.WHITE);
        
        topButtonPanel.setLayout(new FlowLayout());
        setButtonFontSize(20);
        topButtonPanel.add(listLandlordBtn);
        topButtonPanel.add(listRentersBtn);
        topButtonPanel.add(updateButton);
        topButtonPanel.setBorder(new EmptyBorder(10, 15, 0, 15));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(requestSummary);
        southPanel.setBorder(new EmptyBorder(10, 15, 0, 15));

        
        title.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        northPanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(title);
        northPanel.add("North", titlePanel);
        northPanel.add("Center", topButtonPanel);
        
    }
    
	private void setButtonFontSize(int fontSize) {
		listLandlordBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
		listRentersBtn.setFont(new Font("Sans", Font.PLAIN, fontSize));
        updateButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
        requestSummary.setFont(new Font("Sans", Font.PLAIN, fontSize));
	}

	public ManagerView() {
		
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Property Listings");
        this.setBackground(Color.WHITE);
        this.setSize(850, 600);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		textBox.setFont(new Font("Sans", Font.PLAIN, 16));
        textBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollText = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
        styler();
        buttonState(false);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", scrollText);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        add("South",southPanel);
        add("North", northPanel);
        add("Center", mainPanel);
        
		setVisible(false);
	}
	public void addCloseListener(WindowAdapter a){
        this.addWindowListener(a);
    }
	public void buttonState(boolean state) {
		listRentersBtn.setEnabled(true);
	}
	public void addElementTextBox(String value){
        model.addElement(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		listLandlordBtn.addActionListener(a);
		listRentersBtn.addActionListener(a);
		updateButton.addActionListener(a);
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		    ManagerView window = new ManagerView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
