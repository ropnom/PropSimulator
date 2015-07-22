package eetac.model.structure;

import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;

public class SimulationBlock extends BasicBlock {

	// math internal matrix
	protected MatrixCollection matrices = new MatrixCollection();
	protected double epsilon = 0.001;
	protected short numequations = 0;
	protected short numvariables = -1;
	protected short numconstants = -1;
	protected short totalequations = -1;
	protected short initnum = -1;
	protected short endnum = -1;
	protected boolean isdefined = false;

	public SimulationBlock() {

	}

	public SimulationBlock(MatrixCollection matrices, double epsilon, short numequations, short initnum, short endnum) {
		super();
		this.matrices = matrices;
		this.epsilon = epsilon;
		this.numequations = numequations;
		this.initnum = initnum;
		this.endnum = endnum;
	}

	public SimulationBlock(short idnum, String name, String description, String reference) {
		super(idnum, name, description, reference);
		// TODO Auto-generated constructor stub
	}

	// GEneral Methods

	protected double getDifferencial(double fx, double fx_delta, double delta) {

		// Make df = (f(x+delta) - f(x))/delta
		double df = (fx_delta - fx) / delta;

		if (Math.abs(df) < GlobalConstants.getDerivate_min())
			return 0;
		else
			return (df);
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

	public boolean isBlockSimulated() {
		/*
		 * Check if component is defined and if num of varaibles, equations and constants are enough to solve.
		 */
		boolean simulate = false;
		if (isdefined) {
			if (numvariables == totalequations && totalequations == (numequations + numconstants)) {
				simulate = true;
			}
		}

		return simulate;
	}
	
	public MatrixCollection Simulate(){
		//crate matrix
		
		return null;
	}

	// GETs and SETs

	public MatrixCollection getMatrices() {
		return matrices;
	}

	public void setMatrices(MatrixCollection matrices) {
		this.matrices = matrices;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
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

	public short getTotalequations() {
		return totalequations;
	}

	public void setTotalequations(short totalequations) {
		this.totalequations = totalequations;
	}

	public boolean isIsdefined() {
		return isdefined;
	}

	public void setIsdefined(boolean isdefined) {
		this.isdefined = isdefined;
	}

}
