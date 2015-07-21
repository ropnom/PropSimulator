package eetac.model.structure;

public class MatrixCollection {

	protected double[] X_equations;
	protected double[] Fx_equations;
	protected double[][] Jx;
	protected String[] variable;
	protected boolean[] constants;

	public MatrixCollection() {

	}

	public MatrixCollection(double[] Fx_equations, 	double[][] Jx) {

		this.Fx_equations = Fx_equations;
		this.Jx = Jx;
	}

	public double[] getFx_equations() {
		return Fx_equations;
	}

	public void setFx_equations(double[] fx_equations) {
		Fx_equations = fx_equations;
	}

	public 	double[][] getJx() {
		return Jx;
	}

	public void setJx(	double[][] jx) {
		Jx = jx;
	}

	public double[] getX_equations() {
		return X_equations;
	}

	public void setX_equations(double[] x_equations) {
		X_equations = x_equations;
	}

}
