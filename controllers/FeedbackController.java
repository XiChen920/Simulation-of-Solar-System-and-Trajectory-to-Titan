package phase3.controllers;

import phase3.Handler;
import phase3.calculations.Atmosphere;
import phase3.calculations.Constants;
import phase3.calculations.Forces;
import phase3.calculations.ThrustLander;
import phase3.calculations.Vector2d;
import phase3.input.ArtificialSpaceObject;
import phase3.solvers.EulerSolver;
/**
 * A controller which responds to live changes within its state (unfinished)
 *
 */
public class FeedbackController {
	private Handler handler;
	private Atmosphere atmosphere;
	
	
	public FeedbackController(Handler handler) {
		this.handler = handler;
		atmosphere = handler.getAtmosphere();
	}
	
	
	public void solve() {
		ArtificialSpaceObject.Lander.setut(computeThrustAccelerationX());
		EulerSolver.solveLander(Constants.getStepSize(), ArtificialSpaceObject.Lander);

	}
	
	
	
	
	private double computeThrustAccelerationX() {
		double x0 = ArtificialSpaceObject.Lander.getLocation().getX();
		double x0dot = ArtificialSpaceObject.Lander.getVelocity().getX();
		double x0dotdot = ArtificialSpaceObject.Lander.getAcceleration().getX();
		double theta0 = ArtificialSpaceObject.Lander.getTheta();
		double theta0dot = ArtificialSpaceObject.Lander.getdTheta();
		double t = 1;
		
		double dx = 0+x0;
		
		double fside;
		if(x0>0) {
			fside = -25.0;
			double[] thrusters = {0, 0, 25};
			ThrustLander.setCurrentForceThrusters(thrusters);
		} else if(x0<0) {
			fside = 25.0;
			double[] thrusters = {25, 0, 0};
			ThrustLander.setCurrentForceThrusters(thrusters);
		} else {
			fside = 0;
		}
		
		//calculate theta as a result of side thrust
		double theta = (fside/2*ArtificialSpaceObject.Lander.getNetMass())*t*t + theta0dot*t + theta0;
		
		
		//depending on the angle after applying sidethrust, ut is the acceleration needed by main thruster to achieve x = 0
		double ut;
		if(theta != 0) {
			ut = -(x0 + x0dot*t)/(t*t*Math.sin(theta));
		} else {
			ut = 0;
		}
		if(ut < 0) {
			ut = 0;
		}
		
		
		return ut;
	}
	
	private void sensor() {
		double lookintofuture = 1; //10 seconds
		if(!predictFutureX(lookintofuture)) {
			
		}
	}
	

	private boolean predictFutureX(double t) {
		double xfinal = ArtificialSpaceObject.Lander.getLocation().getX() + ArtificialSpaceObject.Lander.getVelocity().getX()*t + ArtificialSpaceObject.Lander.getAcceleration().getX()*t*t;
		double dx = 0.1;
		if(Math.abs(xfinal-ArtificialSpaceObject.Lander.getLocation().getX()) < dx) {
			return true;
		}
		return false; 
	}
	
	
	
	
}
