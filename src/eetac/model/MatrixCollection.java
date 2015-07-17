package eetac.model;

public class MatrixCollection {

	double[] Fx_equations;
	double[][] Jx;

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

}
