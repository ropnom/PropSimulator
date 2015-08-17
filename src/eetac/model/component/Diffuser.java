package eetac.model.component;

import eetac.model.basicstructure.BasicBlock;

public class Diffuser extends BasicBlock{

	protected double Pt_0;
	protected double Tt_0;
	protected double Rho= -1;

	protected float AreaIn = -1;
	protected float AreaOut = -1;
	protected double Vin= -1;
	protected double Vout= -1;

	protected double MassFlow = 0;	
	protected boolean isworking = false;

	public Diffuser(double MassFlow) {
		this.MassFlow = MassFlow;
		
	}

	public Diffuser() {

	}

	public Diffuser(double Vin, float AreaIn, double Rho) {
		this.Vin = Vin;
		this.AreaIn = AreaIn;
		this.Rho = Rho;
		CalPArameter();
	}

	public Diffuser(double Vin, float AreaIn, float AreaOut, double Rho) {
		this.Vin = Vin;
		this.AreaIn = AreaIn;
		this.AreaOut = AreaOut;
		this.Rho = Rho;
		CalPArameter();
	}

	// Funciones
	protected void CalPArameter() {

		if(AreaIn !=-1 && Vin!=-1 && Rho!=-1){
			isworking=true;
		}
		if (MassFlow == 0 && isworking) {
			MassFlow = Rho * Vin * AreaIn;
		}
		
		if(AreaOut !=-1)
		{
			Vout = Vin*AreaIn/AreaOut;
		}
		
		
	}
	
	public String Difuser_Values(){
		String line = "";
		line += " Vin is  "+Vin;
		line += " AreaIn is  "+AreaIn;
		line += " Rho is  "+Rho;
		line += " Vout is  "+Vout;
		line += " MassFlow is  "+MassFlow;
		return line;
	}

	// GETs and SETs

	public float getAreaIn() {
		return AreaIn;
	}

	public void setAreaIn(float areaIn) {
		AreaIn = areaIn;
	}

	public float getAreaOut() {
		return AreaOut;
	}

	public void setAreaOut(float areaOut) {
		AreaOut = areaOut;
	}

	public double getVin() {
		return Vin;
	}

	public void setVin(double vin) {
		Vin = vin;
	}

	public double getVout() {
		return Vout;
	}

	public void setVout(double vout) {
		Vout = vout;
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

	public double getMassFlow() {
		return MassFlow;
	}

	public void setMassFlow(double massFlow) {
		MassFlow = massFlow;
	}

	public double getRho() {
		return Rho;
	}

	public void setRho(double rho) {
		Rho = rho;
	}

	public boolean isIsworking() {
		return isworking;
	}

	public void setIsworking(boolean isworking) {
		this.isworking = isworking;
	}

}
