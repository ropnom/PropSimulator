package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.Diffusser;
import eetac.model.realcomponent.Nozzle;

public class Nozzletest {

	protected static Nozzle nozzle;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static Engine engine;

	public void CargarValores() {
		nozzle = new Nozzle();
		// init values of test
		nozzle.setPin(40000);
		nozzle.setTin(984);
		nozzle.setMassFlow_in(1000);
		nozzle.setPout(22500);
		nozzle.setTout(800);
		nozzle.setMassFlow_out(1000);
		nozzle.setVelocity(300);
		nozzle.setPatmosfere(22500);

		nozzle.genX();

		// reference plane
		nozzle.setInitnum((short) 0);
		nozzle.setEndnum(nozzle.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = nozzle.getMatrices();

		// Get variables and constants
		double[][] X = matriz.getX_equations();
		boolean[] constants = matriz.getConstants();

		// Change constants introduced by user
		X[0][0] = 40000;
		constants[0] = true;
		X[1][0] = 984;
		constants[1] = true;
		X[2][0] = 1000;
		constants[2] = true;

		// insert change in matrix
		matriz.setX_equations(X);
		matriz.setConstants(constants);

		// insert matrix in compresor
		nozzle.setIsdefined(true);
		nozzle.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("Test Compresor");
		CargarValores();

		// assert statements
		System.out.print("Check Compresor parameters...");
		assertEquals("Idnum mus be " + (GlobalConstants.getNozzle() + 1), (GlobalConstants.getNozzle() + 1), nozzle.getIdnum());
		assertEquals("Num equations must be 4", 4, nozzle.getNumequations());
		assertEquals("Num variables must be 7", 7, nozzle.getNumvariables());
		assertEquals("Num constants must be 3", 3, nozzle.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 40000 PA in matrix", 40000, nozzle.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 40000 PA in variable", 40000, nozzle.getPin(), 0.01);

		assertEquals("Tin must be 984 ºKin matrix", 984, nozzle.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 984 ºK  in variable", 984, nozzle.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, nozzle.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, nozzle.getMassFlow_in(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		nozzle.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", 0, nozzle.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -52.261, nozzle.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, nozzle.getMatrices().getFx_equations()[2][0], 0.01);
		assertEquals("Funcion 3 must be", -125.776, nozzle.getMatrices().getFx_equations()[3][0], 0.01);

		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		nozzle.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = {{0,0,0,1.00000001100000,0,0,0},{0.00532263500000000,-0.866118860000000,0,-0.00946246200000000,1,0,0},{0,0,-1,0,0,1,0},{0,-2.31400000000000,0.425775958000000,0,2.31400000000000,0,1},{0.999999975000000,0,0,0,0,0,0},{0,1,0,0,0,0,0},{0,0,1,0,0,0,0}};
		
		// assert Jx
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + nozzle.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], nozzle.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {

		System.out.println("init diffuser in engine test");
		engine = new Engine();
		engine.addBlock(nozzle);
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
