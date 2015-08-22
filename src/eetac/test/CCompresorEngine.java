package eetac.test;

import eetac.mathcore.MathCore;
import eetac.model.MatrixCollection;
import eetac.model.basicstructure.Engine;
import eetac.model.realcomponent.Compressor;

public class CCompresorEngine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("init comrpesor in engine test");

		MathCore core = new MathCore();
		Engine engine = new Engine();
		Compressor compressor = new Compressor();

		// get matrix objet inicializated by compresor
		MatrixCollection matriz = compressor.getMatrices();

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

		engine.addBlock(compressor);
		engine.BuildMatrix();
		engine.PrintMatrix();
		
		core.setEng(engine);
		
		core.RunIteration();
	}

}
