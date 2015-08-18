package eetac.model;

import java.util.Arrays;

public class AuxMethods {

	public static double[][] Copy_matrix(double[][] matrix) {

		double[][] copy = new double[matrix.length][];

		for (int i = 0; i < matrix.length; i++) {
			copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
			// For Java versions prior to Java 6 use the next:
			// System.arraycopy(original[i], 0, result[i], 0,
			// original[i].length);
		}

		return copy;
	}

	public static double[][] Inset_in_matrix(double[][] matrix, double[][] inserted, int initx, int endx, int inity, int endy) {

		for (int i = initx; i < endx; i++) {
			// Vertical array i
			for (int j = inity; j < endy; j++) {
				// Horizontal array j
				matrix[j][i] = inserted[j - inity][i - initx];
			}
		}

		return matrix;

	}

	public static double[][] Extract_of_matrix(double[][] matrix, int inity, int endy) {

		double[][] extract = new double[endy - inity][0];

		for (int j = inity; j < endy; j++) {
			// Horizontal array j
			extract[j] = Arrays.copyOf(matrix[j], matrix[j].length);
		}

		return extract;

	}
}
