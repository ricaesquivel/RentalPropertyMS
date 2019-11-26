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
public class SummaryView extends JFrame{

	public boolean registered;		// true if registered renter
	public String username;
	
	JLabel title = new JLabel("Summary Report");
	JLabel title2 = new JLabel("   Rented Property");
	JPanel topButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel topTitlePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    
    JLabel total = new JLabel("sample");
	
    String[] headers = {"col1", "col2"};
    String[][] data = {{"col1 row1", "col2 row1"},
    					{"col1 row2", "col2 row2"}
    };
	
    public DefaultTableModel model = new DefaultTableModel(data, headers);
    public JTable textBox = new JTable(model);

    
    public void styler() {
    	this.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        topButtonPanel.setBackground(Color.WHITE);
        bottomButtonPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.WHITE);
        
        topButtonPanel.setLayout(new FlowLayout());
        topButtonPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
        topButtonPanel.add(total);
        
        total.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
        title2.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
        title.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        northPanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(title);
        northPanel.add("North", titlePanel);
        northPanel.add("West", topButtonPanel);
        northPanel.add("South",title2);
        
        
        textBox.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));        
        textBox.setRowHeight(25);       
    }
    
	public SummaryView() {
		
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
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", scrollText);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        add("North", northPanel);
        add("Center", mainPanel);
        
		setVisible(false);
	}

	
	public void setLabels(String t) {
		total.setText(t);
	}


	public void addElementTextBox(String[] value){
        model.addRow(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
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
			SummaryView window = new SummaryView();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSelectionListener(ListSelectionListener a) {
		textBox.getSelectionModel().addListSelectionListener(a);
	}
}
