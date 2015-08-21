package eetac.model.realcomponent;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.FlowBlock;

public class CombustionChamber extends FlowBlock {

	protected double Massfuel;
	protected double n_fuel;
	protected double E_b;

	public CombustionChamber() {
		super();
		Gen_info();
	}

	// Introduciomos los datos

	// Descripcion e introduccion interna
	@Override
	protected void Gen_info() {

		this.idnum = 500;
		this.name = "Generic Combustion Chamber model 1";
		this.description = "This component is a basic model of Combustion Chamber with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 4;
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

		this.Pin = 95000;
		this.Tin = 290;
		this.MassFlow_in = 1000;

		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out = 1000;

		// Propias

	}

	// Sobreescrivimos funciones

	@Override
	protected double getvariable(int index) {
		double variable = 0;

		switch (index) {
		case 1:
			variable = this.Pin;
			break;
		case 2:
			variable = this.Tin;
			break;
		case 3:
			variable = this.MassFlow_in;
			break;
		case 4:
			variable = this.Pout;
			break;
		case 5:
			variable = this.Tout;
			break;
		case 6:
			variable = this.MassFlow_out;
			break;
		case 7:
			variable = this.Massfuel;
			break;
		case 8:
			variable = this.n_fuel;
			break;
		case 9:
			variable = E_b;
			break;

		default:
			break;
		}

		return variable;
	}

	@Override
	protected void setvariable(double variable, int index) {

		switch (index) {
		case 1:
			this.Pin = variable;
			break;
		case 2:
			this.Tin = variable;
			break;
		case 3:
			this.MassFlow_in = variable;
			break;
		case 4:
			this.Pout = variable;
			break;
		case 5:
			this.Tout = variable;
			break;
		case 6:
			this.MassFlow_out = variable;
			break;
		case 7:
			this.Massfuel = variable;
			break;
		case 8:
			this.n_fuel = variable;
			break;
		case 9:
			E_b = variable;
			break;

		default:
			break;
		}
	}

	@Override
	protected double getFx(double[][] X, int equation) {
		double fx = 0;

		switch (equation) {
		case 0:
			fx = PressureRelations(X);
			break;
		case 1:
			fx = TemperatureRelations(X);
			break;
		case 2:
			fx = MassFlowRelations(X);
			break;
		case 3:
			fx = HeatRelations(X);
			break;
		
		default:
			break;
		}
		return fx;
	}

	// GENERACION DE ECUACIONES

	protected double PressureRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * PI | Equation 1: Pout-Pin*(1-eb) = 0
		 */
		// return (this.Pout - this.Pin * (1-eb));
		return (X[3][0] - X[0][0] * (1 - X[8][0]));
	}

	protected double TemperatureRelations(double[][] X) {
		/*
		 * TEMPERATURE RELATION Tout = Tin * TAU | Equation 2: Tout-Tin*Tau = 0
		 */
		// return (this.Tout - this.Tin * this.Tau);
		return (X[4][0] - (X[1][0] + (X[7][0] * (X[5][0] / X[2][0] - 1) * 55555)) / 1005);
	}

	protected double MassFlowRelations(double[][] X) {
		/*
		 * MASS RELATION MASSin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		// return (this.MassFlow_in - this.MassFlow_out);
		return (X[2][0] + X[6][0] - (X[5][0]));
	}


	protected double HeatRelations(double[][] X) {
		/*
		 * HEAT RELATION Work= CP | Equation 6: Work-Massflow*Cp(Tin-Tout) = 0
		 */
		if (X[2][0] >= X[5][0]) {

			return (X[8][0] - X[2][0] * AirPropierties.getCp_c() * (X[1][0] - X[4][0]));
		} else {
			return (X[8][0] - X[5][0] * AirPropierties.getCp_c() * (X[1][0] - X[4][0]));
		}
	}

}
