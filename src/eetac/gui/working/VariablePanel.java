package eetac.gui.working;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VariablePanel extends JPanel {

	/**
	 * This compoennt is a panel with checkbox, label, texbox for varaible data.
	 */
	private static final long serialVersionUID = 1L;
	
	JCheckBox variable;
	JTextField value;
	
	public VariablePanel() {

		this.variable = new JCheckBox("Nombrevariable", false);
		this.value = new JTextField("0.15");
		
		value.setEnabled(false);
		
		this.add(variable);
		this.add(value);
	}

}
