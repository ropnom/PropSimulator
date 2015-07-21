package eetac.model.structure;

public class SimulationBlock extends BasicBlock {

	// math internal matrix
	MatrixCollection matrices = new MatrixCollection();
	protected double epsilon = 0.001;
	protected short numequations = 0;
	protected short initnum = -1;
	protected short endnum = -1;
	protected short numvariables = -1;
	protected short numconstants = -1;
	protected short totalequations = -1;

	public SimulationBlock() {

	}

	public SimulationBlock(MatrixCollection matrices, double epsilon, short numequations, short initnum, short endnum) {
		super();
		this.matrices = matrices;
		this.epsilon = epsilon;
		this.numequations = numequations;
		this.initnum = initnum;
		this.endnum = endnum;
	}

	public SimulationBlock(short idnum, String name, String description, String reference) {
		super(idnum, name, description, reference);
		// TODO Auto-generated constructor stub
	}

	// GEneral Methods
	protected double[] X_equations() {

		// Make X(x) vector using functions
		return null;
	}

	protected String[] Variables_equations() {

		// Variables of X
		return null;
	}

	protected boolean[] Constants_equations() {

		// Boolean constants
		return null;
	}

	protected double[] Fx_equations(double[] X) {

		// Make F(x) vector using functions
		return null;
	}

	protected double[][] Jx_equations(double[] X, double[] Fx, boolean[] constants) {

		// Make Jx = [(F(x)- F(x+e))/e] por variable

		return null;
	}

	protected MatrixCollection getMatrix() {

		// Generar matriz de X
		double[] X = X_equations();
		String[] variable = Variables_equations();
		boolean[] constants = Constants_equations();

		double[] Fx = Fx_equations(X);
		double[][] Jx_equations = Jx_equations(X, Fx, constants);

		return matrices;
	}

	// GETs and SETs

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

	public short getNumequations() {
		return numequations;
	}

	public void setNumequations(short numequations) {
		this.numequations = numequations;
	}

	public short getInitnum() {
		return initnum;
	}

	public void setInitnum(short initnum) {
		this.initnum = initnum;
	}

	public short getEndnum() {
		return endnum;
	}

	public void setEndnum(short endnum) {
		this.endnum = endnum;
	}

	public short getNumvariables() {
		return numvariables;
	}

	public void setNumvariables(short numvariables) {
		this.numvariables = numvariables;
	}

	public short getNumconstants() {
		return numconstants;
	}

	public void setNumconstants(short numconstants) {
		this.numconstants = numconstants;
	}

	public short getTotalequations() {
		return totalequations;
	}

	public void setTotalequations(short totalequations) {
		this.totalequations = totalequations;
	}

}
