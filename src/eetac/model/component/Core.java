package eetac.model.component;

import java.util.Date;

public class Core {

	// global parameters of core
	protected int MAX_ITERACTIONS = 100;
	protected float tolerance = 0.0001f;

	// Auxiliary parameters
	protected short num_equations = 0;
	protected short num_variables = 0;
	protected Date init;

	// Mant variables
	double[] matrix_X;
	double[] matrix_Fx;
	double[][] matrix_Jacobian;

	public void main() {

		// Arrancamos el Core

		// check de num ecuaciones num de incognitas
		
		//Recorremos la lista de modulos obteniendo el numero de ecuaciones
		//Recorremos la lista de variables conocidas.
		
		//cargamos valores iniciales
		
		// ** ITERACION **
		
		//Calculamos Fx
		
		//Calculamos Jx
		
		//Calculamos nueva X
		
		// ** FIN ITERACION **
	}

}
