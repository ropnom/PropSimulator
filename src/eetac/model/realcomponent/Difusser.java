package eetac.model.realcomponent;

import eetac.model.GlobalConstants;
import eetac.model.basicstructure.DinamicFlowBlock;

public class Difusser extends DinamicFlowBlock {

	public Difusser() {
		super();
		Gen_info();
	}

	// Order of variable in vector
	// this.Pin = X[0][0];
	// this.Tin = X[1][0];
	// this.MassFlow_in = X[2][0];
	// this.Pout = X[3][0];
	// this.Tout = X[4][0];
	// this.MassFlow_out = X[5][0];
	// this.Pi = X[6][0];
	// this.Tau = X[7][0];
	// this.work = X[8][0];
	// this.n_i = X[9][0];
	// this.n_p = X[10][0];

	// Init basic information od basicblock, description reference etc...
	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getCompresor()+1);
		this.level = 1;
		this.name = "Generic Diffuser model  "+this.level;
		this.description = "This component is a basic model of diffuser with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 6;
		this.numvariables = 11;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_"+this.blocknumber+"_diffuser_in";
		variable[1] = "T_"+this.blocknumber+"_diffuser_in";
		variable[2] = "Mass_"+this.blocknumber+"_diffuser_in";
		variable[3] = "P_"+this.blocknumber+"_diffuser_out";
		variable[4] = "T_"+this.blocknumber+"_diffuser_out";
		variable[5] = "Mass_"+this.blocknumber+"_diffuser_out";
		

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Pin;
		X[1][0] = this.Tin;
		X[2][0] = this.MassFlow_in;
		X[3][0] = this.Pout;
		X[4][0] = this.Tout;
		X[5][0] = this.MassFlow_out;
		
		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	protected void initvalues() {

		this.Pin = 95000;
		this.Tin = 290;
		this.MassFlow_in = 1000;

		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out = 1000;


	}


}
