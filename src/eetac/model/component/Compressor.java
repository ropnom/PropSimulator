package eetac.model.component;

import eetac.model.structure.AirPropierties;
import eetac.model.structure.FlowWorkBlock;

public class Compressor extends FlowWorkBlock {

	public Compressor() {
		GenCompressor_info();
	}

	// Introduciomos los datos
	public Compressor(double Pin, double Tin, double MassFlow_in, double Pout, double Tout, double MassFlow_out, double Pi, double Tau, double work, double n_i, double n_p) {

		GenCompressor_info();
	}

	// Descripcion e introduccion interna
	private void GenCompressor_info() {
		this.idnum = 200;
		this.name = "Generic Compressor model 1";
		this.description = "This component is a basic model of compresion with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";
		this.numequations = 6;
		this.numvariables = 11;

		// this.initnum = initnum;
		// this.endnum = endnum;
	}

	// Genermos las matrices.

	@Override
	protected double[] X_equations() {

		double[][] X = new double[this.numvariables][];
		X[0] = this.Pin;
		X[1] = this.Tin;
		X[2] = this.MassFlow_in;
		X[3] = this.Pout;
		X[4] = this.Tout;
		X[5] = this.MassFlow_out;
		X[6] = this.Pi;
		X[7] = this.Tau;
		X[8] = this.work;
		X[9] = this.n_i;
		X[10] = this.n_p;

		return (X);
	}

	@Override
	protected String[] Variables_equations() {

		String[] variable = new String[this.numvariables];
		variable[0] = "P_compresor_in";
		variable[1] = "T_compresor_in";
		variable[2] = "Mass_compresor_in";
		variable[3] = "P_compresor_out";
		variable[4] = "T_compresor_out";
		variable[5] = "Mass_compresor_out";
		variable[6] = "Pressure_ratio";
		variable[7] = "Temperature_ratio";
		variable[8] = "Work_compresor";
		variable[9] = "Issentropic_efficiency";
		variable[10] = "Politropic_efficiency";

		return variable;
	}

	@Override
	protected boolean[] Constants_equations() {

		boolean[] constants = new boolean[this.numvariables];
		constants[0] = false;
		constants[1] = false;
		constants[2] = false;
		constants[3] = false;
		constants[4] = false;
		constants[5] = false;
		constants[6] = false;
		constants[7] = false;
		constants[8] = false;
		constants[9] = false;
		constants[10] = false;

		return constants;
	}

	@Override
	protected double[] Fx_equations(double[] X) {

		double[][] Fx = new double[this.totalequations][];
		Fx[0] = PressureRelations();
		Fx[1] = TemperatureRelations();
		Fx[2] = MassFlowRelations();
		Fx[3] = PolitropicRelations();
		Fx[4] = IsentropicRelations();
		Fx[5] = WorkRelations();

		for (int i = 6; i < (this.totalequations); i++) {
			// If is a known variable, X_i - cte = 0;
			Fx[i] = 0;
		}

		return (Fx);
	}

	@Override
	protected double[][] Jx_equations(double[] X, double[] Fx, boolean[] constants) {

		double[][] Jx = new double[this.numvariables][this.totalequations];

		return Jx;
	}

	// GENERACION DE ECUACIONES
	protected double PressureRelations() {
		/*
		 * PRESSURE RELATION POUT = PINT * PI | Equation 1: Pout-Pin*PI = 0
		 */
		return (this.Pout - this.Pin * this.Pi);
	}

	protected double TemperatureRelations() {
		/*
		 * TEMPERATURE RELATION Tout = Tin * TAU | Equation 2: Tout-Tin*Tau = 0
		 */
		return (this.Tout - this.Tin * this.Tau);
	}

	protected double MassFlowRelations() {
		/*
		 * MASS RELATION MASSin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		return (this.MassFlow_in - this.MassFlow_out);
	}

	protected double PolitropicRelations() {
		/*
		 * Pi-Tau RELATION PI= TAU ^(GAMMA/(GAMA-1)*n_p) | Equation 4: PI - TAU
		 * ^(GAMMA/(GAMA-1)*n_p) = 0
		 */
		return (this.Pi - Math.pow(this.Tau, (AirPropierties.getGamma_politropic_air() * this.n_p)));
	}

	protected double IsentropicRelations() {
		/*
		 * Pi-Tau RELATION PI= (1-n_i*(TAU-1)) ^GAMMA/(GAMA-1) | Equation 5: PI
		 * - (1-n_i*(TAU-1)) ^(GAMMA/(GAMA-1)) = 0
		 */
		return (this.Pi - Math.pow((1 - this.n_i * (this.Tau - 1)), AirPropierties.getGamma_politropic_air()));
	}

	protected double WorkRelations() {
		/*
		 * Work RELATION Work= CP | Equation 6: Work-Massflow*Cp(Tout-Tin) = 0
		 */
		return (this.work - MassFlow_out * AirPropierties.getCp_c() * (this.Tout - this.Tin));
	}

}
