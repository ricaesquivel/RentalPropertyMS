package view;

import org.eclipse.swt.widgets.Shell;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	
	JLabel SelectUserLabel = new JLabel("Please Select User Type");
	JPanel buttonPanel = new JPanel();
	JPanel topTitle = new JPanel();
	
	public LoginView() {
		
//		getContentPane().add(SelectUserLabel, BorderLayout.NORTH);
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        this.setBackground(Color.WHITE);
        this.setSize(700, 400);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		SelectUserLabel.setFont(new Font("Sans", Font.BOLD, 18));
        topTitle.add(SelectUserLabel);
		
		buttonPanel.setLayout(new FlowLayout());
		
		add("Center", buttonPanel);
        add("North", topTitle);
        setVisible(false);
	}

	protected Shell shlRentalpropertyms;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginView window = new LoginView();
//			window.open();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shlRentalpropertyms.open();
//		shlRentalpropertyms.layout();
//		while (!shlRentalpropertyms.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 */
//	protected void createContents() {
//		shlRentalpropertyms = new Shell();
//		shlRentalpropertyms.setForeground(SWTResourceManager.getColor(255, 255, 255));
//		shlRentalpropertyms.setBackground(SWTResourceManager.getColor(255, 255, 255));
//		shlRentalpropertyms.setMinimumSize(new Point(600, 400));
//		shlRentalpropertyms.setSize(450, 300);
//		shlRentalpropertyms.setText("RentalPropertyMS");
//
//	}

}
