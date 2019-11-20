package client.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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
import javax.swing.JDialog;

import java.awt.Dimension;

@SuppressWarnings("serial")

public class LandlordEmailView extends JFrame {
    JPanel topSide = new JPanel();
    JPanel bottomSide = new JPanel();

    JLabel titleLabel = new JLabel("Messages sent to you");
    JPanel titlePanel = new JPanel();
    
    public JButton deleteBtn = new JButton("Delete");
    public JButton openBtn = new JButton("Open");
    
    String[] headers = {"col1", "col2"};
    String[][] data = {{"col1 row1", "col2 row1"},
    					{"col1 row2", "col2 row2"}
    };
	
    public DefaultTableModel model = new DefaultTableModel(data, headers);
    public JTable textBox = new JTable(model);

    public void constructTopSide(){
    	
        topSide.setPreferredSize(new Dimension(500, 200));
        topSide.setMaximumSize(new Dimension(10000, 200));
        topSide.setBackground(Color.WHITE);
        
        textBox.setFont(new Font("Sans", Font.PLAIN, 16));
        textBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textBox.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));        
        textBox.setRowHeight(25);  
        JScrollPane scrollText = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollText.setMaximumSize(new Dimension(32767, 300));
        scrollText.setPreferredSize(new Dimension(500, 200));
        
        
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titlePanel.add(titleLabel);
        topSide.add("North", titlePanel);
        topSide.add("Center", scrollText);
    }

    public void constructBottomSide(){
    	bottomSide.setBackground(Color.WHITE);
        bottomSide.add("North",openBtn);
        bottomSide.add("Center", deleteBtn);
    }
    public void displayEmail(String text){  
    	JDialog d = new JDialog(this, "Email");
    	d.setBackground(Color.WHITE);
    	JTextArea field = new JTextArea(text);
    	field.setAlignmentY(TOP_ALIGNMENT);
    	field.setAlignmentX(LEFT_ALIGNMENT);
    	field.setEditable(false);
    	field.setLineWrap(true);
    	d.add(field);
    	d.setSize(250,250);
    	d.setLocation(500, 500);
    	d.setVisible(true);
    }
    public LandlordEmailView(){
    	
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Email");
        this.setBackground(Color.WHITE);
        this.setSize(600, 330);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        constructTopSide();
        constructBottomSide();
        
        openBtn.setFont(new Font("Sans", Font.PLAIN, 20));
        deleteBtn.setFont(new Font("Sans", Font.PLAIN, 20));

        getContentPane().add("Center",topSide);
        getContentPane().add("South",bottomSide);
        clear();
        textBox.setDefaultEditor(Object.class, null);
        setVisible(false);
        
    }
    
    public void addElementTextBox(String[] value){
        model.addRow(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		deleteBtn.addActionListener(a);
		openBtn.addActionListener(a);
	}
	public void clear() {
		model.setColumnCount(0);
		model.setRowCount(0);
	}
	public void setCols(String[] cols) {
		model.setColumnIdentifiers(cols);
	}
	
	public void addSelectionListener(ListSelectionListener a) {
		textBox.getSelectionModel().addListSelectionListener(a);
	}
	
	public void deleteRow(int row) {
		((DefaultTableModel)textBox.getModel()).removeRow(row);
	}

    public static void main(String[] args) {
        try {
            LandlordEmailView window = new LandlordEmailView();
            window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}