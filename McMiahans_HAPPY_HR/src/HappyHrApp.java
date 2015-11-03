import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSeparator;
import java.awt.BorderLayout;


public class HappyHrApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					HappyHrApp window = new HappyHrApp();
					window.frame.setVisible(true);

						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
		});
	}

	/**
	 * Create the application.
	 */
	public HappyHrApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnBarInfo = new JMenu("Bar Info");
		menuBar.add(mnBarInfo);
		
		JMenuItem mntmShowAllBars = new JMenuItem("show all bars");
		mnBarInfo.add(mntmShowAllBars);
		
		JMenuItem mntmShowActiveHappy = new JMenuItem("show active happy hour options");
		mnBarInfo.add(mntmShowActiveHappy);
		
		JMenuItem mntmFindBarBy = new JMenuItem("find bar by name");
		mnBarInfo.add(mntmFindBarBy);
		
		JMenuItem mntmAddNewBar = new JMenuItem("add new  bar");
		mnBarInfo.add(mntmAddNewBar);
		
		JMenuItem mntmRemoveBar = new JMenuItem("remove bar");
		mnBarInfo.add(mntmRemoveBar);
		
		JSeparator separator_1 = new JSeparator();
		menuBar.add(separator_1);
		
		JSeparator separator = new JSeparator();
		menuBar.add(separator);
		
		JButton btnQuit = new JButton("quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(btnQuit);
		
		JLabel lblNewLabel = new JLabel("                       McMiahans's Happy Hour Tracker");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
