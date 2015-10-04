package eetac.mathcore;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import eetac.model.Result;
import eetac.model.basicstructure.Engine;
import eetac.model.basicstructure.SimulationProject;

public class MathCore {
	
	protected Result result;

	// DEfines variables of problem
	protected SimulationProject simproject;
	protected double[][] X;
	protected double[][] Fx;
	protected double[][] Jx;
	protected boolean[] constants;

	// Iteration and results
	protected double[][] X_new;

	// AuxVariables
	protected double relativetolerance = 0.001;
	protected double absoluttolerance = 0.01;

	// Iteration Finished
	protected boolean finished = false;
	protected int Max_iteration = 100;

	// Propierties of Iteration
	protected int numiteration = 0;
	protected long time = 0;

	public boolean check_solve() {

		boolean cumple = false;
		for (int i = 0; ((i < X.length) && !finished); i++) {

//			if ((Math.abs(X_new[i][0] - X[i][0]) < absoluttolerance) || (Math.abs(X_new[i][0] / X[i][0] - 1) < relativetolerance) || (Fx[i][0] < absoluttolerance)) {
//				cumple = true;
//			} else {
//				cumple = false;
//				break;
//			}
			
			if ((Math.abs(Fx[i][0]) < absoluttolerance)) {
				cumple = true;
			} else {
				cumple = false;
				break;
			}

		}

		return cumple;

	}

	public void GetMatrixEngine() {

		//simproject.BuildMatrix();
		X = simproject.getMatrixProyect().getX_equations();
		Fx = simproject.getMatrixProyect().getFx_equations();
		Jx = simproject.getMatrixProyect().getJx();
		constants = simproject.getMatrixProyect().getConstants();

	}

	public void ConstantsValues() {

		for (int m = 0; m < X_new.length; m++) {
			if (constants[m]) {
				X_new[m][0] = X[m][0];

			}
			System.out.println("X_" + m + " " + X_new[m][0]);
		}

	}

	public void SetMatrixEngine() {

		simproject.UpdateMatrixinComponents(X_new);

	}

	public void RunIteration() {

		DoubleMatrix Functions;
		DoubleMatrix Jacobian;
		DoubleMatrix variables = null;

		this.numiteration = 0;
		this.time = System.currentTimeMillis();
		while (this.numiteration < Max_iteration && !finished) {
						
			GetMatrixEngine();
			Functions = new DoubleMatrix(Fx);
			Jacobian = new DoubleMatrix(Jx);
			variables = new DoubleMatrix(X);

			variables = variables.sub(Solve.pinv(Jacobian).mmul(Functions));

			X_new = variables.toArray2();

			System.out.println();
			ConstantsValues();
			System.out.println();

			SetMatrixEngine();

			this.finished = check_solve();

			this.numiteration++;

		}

		this.time = System.currentTimeMillis() - this.time;
		
		System.out.println(variables.rows + "x" + variables.columns + ": " + variables);
		
		result = new Result(X_new, time, numiteration);

	}

	// SET and GETS

	public SimulationProject getEng() {
		return simproject;
	}

	public void setEng(SimulationProject eng) {
		this.simproject = eng;
	}

	public double[][] getX() {
		return X;
	}

	public void setX(double[][] x) {
		X = x;
	}

	public double[][] getFx() {
		return Fx;
	}

	public void setFx(double[][] fx) {
		Fx = fx;
	}

	public double[][] getJx() {
		return Jx;
	}

	public void setJx(double[][] jx) {
		Jx = jx;
	}

	public double[][] getX_new() {
		return X_new;
	}

	public void setX_new(double[][] x_new) {
		X_new = x_new;
	}

	public double getRelativetolerance() {
		return relativetolerance;
	}

	public void setRelativetolerance(double relativetolerance) {
		this.relativetolerance = relativetolerance;
	}

	public double getAbsoluttolerance() {
		return absoluttolerance;
	}

	public void setAbsoluttolerance(double absoluttolerance) {
		this.absoluttolerance = absoluttolerance;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getMax_iteration() {
		return Max_iteration;
	}

	public void setMax_iteration(int max_iteration) {
		Max_iteration = max_iteration;
	}

	public boolean[] getConstants() {
		return constants;
	}

	public void setConstants(boolean[] constants) {
		this.constants = constants;
	}

	public int getNumiteration() {
		return numiteration;
	}

	public void setNumiteration(int numiteration) {
		this.numiteration = numiteration;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public SimulationProject getSimproject() {
		return simproject;
	}

	public void setSimproject(SimulationProject simproject) {
		this.simproject = simproject;
	}
	
	

}
