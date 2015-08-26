package eetac.model.realcomponent;

import eetac.model.GlobalConstants;
import eetac.model.basicstructure.FlowWorkBlock;
import eetac.model.propierties.AirPropierties;

public class Tubine extends FlowWorkBlock {

	public Tubine() {
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
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getTurbine() + 1);
		this.level = 1;
		this.name = "Generic Turbine model  "+this.level;
		this.description = "This component is a basic model of turbine with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 6;
		this.numvariables = 11;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_" + this.blocknumber + "_turbine_in";
		variable[1] = "T_" + this.blocknumber + "_turbine_in";
		variable[2] = "Mass_" + this.blocknumber + "_turbine_in";
		variable[3] = "P_" + this.blocknumber + "_turbine_out";
		variable[4] = "T_" + this.blocknumber + "_turbine_out";
		variable[5] = "Mass_" + this.blocknumber + "_turbine_out";
		variable[6] = "Pressure_ratio" + this.blocknumber + "_turbine";
		variable[7] = "Temperature_ratio" + this.blocknumber + "_turbine";
		variable[8] = "Work_" + this.blocknumber + "_compresor";
		variable[9] = "Issentropic_efficiency_" + this.blocknumber + "_turbine";
		variable[10] = "Politropic_efficiency_" + this.blocknumber + "_turbine";

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Pin;
		X[1][0] = this.Tin;
		X[2][0] = this.MassFlow_in;
		X[3][0] = this.Pout;
		X[4][0] = this.Tout;
		X[5][0] = this.MassFlow_out;
		X[6][0] = this.Pi;
		X[7][0] = this.Tau;
		X[8][0] = this.work;
		X[9][0] = this.n_i;
		X[10][0] = this.n_p;

		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	protected void initvalues() {

		// TODO: MIQUEL PONER AQUI EJEMPLOS EXCEL
		this.Pin = 95000;
		this.Tin = 290;
		this.MassFlow_in = 1000;

		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out = 1000;

		this.Pi = 18;
		this.Tau = 2.7;
		this.work = -450000000;
		this.n_i = 0.8;
		this.n_p = 0.88;

	}

	// A turbine works equal toa compresor because is a descompresor.
	// Efficiencia is the inverse of the compressor ... so we need to overray equations
	
	@Override
	protected double PolitropicRelations(double[][] X) {
		/*
		 * Pi-Tau RELATION TAU= PI ^(GAMMA/(GAMA-1)*n_p) | Equation 4: TAU - PI
		 * ^(GAMMA/((GAMA-1)*1/n_p)) = 0
		 */
		// return (this.Pi - Math.pow(this.Tau,
		// (AirPropierties.getGamma_politropic_air() / this.n_p)));

		return (X[6][0] - Math.pow(X[7][0], (AirPropierties.getGamma_1_gama_air() / X[10][0])));
	}

	@Override
	protected double IsentropicRelations(double[][] X) {
		/*
		 * Pi-Tau RELATION PI= (1+n_i*(TAU-1)) ^GAMMA/(GAMA-1) | Equation 5: PI
		 * - (1+n_i*(TAU-1)) ^(GAMMA/(GAMA-1)) = 0
		 */
		// return (this.PI - Math.pow((1 + (this.Tau - 1)/this.n_i ),
		// AirPropierties.getGamma_politropic_air()));

		return (X[6][0] - Math.pow((1 + (X[7][0] - 1)/X[9][0] ), AirPropierties.getGamma_1_gama_air()));
	}


}
