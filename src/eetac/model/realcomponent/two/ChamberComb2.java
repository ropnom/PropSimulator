package eetac.model.realcomponent.two;

import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.CombustionFlowBlock;
import eetac.model.basicstructure.FlowWorkBlock;

public class ChamberComb2 extends CombustionFlowBlock {

	public ChamberComb2() {
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
	// this.Massfuel = X[6][0];
	// this.n_fuel = X[7][0];
	// this.E_b = X[8][0];
	
	// Init basic information od basicblock, description reference etc...
	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getCombustionchamber() + 1) ;
		this.level = 1;
		this.name = "Generic Combustion Chamber model  "+this.level;
		this.description = "This component is a basic model of Combustion Chamber with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 3;
		this.numvariables = 9;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_" + this.blocknumber + "_combustionchamber_in";
		variable[1] = "T_" + this.blocknumber + "_combustionchamber_in";
		variable[2] = "Mass_" + this.blocknumber + "_combustionchamber_in";
		variable[3] = "P_" + this.blocknumber + "_combustionchamber_out";
		variable[4] = "T_" + this.blocknumber + "_combustionchamber_out";
		variable[5] = "Mass_" + this.blocknumber + "_combustionchamber_out";
		variable[6] = "Massfuel" + this.blocknumber + "_combustionchamber";
		variable[7] = "EfficiencyFuel" + this.blocknumber + "_combustionchamber";
		variable[8] = "CombustionEfficiency_" + this.blocknumber + "_combustionchamber";

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Pin;
		X[1][0] = this.Tin;
		X[2][0] = this.MassFlow_in;
		X[3][0] = this.Pout;
		X[4][0] = this.Tout;
		X[5][0] = this.MassFlow_out;
		X[6][0] = this.Massfuel;
		X[7][0] = this.n_fuel;
		X[8][0] = this.E_b;

		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	protected void initvalues() {

		// TODO: Miquel poner aqui valores del excel
		this.Pin = 95000;
		this.Tin = 290;
		this.MassFlow_in = 1000;

		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out = 1000;

		this.Massfuel = 1000;
		this.n_fuel = 0.9;
		this.E_b = 0.95;

	}


}
