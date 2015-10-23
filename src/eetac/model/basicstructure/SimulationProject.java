package eetac.model.basicstructure;

import java.io.Serializable;

import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;

public class SimulationProject extends BasicBlock implements Serializable {

	// Engine matrix
	protected MatrixCollection matrixProyect;

	// Check math dimensions and solve
	protected short numvariables = 0;
	protected short numequations = 0;
	protected short numconstants = 0;
	protected short numrelations = 0;
	protected boolean simulate = false;

	// cosas
	private SimulationBlock block;
	
	public SimulationProject(SimulationBlock b) {
		this.matrixProyect = new MatrixCollection();
		this.block = b;
	}

	public SimulationProject() {
		this.matrixProyect = new MatrixCollection();
	}

	public SimulationProject(SimulationProject a) {
		super(a);
		this.matrixProyect = new MatrixCollection(a.getMatrixProyect());

		this.numvariables = a.getNumvariables();
		this.numequations = a.getNumequations();
		this.numconstants = a.getNumconstants();
		this.numrelations = a.getNumrelations();
		this.simulate = a.isSimulate();

	}

	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getEngine() + 1);
		;
		this.name = "Generic Simulator Project 1";
		this.description = "This a generic simulator block without restrictions";
		this.reference = "Teorical Reference Termodinamics";
	}

	protected void NumEquaVariConts() {

		// reset values
		this.numvariables = block.getNumvariables();
		this.numequations = block.getNumequations();
		this.numconstants = block.getNumconstants();
		this.numrelations = 0;

		if (numvariables == (numequations + numconstants + numrelations))
			simulate = true;
		else
			simulate = false;

	}

	public void BuildMatrix() {

		// check if the engine can be solve
		NumEquaVariConts();

		if (simulate) {
			// recal all matrix
			iteration();
		}

	}

	public void iteration() {

		// include relations in X matrix
		double[][] X = new double[this.numvariables][1];
		double[][] Fx = new double[this.numvariables][1];
		double[][] Jx = new double[this.numvariables][this.numvariables];
		String[] variable = new String[this.numvariables];
		boolean[] constants = new boolean[this.numvariables];

		// Calculate matrix of block component
		this.block.genMatrix();

		// Auxiliary objet-values Block-to-engine
		this.block.getMatrices();

		// Get X equations from block
		X = this.block.getMatrices().getX_equations();
		// Get Fx equations from block
		Fx = this.block.getMatrices().getFx_equations();
		// Get Jx equations from block
		Jx = this.block.getMatrices().getJx();
		// Get varaible names from block
		variable = this.block.getMatrices().getVariable();
		// Get boolean constants from block
		constants = this.block.getMatrices().getConstants();

		this.matrixProyect.setX_equations(X);
		this.matrixProyect.setFx_equations(Fx);
		this.matrixProyect.setJx(Jx);
		this.matrixProyect.setVariable(variable);
		this.matrixProyect.setConstants(constants);

	}

	public void UpdateMatrixinComponents(double[][] X_new) {

		this.block.iteration(X_new);
		iteration();

	}

	public MatrixCollection getMatrixProyect() {
		return matrixProyect;
	}

	public void setMatrixProyect(MatrixCollection matrixProyect) {
		this.matrixProyect = matrixProyect;
		UpdateMatrixinComponents(matrixProyect.getX_equations());
	}

	public short getNumvariables() {
		return numvariables;
	}

	public void setNumvariables(short numvariables) {
		this.numvariables = numvariables;
	}

	public short getNumequations() {
		return numequations;
	}

	public void setNumequations(short numequations) {
		this.numequations = numequations;
	}

	public short getNumconstants() {
		return numconstants;
	}

	public void setNumconstants(short numconstants) {
		this.numconstants = numconstants;
	}

	public short getNumrelations() {
		return numrelations;
	}

	public void setNumrelations(short numrelations) {
		this.numrelations = numrelations;
	}

	public boolean isSimulate() {
		return simulate;
	}

	public void setSimulate(boolean simulate) {
		this.simulate = simulate;
	}

	public SimulationBlock getBlock() {
		return block;
	}

	public void setBlock(SimulationBlock block) {
		this.block = block;
	}

}
