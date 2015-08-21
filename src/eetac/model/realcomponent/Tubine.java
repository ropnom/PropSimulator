package eetac.model.realcomponent;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.FlowWorkBlock;

public class Tubine extends FlowWorkBlock {

	public Tubine() {
		super();
		GenCompressor_info();
	}

	// Introduciomos los datos

	// Descripcion e introduccion interna
	protected void GenCompressor_info() {

		this.idnum = (short) (GlobalConstants.getTurbine() + 1) ;
		this.name = "Generic Turbine model 1";
		this.description = "This component is a basic model of turbine with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 6;
		this.numvariables = 11;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_"+this.blocknumber+"_turbine_in";
		variable[1] = "T_"+this.blocknumber+"_turbine_in";
		variable[2] = "Mass_"+this.blocknumber+"_turbine_in";
		variable[3] = "P_"+this.blocknumber+"_turbine_out";
		variable[4] = "T_"+this.blocknumber+"_turbine_out";
		variable[5] = "Mass_"+this.blocknumber+"_turbine_out";
		variable[6] = "Pressure_ratio"+this.blocknumber+"_turbine";
		variable[7] = "Temperature_ratio"+this.blocknumber+"_turbine";
		variable[8] = "Work_"+this.blocknumber+"_compresor";
		variable[9] = "Issentropic_efficiency_"+this.blocknumber+"_turbine";
		variable[10] = "Politropic_efficiency_"+this.blocknumber+"_turbine";

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

	// Sobreescrivimos funciones

	@Override
	protected double getvariable(int index) {
		double variable = 0;

		switch (index) {
		case 0:
			variable = this.Pin;
			break;
		case 1:
			variable = this.Tin;
			break;
		case 2:
			variable = this.MassFlow_in;
			break;
		case 3:
			variable = this.Pout;
			break;
		case 4:
			variable = this.Tout;
			break;
		case 5:
			variable = this.MassFlow_out;
			break;
		case 6:
			variable = this.Pi;
			break;
		case 7:
			variable = this.Tau;
			break;
		case 8:
			variable = work;
			break;
		case 9:
			variable = this.n_i;
			break;
		case 10:
			variable = this.n_p;
			break;

		default:
			break;
		}

		return variable;
	}

	@Override
	protected void setvariable(double variable, int index) {

		switch (index) {
		case 0:
			this.Pin = variable;
			break;
		case 1:
			this.Tin = variable;
			break;
		case 2:
			this.MassFlow_in = variable;
			break;
		case 3:
			this.Pout = variable;
			break;
		case 4:
			this.Tout = variable;
			break;
		case 5:
			this.MassFlow_out = variable;
			break;
		case 6:
			this.Pi = variable;
			break;
		case 7:
			this.Tau = variable;
			break;
		case 8:
			work = variable;
			break;
		case 9:
			this.n_i = variable;
			break;
		case 10:
			this.n_p = variable;
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
			fx = PolitropicRelations(X);
			break;
		case 4:
			fx = IsentropicRelations(X);
			break;
		case 5:
			fx = WorkRelations(X);
			break;

		default:
			break;
		}
		return fx;
	}


	// GENERACION DE ECUACIONES

	protected double PressureRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * PI | Equation 1: Pout-Pin*PI = 0
		 */
		// return (this.Pout - this.Pin * this.Pi);
		return (X[3][0] - X[0][0] * X[6][0]);
	}

	protected double TemperatureRelations(double[][] X) {
		/*
		 * TEMPERATURE RELATION Tout = Tin * TAU | Equation 2: Tout-Tin*Tau = 0
		 */
		// return (this.Tout - this.Tin * this.Tau);
		return (X[4][0] - X[1][0] * X[7][0]);
	}

	protected double MassFlowRelations(double[][] X) {
		/*
		 * MASS RELATION MASSin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		// return (this.MassFlow_in - this.MassFlow_out);
		return (X[2][0] - X[5][0]);
	}

	protected double PolitropicRelations(double[][] X) {
		/*
		 * Pi-Tau RELATION TAU= PI ^(GAMMA/(GAMA-1)*n_p) | Equation 4: TAU - PI
		 * ^(GAMMA/((GAMA-1)*1/n_p)) = 0
		 */
		// return (this.Pi - Math.pow(this.Tau,
		// (AirPropierties.getGamma_politropic_air() * this.n_p)));

		return (X[6][0] - Math.pow(X[7][0], (AirPropierties.getGamma_1_gama_air() * X[10][0])));
	}

	protected double IsentropicRelations(double[][] X) {
		/*
		 * Pi-Tau RELATION PI= (1+n_i*(TAU-1)) ^GAMMA/(GAMA-1) | Equation 5: PI
		 * - (1+n_i*(TAU-1)) ^(GAMMA/(GAMA-1)) = 0
		 */
		// return (this.PI - Math.pow((1 + this.n_i * (this.Tau - 1)),
		// AirPropierties.getGamma_politropic_air()));

		return (X[6][0] - Math.pow((1 + X[9][0] * (X[7][0] - 1)), AirPropierties.getGamma_1_gama_air()));
	}

	protected double WorkRelations(double[][] X) {
		/*
		 * Work RELATION Work= CP | Equation 6: Work-Massflow*Cp(Tin-Tout) = 0
		 */
		if (X[2][0] >= X[5][0]) {

			return (X[8][0] - X[2][0] * AirPropierties.getCp_c() * (X[1][0] - X[4][0]));
		} else {
			return (X[8][0] - X[5][0] * AirPropierties.getCp_c() * (X[1][0] - X[4][0]));
		}
	}

}
