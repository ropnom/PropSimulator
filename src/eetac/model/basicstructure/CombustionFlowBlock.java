package eetac.model.basicstructure;

import eetac.model.propierties.AirPropierties;
import eetac.model.propierties.FuelPropierties;

public class CombustionFlowBlock extends FlowBlock {

	/*
	 * THE ORDER OF MATH VARIABLES ARE 1º PIN 2º TIN 3º MASSFLOWIN 4º POUT 5º
	 * TOUT 6º MASSFLOWOUT 7º MASSFUEL 8º FUEL EFFICIENCY 9º PRESSURE EFFICIENCY
	 * RANGE 0-8 OTHER VARIABLES IN COMPLEX BLOCKS
	 */

	protected double Massfuel;
	protected double n_fuel;
	protected double E_b;

	// Aux variable
	protected int fueltype = 0;

	public CombustionFlowBlock() {
		super();
	}

	public CombustionFlowBlock(CombustionFlowBlock a) {
		super(a);
		this.Massfuel = a.getMassfuel();
		this.n_fuel = a.getN_fuel();
		this.E_b = a.getE_b();
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
			variable = this.Massfuel;
			break;
		case 7:
			variable = this.n_fuel;
			break;
		case 8:
			variable = E_b;
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
			this.Massfuel = variable;
			break;
		case 7:
			this.n_fuel = variable;
			break;
		case 8:
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

		default:
			break;
		}
		return fx;
	}

	// GENERACION DE ECUACIONES

	protected double PressureRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * (1-E_b) | Equation 1: Pout-Pin*(1-eb)
		 * = 0
		 */
		// return (this.Pout - this.Pin * (1-eb));
		return (X[3][0] - X[0][0] * (1 - X[8][0]));
	}

	protected double TemperatureRelations(double[][] X) {
		/*
		 * TEMPERATURE RELATION Tout = Tin * TAU | Equation 2: Tout-Tin*Tau = 0
		 */
		// return (this.Tout - (this.Tin+((this.Mfuel*N_fuel*hfuel)/(Min*cp))*(Min/Mout) );
	
		return (X[4][0] - (X[1][0]+((X[6][0]*X[7][0]*FuelPropierties.getFuel(this.fueltype).getHf())/(X[2][0]*AirPropierties.getCp_c())))*(X[2][0]/X[5][0]));
	}

	protected double MassFlowRelations(double[][] X) {
		/*
		 * MASS RELATION MASSin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		// return (this.MassFlow_in - this.MassFlow_out);
		return (X[2][0] + X[6][0] - X[5][0]);
	}

	public double getMassfuel() {
		return Massfuel;
	}

	public void setMassfuel(double massfuel) {
		Massfuel = massfuel;
	}

	public double getN_fuel() {
		return n_fuel;
	}

	public void setN_fuel(double n_fuel) {
		this.n_fuel = n_fuel;
	}

	public double getE_b() {
		return E_b;
	}

	public void setE_b(double e_b) {
		E_b = e_b;
	}

	public int getFueltype() {
		return fueltype;
	}

	public void setFueltype(int fueltype) {
		this.fueltype = fueltype;
	}

}
