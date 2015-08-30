package eetac.model.realcomponent;

import eetac.model.GlobalConstants;
import eetac.model.basicstructure.ShaftBlock;

public class Shaft extends ShaftBlock {

	public Shaft() {
		super();
		Gen_info();
	}

	public Shaft(Shaft a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	// Order of variable in vector
	// this.Win = X[0][0];
	// this.Wout = X[1][0];
	// this.N_axis = X[2][0];

	// Init basic information od basicblock, description reference etc...
	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getAxes() + 1);
		this.level = 1;
		this.name = "Generic Shaft model  " + this.level;
		this.description = "This component is a basic model of Shaft only based of work trasnsmision";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 1;
		this.numvariables = 3;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "W_" + this.blocknumber + "_shaft_in";
		variable[1] = "W_" + this.blocknumber + "_shaft_out";
		variable[2] = "Efficiency_" + this.blocknumber + "_shaft";

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Work_in;
		X[1][0] = this.Work_out;
		X[2][0] = this.n_axis;

		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	protected void initvalues() {

		// TODO: insert new values
		this.Work_in = 95000;
		this.Work_out = 1800000;
		this.n_axis = 0.8;

	}

}
