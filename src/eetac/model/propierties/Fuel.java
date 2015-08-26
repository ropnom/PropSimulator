package eetac.model.propierties;

public class Fuel {
	
	protected String name = "not defined";
	protected double density;
	protected double hf;
	
	public Fuel(){
		
	}

	public Fuel(String name, double density, double hf) {
		super();
		this.name = name;
		this.density = density;
		this.hf = hf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getHf() {
		return hf;
	}

	public void setHf(double hf) {
		this.hf = hf;
	}
	
	

}
