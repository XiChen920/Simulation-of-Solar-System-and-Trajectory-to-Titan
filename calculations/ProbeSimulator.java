package phase3.calculations;

import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;
import phase3.solvers.EulerSolver;
import phase3.solvers.RungeKuttaSolver;
import phase3.solvers.VerletSolver;

/**
 * A class which simulates the trajectory of the probe
 *
 *
 */
public class ProbeSimulator implements ProbeSimulatorInterface{

	@Override
	public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {
		Vector3dInterface initialProbeLocation = new Vector3d(getLocationOnEarthSurface(p0));
		Vector3dInterface initialProbeVelocity = new Vector3d(getProbeVelocity(v0));
		
		ArtificialSpaceObject.SpaceShip.setLocation((Vector3d) initialProbeLocation);
		ArtificialSpaceObject.SpaceShip.setVelocity((Vector3d) initialProbeVelocity);
		
		Vector3dInterface[] trajectoryProbe = new Vector3d[ts.length];
		
		double secondsPassed = 0;
		trajectoryProbe[0] = initialProbeLocation;
		double h = ts[1];
		
		switch(Constants.getSolver()) {
			case 0:
				
			//loop until end of evolution is reached
			for(int i = 1; i<trajectoryProbe.length; i++) {
				
				//first calculate the position for every planet on next step
				for(int j = 0; j<SpaceObject.values().length; j++) {
					EulerSolver.solve(h, SpaceObject.values()[j]);
				}
				
				//then calculate the position of spaceship after planets moved and itself moves
				EulerSolver.solve(h, ArtificialSpaceObject.Probe);
				trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
				secondsPassed += h;
				if(i+1 < ts.length) {
					h = ts[i+1]-ts[i];
				}
				
			}
			break;
			case 1:
			//loop until end of evolution is reached
			for(int i = 1; i<trajectoryProbe.length; i++) {
				
				//first calculate the position for every planet on next step
				for(int j = 0; j<SpaceObject.values().length; j++) {
					RungeKuttaSolver.solve(h, SpaceObject.values()[j]);
				}
				
				//then calculate the position of spaceship after planets moved and itself moves
				RungeKuttaSolver.solve(h, ArtificialSpaceObject.Probe);
				trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
				secondsPassed += h;
				if(i+1 < ts.length) {
					h = ts[i+1]-ts[i];
				}
			}
			break;
			case 2:
			//loop until end of evolution is reached
			for(int i = 1; i<trajectoryProbe.length; i++) {
				
				//first calculate the position for every planet on next step
				for(int j = 0; j<SpaceObject.values().length; j++) {
					VerletSolver.solve(h, SpaceObject.values()[j], secondsPassed);
				}
				
				//then calculate the position of spaceship after planets moved and itself moves
				VerletSolver.solve(h, ArtificialSpaceObject.Probe, secondsPassed);
				trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
				secondsPassed += h;
				if(i+1 < ts.length) {
					h = ts[i+1]-ts[i];
				}
			}
			break;
	
		}
		return trajectoryProbe;
	}

	@Override
	public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {
		Vector3dInterface initialProbeLocation = new Vector3d(getLocationOnEarthSurface(p0));
		Vector3dInterface initialProbeVelocity = new Vector3d(getProbeVelocity(v0));	
		
		ArtificialSpaceObject.Probe.setLocation((Vector3d) initialProbeLocation);
		ArtificialSpaceObject.Probe.setVelocity((Vector3d) initialProbeVelocity);
		
		double residualStepsize = 0;
		Vector3dInterface[] trajectoryProbe;
		if(tf%h==0) {
			trajectoryProbe = new Vector3d[(int) (Math.round(tf/h)+1)];
		} 
		else {
			residualStepsize = tf%h;
			trajectoryProbe = new Vector3d[(int) (Math.round(tf/h)+2)];
		}
	
		double secondsPassed = 0;
		trajectoryProbe[0] = initialProbeLocation;
		
		switch(Constants.getSolver()) {
			case 0:
				
				//loop until end of evolution is reached
				for(int i = 1; i<trajectoryProbe.length; i++) {
					if(i == trajectoryProbe.length-1) {
						h = residualStepsize;
					}
					//first calculate the position for every planet on next step
					for(int j = 0; j<SpaceObject.values().length; j++) {
						EulerSolver.solve(h, SpaceObject.values()[j]);
					}
					
					//then calculate the position of spaceship after planets moved and itself moves
					EulerSolver.solve(h, ArtificialSpaceObject.Probe);
					trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
					secondsPassed += h;
				}
				break;
			case 1:
				//loop until end of evolution is reached
				for(int i = 1; i<trajectoryProbe.length; i++) {
					if(i == trajectoryProbe.length-1) {
						h = residualStepsize;
					}
					//first calculate the position for every planet on next step
					for(int j = 0; j<SpaceObject.values().length; j++) {
						RungeKuttaSolver.solve(h, SpaceObject.values()[j]);
					}
					
					//then calculate the position of spaceship after planets moved and itself moves
					RungeKuttaSolver.solve(h, ArtificialSpaceObject.Probe);
					trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
					secondsPassed += h;
				}
				break;
			case 2:
				//loop until end of evolution is reached
				for(int i = 1; i<trajectoryProbe.length; i++) {
					if(i == trajectoryProbe.length-1) {
						h = residualStepsize;
					}
					//first calculate the position for every planet on next step
					for(int j = 0; j<SpaceObject.values().length; j++) {
						VerletSolver.solve(h, SpaceObject.values()[j], secondsPassed);
					}
					
					//then calculate the position of spaceship after planets moved and itself moves
					VerletSolver.solve(h, ArtificialSpaceObject.Probe, secondsPassed);
					trajectoryProbe[i] = ArtificialSpaceObject.Probe.getLocation();
					secondsPassed += h;
				}
				break;
		}

		return trajectoryProbe;
	}
	
	
	
	/**
	 * 
	 * the position of the anything on the surface of the earth is equal to 
	 * position vector (earth relative to the barycentre) + 
	 * direction vector from earth center to anywhere (which has length exactly equal to radius of earth)
	 * 
	 * direction vector = 
	 * @param p0 initial position of the probe compared to earth
	 * @return position of the probe relative to earth + position of earth
	 */
	private Vector3dInterface getLocationOnEarthSurface(Vector3dInterface p0) {
		Vector3dInterface positionEarth = new Vector3d(SpaceObject.Earth.getLocation());
		Vector3dInterface positionProbe = new Vector3d(positionEarth);
		positionProbe = positionProbe.add(p0);
		//System.out.println("probe position: "+positionProbe.toString());
		return positionProbe;
	}
	
	/**
	 * @param v0 initial velocity of probe compared to earth
	 * @return velocity of probe relative to earth + velocity of earth
	 */
	private Vector3dInterface getProbeVelocity(Vector3dInterface v0) {
		Vector3dInterface velocityEarth = new Vector3d(SpaceObject.Earth.getVelocity());
		Vector3dInterface velocityProbe = new Vector3d(velocityEarth);
		velocityProbe = velocityProbe.add(v0);
		//System.out.println("probe velocity: "+velocityProbe.toString());
		return velocityProbe;
	}

}
