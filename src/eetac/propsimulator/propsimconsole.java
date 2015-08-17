package eetac.propsimulator;

import eetac.model.MatrixCollection;
import eetac.model.realcomponent.Compressor;
import eetac.model.realcomponent.Diffuser;

public class propsimconsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("*************************************************");
		System.out.println("iniciando primeras linea de propsim en modo test");
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("Creamos y probamso el difusor");

		Diffuser difusor1 = new Diffuser(45.6);
		System.out.println(difusor1.Difuser_Values());

		System.out.println();
		Diffuser difusor2 = new Diffuser(56.5, 6.4f, 0.895);
		System.out.println(difusor2.Difuser_Values());

		System.out.println();
		Diffuser difusor3 = new Diffuser(62.4, 6.8f, 95.8f, 0.87);
		System.out.println(difusor3.Difuser_Values());

		System.out.println();

		System.out.println("Test Diffusor End");

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Test Compresor");

		Compressor compresor = new Compressor();

		MatrixCollection matriz = compresor.getMatrices();
		
		//Ponemos el valor de la variables
		double[][] X = matriz.getX_equations();
		boolean[] constants = matriz.getConstants();
		
		X[0][0] = 97000;
		constants[0] = true;
		X[1][0] = 290;
		constants[1] = true;
		X[2][0] = 1000;
		constants[2] = true;
		X[3][0] = 1720000;
		constants[3] = true;
		X[4][0] = 755;
		constants[4] = true;
		
		
		matriz.setX_equations(X);
		matriz.setConstants(constants);
		
		compresor.setMatrices(matriz);
		compresor.setIsdefined(true);
		
		compresor.Simulate();

		// Check equation and variables
		if (compresor.isBlockSimulated()) {
			System.out.println("Yes, we can simulate, with " + compresor.getNumequations() + " equations, " + compresor.getNumconstants() + " constants and a total of " + compresor.getNumvariables() + " varaibles.");

		} else {
			System.out.println("Fail, we have " + compresor.getNumequations() + " equations, " + compresor.getNumconstants() + " constants and a total of " + compresor.getNumvariables() + " varaibles.");
			System.out.println(" We need:" + (compresor.getNumvariables() - compresor.getNumequations() - compresor.getNumconstants()) + " constants to define the system");
		}

		matriz = compresor.getMatrices();

		for (int i = 0; i < compresor.getNumvariables(); i++) {
			System.out.println(" X_" + i + ": " + matriz.getX_equations()[i][0]);
		}
		
		if (matriz.getFx_equations() != null) {
			for (int l = 0; l < matriz.getFx_equations().length; l++) {
				
					System.out.println(" Fx_" + l + ": " + matriz.getFx_equations()[l][0]);
				
			}
		}

		if (matriz.getJx() != null) {
			for (int l = 0; l < matriz.getJx().length; l++) {
				for (int j = 0; j < matriz.getJx()[l].length; j++) {
					System.out.println(" Jx_" + l + "_" + j + ": " + matriz.getJx()[l][j]);
				}
			}
		}
	}
}
