package eetac.mathcore;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

public class MathCore {

	public double[][] X;
	public double[][] Fx;
	public double[][] Jx;
	public double relativetolerance;
	public double absoluttolerance;
	public boolean finished = false;
	public int Max_iteration = 100;

	public void RunIteration() {
		
		//Get and define matrix equations
		X = new double[2][1];
		X[0][0] = 5;
		X[1][0] = 5;
		Fx = new double[1][2];
		Jx = new double[2][2];

		variables = new DoubleMatrix(X);
		System.out.println(variables.rows + "x" + variables.columns + ": " + variables);

	}
	
	protected void getEquations(){
		
	}

	protected void iteration() {

	
		int i = 0;

		while (i < Max_iteration && !acabar) {

			Fx_cal(X[0][0], X[1][0]);
			Jx_cal(X[0][0], X[1][0]);

			Functions = new DoubleMatrix(Fx);
			Jacobian = new DoubleMatrix(Jx);

			Jacobian_inverse = Solve.pinv(Jacobian);
			variables = variables.sub((Functions.dot(Jacobian_inverse)));

			X = variables.toArray2();
			check_solve();

			System.out.println(variables.rows + "x" + variables.columns + ": " + variables);

		}

		System.out.println(Fx[0][0] + " " + Fx[0][1]);

	}

}
