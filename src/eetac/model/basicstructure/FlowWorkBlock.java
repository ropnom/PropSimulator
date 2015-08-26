package eetac.model.basicstructure;

import eetac.model.propierties.AirPropierties;


public abstract class FlowWorkBlock extends FlowBlock {
	/*
	 * THE ORDER OF MATH VARIABLES ARE
	 * 1º PIN 
	 * 2º TIN 
	 * 3º MASSFLOWIN 
	 * 4º POUT 
	 * 5º TOUT 
	 * 6º MASSFLOWOUT 
	 * 7º PI = POUT/PIN 
	 * 8º TAU = TOUT/TIN 
	 * 9º WORK 
	 * 10º ISENTROPIC EFFICENCY 
	 * 11º POLITROPIC EFFICENCY
	 * 
	 * RANGE 0-10
	 * OTHER VARIABLES IN COMPLEX BLOCKS
	 */
	protected double Pi = 1;
	protected double Tau = 1;

	protected double work = 0;
	protected double n_i = 1;
	protected double n_p = 1;

	public FlowWorkBlock() {
		super();
	}

	public FlowWorkBlock(double pi, double tau, double work, double n_i, double n_p) {
		super();
		Pi = pi;
		Tau = tau;
		this.work = work;
		this.n_i = n_i;
		this.n_p = n_p;
	}

	// Copy contructor
	public FlowWorkBlock(FlowWorkBlock a) {
		super(a);
		Pi = a.getPi();
		Tau = a.getTau();
		this.work = a.getWork();
		this.n_i = a.getN_i();
		this.n_p = a.getN_p();
	}

	// get generic variable of component by number order
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

	// Set a generic variable by num order
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

	// get Fx generic by num order
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
	// Fx[0][0] = PressureRelations(X);
	// Fx[1][0] = TemperatureRelations(X);
	// Fx[2][0] = MassFlowRelations(X);
	// Fx[3][0] = PolitropicRelations(X);
	// Fx[4][0] = IsentropicRelations(X);
	// Fx[5][0] = WorkRelations(X);

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

	public double getPi() {
		return Pi;
	}

	public void setPi(double pi) {
		Pi = pi;
	}

	public double getTau() {
		return Tau;
	}

	public void setTau(double tau) {
		Tau = tau;
	}

	public double getN_i() {
		return n_i;
	}

	public void setN_i(double n_i) {
		this.n_i = n_i;
	}

	public double getN_p() {
		return n_p;
	}

	public void setN_p(double n_p) {
		this.n_p = n_p;
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		this.work = work;
	}

}
