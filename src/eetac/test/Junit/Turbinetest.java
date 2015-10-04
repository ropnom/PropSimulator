package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.basicstructure.SimulationProject;
import eetac.model.realcomponent.Compressor;
import eetac.model.realcomponent.Turbine;

public class Turbinetest {

	protected static Turbine turbine;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static SimulationProject project;

	public void CargarValores() {
		turbine = new Turbine();
		// init values of test
		turbine.setPin(1480000);
		turbine.setTin(1200);
		turbine.setMassFlow_in(1016);
		turbine.setPout(399600);
		turbine.setTout(900);
		turbine.setMassFlow_out(1016);
		turbine.setPi(0.3);
		turbine.setTau(0.8);
		turbine.setWork(325000000);
		turbine.setN_i(0.9);
		turbine.setN_p(0.85);
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
		X[0][0] = 1480000;
		constants[0] = true;
		X[1][0] = 1200;
		constants[1] = true;
		X[2][0] = 1016;
		constants[2] = true;
		X[3][0] = 399600;
		constants[3] = true;
		X[4][0] = 900;
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
		assertEquals("Idnum mus be " + (GlobalConstants.getTurbine() + 1), (GlobalConstants.getTurbine() + 1), turbine.getIdnum());
		assertEquals("Num equations must be 6", 6, turbine.getNumequations());
		assertEquals("Num variables must be 11", 11, turbine.getNumvariables());
		assertEquals("Num constants must be 5", 5, turbine.getNumconstants());
		System.out.println(" OK");

		// assert statements
		System.out.print("Check Inputs...");
		assertEquals("Pin must be 1480000 PA in matrix", 1480000.0, turbine.getMatrices().getX_equations()[0][0], 0.01);
		assertEquals("Pin must be 1480000 PA in variable", 1480000.0, turbine.getPin(), 0.01);

		assertEquals("Tin must be 1200 ºKin matrix", 1200.0, turbine.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 1200 ºK  in variable", 1200.0, turbine.getTin(), 0.01);

		assertEquals("Min must be 1016 kg/seg in matrix", 1016.0, turbine.getMatrices().getX_equations()[2][0], 0.01);
		assertEquals("Min must be 1016 kg/seg  in variable", 1016.0, turbine.getMassFlow_in(), 0.01);

		assertEquals("Pout must be 399600 PA in matrix", 399600.0, turbine.getMatrices().getX_equations()[3][0], 0.01);
		assertEquals("Pout must be 399600 PA  in variable", 399600.0, turbine.getPout(), 0.01);

		assertEquals("Tout must be 900 PA in matrix", 900.0, turbine.getMatrices().getX_equations()[4][0], 0.01);
		assertEquals("Tout must be 900 PA  in variable", 900.0, turbine.getTout(), 0.01);

		System.out.println(" OK");

	}

	@Test
	public void test_funciones() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		turbine.Simulate();
		// assert functions
		System.out.print("Functions Inputs...");
		assertEquals("Funcion 0 must be", -44400.0, turbine.getMatrices().getFx_equations()[0][0], 0.01);
		assertEquals("Funcion 1 must be", -60, turbine.getMatrices().getFx_equations()[1][0], 0.01);
		assertEquals("Funcion 2 must be", 0, turbine.getMatrices().getFx_equations()[2][0], 0.01);
		assertEquals("Funcion 3 must be", -0.049631459, turbine.getMatrices().getFx_equations()[3][0], 0.01);
		assertEquals("Funcion 4 must be", -0.065674235, turbine.getMatrices().getFx_equations()[4][0], 0.01);
		assertEquals("Funcion 5 must be", -27653600, turbine.getMatrices().getFx_equations()[5][0], 0.01);
		System.out.println(" OK");
	}

	@Test
	public void test_jacobiano() {

		// Regenerate the matrix objet with the values of the last test (user
		// values)
		turbine.Simulate();
		// create reference matrix

		boolean showtest = false;

		double[][] referencia = {{-0.299999956000000,0,0,1.00000004700000,0,0,-1480000,0,0,0,0},{0,-0.800000000000000,0,0,1,0,0,-1200,0,0,0},{0,0,-1,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,1,-2.05867689900000,0,0,-0.432233736000000},{0,0,0,0,0,0,1,-2.09158439100000,0,-0.464667363000000,0},{0,-1175512,-347099.999800000,0,1175512,-347099.999800000,0,0,1.00016593900000,0,0},{1.00000063000000,0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0},{0,0,0,1.00000004700000,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,0,0}};
		
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
		
		System.out.println("init compresor simulation in engine test");
		project = new SimulationProject(turbine);
		project.BuildMatrix();

		// Check matrix

		assertEquals("Check Objet", turbine, project.getBlock());
		assertEquals("Check Numconstants", turbine.getNumconstants(), project.getNumconstants());
		assertEquals("Check Numequations", turbine.getNumequations(), project.getNumequations());
		assertEquals("Check Numrelations", turbine.getNumrelations(), project.getNumrelations());
		assertEquals("Check Numvariables", turbine.getNumvariables(), project.getNumvariables());
		assertEquals("Check Numvariables", turbine.getNumvariables(), project.getNumvariables());
	}

	@Test
	public void test_math_core() {

		core = new MathCore();
		core.setEng(project);
		core.RunIteration();
		double[][] resultado = new double[11][1];

		resultado[0][0] = 1480000;
		resultado[1][0] = 1200;
		resultado[2][0] = 1016;
		resultado[3][0] = 399600;
		resultado[4][0] = 900;
		resultado[5][0] = 1016;
		resultado[6][0] = 0.27;
		resultado[7][0] = 0.75;
		resultado[8][0] = 352653600;
		resultado[9][0] = 0.89612816;
		resultado[10][0] = 0.8795256;

		for (int i = 0; i < 11; i++) {
			assertEquals("Check resultados "+i, core.getResult().getMatrix()[i][0], resultado[i][0], 0.1);
		}

		System.out.println("Num the iterations was: " + core.getNumiteration());
		System.out.println("Timing was: " + core.getTime() + " milisecons");

	}

}
