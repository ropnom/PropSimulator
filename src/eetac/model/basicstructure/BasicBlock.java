package eetac.model.basicstructure;

import java.io.Serializable;


public abstract class BasicBlock implements Serializable {
	/* This class provide the Basic information like identificator, order, name, description and original reference of element.
	 * 
	 */
	protected short idnum = 0;
	protected short blocknumber = 0;
	protected short level = 0;
	
	protected String name = "Not defined";
	protected String description = "Not defined";
	protected String reference;
	

	public BasicBlock() {
		
	}
	
	public BasicBlock(BasicBlock a) {
		super();
		this.idnum = a.getIdnum();
		this.name = a.getName();
		this.description = a.getDescription();
		this.reference = a.getReference();
		this.level = a.getLevel();
	}

	// GEneral Methods
	protected void Gen_info() {
		/*
		 * This method init the variables	
		 */
		
		this.idnum = 0;//get from GlobalConstants
		this.blocknumber = 0;// order in engine
		this.level = 0;// type of element complexity
		
		this.name = "Not defined";
		this.description = "Not defined";
		this.reference = "Not reference";
		
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

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}
	

}
