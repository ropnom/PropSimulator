package eetac.model.realcomponent;

import eetac.model.AuxMethods;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.FlowWorkBlock;

public class Compressor extends FlowWorkBlock {

	public Compressor() {
		GenCompressor_info();
	}

	// Introduciomos los datos
	public Compressor(double Pin, double Tin, double MassFlow_in, double Pout, double Tout, double MassFlow_out, double Pi, double Tau, double work, double n_i, double n_p) {

		GenCompressor_info();
	}

	// Descripcion e introduccion interna
	protected void GenCompressor_info() {
		this.idnum = 200;
		this.name = "Generic Compressor model 1";
		this.description = "This component is a basic model of compresion with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";
		this.numequations = 6;
		this.numvariables = 11;
		initvalues();
		genMatrix();

		// this.initnum = initnum;
		// this.endnum = initnum + numvaribles;
	}

	@Override
	protected void initvalues() {
		
		this.Pin = 95000;
		this.Tin = 290;		
		this.MassFlow_in = 1000;
		
		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out= 1000;
		
		this.Pi = 18;
		this.Tau=2.7;
		this.work = 450000000;	
		this.n_i = 0.8;
		this.n_p = 0.88;
		

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
			variable = this.Pi;
			break;
		case 8:
			variable = this.Tau;
			break;
		case 9:
			variable = work;
			break;
		case 10:
			variable = this.n_i;
			break;
		case 11:
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
			this.Pi = variable;
			break;
		case 8:
			this.Tau = variable;
			break;
		case 9:
			work = variable;
			break;
		case 10:
			this.n_i = variable;
			break;
		case 11:
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

	@Override
	protected void genMatrix() {
		// Gen variable names
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
		

		// GEN X vecto
		double[][] X = new double[this.numvariables][1];
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
		

		if (isdefined) {

			int totalequations = numequations + numconstants;
			// Gen Fx vector
			double[][] Fx = new double[totalequations][1];
			// Fx[0][0] = PressureRelations(X);
			// Fx[1][0] = TemperatureRelations(X);
			// Fx[2][0] = MassFlowRelations(X);
			// Fx[3][0] = PolitropicRelations(X);
			// Fx[4][0] = IsentropicRelations(X);
			// Fx[5][0] = WorkRelations(X);

			for (int i = 0; i < (totalequations); i++) {
				// If is a known variable, X_i - cte = 0;
				if (i < numequations) {
					Fx[i][0] = getFx(X, i);
				} else {
					Fx[i][0] = 0;
				}

			}

			// Gen Jx Matrix
			double[][] Jx = new double[this.numvariables][totalequations];
			double[][] X_delta = AuxMethods.Copy_matrix(X);

			for (int i = 0; i < numvariables; i++) {
				// iteracion por filas x1, x2 ..xn
				X_delta[i][0] += GlobalConstants.getDelta();

				for (int j = 0; j < totalequations; j++) {

					if (j < numequations) {
						// Calculate parcial derivate of f_j/x_i
						Jx[j][i] = getDifferencial(Fx[j][0], getFx(X_delta, j), GlobalConstants.getDelta());

					} else {
						// Constantes X_k - cte =0
						// derivada 0, salvo sobre si mismas que es 1
						Jx[j][i] = 0;

					}
				}
				X_delta[i][0] = X[i][0];
			}

			this.matrices.setFx_equations(Fx);
			this.matrices.setJx(Jx);

		} else {
			// Gen boolean constant know for init objet
			boolean[] constants = new boolean[this.numvariables];
			for (int l = 0; l < numvariables; l++) {
				constants[l] = false;
			}

			this.matrices.setConstants(constants);
		}

	}

	@Override
	public MatrixCollection Simulate() {

		if (isBlockSimulated()) {
			genMatrix();
		}

		return this.matrices;
	}


	@Override
	protected void iteration(double[][] X) {

		// Actulizar matriz X y re iterar
		this.Pin = X[0][0];
		this.Tin = X[1][0];
		this.MassFlow_in = X[2][0];
		this.Pout = X[3][0];
		this.Tout = X[4][0];
		this.MassFlow_out = X[5][0];
		this.Pi = X[6][0];
		this.Tau = X[7][0];
		this.work = X[8][0];
		this.n_i = X[9][0];
		this.n_p = X[10][0];

		genMatrix();

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
		
		
		return (X[6][0] - Math.pow(X[7][0], (AirPropierties.getGamma_politropic_air() * X[10][0])));
	}

	protected double IsentropicRelations(double[][] X) {
		/*
		 * Pi-Tau RELATION PI= (1+n_i*(TAU-1)) ^GAMMA/(GAMA-1) | Equation 5: PI
		 * - (1+n_i*(TAU-1)) ^(GAMMA/(GAMA-1)) = 0
		 */
		// return (this.Tau - Math.pow((1 + this.n_i * (this.Tau - 1)),
		// AirPropierties.getGamma_politropic_air()));

		
		return (X[6][0] - Math.pow((1 + X[9][0] * (X[7][0] - 1)), AirPropierties.getGamma_politropic_air()));
	}

	protected double WorkRelations(double[][] X) {
		/*
		 * Work RELATION Work= CP | Equation 6: Work-Massflow*Cp(Tout-Tin) = 0
		 */
		return (X[8][0] - X[5][0] * AirPropierties.getCp_c() * (X[4][0] - X[1][0]));
	}

}