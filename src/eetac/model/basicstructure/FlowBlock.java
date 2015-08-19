package eetac.model.basicstructure;

import eetac.model.MatrixCollection;


public abstract class  FlowBlock extends SimulationBlock {
	
	/* THE ORDER OF MATH VARIABLES IS
	 * 1� PIN
	 * 2� TIN
	 * 3� MASSFLOWIN
	 * 4� POUT
	 * 5� TOUT
	 * 6�MASSFLOWOUT
	 * 
	 *  OTHER VARIABLES IN COMPLEX BLOCKS
	 */

	protected double Pin = -1;
	protected double Tin = -1;
	protected double MassFlow_in = -1;

	protected double Pout = -1;
	protected double Tout = -1;
	protected double MassFlow_out = -1;

	//AUX VARIABLES
	protected double Pt_0 = -1;
	protected double Tt_0 = -1;

	public FlowBlock() {
		super();		
	}


	public FlowBlock(MatrixCollection matrices, double epsilon, short numequations, short initnum, short endnum) {
		super(matrices, numequations, initnum, endnum);
		// TODO Auto-generated constructor stub
	}

	public FlowBlock(double pin, double tin, double massFlow_in, double pout, double tout, double massFlow_out, double pt_0, double tt_0) {
		super();
		Pin = pin;
		Tin = tin;
		MassFlow_in = massFlow_in;
		Pout = pout;
		Tout = tout;
		MassFlow_out = massFlow_out;
		Pt_0 = pt_0;
		Tt_0 = tt_0;
	}
	
	//copy contructor
	public FlowBlock(FlowBlock a) {
		super(a);
		Pin = a.getPin();
		Tin = a.getTin();
		MassFlow_in = a.getMassFlow_in();
		Pout = a.getPout();
		Tout = a.getTout();
		MassFlow_out = a.getMassFlow_out();
		Pt_0 = a.getPt_0();
		Tt_0 = a.getTt_0();
		
	}


	public double getPt_0() {
		return Pt_0;
	}

	public void setPt_0(double pt_0) {
		Pt_0 = pt_0;
	}

	public double getTt_0() {
		return Tt_0;
	}

	public void setTt_0(double tt_0) {
		Tt_0 = tt_0;
	}

	public double getPin() {
		return Pin;
	}

	public void setPin(double pin) {
		Pin = pin;
	}

	public double getTin() {
		return Tin;
	}

	public void setTin(double tin) {
		Tin = tin;
	}

	public double getMassFlow_in() {
		return MassFlow_in;
	}

	public void setMassFlow_in(double massFlow_in) {
		MassFlow_in = massFlow_in;
	}

	public double getPout() {
		return Pout;
	}

	public void setPout(double pout) {
		Pout = pout;
	}

	public double getTout() {
		return Tout;
	}

	public void setTout(double tout) {
		Tout = tout;
	}

	public double getMassFlow_out() {
		return MassFlow_out;
	}

	public void setMassFlow_out(double massFlow_out) {
		MassFlow_out = massFlow_out;
	}

}
