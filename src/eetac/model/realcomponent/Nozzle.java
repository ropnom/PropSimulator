package eetac.model.realcomponent;

import eetac.model.GlobalConstants;
import eetac.model.basicstructure.DinamicFlowBlock;
import eetac.model.propierties.AirPropierties;

public class Nozzle extends DinamicFlowBlock {

	// Aux variables
	protected double CPR;
	protected double PR;

	public Nozzle() {
		super();
		Gen_info();
	}

	public Nozzle(Nozzle a) {
		super(a);
		this.CPR = a.getCPR();
		this.PR = a.getPR();
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

		this.idnum = (short) (GlobalConstants.getNozzle() + 1);
		this.level = 1;
		this.name = "Generic Nozzle model  " + this.level;
		this.description = "This component is a basic model of nozzle with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 4;
		this.numvariables = 7;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_" + this.blocknumber + "_nozzle_in";
		variable[1] = "T_" + this.blocknumber + "_nozzle_in";
		variable[2] = "Mass_" + this.blocknumber + "_nozzle_in";
		variable[3] = "P_" + this.blocknumber + "_nozzle_out";
		variable[4] = "T_" + this.blocknumber + "_nozzle_out";
		variable[5] = "Mass_" + this.blocknumber + "_nozzle_out";
		variable[6] = "Velocity_" + this.blocknumber + "_nozzle_in";

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
		super.GenAuxvariables();

		this.sound_velocity = Math.sqrt(AirPropierties.getGamma_c_airfuel() * AirPropierties.getR_c_fuel() * this.Tout);
		this.mach_number = this.velocity / this.sound_velocity;

		/*
		 * PR is the relation between Pressure after turbine and atmosfere
		 * pressure CPR is the fluid relation inside a nozzle where P* / Pin =
		 * (2/(gamma+1)^(gama/(gama-1))
		 */
		this.PR = this.Patmosfere / this.Pin;
		this.CPR = AirPropierties.getCPRR_air_fuel();
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
			fx = VelocityRelations(X);
			break;

		default:
			break;
		}
		return fx;
	}

	@Override
	protected double PressureRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * CPR or POUT = Patmosfere
		 */

		if (AirPropierties.getCPRR_air_fuel() > (this.Patmosfere / X[0][0])) {

			return (X[3][0] - X[0][0] * AirPropierties.getCPRR_air_fuel());

		} else {

			return (X[3][0] - this.Patmosfere);
		}

	}

	@Override
	protected double TemperatureRelations(double[][] X) {
		/*
		 * TEMPERATURE RELATION Tout = Tin * (Pout/Pin)^((gamma-1)/gamma)
		 */

		return (X[4][0] - X[1][0] * Math.pow((X[3][0] / X[0][0]), 1 / AirPropierties.getGamma_gamma_1_airfuel()));
	}

	protected double VelocityRelations(double[][] X) {
		/*
		 * VELOCITY RELATION Vin = MASSout | Equation 3: MASSout-MASSin = 0
		 */
		System.out.println(X[6][0]);
		System.out.println(2 / X[2][0] * AirPropierties.getCp_c_fuel() * (X[1][0] - X[4][0]));
		System.out.println(AirPropierties.getCp_c_fuel());
		
		System.out.println(X[1][0] - X[4][0]);
		System.out.println(X[2][0]);
		
		return (X[6][0] - 2 / X[2][0] * AirPropierties.getCp_c_fuel() * (X[1][0] - X[4][0]));
	}

	public double getCPR() {
		return CPR;
	}

	public void setCPR(double cPR) {
		CPR = cPR;
	}

	public double getPR() {
		return PR;
	}

	public void setPR(double pR) {
		PR = pR;
	}

}
