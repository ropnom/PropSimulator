package eetac.model;

public class AuxMethods {

	public static double[][] Copy_matrix(double[][] matrix) {

		double[][] copy = new double[matrix[1].length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		
		return copy;
		
	}
}
