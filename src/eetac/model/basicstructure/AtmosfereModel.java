package eetac.model.basicstructure;


public class AtmosfereModel extends BasicBlock {

	// Constants
	protected static double R_gas = 286.9; // J (kg K)^-1
	protected static double Gamma = 1.4;
	protected static double Psea_level = 101325; // PA
	protected static double Ptropopause = 22632; // PA
	protected static double T_sea_level = 288.16; // º K
	protected static double T_tropopause = 216.65; // º K
	protected static double Rho_sea_level = 1.225; // kg/m^3

	protected float height = 0; // meters
	protected double velocity = 0; // m/seg

	protected double Pstatic = 0;
	protected double Pdinamic = 0;
	protected double Presssure = 0;
	protected double Machnumber = 0;
	protected double soundspeed = 0;
	protected double Temperature = 0;
	protected double Rho = 0;

	protected double Tt_0 = 0;
	protected double Pt_0 = 0;
	protected double Teta_0 = 0;
	protected double Delta_0 = 0;

	public AtmosfereModel(int height, double velocity) {
		this.height = height;
		this.velocity = velocity;
		GetAtmosphereValues();
	}

	public AtmosfereModel() {

	}

	public void GetAtmosphereValues() {

		CalTemperature();
		CalPstastic();
		CalRho();
		CalPressure();
		CalMach();
		Calstaticpropierties();
	}

	public void CalTemperature() {

		if (height >= 11000) {
			this.Temperature = T_tropopause;
		} else {
			this.Temperature = T_sea_level - 0.0065 * height;
		}
	}

	public void CalRho() {

		this.Rho = Pstatic / (8.314472 * Temperature);
	}

	public void CalPstastic() {

		if (height < 11000) {
			this.Pstatic = Psea_level * Math.pow((Temperature / T_sea_level), 5.2561);
		} else {
			// estara mal revisar que funcion Rgas
			this.Pstatic = Ptropopause * Math.exp(-0.00544431 * (height - 11000));
		}

	}

	public void CalPressure() {

		this.Pdinamic = this.Rho * Math.pow(this.velocity, 2) / 2;
		this.Presssure = this.Pstatic + this.Pdinamic;

	}

	public void CalMach() {
		this.soundspeed = Math.sqrt(Gamma * R_gas * Temperature);
		this.Machnumber = velocity /soundspeed ;
	}

	public void Calstaticpropierties() {

		Tt_0 = Temperature * (1 + (Gamma - 1) / 2 * Math.pow(Machnumber, 2));
		Pt_0 = Presssure * Math.pow((1 + (Gamma - 1) / 2 * Math.pow(Machnumber, 2)), (Gamma / (Gamma - 1)));
		Teta_0 = Tt_0/Temperature;
		Delta_0 = Pt_0/Presssure;
	}

	// GETs and SETs
	

	public static double getPtropopause() {
		return Ptropopause;
	}

	public double getTeta_0() {
		return Teta_0;
	}

	public void setTeta_0(double teta_0) {
		Teta_0 = teta_0;
	}

	public double getDelta_0() {
		return Delta_0;
	}

	public void setDelta_0(double delta_0) {
		Delta_0 = delta_0;
	}

	public static double getR_gas() {
		return R_gas;
	}

	public static void setR_gas(double r_gas) {
		R_gas = r_gas;
	}

	public static double getGamma() {
		return Gamma;
	}

	public static void setGamma(double gamma) {
		Gamma = gamma;
	}

	public double getTt_0() {
		return Tt_0;
	}

	public double getSoundspeed() {
		return soundspeed;
	}

	public void setSoundspeed(double soundspeed) {
		this.soundspeed = soundspeed;
	}

	public void setTt_0(double tt_0) {
		Tt_0 = tt_0;
	}

	public double getPt_0() {
		return Pt_0;
	}

	public void setPt_0(double pt_0) {
		Pt_0 = pt_0;
	}

	public static void setPtropopause(double ptropopause) {
		Ptropopause = ptropopause;
	}

	public static double getT_tropopause() {
		return T_tropopause;
	}

	public static void setT_tropopause(double t_tropopause) {
		T_tropopause = t_tropopause;
	}

	public double getMachnumber() {
		return Machnumber;
	}

	public void setMachnumber(double machnumber) {
		Machnumber = machnumber;
	}

	public static double getPsea_level() {
		return Psea_level;
	}

	public static void setPsea_level(double psea_level) {
		Psea_level = psea_level;
	}

	public static double getT_sea_level() {
		return T_sea_level;
	}

	public static void setT_sea_level(double t_sea_level) {
		T_sea_level = t_sea_level;
	}

	public static double getRho_sea_level() {
		return Rho_sea_level;
	}

	public static void setRho_sea_level(double rho_sea_level) {
		Rho_sea_level = rho_sea_level;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getPstatic() {
		return Pstatic;
	}

	public void setPstatic(double pstatic) {
		Pstatic = pstatic;
	}

	public double getPdinamic() {
		return Pdinamic;
	}

	public void setPdinamic(double pdinamic) {
		Pdinamic = pdinamic;
	}

	public double getPresssure() {
		return Presssure;
	}

	public void setPresssure(double presssure) {
		Presssure = presssure;
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	public double getRho() {
		return Rho;
	}

	public void setRho(double rho) {
		Rho = rho;
	}

}
