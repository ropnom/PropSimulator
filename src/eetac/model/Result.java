package eetac.model;

public class Result {
	
	private double[][] matrix;
	private long time;
	private int iterations;
	
	public Result(){
	
		this.time = 0;
		this.iterations = 0;
		
	}
	
	public Result(double[][] m, long t, int i){
		this.matrix = m;
		this.time = t;
		this.iterations = i;
		
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	
	

}
