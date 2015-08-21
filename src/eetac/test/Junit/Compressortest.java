package eetac.test.Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import eetac.model.MatrixCollection;
import eetac.model.realcomponent.Compressor;

public class Compressortest {

	protected static Compressor compresor;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	public void CargarValores() {
		compresor = new Compressor();
		// init values of test
		compresor.setPin(95000);
		compresor.setTin(290);
		compresor.setMassFlow_in(1000);
		compresor.setPout(1800000);
		compresor.setTout(800);
		compresor.setMassFlow_out(1000);
		compresor.setPi(18);
		compresor.setTau(2.7);
		compresor.setWork(-450000000);
		compresor.setN_i(0.8);
		compresor.setN_p(0.88);
		
		//reference plane
		compresor.setInitnum((short) 0);
		compresor.setEndnum(compresor.getNumvariables());
			
		// get matrix objet inicializated by compresor
		matriz = compresor.getMatrices();

		// Get variables and constants
		double[][] X = matriz.getX_equations();
		boolean[] constants = matriz.getConstants();

		// Change constants introduced by user
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

		// insert cahnge in matrix
		matriz.setX_equations(X);
		matriz.setConstants(constants);

		// insert matrix in compresor
		compresor.setIsdefined(true);
		compresor.setMatrices(matriz);
		
	}

	@Test
	public void test_carga_datos() {
		System.out.println("Test Compresor");
		CargarValores();

		// assert statements
		System.out.println("Check Inputs...");
		assertEquals("Pin must be 97000 PA in matrix", 97000.0, compresor.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 97000 PA in variable", 97000.0, compresor.getPin(), 0.01);

		assertEquals("Tin must be 290 ºKin matrix", 290.0, compresor.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 290 PA  in variable", 290.0, compresor.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, compresor.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, compresor.getMassFlow_in(), 0.01);

		assertEquals("Pout must be 1720000 PA in matrix", 1720000.0, compresor.getMatrices().getX_equations()[3][0], 0.01);
		assertEquals("Pout must be 1720000 PA  in variable", 1720000.0, compresor.getPout(), 0.01);

		assertEquals("Tout must be 755 PA in matrix", 755.0, compresor.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 755 PA  in variable", 755.0, compresor.getTout(), 0.01);

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		compresor.Simulate();
		// assert functions
		System.out.println("Functions Inputs...");
		assertEquals("Funcion 0 must be", -26000.0, compresor.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -28, compresor.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, compresor.getMatrices().getFx_equations()[2][0], 0.01);
		assertEquals("Funcion 3 must be", -3.31083, compresor.getMatrices().getFx_equations()[3][0], 0.01);
		assertEquals("Funcion 4 must be", -2.192589, compresor.getMatrices().getFx_equations()[4][0], 0.01);
		assertEquals("Funcion 5 must be", 17325000, compresor.getMatrices().getFx_equations()[5][0], 0.01);
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		compresor.Simulate();
		// create reference matrix

		double[][] referencia = { { -18.0000020200000, 0, 0, 1.00000063000000, 0, 0, -97000, 0, 0, 0, 0 }, { 0, -2.70000000000000, 0, 0, 1, 0, 0, -290, 0, 0, 0 }, { 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, -24.3110700200000, 0, 0, -74.0974526300000 }, { 0, 0, 0, 0, 0, 0, 1, -23.9583244000000, 0, -50.9138662900000, 0 }, { 0, -1005000, 467324.999600000, 0, 1005000, 467324.999600000, 0, 0, 1.00016593900000, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

		// assert Jx
		System.out.println("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + compresor.getMatrices().getJx()[i][j]);
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], compresor.getMatrices().getJx()[i][j], 0.01);

			}
		}

	}
	
	@Test
	public void test_engine() {
		
	}
	
	@Test
	public void test_math_core() {
		
	}

}
