package eetac.gui.working;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PropSimulator {

	private JFrame frmPropSimulator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropSimulator window = new PropSimulator();
					window.frmPropSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PropSimulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPropSimulator = new JFrame();
		frmPropSimulator.setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Usuarios\\Rodrigo\\workspace\\PropSimulator\\ico\\Jet_Engine_copy.png"));
		frmPropSimulator.setTitle("Prop Simulator");
		frmPropSimulator.setBounds(100, 100, 1163, 742);
		frmPropSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPropSimulator.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmPropSimulator.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Projet a = new Projet();
				//desktopPane.add(a);
			}
		});
		
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmOpenProjet = new JMenuItem("Open Projet");
		mnFile.add(mntmOpenProjet);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
	
	}

}
