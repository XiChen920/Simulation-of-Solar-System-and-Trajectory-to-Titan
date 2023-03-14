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
 * A class which makes the lander land, without letting it crash. (Rotations are not incorporated within this controller)
 *
 */
public class OpenLoopController {
	private Handler handler;
	private Atmosphere atmosphere;
	private int stage;
	
	public OpenLoopController(Handler handler) {
		this.handler = handler;
		this.atmosphere = handler.getAtmosphere();
		stage = 1;
	}
	
	public void solve() {
		slowdown();
		EulerSolver.solveLander(Constants.getStepSize(), ArtificialSpaceObject.Lander);
	}
	
	private void slowdown() {
		double currentheight = ArtificialSpaceObject.Lander.getLocation().getY();
	
		double targetspeed = 0;//m/s
		double currentforce = 0;
		Vector2d f = new Vector2d(0, 0);

		if(ArtificialSpaceObject.Lander.getFuelMass() > 0) {
				if(currentheight < 8000 && currentheight > 2000) {
					System.out.println();
					targetspeed = 15;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				if(currentheight <= 2000 && currentheight > 1000) {
					targetspeed = 10;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}

				if(currentheight <= 1000 && currentheight > 100) {
					targetspeed = 4;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				if(currentheight <= 100 && currentheight >= 20) {
					targetspeed = 1;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				if(currentheight <= 20 && currentheight >= 10) {
					targetspeed = 0.5;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				if(currentheight <= 10 && currentheight >= 5) {
					targetspeed = 0.25;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				if(currentheight <= 5 && currentheight >= 0) {
					targetspeed = 0.05;
					currentforce = 16000;
					ThrustLander.getCurrentForceThrusters()[1] = currentforce;
					f.setY(currentforce);
					ArtificialSpaceObject.Lander.setut(Forces.calculateAccelerationForce(f).norm());	
					if(Math.abs(ArtificialSpaceObject.Lander.getVelocity().getY())<targetspeed) {	
						ThrustLander.getCurrentForceThrusters()[1] = 0;
						ArtificialSpaceObject.Lander.setut(0);	
					}
				}
				ArtificialSpaceObject.Lander.setFuelMass(ArtificialSpaceObject.Lander.getFuelMass() - ThrustLander.getCurrentMassFlowrate()[1]*Constants.getStepSize());

		} else {
			ThrustLander.getCurrentForceThrusters()[1] = 0;
			ArtificialSpaceObject.Lander.setut(0);	
		}
		
		
		
	}
		
		
		
}	

