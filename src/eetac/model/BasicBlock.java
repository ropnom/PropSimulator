package eetac.model;


public class BasicBlock {

	// Model identificator
	protected short idnum = 0;
	protected String name = "Not defined";
	protected String description = "Not defined";
	protected String reference;
	

	public BasicBlock() {
		// TODO Auto-generated constructor stub
	}

	// GEneral Methods

	public String GenDescripcion(){
		String line = "";
		
		
		return line;
	}
	
	// GETs and SETs

	public short getIdnum() {
		return idnum;
	}

	public void setIdnum(short idnum) {
		this.idnum = idnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
