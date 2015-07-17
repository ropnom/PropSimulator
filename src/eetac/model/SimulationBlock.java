package eetac.model;


public class SimulationBlock {

	// Model identificator
	protected short idnum;
	protected String name;
	protected String description;
	protected String reference;

	// propierties
	protected short numequations;
	protected float n_efficiencce = 1;

	public SimulationBlock() {
		// TODO Auto-generated constructor stub
	}

	// GEneral Methods

	protected double[] Fx_equations(double[] X) {

		// Make F(x) vector using functions

		return null;
	}

	protected double[][] Jx_equations(double[] X, double[] Fx, double epsilon ) {

		// Make  Jx = [(F(x)- F(x+e))/e] por variable

		return null;
	}
	
	protected MatrixCollection getMatrix(double[] X,double epsilon ) {

		double[] Fx = Fx_equations(X);
		double[][] Jx_equations = Jx_equations(X, Fx, epsilon);
		MatrixCollection matrices = new MatrixCollection(Fx, Jx_equations);		

		return matrices;
	}
	
	
	
	

	// GETs and SETs

	public short getIdnum() {
		return idnum;
	}

	public void setIdnum(short idnum) {
		this.idnum = idnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public short getNumequations() {
		return numequations;
	}

	public void setNumequations(short numequations) {
		this.numequations = numequations;
	}

	public float getN_efficiencce() {
		return n_efficiencce;
	}

	public void setN_efficiencce(float n_efficiencce) {
		this.n_efficiencce = n_efficiencce;
	}

}
