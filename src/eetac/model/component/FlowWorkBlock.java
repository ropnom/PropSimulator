package eetac.model.component;

public class FlowWorkBlock extends FlowBlock {

	protected double Pi = 1;
	protected double Tau = 1;

	protected double work = 0;

	protected double n_i = 1;
	protected double n_p = 1;

	public FlowWorkBlock() {

	}


	public double getPi() {
		return Pi;
	}

	public void setPi(double pi) {
		Pi = pi;
	}

	public double getTau() {
		return Tau;
	}

	public void setTau(double tau) {
		Tau = tau;
	}

	public double getN_i() {
		return n_i;
	}

	public void setN_i(double n_i) {
		this.n_i = n_i;
	}

	public double getN_p() {
		return n_p;
	}

	public void setN_p(double n_p) {
		this.n_p = n_p;
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		this.work = work;
	}

}
