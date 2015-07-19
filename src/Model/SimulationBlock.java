package Model;


public class SimulationBlock {

	// Model identificator
	protected short idnum = 0;
	protected String name = "Not defined";
	protected String description = "Not defined";
	protected String reference;
	
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
