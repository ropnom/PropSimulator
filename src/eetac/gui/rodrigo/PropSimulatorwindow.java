package eetac.gui.rodrigo;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTabbedPane;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

import eetac.model.basicstructure.SimulationBlock;

public class PropSimulatorwindow extends JFrame {
	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public PropSimulatorwindow() {
	
		
		setTitle("PropSim Alfa 0.1");
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Usuarios\\Rodrigo\\workspace\\PropSimulator\\ico\\Jet_Engine_copy.png"));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadSimulation = new JMenuItem("Load Simulation");
		mnFile.add(mntmLoadSimulation);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseFrame();
			}
		});
		mnFile.add(mntmClose);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmVersion = new JMenuItem("Version");
		mnAbout.add(mntmVersion);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2,0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewEngineSim = new JLabel("ENGINE SIMULATION:");
		lblNewEngineSim.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewEngineSim.setBounds(24, 11, 200, 22);
		panel_1.add(lblNewEngineSim);
		
		JComboBox comboBox_Engine = new JComboBox();
		comboBox_Engine.setModel(new DefaultComboBoxModel(new String[] {"Turbojet", "Turbofan"}));
		comboBox_Engine.setBounds(146, 59, 145, 20);
		panel_1.add(comboBox_Engine);
		
		JLabel lblEngType = new JLabel("Select type of Engine:");
		lblEngType.setBounds(24, 62, 112, 14);
		panel_1.add(lblEngType);
		
		JComboBox comboBox__modeleng = new JComboBox();
		comboBox__modeleng.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBox__modeleng.setBounds(146, 90, 145, 20);
		panel_1.add(comboBox__modeleng);
		
		JLabel lblTermModelEng = new JLabel("Termodinamic Model:");
		lblTermModelEng.setBounds(24, 93, 112, 14);
		panel_1.add(lblTermModelEng);
		
		JButton btnNewSimulator = new JButton("New Engine");
		btnNewSimulator.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewSimulator.setBounds(169, 121, 122, 38);
		panel_1.add(btnNewSimulator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel.add(panel_2);
		
		JLabel lblBlockSimulation = new JLabel("BLOCK SIMULATION:");
		lblBlockSimulation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBlockSimulation.setBounds(24, 11, 200, 22);
		panel_2.add(lblBlockSimulation);
		
		JComboBox comboBox_block = new JComboBox();
		comboBox_block.setModel(new DefaultComboBoxModel(SimulationBlock.getTypeofblocks()));
		comboBox_block.setBounds(146, 59, 145, 20);
		panel_2.add(comboBox_block);
		
		JLabel lblBlockType = new JLabel("Select type of Block");
		lblBlockType.setBounds(24, 62, 112, 14);
		panel_2.add(lblBlockType);
		
		JComboBox comboBox_modelblock = new JComboBox();
		comboBox_modelblock.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBox_modelblock.setBounds(146, 90, 145, 20);
		panel_2.add(comboBox_modelblock);
		
		JLabel lblTermModelBlock = new JLabel("Termodinamic Model:");
		lblTermModelBlock.setBounds(24, 93, 112, 14);
		panel_2.add(lblTermModelBlock);
		
		JButton btnNewBlockSim = new JButton("New Block Sim");
		btnNewBlockSim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewBlockSim.setBounds(169, 121, 122, 38);
		panel_2.add(btnNewBlockSim);
	}
}
