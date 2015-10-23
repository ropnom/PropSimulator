package eetac.model;

import java.io.Serializable;
import java.util.Arrays;

public class MatrixCollection implements Serializable{

	// Math matrix using in math method
	protected double[][] X_equations;
	protected double[][] Fx_equations;
	protected double[][] Jx;

	//Aux matrix for define humaninterfice
	protected String[] variable;
	protected boolean[] constants;

	public MatrixCollection() {

	}
	
	//Copy contructor
	public MatrixCollection(MatrixCollection a) {
		this.X_equations = AuxMethods.Copy_matrix(a.getX_equations());
		this.Fx_equations = AuxMethods.Copy_matrix(a.getFx_equations());
		this.Jx = AuxMethods.Copy_matrix(a.getJx());
		this.variable = Arrays.copyOf(a.getVariable(), a.getVariable().length);
		this.constants = Arrays.copyOf(a.getConstants(),a.getConstants().length);

	}

	public MatrixCollection(double[][] Fx_equations, double[][] Jx) {
		this.Fx_equations = Fx_equations;
		this.Jx = Jx;
	}

	
	public short getnumconstants() {
		/* Return the number of constants define by user in the matrix
		 */
		
		short i = 0;
		for (int j = 0; j < constants.length; j++) {
			if (constants[j])
				i++;
		}
		return i;
	}

	public double[][] getFx_equations() {
		return Fx_equations;
	}

	public void setFx_equations(double[][] fx_equations) {
		Fx_equations = fx_equations;
	}

	public double[][] getJx() {
		return Jx;
	}

	public void setJx(double[][] jx) {
		Jx = jx;
	}

	public double[][] getX_equations() {
		return X_equations;
	}

	public void setX_equations(double[][] x_equations) {
		X_equations = x_equations;
	}

	public String[] getVariable() {
		return variable;
	}

	public void setVariable(String[] variable) {
		this.variable = variable;
	}

	public boolean[] getConstants() {
		return constants;
	}

	public void setConstants(boolean[] constants) {
		this.constants = constants;
	}

}
