package phase3.calculations;

import phase3.input.ArtificialSpaceObject;

/**
 * A class used to define functions needed for calculating different kinds of forces
 *
 */
public class Forces {
	
	/**
	 * 
	 * @param Ft	force provided
	 * @return total acceleration of thrust upon space ship
	 */
	public static Vector2d calculateAccelerationForce(Vector2d F) {
		Vector2d Ft = new Vector2d(F);
		return (Vector2d) Ft.mul(1/ArtificialSpaceObject.Lander.getNetMass());
	}
	/**
	 * method which calculates the total amount of force acting upon the lander 
	 * @param Fg		vector force of gravity acting upon lander
	 * @param Ftmain	vector force of main thruster acting upon lander
	 * @param Ftleft	vector force of left thruster acting upon lander
	 * @param Ftright	vector force of right thruster acting upon lander
	 * @return all forces acting upon lander
	 */
	public static Vector2d calculateLanderTotalForce(Vector2d Fg, Vector2d Fatmos, Vector2d Ftleft, Vector2d Ftmain,Vector2d Ftright) {
		Vector2d Fs = new Vector2d();
		Fs = (Vector2d) Fs.add(Fg);
		Fs = (Vector2d) Fs.add(Fatmos);
		Fs = (Vector2d) Fs.add(Ftmain);
		Fs = (Vector2d) Fs.add(Ftleft);
		Fs = (Vector2d) Fs.add(Ftright);
		return Fs;
	}
	
	/**
	 * method which calculates the amount of gravity acting upon the lander
	 * @param mass		net mass of the lander
	 * @param gravity	gravity of the specific planet acting upon the lander
	 * @return force of gravity
	 */
	public static Vector2d calculateLanderForceGravity(double mass, double gravity) {
		Vector2d Fg = new Vector2d(0,-mass*gravity);
		return (Vector2d) Fg;
	}
	
	/**
	 * method which calculates the amount of gravity acting upon the lander
	 * @param mass		net mass of the lander
	 * @param acceleration	acceleration caused by thrusters
	 * @return force of thrusters
	 */
	public static double calculateLanderForceLength(double mass, double acceleration) {
		return mass*acceleration;
	}
	
	
	/**
	 * method which calculates the amount of thrust acting upon the lander
	 * @param Ftmain	vector force of main thruster acting upon lander
	 * @param Ftleft	vector force of left thruster acting upon lander
	 * @param Ftright	vector force of right thruster acting upon lander
	 * @return 
	 */
	public static Vector2d calculateLanderTotalForceThrust(Vector2d Ftleft, Vector2d Ftmain, Vector2d Ftright) {
		Vector2d Ft = new Vector2d();
		Ft = (Vector2d) Ft.add(Ftmain);
		Ft = (Vector2d) Ft.add(Ftleft);
		Ft = (Vector2d) Ft.add(Ftright);
		return Ft;
	}
	
	public static Vector2d calculateLanderForceThrusterVector(double theta, double thrusterForceLength) {
		double Fx = Math.sin(theta)*thrusterForceLength;
		double Fy = Math.cos(theta)*thrusterForceLength;
		Vector2d Ft = new Vector2d(Fx, Fy);
		return Ft;
	}
}
