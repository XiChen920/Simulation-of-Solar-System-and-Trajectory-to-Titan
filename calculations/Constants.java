package phase3.calculations;
/**
 * A class in which mainly constant variables are located. 
 * It is also used as a class where variables used throughout the whole project are located
 *
 *
 */
public class Constants {
	private static final double AU = 1.49597870691e+11;
	private static final double G = 6.67408 * Math.pow(10,-11);
	private static double stepsize = 100;
	private static int enginespeed = 100;
	private static int drawprecision = 5;
	private static double initialProbeVelocity = 60000.0; //meters per second (60 km/s)
	private static int screenWidth;
	private static int screenHeight;
	private static int solverIndex = 0;
	private static double massFlowRate = 5e-6;
	private static int controllerIndex = 1; //0 is feedback, 1 is open loop
	
	
	public Constants() {
	}
	
	public static int getSolver() {
		return solverIndex;
	}

	public static String getSolverName() {
		String[] solver = {"Euler", "Runge Kutta", "Verlet"};
		return solver[solverIndex];
	}
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	
	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static double getAU() {
		return AU;
	}
	
	public static double getG() {
		return G;
	}
	
	public static double getInitialProbeVelocity() {
		return initialProbeVelocity;
	}
	
	public static double getRealSpeed() {
		return stepsize * enginespeed*60;
	}
	
	public static double getStepSize() {
		return stepsize;
	}
	
	public static int getEngineSpeed() {
		return enginespeed;
	}
	
	public static int getDrawPrecision() {
		return drawprecision;
	}
	
	public static void setStepSize(double d) {
		Constants.stepsize = d;
	}
	
	public static void setEngineSpeed(int enginespeed) {
		Constants.enginespeed = enginespeed;
	}
	
	public static void mulEngineSpeed(double enginespeed) {
		Constants.enginespeed *= enginespeed;
	}
	
	public static void setDrawPrecision(int drawprecision) {
		Constants.drawprecision = drawprecision;
	}
	
	public static void setScreenWidth(int screenWidth) {
		Constants.screenWidth = screenWidth;	
	}
	
	public static void setScreenHeight(int screenHeight) {
		Constants.screenHeight = screenHeight;
	}
	
	public static void setSolver(int solverIndex) {
		Constants.solverIndex = solverIndex;
	}

	public static void mulStepSize(double stepsize) {
		Constants.stepsize *= stepsize;
		
	}

	public static void addEngineSpeed(double d) {
		Constants.enginespeed += d;
	}
	
	public static void subEngineSpeed(double d) {
		Constants.enginespeed -= d;
	}
	
	public static void addStepSize(double d) {
		Constants.stepsize += d;
	}
	
	public static void subStepSize(double d) {
		Constants.stepsize -= d;
	}
	
	public static void setMassFlowRate(double massFlowRate) {
		Constants.massFlowRate = massFlowRate;
	}
	
	public static double getMassFlowRate() {
		return Constants.massFlowRate;
	}

	public static int getController() {
		return Constants.controllerIndex;
	}
	
	public static void setController(int controllerIndex) {
		Constants.controllerIndex = controllerIndex;
	}
	
	
}
