package eetac.model.basicstructure;

import java.util.Comparator;

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
	protected short initnum = -1;
	protected short endnum = -1;

	// Is this block full defined?
	protected boolean isdefined = false;

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

	public SimulationBlock(short idnum, String name, String description, String reference) {
		super(idnum, name, description, reference);
		// TODO Auto-generated constructor stub
	}

	// copy contructor
	public SimulationBlock(SimulationBlock a) {
		super(a);
		this.matrices = new MatrixCollection(a.getMatrices());
		this.numequations = a.getNumequations();
		this.initnum = a.getInitnum();
		this.endnum = a.getEndnum();

	}

	// GEneral Methods
	protected double getvariable(int index) {

		double variable = 0;
		// switch
		return variable;
	}

	protected void setvariable(double variable, int index) {

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

		// Generar matriz de X
		// double[][] X = null;
		// String[] variable = null;
		// boolean[] constants = null;
		// double[][] Fx = null;
		// double[][] Jx_equations = null;

	}

	protected double getDifferencial(double fx, double fx_delta, double delta) {

		// Make df = (f(x+delta) - f(x))/delta
		double df = (fx_delta - fx) / delta;

		return (df);
	}

	protected void iteration(double[][] X) {

		// Actulizar matriz X y re iterar

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
		// crate matrix

		return null;
	}

	protected void initvalues() {

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
