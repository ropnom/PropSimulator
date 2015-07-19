package eetac.model;


public class SimulationBlock extends BasicBlock{
	
	//math internal matrix
	MatrixCollection matrices = new MatrixCollection();	
	protected double epsilon;
	protected short numequations=0;

	public SimulationBlock() {
		// TODO Auto-generated constructor stub
	}

	// GEneral Methods

	protected double[] Fx_equations(double[] X) {

		// Make F(x) vector using functions
		return null;
	}

	protected double[][] Jx_equations( ) {

		// Make  Jx = [(F(x)- F(x+e))/e] por variable

		return null;
	}
	
	protected MatrixCollection getMatrix( ) {

		//Generar matriz de X
		double[] Fx = Fx_equations(matrices.getX_equations());
		double[][] Jx_equations = Jx_equations();			

		return matrices;
	}
		

	// GETs and SETs

	public short getNumequations() {
		return numequations;
	}

	public void setNumequations(short numequations) {
		this.numequations = numequations;
	}

	public MatrixCollection getMatrices() {
		return matrices;
	}

	public void setMatrices(MatrixCollection matrices) {
		this.matrices = matrices;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}


}
