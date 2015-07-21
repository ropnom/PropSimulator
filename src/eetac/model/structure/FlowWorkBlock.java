package eetac.model.structure;

public class FlowWorkBlock extends FlowBlock {

	
	/* THE ORDER OF MATH VARIABLES IS
	 * 1º PIN
	 * 2º TIN
	 * 3º MASSFLOWIN
	 * 4º POUT
	 * 5º TOUT
	 * 6º MASSFLOWOUT
	 * 7º PI = POUT/PIN
	 * 8º TAU = TOUT/TIN
	 * 9º WORK
	 * 10º ISENTROPIC EFFICENCY
	 * 11º POLITROPIC EFFICENCY
	 * 
	 *  OTHER VARIABLES IN COMPLEX BLOCKS
	 */
	protected double Pi = 1;
	protected double Tau = 1;

	protected double work = 0;
	protected double n_i = 1;
	protected double n_p = 1;

	public FlowWorkBlock() {

	}
	

	public FlowWorkBlock(double pi, double tau, double work, double n_i, double n_p) {
		super();
		Pi = pi;
		Tau = tau;
		this.work = work;
		this.n_i = n_i;
		this.n_p = n_p;
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
