package eetac.model.propierties;

import java.util.ArrayList;
import java.util.List;

public class FuelPropierties {

	static protected List<Fuel> listfuel;

	public FuelPropierties() {
		Load();
	}

	public static void Load() {
		
		listfuel = new ArrayList<Fuel>();
		//Crear Fuel default
		Fuel fuel = new Fuel("Generic fuel", 0, 40078000.0);
		//Cargar desde archivo
		
		
		listfuel.add(fuel);

	}
	
	public static Fuel getFuel(int num){
		
		if(listfuel == null){
			Load();
		}
		return listfuel.get(num);
	}

}
