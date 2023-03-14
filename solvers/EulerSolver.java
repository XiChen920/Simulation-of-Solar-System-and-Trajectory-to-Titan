package phase3.solvers;

import phase3.calculations.Calculations;
import phase3.calculations.LanderCalculations;
import phase3.calculations.Vector3d;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;
/**
 * 
 * Euler solver
 * 
 * Euler's method specifically for location, velocity, and acceleration calculations of planets, probes, etc.
 */
public class EulerSolver {
	
	/**
	 * @param step	stepsize for the next point
	 * @param obj1	SpaceObject upon which the solver needs to act
	 */
	public static void solve(double step, SpaceObject obj1){
		Vector3d location = new Vector3d(obj1.getLocation());
		Vector3d velocity1 = new Vector3d(obj1.getVelocity());
		Vector3d acceleration = new Vector3d(Calculations.getAcceleration(obj1));
		//System.out.println(obj1.name()+": "+location.toString());
		Vector3d velocity2 = (Vector3d) velocity1.addMul(step, acceleration);
		Vector3d location2 = new Vector3d(location);
		location2 = (Vector3d) location2.addMul(step, velocity2);
		obj1.setLocation(location2);
		obj1.setVelocity(velocity2);
	}
	
	/**
	 * @param step	stepsize for the next point
	 * @param obj1	ArtificialSpaceObject upon which the solver needs to act
	 */
	public static void solve(double step, ArtificialSpaceObject obj1){
		Vector3d location = new Vector3d(obj1.getLocation());
		Vector3d velocity1 = new Vector3d(obj1.getVelocity());
		Vector3d acceleration = new Vector3d(Calculations.getAcceleration(obj1));
		//9.81 m/s^2
		
		
		//Vector3d acceleration = new Vector3d(0,0,0);
		//System.out.println(obj1.name()+": "+location.toString());
		Vector3d velocity2 = (Vector3d) velocity1.addMul(step, acceleration);
		Vector3d location2 = new Vector3d(location);
		location2 = (Vector3d) location2.addMul(step, velocity2);
		obj1.setLocation(location2);
		obj1.setVelocity(velocity2);
	}
	
	public static void solveLander(double step, ArtificialSpaceObject obj){
		Vector3d location = new Vector3d(obj.getLocation());
		Vector3d velocity1 = new Vector3d(obj.getVelocity());
		Vector3d acceleration = new Vector3d(LanderCalculations.getLanderAcceleration(obj));
		

		Vector3d velocity2 = (Vector3d) velocity1.addMul(step, acceleration);
		Vector3d location2 = new Vector3d(location);
		location2 = (Vector3d) location2.addMul(step, velocity2);
		
		
		if(location2.getY() < 0) {
			location2.setY(0);
		}
		obj.setLocation(location2);
		obj.setVelocity(velocity2);
		obj.setAcceleration(acceleration);
	}
	

	public static void solveTheta(double step, ArtificialSpaceObject obj) {
		double theta = obj.getTheta();
		double firstDerivativeTheta = obj.getdTheta();
		double secondDerivativeTheta = obj.getddTheta();
		
		firstDerivativeTheta += step*secondDerivativeTheta;
		theta += step*firstDerivativeTheta;
		obj.setdTheta(firstDerivativeTheta);
		obj.setTheta(theta);
	}
}
