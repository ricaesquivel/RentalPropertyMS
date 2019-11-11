package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class ListingsView extends JFrame{

	JLabel title = new JLabel("RentalPropertyMS" + "\u2122");
	JPanel topButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();
	
	public DefaultListModel<String> model = new DefaultListModel<>();
    public JList<String> textBox = new JList<>(model);
    
    public JButton searchButton = new JButton("Search");
    public JButton emailButton = new JButton("email Landlord");
	
	public ListingsView() {
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Property Listings");
        this.setBackground(Color.WHITE);
        this.setSize(850, 600);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		
		
		setVisible(false);
	}
	
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		searchButton.addActionListener(a);
		emailButton.addActionListener(a);
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListingsView window = new ListingsView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
