package eetac.model.component;

import eetac.model.SimulationBlock;

public class FlowBlock extends SimulationBlock {

	protected double Pin;
	protected double Tin;
	protected double MassFlow_in;

	protected double Pout;
	protected double Tout;
	protected double MassFlow_out;


	protected double Pt_0;
	protected double Tt_0;

	public FlowBlock() {

	}

	public FlowBlock(double Pt_0, double Tt_0) {
		this.Pt_0 = Pt_0;
		this.Tt_0 = Tt_0;
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
