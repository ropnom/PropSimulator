package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.basicstructure.SimulationProject;
import eetac.model.realcomponent.ChamberComb;
import eetac.model.realcomponent.Compressor;
import eetac.model.realcomponent.PostCombustor;

public class PostcombustionTest {

	protected static PostCombustor postcombustor;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static SimulationProject project;

	public void CargarValores() {
		postcombustor = new PostCombustor();
		// init values of test
		postcombustor.setPin(1487825);
		postcombustor.setTin(778);
		postcombustor.setMassFlow_in(1000);
		postcombustor.setPout(1428400);
		postcombustor.setTout(1293.2446);
		postcombustor.setMassFlow_out(1016);
		postcombustor.setMassfuel(16);;
		postcombustor.setN_fuel(0.98);
		postcombustor.setE_b(0.04);
		postcombustor.genX();
		
		
		

		// reference plane
		postcombustor.setInitnum((short) 0);
		postcombustor.setEndnum(postcombustor.getNumvariables());

		// get matrix objet inicializated by compresor
		matriz = postcombustor.getMatrices();

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
		X[4][0] = 1293.2446;
		constants[4] = true;
		X[7][0] = 0.98;
		constants[7] = true;
		X[8][0] = 0.04;
		constants[8] = true;

		// insert cahnge in matrix
		matriz.setX_equations(X);
		matriz.setConstants(constants);

		// insert matrix in compresor
		postcombustor.setIsdefined(true);
		postcombustor.setMatrices(matriz);

	}

	@Test
	public void test_carga_datos() {
		System.out.println("CombustionChamber Test");
		CargarValores();

		// assert statements
		System.out.print("Check PostCombustor parameters...");
		assertEquals("Idnum must be " + (GlobalConstants.getCombustionchamber() + 1), (GlobalConstants.getCombustionchamber() + 1), postcombustor.getIdnum());
		assertEquals("Num equations must be 3", 3, postcombustor.getNumequations());
		assertEquals("Num variables must be 9", 9, postcombustor.getNumvariables());
		assertEquals("Num constants must be 6", 6, postcombustor.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 1487825 PA in matrix", 1487825.0, postcombustor.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 1487825 PA in variable", 1487825.0, postcombustor.getPin(), 0.01);

		assertEquals("Tin must be 778 ºK in matrix", 778.0, postcombustor.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 778 ºK  in variable", 778.0, postcombustor.getTin(), 0.01);

		assertEquals("Min must be 1000 kg/seg in matrix", 1000.0, postcombustor.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1000 kg/seg  in variable", 1000.0, postcombustor.getMassFlow_in(), 0.01);

		assertEquals("Tout must be 1293.244 PA in matrix", 1293.2446, postcombustor.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 1293.244 PA  in variable", 1293.2446, postcombustor.getTout(), 0.01);
		
		assertEquals("n_fuel must be 0.98 PA in matrix", 0.98, postcombustor.getMatrices().getX_equations()[7][0], 0.01);
		assertEquals("n_fuel must be 0.98 PA  in variable", 0.98, postcombustor.getN_fuel(), 0.01);
		
		assertEquals("E_b must be 0.04 PA in matrix", 0.04, postcombustor.getMatrices().getX_equations()[8][0], 0.01);
		assertEquals("E_b must be 0.04 PA  in variable", 0.04, postcombustor.getE_b(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix object with the values of the last test (user
		// values)
		postcombustor.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", 88.0, postcombustor.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -7.0986, postcombustor.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, postcombustor.getMatrices().getFx_equations()[2][0], 0.01);
		
		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix object with the values of the last test (user
		// values)
		postcombustor.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = {{-0.960000325000000,0,0,1.00000063000000,0,0,0,0,1487825},{0,-0.984251969000000,-0.765748030000000,0,1,1.27986523200000,-33.4121982600000,-545.505277700000,0},{0,0,-1,0,0,1,-1,0,0},{1.00000063000000,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0},{0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,1}};
		
		
		System.out.print("JX...");
		for (int i = 0; i < referencia.length; i++) {
			for (int j = 0; j < referencia[i].length; j++) {

				if (showtest) {
					System.out.println("Jx_" + i + "_" + j + " must be " + referencia[i][j] + " is " + postcombustor.getMatrices().getJx()[i][j]);
				}
				assertEquals("Jx_" + i + "_" + j + " must be", referencia[i][j], postcombustor.getMatrices().getJx()[i][j], 0.01);

			}
		}

		System.out.println(" OK");

	}

	@Test
	public void test_engine() {
		System.out.println("init compresor simulation in engine test");
		project = new SimulationProject(postcombustor);
		project.BuildMatrix();

		// Check matrix

		assertEquals("Check Objet", postcombustor, project.getBlock());
		assertEquals("Check Numconstants", postcombustor.getNumconstants(), project.getNumconstants());
		assertEquals("Check Numequations", postcombustor.getNumequations(), project.getNumequations());
		assertEquals("Check Numrelations", postcombustor.getNumrelations(), project.getNumrelations());
		assertEquals("Check Numvariables", postcombustor.getNumvariables(), project.getNumvariables());
		assertEquals("Check Numvariables", postcombustor.getNumvariables(), project.getNumvariables());
	}

	@Test
	public void test_math_core() {

		core = new MathCore();
		core.setEng(project);
		core.RunIteration();
		double[][] resultado = new double[postcombustor.getNumvariables()][1];

		resultado[0][0] = 1487825;
		resultado[1][0] = 778;
		resultado[2][0] = 1000;
		resultado[3][0] = 1428312;
		resultado[4][0] = 1293.2446;
		resultado[5][0] = 1015.77912;
		resultado[6][0] = 15.77913044;
		resultado[7][0] = 0.98;
		resultado[8][0] = 0.04;
	

		for (int i = 0; i < postcombustor.getNumvariables(); i++) {
			assertEquals("Check resultados "+i, core.getResult().getMatrix()[i][0], resultado[i][0], 0.1);
		}

		System.out.println("Num the iterations was: " + core.getNumiteration());
		System.out.println("Timing was: " + core.getTime() + " milisecons");
		// aqui hay que hacer correr el core y ver que el resultado es el que
		// tenemso en excel.


	}

}
