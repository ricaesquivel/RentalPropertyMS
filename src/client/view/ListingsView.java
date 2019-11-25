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
import javax.swing.table.TableColumnModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
public class ListingsView extends JFrame{

	public boolean registered;		// true if registered renter
	public String username;
	
	JLabel title = new JLabel("RentalPropertyMS" + "\u2122");
	JPanel topButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();
	
    String[] headers = {"col1", "col2"};
    String[][] data = {{"col1 row1", "col2 row1"},
    					{"col1 row2", "col2 row2"}
    };
	
    public DefaultTableModel model = new DefaultTableModel(data, headers);
    public JTable textBox = new JTable(model);
    
    public JButton searchButton = new JButton("Search Filter");
    public JButton emailButton = new JButton("Email Landlord");
    public JButton updateButton = new JButton("Clear Filters and Update");
    public JButton subscriptionsButton = new JButton("View Subscriptions");
	
    public void styler() {
    	this.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        topButtonPanel.setBackground(Color.WHITE);
        bottomButtonPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.WHITE);
        
        topButtonPanel.setLayout(new FlowLayout());
        setButtonFontSize(20);
        topButtonPanel.add(searchButton);
        topButtonPanel.add(emailButton);
        topButtonPanel.add(updateButton);
        topButtonPanel.add(subscriptionsButton);
        topButtonPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
        title.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        northPanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(title);
        northPanel.add("North", titlePanel);
        northPanel.add("Center", topButtonPanel);
        
        textBox.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));        
        textBox.setRowHeight(25);       
    }
    
	private void setButtonFontSize(int fontSize) {
		searchButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
		emailButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
		updateButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
		subscriptionsButton.setFont(new Font("Sans", Font.PLAIN, fontSize));
	}

	public ListingsView() {
		
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
        JScrollPane scrollText = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textBox.setDefaultEditor(Object.class, null);
		
        styler();
        buttonState(false);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", scrollText);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        add("North", northPanel);
        add("Center", mainPanel);
        
		setVisible(false);
	}
	public void setSubscriptionButtonState(boolean state) {
		subscriptionsButton.setEnabled(state);
	}
	public void addCloseListener(WindowAdapter a){
        this.addWindowListener(a);
    }
	public void buttonState(boolean state) {
		emailButton.setEnabled(false);
	}
	public void addElementTextBox(String[] value){
        model.addRow(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		searchButton.addActionListener(a);
		emailButton.addActionListener(a);
		updateButton.addActionListener(a);
		subscriptionsButton.addActionListener(a);
	}
	public void autoColWidth() {
		for (int column = 0; column < textBox.getColumnCount(); column++)
		{
		    TableColumn tableColumn = textBox.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < textBox.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = textBox.getCellRenderer(row, column);
		        Component c = textBox.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + textBox.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		 
		    tableColumn.setPreferredWidth( preferredWidth );
		}
		textBox.getTableHeader().setReorderingAllowed(false);
	}
	
	public void hideLandlordCol() {
		TableColumnModel tcm = textBox.getColumnModel();
		tcm.removeColumn( tcm.getColumn(6) );
	}
	
	public void clear() {
		model.setColumnCount(0);
		model.setRowCount(0);
	}
	public void setCols(String[] cols) {
		model.setColumnIdentifiers(cols);
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

	public void addSelectionListener(ListSelectionListener a) {
		textBox.getSelectionModel().addListSelectionListener(a);
	}
}
