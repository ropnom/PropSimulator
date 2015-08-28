package eetac.model.basicstructure;

public class DinamicFlowBlock extends FlowBlock {

	protected double velocity;

	public DinamicFlowBlock() {

	}
	
	public DinamicFlowBlock(DinamicFlowBlock a) {
		super(a);
		this.velocity = a.getVelocity();

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
			variable = this.velocity;
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
			this.velocity = variable;
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
			fx = VelocityRelations(X);
			break;

		default:
			break;
		}
		return fx;
	}
	
	protected double PressureRelations(double[][] X) {
		// To implement
		return 0;
	}

	protected double TemperatureRelations(double[][] X) {
		// To implement
		return 0;
	}

	protected double MassFlowRelations(double[][] X) {
		// To implement
		return 0;
	}
	protected double VelocityRelations(double[][] X) {
		// To implement
		return 0;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

}
