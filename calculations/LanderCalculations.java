package phase3.calculations;

import phase3.input.ArtificialSpaceObject;

import phase3.solvers.EulerSolver;

/**
 * A class which holds the main function for calculating motion for a lander on titan
 *
 */
public class LanderCalculations {

	private static double u;

	public static Vector3d getLanderAcceleration(ArtificialSpaceObject obj) {
		
		Vector3d finalAccelerationVector = new Vector3d(0,0,0);
		
		double r = 2;
		double theta = obj.getTheta();
		double g = 1.352; //m/s^2
		
		Vector2d Ftleft = new Vector2d(Forces.calculateLanderForceThrusterVector(theta, ThrustLander.getCurrentForceThrusters()[0]));
		Vector2d Ftmain = new Vector2d(Forces.calculateLanderForceThrusterVector(theta, ThrustLander.getCurrentForceThrusters()[1]));
		Vector2d Ftright = new Vector2d(Forces.calculateLanderForceThrusterVector(theta, ThrustLander.getCurrentForceThrusters()[2]));
	
		
		double torque = calculateTorque(r, Ftleft, Ftright); 
		double inertia = calculateInertia(ArtificialSpaceObject.Lander.getNetMass(), r);
		double v = torque/inertia;//equal to second derivative of theta, equal to ddTheta within ArtificialSpaceObjects
	    	
    	obj.setddTheta(v);
    	
 
		switch(Constants.getSolver()) {
		    case 0:
		   		EulerSolver.solveTheta(Constants.getStepSize(), obj);
		   		break;
		   	case 1:
		   		break;
		    case 2:
		   		break;	
		}
		
    	u = ArtificialSpaceObject.Lander.getut();
    	
    	if(Math.abs(obj.getVelocity().getY() + g*Constants.getStepSize()) > Atmosphere.getTerminalVelocity()) {
    		g = 0;
    	}
    	Vector2d awind;
    	if(Constants.getController() == 0) {
    		awind = Forces.calculateAccelerationForce(Atmosphere.getWindForce());
    	} else {
    		awind = new Vector2d(0,0);
    	}
    	
    	
    	double xSecondDerivative = u*Math.sin(theta);
    	double ySecondDerivative = u*Math.cos(theta)-g;
    	
    	finalAccelerationVector = (Vector3d) finalAccelerationVector.add(new Vector3d(xSecondDerivative, ySecondDerivative, 0));
    	if(ArtificialSpaceObject.Lander.getVelocity().norm() + awind.norm()*Constants.getStepSize() < 120) {
    		finalAccelerationVector = (Vector3d) finalAccelerationVector.add(awind);
    	}

    	return finalAccelerationVector;
    }

    
    /**
     * 
     * @param r		distance between pivot point and point of force input	
     * @param F		force applied to the object
     * @return torque
     */
    public static double calculateTorque(double r, Vector2d Fleft, Vector2d Fright) {
    	double Fl = Fleft.norm(); //clockwise force negative
    	double Fr = Fright.norm(); //counter clockwise force positive
    	double dF = Fl-Fr;
    	double torque = r * dF;
    	return torque;
    }
    
    

	/**
     * 
     * @param mass 	mass of an object
     * @param r		distance between pivot point and point of force input 
     * @return inertia
     */
    public static double calculateInertia(double mass, double r) {
    	return mass * Math.pow(r, 2);
    }
    
    public static double getU() {
		return u;
	}


	public static void setU(double u) {
		LanderCalculations.u = u;
	}
    
}
