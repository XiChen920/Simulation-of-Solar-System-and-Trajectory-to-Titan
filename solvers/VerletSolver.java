package phase3.solvers;

import phase3.calculations.Calculations;
import phase3.calculations.Constants;
import phase3.calculations.Vector3d;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;


public class VerletSolver {
	
	
	/**
	 * @param stepsize	stepsize for the next point
	 * @param obj1		SpaceObject upon which the solver needs to act
	 * @param t			current time
	 */
	public static void solve(double stepsize, SpaceObject obj1, double t) {
		if(t==0){
			initialSolve(stepsize, obj1);
		} else {
			Vector3d location_nplus1 = new Vector3d(obj1.getVelocity());
			location_nplus1 = (Vector3d) location_nplus1.addMul(stepsize*stepsize, Calculations.getAcceleration(obj1));
			Vector3d newVelocity = new Vector3d(location_nplus1.mul(2).sub(obj1.getLocation()));
			obj1.setLocation(location_nplus1);
			obj1.setVelocity(newVelocity);
		} 
	}
	
	private static void initialSolve(double stepsize, SpaceObject obj1) {
		Vector3d location_0 = obj1.getInitialLocation();
		Vector3d velocity_0 = obj1.getInitialVelocity();
		Vector3d acceleration_0 = Calculations.getInitialAcceleration(obj1);
		
		Vector3d location_1 = new Vector3d(location_0);
		location_1 = (Vector3d) location_1.addMul(stepsize, velocity_0);
		location_1 = (Vector3d) location_1.addMul(0.5*stepsize*stepsize, acceleration_0);
		Vector3d velocity_1 = new Vector3d(location_1.mul(2).sub(location_0));
		obj1.setLocation(location_1); 
		obj1.setVelocity(velocity_1);
	}
	
	/**
	 * @param stepsize	stepsize for the next point
	 * @param obj1		ArtificialSpaceObject upon which the solver needs to act
	 * @param t			current time
	 */
	public static void solve(double stepsize, ArtificialSpaceObject obj1, double t) {
		if(t==0){
			initialSolve(stepsize, obj1);
		} else {
			Vector3d location_nplus1 = new Vector3d(obj1.getVelocity());
			location_nplus1 = (Vector3d) location_nplus1.addMul(stepsize*stepsize, Calculations.getAcceleration(obj1));
			Vector3d newVelocity = new Vector3d(location_nplus1.mul(2).sub(obj1.getLocation()));
			obj1.setLocation(location_nplus1);
			obj1.setVelocity(newVelocity);
		} 
	}
	
	private static void initialSolve(double stepsize, ArtificialSpaceObject obj1) {
		Vector3d location_0 = obj1.getInitialLocation();
		Vector3d velocity_0 = obj1.getInitialVelocity();
		Vector3d acceleration_0 = Calculations.getInitialAcceleration(obj1);
		
		Vector3d location_1 = new Vector3d(location_0);
		location_1 = (Vector3d) location_1.addMul(stepsize, velocity_0);
		location_1 = (Vector3d) location_1.addMul(0.5*stepsize*stepsize, acceleration_0);
		Vector3d velocity_1 = new Vector3d(location_1.mul(2).sub(location_0));
		obj1.setLocation(location_1); 
		obj1.setVelocity(velocity_1);
	}
}
