package eetac.model.propierties;

import eetac.model.GlobalConstants;
import eetac.model.basicstructure.BasicBlock;

public class Fluid extends BasicBlock {

	// basic parameter to get propierties
	// % mier, principal fluid and added fluid

	protected double alfa;

	public Fluid() {

	}

	public Fluid(short blocknumber) {

		this.blocknumber = blocknumber;
	}

	public Fluid(Fluid a) {
		super(a);
	}

	protected void Gen_info() {
		/*
		 * Fluid objet is the propierties of the fluid inside a block
		 */

		this.idnum = (short) (GlobalConstants.getFluidtype() + 1);// get from
																	// GlobalConstants
		this.blocknumber = 0;// order in engine
		this.level = 1;// type of element complexity

		this.name = "Fluid GenericElement";
		this.description = "this objet modelate the fluid propierties along engine for diferent gases, temperatures or mixers";
		this.reference = "Teorical Model";
	}

	public double getCP(double temperature) {

		return 0;
	}

	public double getH(double temperature) {

		return 0;
	}
	
	public double getPR(double temperature) {

		return 0;
	}
	
	public double getGamma(double temperature) {

		return 0;
	}

}
