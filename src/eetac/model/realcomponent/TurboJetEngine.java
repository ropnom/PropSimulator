package eetac.model.realcomponent;

import eetac.model.basicstructure.Engine;

public class TurboJetEngine extends Engine {

	public TurboJetEngine() {
		super();

	}

	public TurboJetEngine(TurboJetEngine a) {
		super(a);

	}

	protected void CreateComponentes() {
		
		//Create components
		Diffusser mydifuser = new Diffusser();
		Compressor mycompresor = new Compressor();
		ChamberComb mychamber = new ChamberComb();
		Turbine myturbie = new Turbine();
		
		//init values for JET
		
		
		//Create relations block

	}

}
