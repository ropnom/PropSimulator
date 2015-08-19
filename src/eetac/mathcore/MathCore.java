package eetac.mathcore;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import eetac.model.basicstructure.Engine;

public class MathCore {

	protected Engine eng;
	protected double[][] X;
	protected double[][] Fx;
	protected double[][] Jx;

	protected double[][] X_new;

	protected double relativetolerance;
	protected double absoluttolerance;

	protected boolean finished = false;
	protected int Max_iteration = 100;

	public boolean check_solve() {

		boolean cumple = false;
		for (int i = 0; ((i < X.length) && !finished); i++) {
			if ((Math.abs(X_new[i][0] - X[i][0]) < absoluttolerance) || Math.abs(X_new[i][0] / X[i][0] - 1) < relativetolerance) {
				cumple = true;
			} else {
				cumple = false;
				break;
			}

		}

		return cumple;

	}

	public void GetMatrixEngine() {
		
		X = eng.getMatrix().getX_equations();
		
	}

	public void SetMatrixEngine() {

	}

	public void RunIteration() {

		DoubleMatrix Functions;
		DoubleMatrix Jacobian;
		DoubleMatrix variables =  new DoubleMatrix(X);
		DoubleMatrix Jacobian_inverse;

		int i = 0;

		while (i < Max_iteration && !finished) {

			GetMatrixEngine();
			Functions = new DoubleMatrix(Fx);
			Jacobian = new DoubleMatrix(Jx);
			variables = new DoubleMatrix(X);

			Jacobian_inverse = Solve.pinv(Jacobian);
			variables = variables.sub((Functions.dot(Jacobian_inverse)));

			X_new = variables.toArray2();
			SetMatrixEngine();

			this.finished = check_solve();

		}

		System.out.println(variables.rows + "x" + variables.columns + ": " + variables);

	}

}
