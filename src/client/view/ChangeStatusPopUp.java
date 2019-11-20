package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import client.controller.LandlordController.MyListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;


@SuppressWarnings("serial")

public class ChangeStatusPopUp extends JFrame{
	
	private MyListener listener;
	
	public JButton submit = new JButton("submit");
	String[] menu = {"--choose one--", "Active","Rented","Cancelled","Suspended"};
	DefaultComboBoxModel<String> dropDown = new DefaultComboBoxModel<String>(menu);
	
    public JComboBox<String> dropDownCombo = new JComboBox<String>(dropDown);
	
    
    public void style() {
        submit.setPreferredSize(new Dimension(150,150));
        submit.setMinimumSize(new Dimension(300,150));
        
        submit.setFont(new Font("Sans", Font.PLAIN, 15));
        
        dropDownCombo.setPreferredSize(new Dimension(200,25));
        dropDownCombo.setMinimumSize(new Dimension(200,25));
        dropDownCombo.setFont(new Font("Sans", Font.PLAIN, 15));
    	
    }
	
	public ChangeStatusPopUp() {
    	this.setTitle("Sign Up");
   	 	this.setSize(700, 75);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        style();
        
        add(dropDownCombo);
        add(submit);
	}
	
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	
	public void addDropdownListener(ItemListener a) {
		dropDownCombo.addItemListener(a);
	}
	
	
	public void addListener(ActionListener a) {
		submit.addActionListener(a);
	}
	public static void main(String[] args) {
		try {
			ChangeStatusPopUp window = new ChangeStatusPopUp();
			window.setVisible(true);
		}catch(Exception a){
			a.printStackTrace();			
		}
	}
}
