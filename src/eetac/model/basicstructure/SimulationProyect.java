package eetac.model.basicstructure;

import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;

public class SimulationProyect extends BasicBlock {

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

	public SimulationProyect() {
		this.matrixProyect = new MatrixCollection();
	}

	public SimulationProyect(SimulationProyect a) {
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
		this.numvariables = 0;
		this.numequations = 0;
		this.numconstants = 0;
		this.numrelations = 0;

		// TODO

		// TODO:
		// Check if the system can be solve
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

	}

	public void UpdateMatrixinComponents(double[][] X_new) {

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
