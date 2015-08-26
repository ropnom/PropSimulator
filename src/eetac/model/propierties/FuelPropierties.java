package eetac.model.propierties;

import java.util.List;

public class FuelPropierties {

	static protected List<Fuel> listfuel;

	public FuelPropierties() {
		Load();
	}

	public void Load() {
		
		//Crear Fuel default
		
		//Cargar desde archivo

	}
	
	public static Fuel getFuel(int num){
		return listfuel.get(num);
	}

}
