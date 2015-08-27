package eetac.model.basicstructure;

import eetac.model.propierties.AirPropierties;

public class AxisBlock extends FlowBlock {

	/*
	 * THE ORDER OF MATH VARIABLES ARE
	 * 1º PIN 
	 * 2º TIN 
	 * 3º MASSFLOWIN 
	 * 4º POUT 
	 * 5º TOUT 
	 * 6º MASSFLOWOUT 
	 * 7º MASSFUEL 
	 * 8º FUEL EFFICIENCY 
	 * 9º PRESSURE EFFICIENCY 
	 * RANGE 0-8
	 * OTHER VARIABLES IN COMPLEX BLOCKS
	 */
	
	protected double Work_in;
	protected double Work_out;
	protected double n_axis;
	
	
	public AxisBlock() {
		super();
	}

	public AxisBlock(AxisBlock a) {
		super(a);
		this.Work_in = a.getWork_in();
		this.Work_out = a.getWork_out();
		this.n_axis = a.getN_axis();
	}

	
	@Override
	public double getvariable(int index) {
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
			variable = this.Work_in;
			break;
		case 7:
			variable = this.Work_out;
			break;
		case 8:
			variable = this.n_axis;
			break;

		default:
			break;
		}

		return variable;
	}

	@Override
	public void setvariable(double variable, int index) {

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
			variable = this.Work_in;
			break;
		case 7:
			variable = this.Work_out;
			break;
		case 8:
			variable = this.n_axis;
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
		 * PRESSURE RELATION POUT = PINT * (1-E_b) | Equation 1: Pout-Pin*(1-eb) = 0
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

	public double getWork_in() {
		return Work_in;
	}

	public void setWork_in(double work_in) {
		Work_in = work_in;
	}

	public double getWork_out() {
		return Work_out;
	}

	public void setWork_out(double work_out) {
		Work_out = work_out;
	}

	public double getN_axis() {
		return n_axis;
	}

	public void setN_axis(double n_axis) {
		this.n_axis = n_axis;
	}
	




}
