package eetac.model.basicstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eetac.model.AuxMethods;
import eetac.model.MatrixCollection;

public class Engine extends BasicBlock {

	// Blocks
	protected List<SimulationBlock> listblocks;
	protected List<BlockRelations> listrelations;
	// Engine matrix
	protected MatrixCollection matrixJet;

	// Check math dimensions and solve
	protected short numvariables = 0;
	protected short numequations = 0;
	protected short numconstants = 0;
	protected short numrelations = 0;
	protected boolean simulate = false;

	// Contructors
	public Engine() {
		super();
		this.listblocks = new ArrayList<SimulationBlock>();
		this.listrelations = new ArrayList<BlockRelations>();
		this.matrixJet = new MatrixCollection();
	}

	public Engine(Engine a) {
		super();
		this.listblocks = new ArrayList<SimulationBlock>(a.getListblocks());
		this.listrelations = new ArrayList<BlockRelations>(a.getListrelations());
		this.matrixJet = new MatrixCollection(a.getMatrix());
		
		this.numvariables = a.getNumvariables();
		this.numequations = a.getNumequations();
		this.numconstants = a.getNumconstants();
		this.numrelations = a.getNumrelations();
		this.simulate = a.isSimulate();
	}

	// Important methods
	public void addBlock(SimulationBlock a) {

		// add element and reorder
		listblocks.add(a);
		Collections.sort(listblocks, new SimulationBlock.Comparators());
		NumEquaVariConts();

	}

	protected void NumEquaVariConts() {

		// reset values
		this.numvariables = 0;
		this.numequations = 0;
		this.numconstants = 0;
		this.numrelations = 0;

		// calculate total variables, equations, constants and relations.
		for (int i = 0; i < listblocks.size(); i++) {

			// give init num to block
			listblocks.get(i).setInitnum(this.numvariables);
			listblocks.get(i).setBlocknumber((short) i);

			this.numvariables += listblocks.get(i).getNumvariables();
			this.numequations += listblocks.get(i).getNumequations();
			this.numconstants += listblocks.get(i).getNumconstants();
			this.numrelations += listblocks.get(i).getNumrelations();
		}

		// Check if the system can be solve
		if (numvariables == (numequations + numconstants + numrelations))
			simulate = true;
		else
			simulate = false;

	}

	protected void PutComponentsRelations_X() {

		// This function change default values of componente by relations
		for (int i = 0; i < listrelations.size(); i++) {
			listrelations.get(i).UpdateValues();
		}
	}

	protected double[][] PutComponentsRelations_Jx(double[][] Jx) {

		// This function change default values of componente by relations
		for (int i = 0; i < listrelations.size(); i++) {
			listrelations.get(i).UpdateValues();
		}
		return Jx;
	}

	public MatrixCollection getMatrix() {

		return matrixJet;
	}

	protected void BuildMatrix() {

		// check if the engine can be solve
		NumEquaVariConts();

		if (simulate) {
			// include relations in X matrix
			PutComponentsRelations_X();
			double[][] X = new double[this.numvariables][1];
			double[][] Fx = new double[this.numvariables][1];
			double[][] Jx = new double[this.numvariables][this.numvariables];
			String[] variable = new String[this.numvariables];
			boolean[] constants = new boolean[this.numvariables];

			MatrixCollection Block_matrix;
			int init = 0;
			int end = 0;

			// get diferent matrix of Simulationsblock
			for (int i = 0; i < listblocks.size(); i++) {

				// Calculate matrix of block component
				listblocks.get(i).genMatrix();

				// Auxiliary objet-values Block-to-engine
				Block_matrix = listblocks.get(i).getMatrices();
				init = listblocks.get(i).getInitnum();
				end = listblocks.get(i).getEndnum();

				// Get X equations from block
				X = AuxMethods.Inset_in_matrix(X, Block_matrix.getX_equations(), 0, 0, init, end);
				// Get Fx equations from block
				Fx = AuxMethods.Inset_in_matrix(Fx, Block_matrix.getFx_equations(), 0, 0, init, end);
				// Get Jx equations from block
				Jx = AuxMethods.Inset_in_matrix(Jx, Block_matrix.getFx_equations(), init, end, init, end);
				// Get varaible names from block
				variable = AuxMethods.Inset_in_Stringvector(variable, Block_matrix.getVariable(), init, end);
				// Get boolean constants from block
				constants = AuxMethods.Inset_in_Booleanvector(constants, Block_matrix.getConstants(), init, end);

			}

			PutComponentsRelations_Jx(Jx);

		}

	}

	public List<SimulationBlock> getListblocks() {
		return listblocks;
	}

	public void setListblocks(List<SimulationBlock> listblocks) {
		this.listblocks = listblocks;
	}

	public List<BlockRelations> getListrelations() {
		return listrelations;
	}

	public void setListrelations(List<BlockRelations> listrelations) {
		this.listrelations = listrelations;
	}

	public MatrixCollection getMatrixJet() {
		return matrixJet;
	}

	public void setMatrixJet(MatrixCollection matrixJet) {
		this.matrixJet = matrixJet;
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

}
