package eetac.gui.rodrigo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RunGui {

	private PropSimulatorwindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunGui window = new RunGui();
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
	public RunGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new PropSimulatorwindow();
		frame.setResizable(false);
		frame.setBounds(100, 100, 328, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
