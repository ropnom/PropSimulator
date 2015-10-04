package eetac.gui.rodrigo;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class Variableitem extends JPanel {

	/**
	 * This compoennt is a panel with checkbox, label, texbox for varaible data.
	 */
	private static final long serialVersionUID = 1L;
	
	JCheckBox variable;
	JTextField value;
	private JTextField textField;
	
	public Variableitem(String name, double variablevalue) {

		this.variable = new JCheckBox("Nombrevariable", false);
		this.value = new JTextField("0.15");
		value.setColumns(10);
		value.setHorizontalAlignment(JTextField.CENTER);
		
		value.setEnabled(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.add(variable);
		this.add(value);
		
	}

}
