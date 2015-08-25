package eetac.mathcore;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import eetac.model.basicstructure.Engine;

public class MathCore {

	protected Engine eng;
	protected double[][] X;
	protected double[][] Fx;
	protected double[][] Jx;
	protected boolean[] constants;

	protected double[][] X_new;

	protected double relativetolerance=0.01;
	protected double absoluttolerance=0.01;

	protected boolean finished = false;
	protected int Max_iteration = 100;

	public boolean check_solve() {

		boolean cumple = false;
		for (int i = 0; ((i < X.length) && !finished); i++) {
			
			if ((Math.abs(X_new[i][0] - X[i][0]) < absoluttolerance) || (Math.abs(X_new[i][0] / X[i][0] - 1) < relativetolerance) || (Fx[i][0]<absoluttolerance)) {
				cumple = true;
			} else {
				cumple = false;
				break;
			}

		}

		return cumple;

	}

	public void GetMatrixEngine() {
		
		X = eng.getMatrixJet().getX_equations();
		Fx = eng.getMatrixJet().getFx_equations();
		Jx = eng.getMatrixJet().getJx();
		constants = eng.getMatrixJet().getConstants();
		
	}
	
	public void ConstantsValues(){
		
		for(int m = 0;m<X_new.length;m++){
			if(constants[m]){
				X_new[m][0] = X[m][0];
				
			}
			System.out.println("X_"+m+" "+X_new[m][0]);
		}
		
	}

	public void SetMatrixEngine() {
		
		eng.UpdateMatrixinComponents(X_new);

	}

	public void RunIteration() {

		DoubleMatrix Functions;
		DoubleMatrix Jacobian;
		DoubleMatrix variables = null;

		int i = 0;

		while (i < Max_iteration && !finished) {

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

		}

		System.out.println(variables.rows + "x" + variables.columns + ": " + variables);

	}

	
	// SET and GETS
	
	public Engine getEng() {
		return eng;
	}

	public void setEng(Engine eng) {
		this.eng = eng;
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
	
	
	

}
