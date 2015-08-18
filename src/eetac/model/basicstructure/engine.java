package eetac.model.basicstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eetac.model.AuxMethods;
import eetac.model.MatrixCollection;

public class engine extends BasicBlock {

	//Blocks
	protected List<SimulationBlock> listblocks;
	// Engine matrix
	protected MatrixCollection matrixJet;
	
	
	//Check math dimensions and solve
	protected short numvariables = 0;
	protected short numequations = 0;
	protected short numconstants = 0;
	protected short numrelations = 0;
	protected boolean simulate = false;

	//Contructors
	public engine() {
		super();
		this.listblocks = new ArrayList<SimulationBlock>();
		this.matrixJet = new MatrixCollection();
	}

	
	//Important methods
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
			
			//give init num to block
			listblocks.get(i).setInitnum(this.numvariables);
			
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

	public MatrixCollection getMathMatrix() {

		NumEquaVariConts();
		if (simulate) {
			double[][] X= new double[this.numvariables][1];
			double[][] Fx = new double[this.numvariables][1];
			double[][] Jx = new double[this.numvariables][this.numvariables];
			
			MatrixCollection Block_matrix;
			int init = 0;
			int end = 0;
			
			// get diferent matrix of Simulationsblock
			for (int i = 0; i < listblocks.size(); i++) {

				Block_matrix = listblocks.get(i).getMatrices();
				init = listblocks.get(i).getInitnum();
				end = listblocks.get(i).getEndnum();
				
				// Get X equations
				X = AuxMethods.Inset_in_matrix(X, Block_matrix.getX_equations(), 0, 0, init, end);
				// Get Fx equations
				Fx = AuxMethods.Inset_in_matrix(Fx, Block_matrix.getFx_equations(), 0, 0, init, end);
				// Get Jx equations
				Jx = AuxMethods.Inset_in_matrix(Jx, Block_matrix.getFx_equations(), init, end, init, end);

			}
			

			return matrixJet;
		}
		else
			return null;

	}
}
