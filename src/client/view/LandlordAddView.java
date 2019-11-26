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

@SuppressWarnings("serial")
public class LandlordAddView extends JFrame{
	
	String[] houseTypes = {"--choose one--", "apartment","attached house", "detached house","bungalow","townhome","mansion"};
	DefaultComboBoxModel<String> comboModel1 = new DefaultComboBoxModel<String>(houseTypes);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox houseDropdown = new JComboBox(comboModel1);
	
	String[] furnished = {"--choose one--", "Unfurnished", "Furnished"};
	DefaultComboBoxModel<String> comboModel2 = new DefaultComboBoxModel<String>(furnished);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox furnishedDropdown = new JComboBox(comboModel2);
	
	String[] quad = {"--choose one--", "NE", "NW", "SW", "SE"};
	DefaultComboBoxModel<String> comboModel3 = new DefaultComboBoxModel<String>(quad);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox quadDropdown = new JComboBox(comboModel3);
	
	private JTextField bedroomField = new JTextField(10);
    private JTextField bathroomField = new JTextField(10);
	
    private JLabel houseTypeLabel = new JLabel("Type");
    private JLabel bedroomLabel = new JLabel("Bedrooms");
    private JLabel bathroomTypeLabel = new JLabel("Bathrooms");
    private JLabel furnishedLabel = new JLabel("Furnished");
    private JLabel quadrantLabel = new JLabel("City Quadrant");
    
    private JPanel criteriaPanel = new JPanel();
    private JPanel houseTypePanel = new JPanel();
    private JPanel bedroomPanel = new JPanel();
    private JPanel bathroomPanel = new JPanel();
    private JPanel furnishedPanel = new JPanel();
    private JPanel quadrantPanel = new JPanel();
    private final Component horizontalStrut = Box.createHorizontalStrut(100);
    private final Component horizontalStrut_1 = Box.createHorizontalStrut(100);
    private final Component vertStrut = Box.createVerticalStrut(10);
    private final Component vertStrut2 = Box.createVerticalStrut(10);
    public JButton submitButton = new JButton("Add");
    public JButton payButton = new JButton("Pay Fee");
    private JPanel submitPanel = new JPanel();
    
    private void labels() {
    	houseDropdown.setMaximumSize(new Dimension(300, 10));
    	houseDropdown.setMinimumSize(new Dimension(100, 22));
    	
//    	houseDropdown.setBackground(Color.WHITE);
    	houseDropdown.setFont(new Font("Sans", Font.PLAIN, 20));
    	furnishedDropdown.setMaximumSize(new Dimension(300, 10));
//    	furnishedDropdown.setBackground(Color.WHITE);
    	furnishedDropdown.setFont(new Font("Sans", Font.PLAIN, 20));
    	quadDropdown.setMaximumSize(new Dimension(300, 10));
//    	quadDropdown.setBackground(Color.WHITE);
    	quadDropdown.setFont(new Font("Sans", Font.PLAIN, 20));
    	
    	
    	houseTypeLabel.setFont(new Font("Sans", Font.PLAIN, 20));
    	bedroomLabel.setFont(new Font("Sans", Font.PLAIN, 20));
    	bathroomTypeLabel.setFont(new Font("Sans", Font.PLAIN, 20));
    	furnishedLabel.setFont(new Font("Sans", Font.PLAIN, 20));
    	quadrantLabel.setFont(new Font("Sans", Font.PLAIN, 20));
    	houseTypePanel.setPreferredSize(new Dimension(10, 6));
    	
    	houseTypePanel.setLayout(new FlowLayout());
//    	houseTypePanel.setBackground(Color.WHITE);
    	houseTypePanel.add(houseTypeLabel);
    	bedroomPanel.setMaximumSize(new Dimension(300, 32767));
    	bedroomPanel.setPreferredSize(new Dimension(300, 5));
//    	bedroomPanel.setBackground(Color.WHITE);
    	bedroomPanel.setLayout(new GridLayout(0, 2, 0, 0));
    	bedroomPanel.add(bedroomLabel);
    	bathroomPanel.setMaximumSize(new Dimension(300, 32767));
    	bathroomPanel.setPreferredSize(new Dimension(10, 5));
//    	bathroomPanel.setBackground(Color.WHITE);
    	bathroomPanel.setLayout(new GridLayout(0, 2, 0, 0));
    	bathroomPanel.add(bathroomTypeLabel);
    	furnishedPanel.setPreferredSize(new Dimension(10, 5));
    	
    	furnishedPanel.setLayout(new FlowLayout());
//    	furnishedPanel.setBackground(Color.WHITE);
    	furnishedPanel.add(furnishedLabel);
    	quadrantPanel.setPreferredSize(new Dimension(10, 5));
    	
    	quadrantPanel.setLayout(new FlowLayout());
//    	quadrantPanel.setBackground(Color.WHITE);
    	quadrantPanel.add(quadrantLabel);
    	
    	criteriaPanel.setLayout(new BoxLayout(criteriaPanel, BoxLayout.Y_AXIS));
    	criteriaPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
    	
    }
    
