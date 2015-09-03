package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.Compressor;
import eetac.model.realcomponent.Diffusser;

public class Diffusortest {

	protected static Diffusser diffusor;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static Engine engine;

	public void CargarValores() {
		diffusor = new Diffusser();
		// init values of test
		diffusor.setPin(26500);
		diffusor.setTin(223.1);
		diffusor.setMassFlow_in(1000);
		diffusor.setPout(210000);
		diffusor.setTout(400);
		diffusor.setMassFlow_out(1000);
		diffusor.setVelocity(600);

		diffusor.genX();

		// reference plane
		diffusor.setInitnum((short) 0);
		diffusor.setEndnum(diffusor.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = diffusor.getMatrices();

		// Get variables and constants
		double[][] X = matriz.getX_equations();
		boolean[] constants = matriz.getConstants();

		// Change constants introduced by user
		X[0][0] = 26500;
		constants[0] = true;
		X[1][0] = 223.1;
		constants[1] = true;
		X[2][0] = 1000;
		constants[2] = true;

		X[6][0] = 600;
		constants[6] = true;

		// insert change in matrix
		matriz.setX_equations(X);
		matriz.setConstants(constants);

		// insert matrix in compresor
		diffusor.setIsdefined(true);
		diffusor.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("Test Compresor");
		CargarValores();

		// assert statements
		System.out.print("Check Compresor parameters...");
		assertEquals("Idnum mus be " + (GlobalConstants.getDifusser() + 1), (GlobalConstants.getDifusser() + 1), diffusor.getIdnum());
		assertEquals("Num equations must be 3", 3, diffusor.getNumequations());
		assertEquals("Num variables must be 7", 7, diffusor.getNumvariables());
		assertEquals("Num constants must be 4", 4, diffusor.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 26500 PA in matrix", 26500, diffusor.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 26500 PA in variable", 26500, diffusor.getPin(), 0.01);

		assertEquals("Tin must be 223.1 ºKin matrix", 223.1, diffusor.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 223.1 ºK  in variable", 223.1, diffusor.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, diffusor.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, diffusor.getMassFlow_in(), 0.01);

		assertEquals("Velocity must be 600 m/s in matrix", 600, diffusor.getMatrices().getX_equations()[6][0], 0.01);
		assertEquals("Velocity must be 600 m/s  in variable", 600, diffusor.getVelocity(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		diffusor.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", 1529.459, diffusor.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -2.2, diffusor.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, diffusor.getMatrices().getFx_equations()[2][0], 0.01);

		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		diffusor.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = { { -7.86681310300000, 1456.35219700000, 0, 1.00000004700000, 0, 0, -1083.04163100000 }, { 0, -1, 0, 0, 1, 0, -0.597000124000000 }, { 0, 0, -1, 0, 0, 1, 0 }, { 1.00000001100000, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 } };

		// assert Jx
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + diffusor.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], diffusor.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {

		System.out.println("init diffuser in engine test");
		engine = new Engine();
		engine.addBlock(diffusor);
		engine.BuildMatrix();
		engine.PrintMatrix();

		// TODO: Miquel aqui hay que testear que las ecuaciones del engien son
		// las mismas que el comrpesor

	}

	@Test
	public void test_math_core() {

		core = new MathCore();
		core.setEng(engine);
		core.RunIteration();
		System.out.println("Num the iterations was: "+core.getNumiteration());
		System.out.println("Timing was: "+core.getTime()+" milisecons");

		// aqui hay que hacer correr el core y ver que el resultado es el que
		// tenemso en excel.

	}

}
