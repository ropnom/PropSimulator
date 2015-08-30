package eetac.model.propierties;

import java.util.List;

public class AirPropierties {

	// Constant air propierties Gamma
	protected static double gamma_c_air = 1.4;
	protected static double gamma_c_airfuel = 1.333;

	// These variables are (gamma/(gamma-1)
	protected static double gamma_gamma_1_air = 3.5;
	protected static double gamma_gamma_1_airfuel = 4.003003;

	// Air propierties
	protected static double cp_c = 1005;
	protected static double R_c = 8.3144621;

	// Air fuel propierties
	protected static double cp_c_fuel = 1005;
	protected static double R_c_fuel = 8.3144621;

	// Nozzle Critic Pressure Ratio
	// (2/(gamma+1)^(gama/(gama-1)
	protected static double CPRR_air_fuel = 0.53983392;

	// Table Air propierties
	protected List<Fluid> listfluids;

	// Gets y Sets
	public static double getGamma_c_air() {
		return gamma_c_air;
	}

	public static void setGamma_c_air(double gamma_c_air) {
		AirPropierties.gamma_c_air = gamma_c_air;
	}

	public static double getGamma_c_airfuel() {
		return gamma_c_airfuel;
	}

	public static void setGamma_c_airfuel(double gamma_c_airfuel) {
		AirPropierties.gamma_c_airfuel = gamma_c_airfuel;
	}

	public static double getGamma_gamma_1_air() {
		return gamma_gamma_1_air;
	}

	public static void setGamma_gamma_1_air(double gamma_politropic_air) {
		AirPropierties.gamma_gamma_1_air = gamma_politropic_air;
	}

	public static double getGamma_gamma_1_airfuel() {
		return gamma_gamma_1_airfuel;
	}

	public static void setGamma_gamma_1_airfuel(double gamma_politropic_airfuel) {
		AirPropierties.gamma_gamma_1_airfuel = gamma_politropic_airfuel;
	}

	public static double getCp_c() {
		return cp_c;
	}

	public static void setCp_c(double cp_c) {
		AirPropierties.cp_c = cp_c;
	}

	public static double getR_c() {
		return R_c;
	}

	public static void setR_c(double r) {
		R_c = r;
	}

	public static double getCp_c_fuel() {
		return cp_c_fuel;
	}

	public static void setCp_c_fuel(double cp_c_fuel) {
		AirPropierties.cp_c_fuel = cp_c_fuel;
	}

	public static double getR_c_fuel() {
		return R_c_fuel;
	}

	public static void setR_c_fuel(double r_c_fuel) {
		R_c_fuel = r_c_fuel;
	}

	public static double getCPRR_air_fuel() {
		return CPRR_air_fuel;
	}

	public static void setCPRR_air_fuel(double cPRR_air_fuel) {
		CPRR_air_fuel = cPRR_air_fuel;
	}
	
	

}
