package eetac.gui.rodrigo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eetac.model.basicstructure.SimulationBlock;
import eetac.model.realcomponent.Compressor;

public class VariablesEdit {

	private JFrame frame;
	private SimulationBlock block;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VariablesEdit window = new VariablesEdit();
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
	public VariablesEdit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		block = new Compressor();		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0,1));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Variableitem[] varitems = new Variableitem[block.getNumvariables()];  
		
		for(int i = 0; i<block.getNumvariables();i++){
			//Create each variable item
			varitems[i] = new Variableitem(block, i);
			panel.add(varitems[i]);
		}
	}
}
