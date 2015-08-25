package eetac.test.Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.Compressor;

public class Compressortest {

	protected static Compressor compressor;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static Engine engine;

	public void CargarValores() {
		compressor = new Compressor();
		// init values of test
		compressor.setPin(95000);
		compressor.setTin(290);
		compressor.setMassFlow_in(1000);
		compressor.setPout(1800000);
		compressor.setTout(800);
		compressor.setMassFlow_out(1000);
		compressor.setPi(18);
		compressor.setTau(2.7);
		compressor.setWork(-450000000);
		compressor.setN_i(0.8);
		compressor.setN_p(0.88);

		// reference plane
		compressor.setInitnum((short) 0);
		compressor.setEndnum(compressor.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = compressor.getMatrices();

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
		compressor.setIsdefined(true);
		compressor.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("Test Compresor");
		CargarValores();

		// assert statements
		System.out.print("Check Compresor parameters...");
		assertEquals("Idnum mus be " + (GlobalConstants.getCompresor() + 1), (GlobalConstants.getCompresor() + 1), compressor.getIdnum());
		assertEquals("Num equations must be 6", 6, compressor.getNumequations());
		assertEquals("Num variables must be 11", 11, compressor.getNumvariables());
		assertEquals("Num constants must be 5", 5, compressor.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 97000 PA in matrix", 97000.0, compressor.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 97000 PA in variable", 97000.0, compressor.getPin(), 0.01);

		assertEquals("Tin must be 290 ºKin matrix", 290.0, compressor.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 290 PA  in variable", 290.0, compressor.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, compressor.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, compressor.getMassFlow_in(), 0.01);

		assertEquals("Pout must be 1720000 PA in matrix", 1720000.0, compressor.getMatrices().getX_equations()[3][0], 0.01);
		assertEquals("Pout must be 1720000 PA  in variable", 1720000.0, compressor.getPout(), 0.01);

		assertEquals("Tout must be 755 PA in matrix", 755.0, compressor.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 755 PA  in variable", 755.0, compressor.getTout(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		compressor.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", -26000.0, compressor.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -28, compressor.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, compressor.getMatrices().getFx_equations()[2][0], 0.01);
		assertEquals("Funcion 3 must be", -3.31083, compressor.getMatrices().getFx_equations()[3][0], 0.01);
		assertEquals("Funcion 4 must be", -2.192589, compressor.getMatrices().getFx_equations()[4][0], 0.01);
		assertEquals("Funcion 5 must be", 17325000, compressor.getMatrices().getFx_equations()[5][0], 0.01);
		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		compressor.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = { { -18.0000020200000, 0, 0, 1.00000063000000, 0, 0, -97000, 0, 0, 0, 0 }, { 0, -2.70000000000000, 0, 0, 1, 0, 0, -290, 0, 0, 0 }, { 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, -24.3110700200000, 0, 0, -74.0974526300000 }, { 0, 0, 0, 0, 0, 0, 1, -23.9583244000000, 0, -50.9138662900000, 0 }, { 0, -1005000, 467324.999600000, 0, 1005000, 467324.999600000, 0, 0, 1.00016593900000, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

		// assert Jx
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + compressor.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], compressor.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {
		
		System.out.println("init comrpesor in engine test");
		engine = new Engine();
		engine.addBlock(compressor);
		engine.BuildMatrix();
		engine.PrintMatrix();
		
		// TODO: Miquel aqui hay que testear que las ecuaciones del engien son las mismas que el comrpesor

	}

	@Test
	public void test_math_core() {

		core = new MathCore();
		core.setEng(engine);
		core.RunIteration();
		
		// aqui hay que hacer correr el core y ver que el resultado es el que tenemso en excel.

	}

}
