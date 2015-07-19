package eetac.model.component;


public class Diffuser extends FlowBlock {

	protected float AreaIn;
	protected float AreaOut;
	protected double Vin;
	protected double Vout;

	public Diffuser() {

	}

	public Diffuser(double Vin, float AreaIn) {
		this.Vin = Vin;
		this.AreaIn = AreaIn;
	}

	public Diffuser(double Vin, float AreaIn, float AreaOut) {
		this.Vin = Vin;
		this.AreaIn = AreaIn;
		this.AreaOut = AreaOut;

	}

	@Override
	protected double[] Fx_equations(double[] X) {
		// TODO Auto-generated method stub
		return super.Fx_equations(X);
	}

	@Override
	protected double[][] Jx_equations() {
		// TODO Auto-generated method stub
		return super.Jx_equations();
	}

	
	//GETs and SETs
	
	

	public float getAreaIn() {
		return AreaIn;
	}

	public void setAreaIn(float areaIn) {
		AreaIn = areaIn;
	}

	public float getAreaOut() {
		return AreaOut;
	}

	public void setAreaOut(float areaOut) {
		AreaOut = areaOut;
	}

	public double getVin() {
		return Vin;
	}

	public void setVin(double vin) {
		Vin = vin;
	}

	public double getVout() {
		return Vout;
	}

	public void setVout(double vout) {
		Vout = vout;
	}
	

}
