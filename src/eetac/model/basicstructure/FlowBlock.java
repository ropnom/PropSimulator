package eetac.model.basicstructure;

import eetac.model.MatrixCollection;

public abstract class FlowBlock extends SimulationBlock {

	/*
	 * THE ORDER OF MATH VARIABLES IS 1º PIN 2º TIN 3º MASSFLOWIN 4º POUT 5º
	 * TOUT 6º MASSFLOWOUT
	 * 
	 * OTHER VARIABLES IN COMPLEX BLOCKS
	 */

	protected double Pin = -1;
	protected double Tin = -1;
	protected double MassFlow_in = -1;

	protected double Pout = -1;
	protected double Tout = -1;
	protected double MassFlow_out = -1;

	// AUX VARIABLES
	protected double Pt_0 = -1;
	protected double Tt_0 = -1;
	protected double A_in = -0;
	protected double A_out = -0;

	public FlowBlock() {
		super();
	}

	// copy contructor
	public FlowBlock(FlowBlock a) {
		super(a);
		this.Pin = a.getPin();
		this.Tin = a.getTin();
		this.MassFlow_in = a.getMassFlow_in();
		this.Pout = a.getPout();
		this.Tout = a.getTout();
		this.MassFlow_out = a.getMassFlow_out();
		this.Pt_0 = a.getPt_0();
		this.Tt_0 = a.getTt_0();
		this.A_in = a.getA_in();
		this.A_out = a.getA_out();

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

	public double getA_in() {
		return A_in;
	}

	public void setA_in(double a_in) {
		A_in = a_in;
	}

	public double getA_out() {
		return A_out;
	}

	public void setA_out(double a_out) {
		A_out = a_out;
	}

}
