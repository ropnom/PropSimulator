package eetac.gui.rodrigo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eetac.model.basicstructure.SimulationBlock;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class Variableitem extends JPanel {

	/**
	 * This compoennt is a panel with checkbox, label, texbox for varaible data.
	 */

	private static final long serialVersionUID = 1L;

	private JCheckBox variable;
	private JTextField value;
	private int variableindex;

	protected SimulationBlock a;

	private void PutVariable(double var) {
		a.getMatrices().getX_equations()[variableindex][0] = var;
		// re calcular bloque
	}

	private String getVarValue() {

		return ("" + a.getMatrices().getX_equations()[variableindex][0]);
	}

	public Variableitem(SimulationBlock a, int varindex) {

		
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.a = a;
		this.variableindex = varindex;
		this.variable = new JCheckBox("<variable>", false);
		variable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (variable.isSelected()) {
					value.setEnabled(true);
				} else {
					value.setEnabled(false);
				}
			}
		});
		this.value = new JTextField("" + a.getMatrices().getX_equations()[variableindex][0]);
		value.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PutVariable(Double.parseDouble(value.getText()));

				} catch (Exception e) {
					value.setText(getVarValue());
				}
			}
		});
		value.setColumns(10);
		value.setHorizontalAlignment(JTextField.CENTER);

		value.setEnabled(false);
		setLayout(new GridLayout(0, 3, 0, 0));

		
		this.add(variable);
		this.add(value);
		
		JLabel lblNewLabel = new JLabel("{units}");
		add(lblNewLabel);

	}

	public JCheckBox getVariable() {
		return variable;
	}

	public void setVariable(JCheckBox variable) {
		this.variable = variable;
	}

	public JTextField getValue() {
		return value;
	}

	public void setValue(JTextField value) {
		this.value = value;
	}

	public int getVariableindex() {
		return variableindex;
	}

	public void setVariableindex(int variableindex) {
		this.variableindex = variableindex;
	}

	public SimulationBlock getA() {
		return a;
	}

	public void setA(SimulationBlock a) {
		this.a = a;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
