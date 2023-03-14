package phase3.input;

import phase3.calculations.Calculations;
import phase3.calculations.Vector2d;
import phase3.calculations.Vector3d;

/**
 * An enum which initializes the data for the probe, spaceship, etc at t0 = 1/4/2020
 */

public enum ArtificialSpaceObject{
    SpaceShip(78000,0,2,2,0,0,0,5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01),
    Probe(15000,0,0,0,0,0,0,0,0,0),
    Lander(6000,6000,4,4,0,150000,0,0,0,0);


    private double netMass, fuelMass, dryMass;
    
    private final double width, height;
    private Vector3d location;
    private Vector3d velocity;
    private Vector3d acceleration;
    private double ut;

    private Vector3d[] resetVectors = new Vector3d[3];
    private double[] resetDoubles = new double[3];
    private double theta, dTheta, ddTheta;

    ArtificialSpaceObject(double dryMass, double fuelMass, double width, double height, double x, double y, double z, double xv, double yv, double zv){
        this.dryMass = dryMass;
        this.fuelMass = fuelMass;
        this.netMass = dryMass + fuelMass;

        this.width = width;
        this.height = height;
      
        this.location = new Vector3d(x,y,z);
        if(this.ordinal() == 0) {
        	setSpaceShipProperties();
        }	
        
        this.velocity = new Vector3d(xv,yv,zv);
        this.acceleration= new Vector3d(0,0,0);

        
        this.theta = 0;
        this.dTheta = 0;
        this.ddTheta = 0;
        
        this.ut = 0;
        
        resetVectors[0] = this.location;
        resetVectors[1] = this.velocity;
        resetVectors[2] = this.acceleration;
        resetDoubles[0] = this.theta;
        resetDoubles[1] = this.dTheta;
        resetDoubles[2] = this.ddTheta;
      
    }
    
    public void reset() {

    	this.setLocation(resetVectors[0]);
    	this.setVelocity(resetVectors[1]);
    	this.setAcceleration(resetVectors[2]);
    	this.setTheta(resetDoubles[0]);
    	this.setdTheta(resetDoubles[1]);
    	this.setddTheta(resetDoubles[2]);
    }
    
    public void setSpaceShipProperties() {
		this.setLocation(Calculations.getProbeSurfacePosition(SpaceObject.Earth.getLocation(), SpaceObject.Titan.getLocation(), SpaceObject.Earth.getRadius()));
	}
    
    
    //getters & setters
    
    public Vector3d getInitialLocation() {
    	return resetVectors[0];
    }
    
    public Vector3d getInitialVelocity() {
    	return resetVectors[1];
    }
    
    public double getNetMass() {
        return netMass;
    }
    
    public double getFuelMass() {
        return fuelMass;
    }
    
    public double getDryMass() {
    	return dryMass;
    }

    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
    	return height;
    }

    public Vector3d getLocation() {
        return location;
    }

    public Vector3d getVelocity() {
        return velocity;
    }
    
    public Vector3d getAcceleration() {
        return acceleration;
    }
    
    public void setLocation(Vector3d location) {
    	this.location = location;
    }
    
    public void setLocation(Vector2d loc) {
    	Vector3d l = new Vector3d(loc.getX(), loc.getY(), 0);
 	
    	this.location = l;
    }
    
    public void setVelocity(Vector3d velocity) {
    	this.velocity = velocity;
    }
    
    public void setAcceleration(Vector3d acceleration) {
    	this.acceleration = acceleration;

    }
    
    public void setFuelMass(double fuelmass) {
    	double dFuelMass = this.fuelMass - fuelmass;
    	this.netMass = this.netMass - dFuelMass;
    	this.fuelMass = fuelmass;
    }

    public double getTheta() {
      return theta;
    }

    public void setTheta(double theta) {
      this.theta = theta;
    }

    public double getdTheta() {
      return dTheta;
    }

    public void setdTheta(double dTheta) {
      this.dTheta = dTheta;
    }

    public double getddTheta() {
      return ddTheta;
    }

    public void setddTheta(double ddTheta) {
      this.ddTheta = ddTheta;
    }
    
    public boolean isUpsideDown() {
    	double degrees = this.theta * 180/Math.PI;
    	if(Math.floor((degrees-90)/180)%2 == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean degrees() {
    	double degrees = this.theta * 180/Math.PI;
    	if(Math.floor(degrees/90)%2 == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public double getut() {
    	return ut;
    }
    
    public void setut(double ut) {
    	this.ut = ut;
    }
    
}

