package eetac.model.realcomponent.two;

import eetac.model.basicstructure.Engine;

public class TurboJetEngine2 extends Engine {

	public TurboJetEngine2() {
		super();

	}

	public TurboJetEngine2(TurboJetEngine2 a) {
		super(a);

	}

	protected void CreateComponentes() {
		
		//Create components
		Diffusser2 mydifuser = new Diffusser2();
		Compressor2 mycompresor = new Compressor2();
		ChamberComb2 mychamber = new ChamberComb2();
		Turbine2 myturbie = new Turbine2();
		
		//init values for JET
		
		
		//Create relations block

	}

}
