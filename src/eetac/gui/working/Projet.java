package eetac.gui.working;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class Projet {

	private JInternalFrame frmNewProject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projet window = new Projet();
					window.frmNewProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Projet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewProject = new JInternalFrame();
		frmNewProject.setTitle("New Project");
		frmNewProject.setBounds(100, 100, 870, 549);
		frmNewProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar_up = new JToolBar();
		frmNewProject.getContentPane().add(toolBar_up, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		toolBar_up.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		toolBar_up.add(btnNewButton_1);
		
		JToolBar toolBar_down = new JToolBar();
		frmNewProject.getContentPane().add(toolBar_down, BorderLayout.SOUTH);
		
		JButton btnNewButton_3 = new JButton("New button");
		toolBar_down.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		toolBar_down.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		toolBar_down.add(progressBar);
		
		JPanel panel = new JPanel();
		frmNewProject.getContentPane().add(panel, BorderLayout.WEST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		tabbedPane.addTab("New tab", null, btnNewButton_2, null);
		
		JTextArea textArea = new JTextArea();
		tabbedPane.addTab("New tab", null, textArea, null);
		
		JPanel panel_1 = new JPanel();
		frmNewProject.getContentPane().add(panel_1, BorderLayout.CENTER);
	}

}
