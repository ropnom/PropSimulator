package other_JAVA_test;

public class Objeto1 {

	protected int idnum;
	protected String campos;
	protected double[][] cosas;

	public Objeto1(int idnum, String campos) {

		this.idnum = idnum;
		this.campos = campos;
	}

	public int getIdnum() {
		return idnum;
	}

	public void setIdnum(int idnum) {
		this.idnum = idnum;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public double[][] getCosas() {
		return cosas;
	}

	public void setCosas(double[][] cosas) {
		this.cosas = cosas;
	}
	
	

}
