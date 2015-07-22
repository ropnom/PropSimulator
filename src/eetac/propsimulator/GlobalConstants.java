package eetac.propsimulator;

public  class GlobalConstants {
	
	protected static double delta = 0.0001;
	protected static double derivate_min = 0.001;

	
	
	
	public static double getDelta() {
		return delta;
	}

	public static void setDelta(double delta) {
		GlobalConstants.delta = delta;
	}

	public static double getDerivate_min() {
		return derivate_min;
	}

	public static void setDerivate_min(double derivate_min) {
		GlobalConstants.derivate_min = derivate_min;
	}
	
	

}
