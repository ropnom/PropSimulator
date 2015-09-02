package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.Compressor;
import eetac.model.realcomponent.Turbine;

public class Turbinetest {

	protected static Turbine turbine;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static Engine engine;

	public void CargarValores() {
		turbine = new Turbine();
		// init values of test
		turbine.setPin(300557.5757);
		turbine.setTin(1200);
		turbine.setMassFlow_in(1000);
		turbine.setPout(1800000);
		turbine.setTout(800);
		turbine.setMassFlow_out(1000);
		turbine.setPi(18);
		turbine.setTau(2.7);
		turbine.setWork(-450000000);
		turbine.setN_i(0.8);
		turbine.setN_p(0.88);
		turbine.genX();

		// reference plane
		turbine.setInitnum((short) 0);
		turbine.setEndnum(turbine.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = turbine.getMatrices();

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
		turbine.setIsdefined(true);
		turbine.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("Test Compresor");
		CargarValores();

		// assert statements
		System.out.print("Check Compresor parameters...");
		assertEquals("Idnum mus be " + (GlobalConstants.getCompresor() + 1), (GlobalConstants.getCompresor() + 1), turbine.getIdnum());
		assertEquals("Num equations must be 6", 6, turbine.getNumequations());
		assertEquals("Num variables must be 11", 11, turbine.getNumvariables());
		assertEquals("Num constants must be 5", 5, turbine.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 97000 PA in matrix", 97000.0, turbine.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 97000 PA in variable", 97000.0, turbine.getPin(), 0.01);

		assertEquals("Tin must be 290 ºKin matrix", 290.0, turbine.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 290 ºK  in variable", 290.0, turbine.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, turbine.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, turbine.getMassFlow_in(), 0.01);

		assertEquals("Pout must be 1720000 PA in matrix", 1720000.0, turbine.getMatrices().getX_equations()[3][0], 0.01);
		assertEquals("Pout must be 1720000 PA  in variable", 1720000.0, turbine.getPout(), 0.01);

		assertEquals("Tout must be 755 PA in matrix", 755.0, turbine.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 755 PA  in variable", 755.0, turbine.getTout(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		turbine.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", -26000.0, turbine.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -28, turbine.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, turbine.getMatrices().getFx_equations()[2][0], 0.01);
		assertEquals("Funcion 3 must be", -3.31083, turbine.getMatrices().getFx_equations()[3][0], 0.01);
		assertEquals("Funcion 4 must be", -2.192589, turbine.getMatrices().getFx_equations()[4][0], 0.01);
		assertEquals("Funcion 5 must be", 17325000, turbine.getMatrices().getFx_equations()[5][0], 0.01);
		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		turbine.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = { { -18.0000020200000, 0, 0, 1.00000063000000, 0, 0, -97000, 0, 0, 0, 0 }, { 0, -2.70000000000000, 0, 0, 1, 0, 0, -290, 0, 0, 0 }, { 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, -24.3110700200000, 0, 0, -74.0974526300000 }, { 0, 0, 0, 0, 0, 0, 1, -23.9583244000000, 0, -50.9138662900000, 0 }, { 0, -1005000, 467324.999600000, 0, 1005000, 467324.999600000, 0, 0, 1.00016593900000, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

		// assert Jx
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + turbine.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], turbine.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {
		
		System.out.println("init comrpesor in engine test");
		engine = new Engine();
		engine.addBlock(turbine);
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
