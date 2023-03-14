package phase3.calculations;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;

/**
 * A class which has methods for several different kind of calculations used throughout the program
 */
public class Calculations{
	/**
	 * Converts a horizontal position or width, of default screen width 2560, 
	 * to an integer relative to the actual screen width
	 * 
	 * @param 	defaultWidth	horizontal position or width in integers of an object
	 * @return	the result of the defaultWidth divided by 2560
	 */
	public static int fitToScreenWidth(int defaultWidth) {
		double temp = defaultWidth;
		double ratio = temp/2560;
		return (int) Math.round(Constants.getScreenWidth() * ratio);
	}
	
	/**
	 * Converts a vertical position or height, of default screen height 1440, 
	 * to an integer relative to the actual screen height
	 * 
	 * @param 	defaultWidth	vertical position or height in integers of an object
	 * @return	the result of the defaultHeight divided by 1440
	 */
	public static int fitToScreenHeight(int defaultHeight) {
		double temp = defaultHeight;
		double ratio = temp/1440;
		return (int) Math.round(Constants.getScreenHeight() * ratio);
	}
	
	
	/**
	 * Method which checks if two vector3d objects are equal
	 * @param a vector3d object
	 * @param b vector3d object
	 * @return false if they are unequal, true if they aren't
	 */
	public static boolean collisionDetection(Vector3d a, Vector3d b) {
		if(!a.equals(b)) {
			return false;
		}
		return true;
	}
	
	/**
	 * method which checks if a SpaceObject is colliding with any other SpaceObject
	 * @param obj1 SpaceObject to be checked
	 * @return false if there are no collisions between obj1 and other SpaceObjects,
	 * true if there is a collision
	 */
	public static boolean collisionDetection(SpaceObject obj1) {
		int obj1Index = SpaceObject.valueOf(obj1.name()).ordinal();
		
		for(int i = 0; i < 11; i++) {
    		if(i != obj1Index) {    
                if(collisionDetection(obj1, SpaceObject.values()[i])){
                	return true;
                }
    		}
    		
    	}
		return false;		
	}
	
	/**
	 * method which checks if a SpaceObject is colliding with another SpaceObject
	 * @param obj1 SpaceObject to be checked
	 * @param obj2 other SpaceObject
	 * @return false if the radius of obj1 doesn't intersect with the radius of obj2,
	 * true if they do
	 */
	public static boolean collisionDetection(SpaceObject obj1, SpaceObject obj2) {
		
		
		if(obj1.getLocation().dist(obj2.getLocation()) >= (obj1.getRadius() + obj2.getRadius()) || obj1.getMass() > obj2.getMass() ) {
			return false;
		}
		return true;
	}
	
	/**
	 * A method which calculates the location of the projectile based on the position of the planet it is on 
	 * and the target location it wants to go to.
	 * @param locationStartingPlanet	a vector3d object which is the location of the planet from which the projectile launches
	 * @param locationTargetPlanet		a vector3d object which is the target location of the target planet
	 * @param radiusStartingPlanet		a double which is the radius of the planet from which the projectile launches
	 * @return returns the location of the projectile for launch
	 */
	public static Vector3d getProbeSurfacePosition (Vector3d locationStartingPlanet, Vector3d locationTargetPlanet, double radiusStartingPlanet) {
		Vector3d dir = locationStartingPlanet.normalizeAB(locationTargetPlanet);
		Vector3d location = new Vector3d(locationStartingPlanet);
		location = (Vector3d) location.add(dir.mul(radiusStartingPlanet));
		return location;
	}
	
	/**
	 * 
	 * @param step stepsize
	 * @param vel0 velocity at point 0
	 * @param vel1 velocity at point 1
	 * @return average distance traveled between two points  
	 */
    public static Vector3d getDistanceTraveled(double step, Vector3d vel0, Vector3d vel1) {
    	Vector3d v0 = new Vector3d(vel0);
    	Vector3d v1 = new Vector3d(vel1);
    	
    	//average velocity
    	Vector3d va = new Vector3d(v0.add(v1).mul((double) 1/2));
    	
    	//distance traveled
    	Vector3d s = new Vector3d(va.mul(step));
    	return s;
    }
    
    public static Vector3d getFinalVelocity(double step, Vector3d vel0, Vector3d acceleration) {
    	Vector3d v1 = new Vector3d(vel0);
    	v1.add(acceleration.mul(step));
    	return v1;
    }
	
