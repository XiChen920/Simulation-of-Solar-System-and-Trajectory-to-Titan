package phase3.solvers;

import phase3.calculations.Calculations;
import phase3.calculations.Vector3d;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;
/**
 *
 *
 *	4th order Runge Kutta method specifically for location, velocity, and acceleration calculations of planets, probes, etc.
 */

public class RungeKuttaSolver {
	

	
	/**
	 * @param stepsize	stepsize for the next point
	 * @param obj1		SpaceObject upon which the solver needs to act
	 */
	public static void solve(double stepsize, SpaceObject obj1) {
		Vector3d location0 = obj1.getLocation();
		Vector3d velocity0 = obj1.getVelocity();
		Vector3d acceleration0 = Calculations.getAcceleration(obj1);
		
		Vector3d k1 = (Vector3d) acceleration0.mul(stepsize);
		//System.out.println(obj1.name()+": "+k1);
		
		Vector3d velocity1 = new Vector3d(velocity0.addMul(0.5*stepsize, k1));
		Vector3d location1 = new Vector3d(location0.addMul(0.5*stepsize, velocity1));//maybe add *stepsize here too
		obj1.setVelocity(velocity1);
		obj1.setLocation(location1);
		Vector3d acceleration1 = Calculations.getAcceleration(obj1);
		
		Vector3d k2 = (Vector3d) acceleration1.mul(stepsize);
		//System.out.println(obj1.name()+": "+k2);
		
		Vector3d velocity2 = new Vector3d(velocity0.addMul(0.5*stepsize, k2));
		Vector3d location2 = new Vector3d(location0.addMul(0.5*stepsize, velocity2));
		obj1.setVelocity(velocity2);
		obj1.setLocation(location2);
		Vector3d acceleration2 = Calculations.getAcceleration(obj1);
		
		Vector3d k3 = (Vector3d) acceleration2.mul(stepsize);
		//System.out.println(obj1.name()+": "+k3);
		
		Vector3d velocity3 = new Vector3d(velocity0.addMul(stepsize, k3));
		Vector3d location3 = new Vector3d(location0.addMul(stepsize, velocity3));
		obj1.setVelocity(velocity3);
		obj1.setLocation(location3);
		Vector3d acceleration3 = Calculations.getAcceleration(obj1);
		
		Vector3d k4 = (Vector3d) acceleration3.mul(stepsize);
		//System.out.println(obj1.name()+": "+k4);
		
		Vector3d k = new Vector3d(0,0,0);
		k = (Vector3d) k.addMul(0.166666666666666667, k1.addMul(2, k2).addMul(2, k3).add(k4));
		Vector3d velocityFinal = (Vector3d) velocity0.add(k);
		//System.out.println(obj1.name()+": "+k);
		
		Vector3d distanceTraveled = Calculations.getDistanceTraveled(stepsize, velocity0, velocityFinal);
		obj1.setVelocity(velocityFinal);
		obj1.setLocation((Vector3d) location0.add(distanceTraveled));
		
		
	}
	
	/**
	 * @param stepsize	stepsize for the next point
	 * @param obj1		ArtificialSpaceObject upon which the solver needs to act
	 */
	public static void solve(double stepsize, ArtificialSpaceObject obj1) {
		Vector3d location0 = obj1.getLocation();
		Vector3d velocity0 = obj1.getVelocity();
		Vector3d acceleration0 = Calculations.getAcceleration(obj1);
		
		Vector3d k1 = (Vector3d) acceleration0.mul(stepsize);
		//System.out.println(obj1.name()+": "+k1);
		
		Vector3d velocity1 = new Vector3d(velocity0.addMul(0.5*stepsize, k1));
		Vector3d location1 = new Vector3d(location0.addMul(0.5*stepsize, velocity1));//maybe add *stepsize here too
		obj1.setVelocity(velocity1);
		obj1.setLocation(location1);
		Vector3d acceleration1 = Calculations.getAcceleration(obj1);
		
		Vector3d k2 = (Vector3d) acceleration1.mul(stepsize);
		//System.out.println(obj1.name()+": "+k2);
		
		Vector3d velocity2 = new Vector3d(velocity0.addMul(0.5*stepsize, k2));
		Vector3d location2 = new Vector3d(location0.addMul(0.5*stepsize, velocity2));
		obj1.setVelocity(velocity2);
		obj1.setLocation(location2);
		Vector3d acceleration2 = Calculations.getAcceleration(obj1);
		
		Vector3d k3 = (Vector3d) acceleration2.mul(stepsize);
		//System.out.println(obj1.name()+": "+k3);
		
		Vector3d velocity3 = new Vector3d(velocity0.addMul(stepsize, k3));
		Vector3d location3 = new Vector3d(location0.addMul(stepsize, velocity3));
		obj1.setVelocity(velocity3);
		obj1.setLocation(location3);
		Vector3d acceleration3 = Calculations.getAcceleration(obj1);
		
		Vector3d k4 = (Vector3d) acceleration3.mul(stepsize);
		//System.out.println(obj1.name()+": "+k4);
		
		Vector3d k = new Vector3d(0,0,0);
		k = (Vector3d) k.addMul(0.166666666666666667, k1.addMul(2, k2).addMul(2, k3).add(k4));
		Vector3d velocityFinal = (Vector3d) velocity0.add(k);
		//System.out.println(obj1.name()+": "+k);
		
		Vector3d distanceTraveled = Calculations.getDistanceTraveled(stepsize, velocity0, velocityFinal);
		obj1.setVelocity(velocityFinal);
		obj1.setLocation((Vector3d) location0.add(distanceTraveled));
		
		
	}
	



}

