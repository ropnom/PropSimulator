package eetac.gui.component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eetac.model.basicstructure.SimulationBlock;

public class VariableBlockPannel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5251126424195067548L;
	
	protected JCheckBox variable;
	protected JTextField value;
	protected int type = 0;
	protected int numvariable=0;

	protected VariablesPannel mypanel;
	protected SimulationBlock myblock;

	public VariableBlockPannel() {
		
		variable = new JCheckBox("Not defined",false);
		variable.setEnabled(false);
		value = new JTextField();
		value.setEnabled(false);
		
		this.add(variable);
		this.add(value);
	}
	
	public VariableBlockPannel(VariablesPannel mypannel, SimulationBlock myblock, int num) {
		this.mypanel = mypannel;
		this.myblock = myblock;
		this.numvariable = num;
		
		this.type = myblock.getType(numvariable);
		
		variable = new JCheckBox(myblock.getMatrices().getVariable()[numvariable], myblock.getMatrices().getConstants()[numvariable]);
		value = new JTextField(""+myblock.getMatrices().getX_equations()[numvariable][0]);
		
		this.add(variable);
		this.add(value);
		
	}
	
	//Evento de checkbox
	
	
	//Evento textfiel change

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public VariablesPannel getMypanel() {
		return mypanel;
	}

	public void setMypanel(VariablesPannel mypanel) {
		this.mypanel = mypanel;
	}

	public int getNumvariable() {
		return numvariable;
	}

	public void setNumvariable(int numvariable) {
		this.numvariable = numvariable;
	}

	public SimulationBlock getMyblock() {
		return myblock;
	}

	public void setMyblock(SimulationBlock myblock) {
		this.myblock = myblock;
	}
	

}
