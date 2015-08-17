package eetac.model;

import java.util.Arrays;

public class AuxMethods {

	public static double[][] Copy_matrix(double[][] matrix) {

		double[][] copy = new double[matrix.length][];
			
		for (int i = 0; i < matrix.length; i++) {
			copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
	        // For Java versions prior to Java 6 use the next:
	        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
	    }
	
		
		return copy;
		
	}
}
