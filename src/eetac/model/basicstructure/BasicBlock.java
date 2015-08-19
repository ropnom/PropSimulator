package eetac.model.basicstructure;


public abstract class BasicBlock {
	/* This class provide the Basic information like identificator, order, name, description and original reference of element.
	 * 
	 */
	protected short idnum = 0;
	protected short blocknumber = 0;
	protected String name = "Not defined";
	protected String description = "Not defined";
	protected String reference;
	

	public BasicBlock() {
		
	}

	public BasicBlock(short idnum, String name, String description, String reference) {
		super();
		this.idnum = idnum;
		this.name = name;
		this.description = description;
		this.reference = reference;
	}
	
	public BasicBlock(BasicBlock a) {
		super();
		this.idnum = a.getIdnum();
		this.name = a.getName();
		this.description = a.getDescription();
		this.reference = a.getReference();
	}

	// GEneral Methods
	protected void GenCompressor_info() {
		/*
		 * This method init the variables	
		 */
		
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
