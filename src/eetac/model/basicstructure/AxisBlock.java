package eetac.model.basicstructure;


public class AxisBlock extends SimulationBlock {

	/*
	 * THE ORDER OF MATH VARIABLES ARE 1º PIN 2º TIN 3º MASSFLOWIN 4º POUT 5º
	 * TOUT 6º MASSFLOWOUT 7º MASSFUEL 8º FUEL EFFICIENCY 9º PRESSURE EFFICIENCY
	 * RANGE 0-8 OTHER VARIABLES IN COMPLEX BLOCKS
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
	public void GenAuxvariables() {
		super.GenAuxvariables();

		// Show variables
	}

	@Override
	public double getvariable(int index) {
		double variable = 0;

		switch (index) {
		case 0:
			variable = this.Work_in;
			break;
		case 1:
			variable = this.Work_out;
			break;
		case 2:
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
			this.Work_in = variable;
			break;
		case 1:
			this.Work_out = variable;
			break;
		case 2:
			this.n_axis = variable;
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
			fx = WorkRelations(X);
			break;
	
		default:
			break;
		}
		return fx;
	}

	// GENERACION DE ECUACIONES

	protected double WorkRelations(double[][] X) {
		/*
		 * PRESSURE RELATION POUT = PINT * (1-E_b) | Equation 1: Pout-Pin*(1-eb)
		 * = 0
		 */
		// return (this.Pout - this.Pin * (1-eb));
		return (X[3][0] - X[0][0] * (1 - X[8][0]));
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
