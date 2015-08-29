package eetac.model.basicstructure;

public class DinamicFlowBlock extends FlowBlock {

	protected double velocity;
	
	// Aux variables
	protected double sound_velocity;
	protected double mach_number;

	public DinamicFlowBlock() {

	}
	
	public DinamicFlowBlock(DinamicFlowBlock a) {
		super(a);
		this.velocity = a.getVelocity();
		this.sound_velocity = a.getSound_velocity();
		this.mach_number = a.getMach_number();

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
	
	

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getSound_velocity() {
		return sound_velocity;
	}

	public void setSound_velocity(double sound_velocity) {
		this.sound_velocity = sound_velocity;
	}

	public double getMach_number() {
		return mach_number;
	}

	public void setMach_number(double mach_number) {
		this.mach_number = mach_number;
	}
	
	

}
