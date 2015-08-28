package eetac.model;

public  class GlobalConstants {
	
	//Math constants
	protected static double delta = 0.0001;
	
	//Reference number
	protected static short engine = 0;	
	protected static short difusser = 200;
	protected static short compresor = 300;
	protected static short combustionchamber = 500;
	protected static short turbine = 700;
	protected static short postcombustion = 900;
	protected static short nozzle = 1000;
	protected static short axes = 1200;
	protected static short axes_box = 1400;
	
	
	
	public static double getDelta() {
		return delta;
	}

	public static void setDelta(double delta) {
		GlobalConstants.delta = delta;
	}

	public static short getEngine() {
		return engine;
	}

	public static void setEngine(short engine) {
		GlobalConstants.engine = engine;
	}

	public static short getDifusser() {
		return difusser;
	}

	public static void setDifusser(short difusor) {
		GlobalConstants.difusser = difusor;
	}

	public static short getCompresor() {
		return compresor;
	}

	public static void setCompresor(short compresor) {
		GlobalConstants.compresor = compresor;
	}

	public static short getCombustionchamber() {
		return combustionchamber;
	}

	public static void setCombustionchamber(short combustionchamber) {
		GlobalConstants.combustionchamber = combustionchamber;
	}

	public static short getTurbine() {
		return turbine;
	}

	public static void setTurbine(short turbine) {
		GlobalConstants.turbine = turbine;
	}

	public static short getPostcombustion() {
		return postcombustion;
	}

	public static void setPostcombustion(short postcombustion) {
		GlobalConstants.postcombustion = postcombustion;
	}

	public static short getNozzle() {
		return nozzle;
	}

	public static void setNozzle(short nozzle) {
		GlobalConstants.nozzle = nozzle;
	}

	public static short getAxes() {
		return axes;
	}

	public static void setAxes(short axes) {
		GlobalConstants.axes = axes;
	}

	public static short getAxes_box() {
		return axes_box;
	}

	public static void setAxes_box(short axes_box) {
		GlobalConstants.axes_box = axes_box;
	}

		
	

}
