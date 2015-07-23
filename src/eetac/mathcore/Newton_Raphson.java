package eetac.mathcore;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

public class Newton_Raphson {

	public static double[][] X;
	public static double[][] Fx;
	public static double[][] Jx;
	public static double ephsilon = 0.0000000001;
	public static double tolerance = 0.01;

	public static DoubleMatrix variables;
	public static DoubleMatrix Functions;
	public static DoubleMatrix Jacobian;
	public static DoubleMatrix Jacobian_inverse;

	public static boolean acabar = false;
	public static int Max_iteration = 100;

	public static void check_solve() {

		if (Math.abs(Fx[0][0]) <= tolerance && Math.abs(Fx[0][1]) <= tolerance)
			acabar = true;

	}

	public static double F1(double x1, double x2) {

		return (Math.pow(x1, 2) - 10 * x1 + Math.pow(x2, 2) + 8);

	}

	public static double F2(double x1, double x2) {

		return (x1 * Math.pow(x2, 2) + x1 - 10 * x2 + 8);
	}

	public static void Fx_cal(double x1, double x2) {

		Fx[0][0] = F1(x1, x2);
		Fx[0][1] = F2(x1, x2);

	}

	public static void Jx_cal(double x1, double x2) {
		
		//Esta bien definido el jacobiano??¿¿¿

		Jx[0][0] = -(Fx[0][0] - F1(x1 + ephsilon, x2)) / ephsilon;
		Jx[0][1] = -(Fx[0][1] - F2(x1 + ephsilon, x2)) / ephsilon;
		Jx[1][0] = -(Fx[0][0] - F1(x1, x2 + ephsilon)) / ephsilon;
		Jx[1][1] = -(Fx[0][1] - F2(x1, x2 + ephsilon)) / ephsilon;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//X = new double[][] { { 5 }, { 5 } };
		X = new double[2][1];
		X[0][0] = 5;
		X[1][0] = 5;
		Fx = new double[1][2];
		Jx = new double[2][2];

		variables = new DoubleMatrix(X);
		System.out.println(variables.rows + "x" + variables.columns + ": " + variables);
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
		
		System.out.println(Fx[0][0] +" "+Fx[0][1]);

		// double[][] data = new double[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9,
		// 10 }, { 11, 12, 13, 14, 15 } };
		// DoubleMatrix matrix = new DoubleMatrix(data);
		//
		// DoubleMatrix vector = new DoubleMatrix(new double[] { 3, 3, 3, 3, 3
		// });
		// DoubleMatrix result = matrix.mmul(vector);
		//
		// System.out.println(matrix.rows + "x" + matrix.columns + ": " +
		// matrix);
		// System.out.println(vector.rows + "x" + vector.columns + ": " +
		// vector);
		// System.out.println(result.rows + "x" + result.columns + ": " +
		// result);

	}
}
