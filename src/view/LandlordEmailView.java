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

public class LandlordEmailView extends JFrame {
    JPanel topSide = new JPanel();
    JPanel bottomSide = new JPanel();

    JButton deleteBtn = new JButton("Delete");
    JButton openBtn = new JButton("Open");
    public DefaultListModel<String> model = new DefaultListModel<>();
    public JList<String> textBox = new JList<>(model);


    public void constructTopSide(){
        //leftSide.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        topSide.setPreferredSize(new Dimension(500, 200));
        topSide.setMaximumSize(new Dimension(10000, 200));
        textBox.setFont(new Font("Sans", Font.PLAIN, 16));
        textBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollText = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        topSide.add("Center", scrollText);
    }

    public void constructBottomSide(){
        bottomSide.add("North",openBtn);
        bottomSide.add("Center", deleteBtn);
    }


    public LandlordEmailView(){
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Email");
        this.setBackground(Color.WHITE);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        constructTopSide();
        constructBottomSide();

        add("Center",topSide);
        add("South",bottomSide);
        
        
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