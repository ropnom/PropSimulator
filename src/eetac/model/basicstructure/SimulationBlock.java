package eetac.model.basicstructure;

import java.util.Comparator;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;

public class SimulationBlock extends BasicBlock {
	/*
	 * This class provide of need variables for work inside math core of
	 * simulation. Contain a MatricCollection of equations, counters of
	 * variables, contants, relations ... Contain the main methods of a
	 * Simulation block lilke Simulate(), getFx, getvariable,
	 * setvariable,getdifferencial...
	 */

	// math internal matrix
	protected MatrixCollection matrices = new MatrixCollection();

	// internal check of variables
	protected short numequations = 0;
	protected short numvariables = 0;
	protected short numconstants = 0;
	protected short numrelations = 0;

	// reference for math arrays in engine
	protected short initnum = 0;
	protected short endnum = 0;

	// Is this block full defined?
	protected boolean isdefined = false;

	protected static int[] type;

	public SimulationBlock() {
		super();

	}

	public SimulationBlock(MatrixCollection matrices, short numequations, short initnum, short endnum) {
		super();
		this.matrices = matrices;
		this.numequations = numequations;
		this.initnum = initnum;
		this.endnum = endnum;
	}

	// copy contructor
	public SimulationBlock(SimulationBlock a) {
		super(a);
		this.matrices = new MatrixCollection(a.getMatrices());
		this.numequations = a.getNumequations();
		this.initnum = a.getInitnum();
		this.endnum = a.getEndnum();

	}

	public void GenAuxvariables() {

	}

	// GEneral Methods
	public double getvariable(int index) {

		double variable = 0;
		// switch
		return variable;
	}

	public void setvariable(double variable, int index) {

		// Set variable
	}

	protected double getFx(double[][] X, int equation) {

		// switch(equation){
		// case 1:
		// break;
		// default:
		// break;
		// }
		return 0;
	}

	protected void genMatrix() {

		int totalequations = numequations + numconstants;

		double[][] Fx = new double[totalequations][1];
		double[][] Jx = new double[this.numvariables][totalequations];
		boolean[] constants;

		if (isdefined) {

			double[][] X = matrices.getX_equations();			
			constants = this.matrices.getConstants();
			// Gen Fx vector

			// Fx[0][0] = PressureRelations(X);
			// Fx[1][0] = TemperatureRelations(X);
			// Fx[2][0] = MassFlowRelations(X);
			// Other

			for (int i = 0; i < (totalequations); i++) {
				// If is a known variable, X_i - cte = 0;
				if (i < numequations) {
					Fx[i][0] = getFx(X, i);
				} else {
					Fx[i][0] = 0;
				}

			}
			
			Jx = GenJacobian(X,Fx,constants,totalequations);

		} else {
			// Gen boolean constant know for init objet
			constants = new boolean[this.numvariables];
			for (int l = 0; l < numvariables; l++) {
				constants[l] = false;
			}

			this.matrices.setConstants(constants);
		}
		this.matrices.setFx_equations(Fx);
		this.matrices.setJx(Jx);

	}

	protected double[][] GenJacobian(double[][] X, double[][] Fx, boolean[] constants, int totalequations) {
		// Gen Jx Matrix
		double[][] Jx = new double[this.numvariables][totalequations];
		double[][] X_delta = AuxMethods.Copy_matrix(X);

		for (int i = 0; i < numvariables; i++) {
			// iteracion por filas x1, x2 ..xn
			X_delta[i][0] += GlobalConstants.getDelta();

			for (int j = 0; j < numequations; j++) {
				// Calculate parcial derivate of f_j/x_i
				Jx[j][i] = getDifferencial(Fx[j][0], getFx(X_delta, j), GlobalConstants.getDelta());
			}
			X_delta[i][0] = X[i][0];
		}

		// Fix the ecuations generate by contansts.
		boolean encontrado = false;
		int i = 0;
		for (int j = numequations; j < totalequations; j++) {

			encontrado = false;
			while (i < numvariables && !encontrado) {
				if (constants[i]) {
					Jx[j][i] = 1;
					encontrado = true;
				}
				i++;

			}
		}
		
		return(Jx);
	}

	protected double getDifferencial(double fx, double fx_delta, double delta) {

		// Make df = (f(x+delta) - f(x))/delta
		double df = (fx_delta - fx) / delta;

		return (df);
	}

	protected void iteration(double[][] X) {

		for (int i = 0; i < this.numvariables; i++) {

			setvariable(X[i + initnum][0], i);
		}
		genMatrix();

	}

	public void genX() {

		for (int i = 0; i < this.numvariables; i++) {
			this.matrices.getX_equations()[i][0] = getvariable(i);
		}

	}

	public boolean isBlockSimulated() {
		/*
		 * Check if component is defined and if num of varaibles, equations and
		 * constants are enough to solve.
		 */
		boolean simulate = false;
		if (isdefined) {
			if (numvariables == (numequations + numconstants + numrelations)) {
				simulate = true;
				// Put seed value
			}
		}

		return simulate;
	}

	public MatrixCollection Simulate() {

		if (isBlockSimulated()) {
			genMatrix();
		}

		return this.matrices;
	}

	protected void initvalues() {

	}

	public int getType(int num) {

		return (type[num]);

	}

	// ***************************
	// GETs and SETs

	public MatrixCollection getMatrices() {
		return matrices;
	}

	public void setMatrices(MatrixCollection matrices) {

		this.matrices = matrices;
		this.numconstants = matrices.getnumconstants();
		iteration(matrices.getX_equations());
	}

	public short getNumequations() {
		return numequations;
	}

	public void setNumequations(short numequations) {
		this.numequations = numequations;
	}

	public short getInitnum() {
		return initnum;
	}

	public void setInitnum(short initnum) {
		this.initnum = initnum;
		this.endnum = (short) (initnum + numvariables);
	}

	public short getEndnum() {
		return endnum;
	}

	public void setEndnum(short endnum) {
		this.endnum = endnum;
	}

	public short getNumvariables() {
		return numvariables;
	}

	public void setNumvariables(short numvariables) {
		this.numvariables = numvariables;
	}

	public short getNumconstants() {
		return numconstants;
	}

	public void setNumconstants(short numconstants) {
		this.numconstants = numconstants;
	}

	public boolean isIsdefined() {
		return isdefined;
	}

	public void setIsdefined(boolean isdefined) {
		this.isdefined = isdefined;
	}

	public short getNumrelations() {
		return numrelations;
	}

	public void setNumrelations(short numrelations) {
		this.numrelations = numrelations;
	}

	public static class Comparators implements Comparator<SimulationBlock> {

		// Comparator function for order in array
		public int compare(SimulationBlock obj1, SimulationBlock obj2) {

			// check id priority
			if (obj1.getIdnum() > obj1.getIdnum()) {
				return 1;
			} else if (obj1.getIdnum() < obj1.getIdnum()) {
				return -1;
			} else {

				// If there are two identical component order by adding.
				if (obj1.getBlocknumber() > obj1.getBlocknumber()) {
					return 1;
				} else if (obj1.getBlocknumber() < obj1.getBlocknumber()) {
					return -1;
				}
			}

			return 0;

		}

	}

}
