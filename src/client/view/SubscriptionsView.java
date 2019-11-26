package client.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

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

public class SubscriptionsView extends JFrame {
    JPanel topSide = new JPanel();
    JPanel bottomSide = new JPanel();

    JLabel titleLabel = new JLabel("Your Subscriptions");
    JPanel titlePanel = new JPanel();
    
    public JButton deleteBtn = new JButton("Delete");
    public JButton viewBtn = new JButton("View");
//    JButton openBtn = new JButton("Open");
    
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
        topSide.setLayout(new BorderLayout());
        
        textBox.setFont(new Font("Sans", Font.PLAIN, 16));
        textBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textBox.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));        
        textBox.setRowHeight(25);  
        JScrollPane scrollText = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        scrollText.setMaximumSize(new Dimension(32767, 300));
        scrollText.setPreferredSize(new Dimension(500, 200));
        
        
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titlePanel.add(titleLabel);
        topSide.add("North", titlePanel);
        textPanel.add(scrollText);
        topSide.add("Center", textPanel);
    }

    public void constructBottomSide(){
    	bottomSide.setBackground(Color.WHITE);
//        bottomSide.add("North",openBtn);
    	bottomSide.add("West", deleteBtn);
        bottomSide.add("East", viewBtn);
    }

    public SubscriptionsView(){
    	
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Subscriptions");
        this.setBackground(Color.WHITE);
        this.setSize(800, 330);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        constructTopSide();
        constructBottomSide();
        
//        openBtn.setFont(new Font("Sans", Font.PLAIN, 20));
        deleteBtn.setFont(new Font("Sans", Font.PLAIN, 20));
        viewBtn.setFont(new Font("Sans", Font.PLAIN, 20));

        getContentPane().add("Center",topSide);
        getContentPane().add("South",bottomSide);
        clear();
        deleteBtn.setEnabled(false);
        viewBtn.setEnabled(false);
        textBox.setDefaultEditor(Object.class, null);
        setVisible(false);
        
    }
    public void deleteRow(int row) {
    	((DefaultTableModel)textBox.getModel()).removeRow(0);
    }
    public void addElementTextBox(String[] value){
        model.addRow(value);
    }
	public void errorMessage(String error){
        JOptionPane.showMessageDialog(this, error);
    }
	public void addListener(ActionListener a) {
		deleteBtn.addActionListener(a);
		viewBtn.addActionListener(a);
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

    public static void main(String[] args) { 
        try {
            SubscriptionsView window = new SubscriptionsView();
            window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}