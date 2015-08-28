package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.ChamberComb;
import eetac.model.realcomponent.Compressor;

public class CombustionChamberTest {

	protected static ChamberComb chamber;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static Engine engine;

	public void CargarValores() {
		chamber = new ChamberComb();
		// init values of test
		chamber.setPin(1487825);
		chamber.setTin(778);
		chamber.setMassFlow_in(1000);
		chamber.setPout(1428400);
		chamber.setTout(1373);
		chamber.setMassFlow_out(1016);
		chamber.setMassfuel(16);;
		chamber.setN_fuel(0.98);
		chamber.setE_b(0.04);
		chamber.genX();
		
		
		

		// reference plane
		chamber.setInitnum((short) 0);
		chamber.setEndnum(chamber.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = chamber.getMatrices();

		// Get variables and constants
		double[][] X = matriz.getX_equations();
		boolean[] constants = matriz.getConstants();

		// Change constants introduced by user
		X[0][0] = 1487825;
		constants[0] = true;
		X[1][0] = 778;
		constants[1] = true;
		X[2][0] = 1000;
		constants[2] = true;
		X[4][0] = 1373;
		constants[4] = true;
		X[7][0] = 0.98;
		constants[7] = true;
		X[8][0] = 0.04;
		constants[8] = true;

		// insert cahnge in matrix
		matriz.setX_equations(X);
		matriz.setConstants(constants);

		// insert matrix in compresor
		chamber.setIsdefined(true);
		chamber.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("CombustionChamber Test");
		CargarValores();

		// assert statements
		System.out.print("Check CombutionChamber parameters...");
		assertEquals("Idnum must be " + (GlobalConstants.getCombustionchamber() + 1), (GlobalConstants.getCombustionchamber() + 1), chamber.getIdnum());
		assertEquals("Num equations must be 3", 3, chamber.getNumequations());
		assertEquals("Num variables must be 9", 9, chamber.getNumvariables());
		assertEquals("Num constants must be 6", 6, chamber.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 1487825 PA in matrix", 1487825.0, chamber.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 1487825 PA in variable", 1487825.0, chamber.getPin(), 0.01);

		assertEquals("Tin must be 778 �K in matrix", 778.0, chamber.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 778 �K  in variable", 778.0, chamber.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, chamber.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, chamber.getMassFlow_in(), 0.01);

		assertEquals("Tout must be 1373 PA in matrix", 1373.0, chamber.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 1373 PA  in variable", 1373.0, chamber.getTout(), 0.01);
		
		assertEquals("n_fuel must be 0.98 PA in matrix", 0.98, chamber.getMatrices().getX_equations()[7][0], 0.01);
		assertEquals("n_fuel must be 0.98 PA  in variable", 0.98, chamber.getN_fuel(), 0.01);
		
		assertEquals("E_b must be 0.04 PA in matrix", 0.04, chamber.getMatrices().getX_equations()[8][0], 0.01);
		assertEquals("E_b must be 0.04 PA  in variable", 0.04, chamber.getE_b(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix object with the values of the last test (user
		// values)
		chamber.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", 88.0, chamber.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -8.1974, chamber.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, chamber.getMatrices().getFx_equations()[2][0], 0.01);
		
		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix object with the values of the last test (user
		// values)
		chamber.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = {{-0.960000325000000,0,0,1.00000063000000,0,0,0,0,1487825},{0,-0.984251969000000,-0.765748030000000,0,1,1.35944612300000,-38.4655854600000,-628.009558500000,0},{0,0,-1,0,0,1,-1,0,0},{1.00000063000000,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0},{0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,1}};
		// assert Jx
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + chamber.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], chamber.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {
		
		System.out.println("init comrpesor in engine test");
		engine = new Engine();
		engine.addBlock(chamber);
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
