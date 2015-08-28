package eetac.model.realcomponent;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.basicstructure.DinamicFlowBlock;
import eetac.model.propierties.AirPropierties;

public class Difusser extends DinamicFlowBlock {

	// Aux variables
	protected double sound_velocity;
	protected double mach_number;

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
	// this.velocity = X[6][0];

	// Init basic information od basicblock, description reference etc...
	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getDifusser() + 1);
		this.level = 1;
		this.name = "Generic Diffuser model  " + this.level;
		this.description = "This component is a basic model of diffuser with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 3;
		this.numvariables = 7;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_" + this.blocknumber + "_diffuser_in";
		variable[1] = "T_" + this.blocknumber + "_diffuser_in";
		variable[2] = "Mass_" + this.blocknumber + "_diffuser_in";
		variable[3] = "P_" + this.blocknumber + "_diffuser_out";
		variable[4] = "T_" + this.blocknumber + "_diffuser_out";
		variable[5] = "Mass_" + this.blocknumber + "_diffuser_out";
		variable[6] = "Velocity_" + this.blocknumber + "_diffuser_in";

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Pin;
		X[1][0] = this.Tin;
		X[2][0] = this.MassFlow_in;
		X[3][0] = this.Pout;
		X[4][0] = this.Tout;
		X[5][0] = this.MassFlow_out;
		X[6][0] = this.velocity;

		GenAuxvariables();

		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	public void GenAuxvariables() {
		this.sound_velocity = Math.sqrt(AirPropierties.getGamma_c_air() * AirPropierties.getR() * this.Tin);
		this.mach_number = this.velocity / this.sound_velocity;
	}

	@Override
	protected void initvalues() {

		// TODO: init values
		this.Pin = 22657;
		this.Tin = 216;
		this.MassFlow_in = 1000;
		this.velocity = 1105.11357;

		this.Pout = 120000;
		this.Tout = 390;
		this.MassFlow_out = 1000;

	}

	@Override
	protected double PressureRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * PI | Equation 1: Pout-Pin*PI = 0
		 */
		// return (this.Pout - this.Pin * this.Pi);
		double estancamiento = (1+(AirPropierties.getGamma_c_air()-1)/2*(this.velocity/Math.sqrt(AirPropierties.getGamma_c_air()*AirPropierties.getR()*this.Tin)));
		
		return (X[3][0] - X[0][0] * Math.pow(estancamiento, AirPropierties.getGamma_1_gama_air()));
	}

	@Override
	protected double TemperatureRelations(double[][] X) {
		/*
		 * TEMPERATURE RELATION Tout = Tin * TAU | Equation 2: Tout-Tin*Tau = 0
		 */
		// return (this.Tout - this.Tin * this.Tau);
		return (X[4][0] - X[1][0] * X[7][0]);
	}

	@Override
	protected double MassFlowRelations(double[][] X) {
		/*
		 * MASS RELATION MASSin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		// return (this.MassFlow_in - this.MassFlow_out);
		return (X[2][0] - X[5][0]);
	}

	@Override
	protected double VelocityRelations(double[][] X) {
		return (X[2][0] - X[5][0]);
	}

}