	public LandlordAddView() {
		
		this.setTitle("Enter Property Information");
   	 	this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        
        labels();
        
        criteriaPanel.add(houseTypePanel);
        houseDropdown.setBackground(Color.WHITE);
        criteriaPanel.add(houseDropdown);
        bedroomField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bedroomField.setPreferredSize(new Dimension(116, 10));
        bedroomField.setMinimumSize(new Dimension(6, 10));
        bedroomField.setMaximumSize(new Dimension(2147483647, 10));
        
    	criteriaPanel.add(furnishedPanel);
    	furnishedDropdown.setBackground(Color.WHITE);
    	criteriaPanel.add(furnishedDropdown);
    	
    	criteriaPanel.add(quadrantPanel);
    	quadDropdown.setBackground(Color.WHITE);
    	criteriaPanel.add(quadDropdown);
    	criteriaPanel.add(vertStrut);
    	
    	bedroomPanel.add(bedroomField);
    	criteriaPanel.add(bedroomPanel);
    	bathroomField.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	
    	bathroomPanel.add(bathroomField);
    	criteriaPanel.add(bathroomPanel);
    	
    	criteriaPanel.add(vertStrut2);
    	submitPanel.setLayout(new FlowLayout());
    	submitButton.setPreferredSize(new Dimension(150, 50));
    	submitButton.setBackground(Color.WHITE);
    	submitPanel.add(submitButton);
    	criteriaPanel.add(submitPanel);
        
        getContentPane().add("Center", criteriaPanel);
        getContentPane().add(horizontalStrut, BorderLayout.WEST);
        getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
        
		setVisible(false);
	}
	public void addCloseListener(WindowAdapter a){
        this.addWindowListener(a);
    }
	public String getBedrooms() {
    	return bedroomField.getText();
    }
	public String getBathrooms() {
		return bathroomField.getText();
	}
	public void errorMessage(String text){
        JOptionPane.showMessageDialog(this, text);
    }
	public void addSubmitListener(ActionListener a) {
		submitButton.addActionListener(a);
	}
	public void addHouseDropdownListener(ItemListener a) {
        houseDropdown.addItemListener(a);
    }
	public void addQuadDropdownListener(ItemListener a) {
		quadDropdown.addItemListener(a);
	}
	public void addfurnishDropdownListener(ItemListener a) {
		furnishedDropdown.addItemListener(a);
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LandlordAddView window = new LandlordAddView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addListener(ActionListener a) {
		submitButton.addActionListener(a);
	}
	
	public void clearText() {
		bedroomField.setText(null);
		bathroomField.setText(null);
		quadDropdown.setSelectedIndex(0);
		houseDropdown.setSelectedIndex(0);
		furnishedDropdown.setSelectedIndex(0);
	}
}
