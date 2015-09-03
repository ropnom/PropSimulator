package eetac.model.basicstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;

public class Engine extends SimulationProject {

	// Blocks
	protected List<SimulationBlock> listblocks;
	protected List<BlockRelations> listrelations;
	

	// Contructors
	public Engine() {
		super();
		this.listblocks = new ArrayList<SimulationBlock>();
		this.listrelations = new ArrayList<BlockRelations>();
		this.matrixProyect = new MatrixCollection();
	}

	public Engine(Engine a) {
		super(a);
		this.listblocks = new ArrayList<SimulationBlock>(a.getListblocks());
		this.listrelations = new ArrayList<BlockRelations>(a.getListrelations());
		
	}

	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getEngine() + 1);
		;
		this.name = "Generic Engine 1";
		this.description = "This a generic engine without restrictions";
		this.reference = "Teorical Reference Termodinamics";
	}

	// Important methods
	public void addBlock(SimulationBlock a) {

		// add element and reorder
		listblocks.add(a);
		Collections.sort(listblocks, new SimulationBlock.Comparators());
		NumEquaVariConts();

	}

	public void deleteBlock() {

		// To Do
	}

	@Override
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

		if (listrelations.size() > 0 && listblocks.size() > 1) {
			// This function change default values of componente by relations
			for (int i = 0; i < listrelations.size(); i++) {
				listrelations.get(i).UpdateValues();
			}
		}
	}

	
	protected double[][] PutComponentsRelations_Jx(double[][] Jx) {

		if (listrelations.size() > 0 && listblocks.size() > 1) {
			// This function change default values of componente by relations
			for (int i = 0; i < listrelations.size(); i++) {
				listrelations.get(i).UpdateValues();
			}
		}
		return Jx;
	}


	@Override
	public void iteration() {

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
			X = AuxMethods.Inset_in_matrix(X, Block_matrix.getX_equations(), 0, 1, init, end);
			// Get Fx equations from block
			Fx = AuxMethods.Inset_in_matrix(Fx, Block_matrix.getFx_equations(), 0, 1, init, end);
			// Get Jx equations from block
			Jx = AuxMethods.Inset_in_matrix(Jx, Block_matrix.getJx(), init, end, init, end);
			// Get varaible names from block
			variable = AuxMethods.Inset_in_Stringvector(variable, Block_matrix.getVariable(), init, end);
			// Get boolean constants from block
			constants = AuxMethods.Inset_in_Booleanvector(constants, Block_matrix.getConstants(), init, end);

		}

		Jx = PutComponentsRelations_Jx(Jx);

		this.matrixProyect.setX_equations(X);
		this.matrixProyect.setFx_equations(Fx);
		this.matrixProyect.setJx(Jx);
		this.matrixProyect.setVariable(variable);
		this.matrixProyect.setConstants(constants);

	}

	@Override
	public void UpdateMatrixinComponents(double[][] X_new) {

		// get diferent matrix of Simulationsblock
		for (int i = 0; i < listblocks.size(); i++) {
			// Get X equations from block
			listblocks.get(i).iteration(X_new);
		}
		iteration();

	}

	public void PrintMatrix() {

		System.out.println(" X matrix:");
		System.out.println();
		for (int i = 0; i < matrixProyect.getX_equations().length; i++) {
			System.out.println("| " + matrixProyect.getX_equations()[i][0] + " |");
		}
		System.out.println();
		System.out.println(" Fx: ");
		System.out.println();
		for (int i = 0; i < matrixProyect.getFx_equations().length; i++) {
			System.out.println("| " + matrixProyect.getFx_equations()[i][0] + " |");
		}
		System.out.println();
		System.out.println(" Jx: ");
		System.out.println();
		for (int i = 0; i < matrixProyect.getJx().length; i++) {
			System.out.print("| ");
			for (int j = 0; j < matrixProyect.getJx()[i].length; j++) {
				System.out.print(matrixProyect.getJx()[i][j] + " ");
			}
			System.out.println(" |");
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
	
	
	



}
