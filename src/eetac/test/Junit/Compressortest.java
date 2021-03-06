package eetac.test.Junit;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import org.junit.Test;

import com.google.gson.Gson;

import eetac.mathcore.MathCore;
import eetac.model.GlobalConstants;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.SimulationProject;
import eetac.model.realcomponent.Compressor;

public class Compressortest {

	protected static Compressor compressor;
	protected static MatrixCollection matriz;
	protected static double[][] X;
	protected static boolean[] constants;

	protected static MathCore core;
	protected static SimulationProject project;

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
		compressor.genX();

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

		assertEquals("Tin must be 290 �Kin matrix", 290.0, compressor.getMatrices().getX_equations()[1][0], 0.01);
		assertEquals("Tin must be 290 �K  in variable", 290.0, compressor.getTin(), 0.01);

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

		double[][] referencia = { { -18.0000020200000, 0, 0, 1.00000063000000, 0, 0, -97000, 0, 0, 0, 0 }, { 0, -2.70000000000000, 0, 0, 1, 0, 0, -290, 0, 0, 0 }, { 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, -24.3110700200000, 0, 0, -74.0974526300000 }, { 0, 0, 0, 0, 0, 0, 1, -23.9583244000000, 0, -50.9138662900000, 0 }, { 0, -1005000, 467324.999600000, 0, 1005000, 467324.999600000, 0, 0, 1.00016593900000, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

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

		System.out.println("init compresor simulation in engine test");
		project = new SimulationProject(compressor);
		project.BuildMatrix();

		// Check matrix

		assertEquals("Check Objet", compressor, project.getBlock());
		assertEquals("Check Numconstants", compressor.getNumconstants(), project.getNumconstants());
		assertEquals("Check Numequations", compressor.getNumequations(), project.getNumequations());
		assertEquals("Check Numrelations", compressor.getNumrelations(), project.getNumrelations());
		assertEquals("Check Numvariables", compressor.getNumvariables(), project.getNumvariables());
		assertEquals("Check Numvariables", compressor.getNumvariables(), project.getNumvariables());

	}

	@Test
	public void test_math_core() {

		core = new MathCore();
		core.setEng(project);
		core.RunIteration();
		double[][] resultado = new double[compressor.getNumvariables()][1];

		resultado[0][0] = 97000;
		resultado[1][0] = 290;
		resultado[2][0] = 1000;
		resultado[3][0] = 1720000;
		resultado[4][0] = 755;
		resultado[5][0] = 1000;
		resultado[6][0] = 17.73195876;
		resultado[7][0] = 2.603448276;
		resultado[8][0] = -467325000;
		resultado[9][0] = 0.794528399;
		resultado[10][0] = 0.858593504;

		for (int i = 0; i < compressor.getNumvariables(); i++) {
			assertEquals("Check resultados " + i, core.getResult().getMatrix()[i][0], resultado[i][0], 0.1);
		}

		System.out.println("Num the iterations was: " + core.getNumiteration());
		System.out.println("Timing was: " + core.getTime() + " milisecons");
		// aqui hay que hacer correr el core y ver que el resultado es el que
		// tenemso en excel.

		try {
			Gson gson = new Gson();
			String json = gson.toJson(project);
			System.out.println(Paths.get("").toAbsolutePath().toString());
			FileOutputStream fout = new FileOutputStream(Paths.get("").toAbsolutePath().toString() + "\\compresor.jprop");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeUTF(json);
			oos.close();
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