    /**
     * A method which calculates the acceleration on a planet based on the other objects in space exerting force upon that planet
     * 
     * The formula used is Newton's law of universal gravity
     * 
     * @param 	obj1	the planet, moon upon which the acceleration needs to be calculated
     * @return	the acceleration of that planet at the specific time it is called
     */
    public static Vector3d getAcceleration(SpaceObject obj1){
    	int obj1Index = SpaceObject.valueOf(obj1.name()).ordinal();
    	Vector3d finalAccelerationVector = new Vector3d(0,0,0);
    	
    	//if(!Calculations.collisionDetection(obj1)) {
	    	if(obj1Index > 0) {
	    		for(int i = 0; i < SpaceObject.values().length; i++) {
	        		if(i != obj1Index) {    
	                    double mass2= SpaceObject.values()[i].getMass();
	                    Vector3d locationObj1 = new Vector3d(obj1.getLocation());
	                    Vector3d locationObj2 = new Vector3d(SpaceObject.values()[i].getLocation());
	                    double d = locationObj1.dist(locationObj2);
	                    
	                   // if()
	                    
	                    Vector3d locationDifferenceVector = (Vector3d) locationObj2.sub(locationObj1);
	                    Vector3d accelerationVector = (Vector3d) locationDifferenceVector.mul(Constants.getG()*mass2/(d*d*d));
	                    
	                    finalAccelerationVector = (Vector3d) finalAccelerationVector.add(accelerationVector);
	        		}
	        		
	        	}
	    	}
    	//}
        return finalAccelerationVector;
    }
    
    /**
     * 
     * Acceleration method for the ArtificialSpaceObjects
     */
    public static Vector3d getAcceleration(ArtificialSpaceObject obj1){
    	Vector3d finalAccelerationVector = new Vector3d(0,0,0);
    	
    	//all spaceObjects are acting upon the spacecraft
		for(int i = 0; i < SpaceObject.values().length; i++) {    
            double mass2= SpaceObject.values()[i].getMass();
            Vector3d locationObj1 = new Vector3d(obj1.getLocation());
            Vector3d locationObj2 = new Vector3d(SpaceObject.values()[i].getInitialLocation());
            double d = locationObj1.dist(locationObj2);
            
            Vector3d locationDifferenceVector = (Vector3d) locationObj2.sub(locationObj1);
            Vector3d accelerationVector = (Vector3d) locationDifferenceVector.mul(Constants.getG()*mass2/(d*d*d));
            
            finalAccelerationVector = (Vector3d) finalAccelerationVector.add(accelerationVector);
    	}
        return finalAccelerationVector;
    }
    
    /**
     * 
     * Acceleration method for the planets and the moon; only used initially for verlet solver
     */
    public static Vector3d getInitialAcceleration(SpaceObject obj1){
    	int obj1Index = SpaceObject.valueOf(obj1.name()).ordinal();
    	Vector3d finalAccelerationVector = new Vector3d(0,0,0);
    	
    	if(!Calculations.collisionDetection(obj1)) {
	    	if(obj1Index > 0) {
	    		for(int i = 0; i < 11; i++) {
	        		if(i != obj1Index) {    
	                    double mass2= SpaceObject.values()[i].getMass();
	                    Vector3d locationObj1 = new Vector3d(obj1.getLocation());
	                    Vector3d locationObj2 = new Vector3d(SpaceObject.values()[i].getInitialLocation());
	                    double d = locationObj1.dist(locationObj2);
	                    
	                    Vector3d locationDifferenceVector = (Vector3d) locationObj2.sub(locationObj1);
	                    Vector3d accelerationVector = (Vector3d) locationDifferenceVector.mul(Constants.getG()*mass2/(d*d*d));
	                    
	                    finalAccelerationVector = (Vector3d) finalAccelerationVector.add(accelerationVector);
	        		}
	        		
	        	}
	    	}
    	}
        return finalAccelerationVector;
    }
    
    /**
     * 
     * Acceleration method for the ArtificialSpaceObject; only used initially for verlet solver
     */
    public static Vector3d getInitialAcceleration(ArtificialSpaceObject obj1){
    	Vector3d finalAccelerationVector = new Vector3d(0,0,0);
    
		for(int i = 0; i < SpaceObject.values().length; i++) { 
            double mass2= SpaceObject.values()[i].getMass();
            Vector3d locationObj1 = new Vector3d(obj1.getLocation());
            Vector3d locationObj2 = new Vector3d(SpaceObject.values()[i].getInitialLocation());
            double d = locationObj1.dist(locationObj2);
            
           // if()
            
            Vector3d locationDifferenceVector = (Vector3d) locationObj2.sub(locationObj1);
            Vector3d accelerationVector = (Vector3d) locationDifferenceVector.mul(Constants.getG()*mass2/(d*d*d));
            
            finalAccelerationVector = (Vector3d) finalAccelerationVector.add(accelerationVector);
		}

        return finalAccelerationVector;
    }
    
    
    
}
