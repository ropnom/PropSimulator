package eetac.model.structure;


public abstract class BasicBlock {

	// Model identificator
	protected short idnum = 0;
	protected short blocknumber = 0;
	protected String name = "Not defined";
	protected String description = "Not defined";
	protected String reference;
	

	public BasicBlock() {
		// TODO Auto-generated constructor stub
	}

	public BasicBlock(short idnum, String name, String description, String reference) {
		super();
		this.idnum = idnum;
		this.name = name;
		this.description = description;
		this.reference = reference;
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

	public short getBlocknumber() {
		return blocknumber;
	}

	public void setBlocknumber(short blocknumber) {
		this.blocknumber = blocknumber;
	}
	

}
