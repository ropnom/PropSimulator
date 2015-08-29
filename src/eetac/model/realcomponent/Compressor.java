package eetac.model.realcomponent;

import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.FlowWorkBlock;

public class Compressor extends FlowWorkBlock {

	public Compressor() {
		super();
		Gen_info();
	}
	
	public Compressor(Compressor a) {
		super(a);
		// TODO Auto-generated constructor stub
	}
	

	// Order of variable in vector
	// this.Pin = X[0][0];
	// this.Tin = X[1][0];
	// this.MassFlow_in = X[2][0];
	// this.Pout = X[3][0];
	// this.Tout = X[4][0];
	// this.MassFlow_out = X[5][0];
	// this.Pi = X[6][0];
	// this.Tau = X[7][0];
	// this.work = X[8][0];
	// this.n_i = X[9][0];
	// this.n_p = X[10][0];

	
	// Init basic information od basicblock, description reference etc...
	@Override
	protected void Gen_info() {

		this.idnum = (short) (GlobalConstants.getCompresor()+1);
		this.level = 1;
		this.name = "Generic Compressor model  "+this.level;
		this.description = "This component is a basic model of compresion with constant propierties for air";
		this.reference = "Teorical Reference Termodinamics";

		initvalues();
		this.numequations = 6;
		this.numvariables = 11;

		// Gen variable names
		String[] variable = new String[this.numvariables];
		variable[0] = "P_"+this.blocknumber+"_compresor_in";
		variable[1] = "T_"+this.blocknumber+"_compresor_in";
		variable[2] = "Mass_"+this.blocknumber+"_compresor_in";
		variable[3] = "P_"+this.blocknumber+"_compresor_out";
		variable[4] = "T_"+this.blocknumber+"_compresor_out";
		variable[5] = "Mass_"+this.blocknumber+"_compresor_out";
		variable[6] = "Pressure_ratio"+this.blocknumber+"_compresor";
		variable[7] = "Temperature_ratio"+this.blocknumber+"_compresor";
		variable[8] = "Work_"+this.blocknumber+"_compresor";
		variable[9] = "Issentropic_efficiency_"+this.blocknumber+"_compressor";
		variable[10] = "Politropic_efficiency_"+this.blocknumber+"_compressor";

		double[][] X = new double[this.numvariables][1];
		// GEN X vecto
		X[0][0] = this.Pin;
		X[1][0] = this.Tin;
		X[2][0] = this.MassFlow_in;
		X[3][0] = this.Pout;
		X[4][0] = this.Tout;
		X[5][0] = this.MassFlow_out;
		X[6][0] = this.Pi;
		X[7][0] = this.Tau;
		X[8][0] = this.work;
		X[9][0] = this.n_i;
		X[10][0] = this.n_p;

		this.matrices.setX_equations(X);
		this.matrices.setVariable(variable);

		genMatrix();
	}

	@Override
	protected void initvalues() {

		this.Pin = 95000;
		this.Tin = 290;
		this.MassFlow_in = 1000;

		this.Pout = 1800000;
		this.Tout = 800;
		this.MassFlow_out = 1000;

		this.Pi = 18;
		this.Tau = 2.7;
		this.work = -450000000;
		this.n_i = 0.8;
		this.n_p = 0.88;

	}


}
