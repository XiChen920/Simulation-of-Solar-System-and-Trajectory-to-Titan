package phase3.input;

import phase3.calculations.Calculations;
import phase3.calculations.Vector3d;
/**
 * An enum which holds all of the data from NASA horizons at t0 = 1/04/2020
 */

public enum SpaceObject{
    Sun(1.988500e30,695700000,-6.806783239281648e+08,1.080005533878725e+09,6.564012751690170e+06,1.420511669610689e+01,-4.954714716629277e+00,3.994237625449041e-01),
    Mercury(3.302e23,2439.7E+3,6.047855986424127e+06,-6.801800047868888e+10,-5.702742359714534e+09,3.892585189044652e+04,2.978342247012996e+03, -3.327964151414740e+03),
    Venus(4.8685e24,6051.8E+3,-9.435345478592035e+10,5.350359551033670e+10,6.131453014410347e+09,-1.726404287724406e+04,-3.073432518238123e+04,5.741783385280979e-04),
    Earth(5.97219e24,6371.008E+3,-1.471922101663588e+11,-2.860995816266412e+10,8.278183193596080e+06,5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01),
    Moon(7.349e22,1.737E+6,-1.472343904597218e+11,-2.822578361503422e+10,1.052790970065631e+07,4.433121605215677e+03,-2.948453614110320e+04,8.896598225322805e+01),
    Mars(6.4171e23,3389.5E+3,-3.615638921529161e+10,-2.167633037046744e+11,-3.687670305939779e+09,2.481551975121696e+04,-1.816368005464070e+03,-6.467321619018108e+02),
    Jupiter(1.89813e27,69911E+3,1.781303138592153e+11,-7.551118436250277e+11,8.532838524802327e+08,1.255852555185220e+04,3.622680192790968e+03,-2.958620380112444e+02),
    Saturn(5.6834e26,58232E+3,6.328646641500651e+11,-1.358172804527507e+12,-1.578520137930810e+09,8.220842186554890e+03,4.052137378979608e+03,-3.976224719266916e+02),
    Titan(1.34553e23,5.15E+6,6.332873118527889e+11,-1.357175556995868e+12,-2.134637041453660e+09,3.056877965721629e+03,6.125612956428791e+03,-9.523587380845593e+02),
    Uranus(8.6813e25,2.5362E+6,2.395195786685187e+12,1.744450959214586e+12,-2.455116324031639e+10,-4.059468635313243e+03,5.187467354884825e+03,7.182516236837899e+01),
    Neptune(1.02413e26,2.4622E+6,4.382692942729203e+12,-9.093501655486243e+11,-8.227728929479486e+10,1.068410720964204e+03,5.354959501569486e+03,-1.343918199987533e+02);

    private final double mass;
    private final double radius;
    private Vector3d location;
    private Vector3d velocity;
    private Vector3d acc;
    private Vector3d[] reset = new Vector3d[3];

    SpaceObject(double mass, double radius, double x, double y, double z, double xv, double yv, double zv){
        this.mass = mass;
        this.radius = radius;
      
        this.location = new Vector3d(x,y,z);
        this.velocity = new Vector3d(xv,yv,zv);
        this.acc= new Vector3d(0,0,0);
        
        
        reset[0] = this.location;
        reset[1] = this.velocity;
        reset[2] = this.acc;
        
    }
    
    public void reset() {
    	this.setLocation(reset[0]);
    	this.setVelocity(reset[1]);
    	this.setAcc(reset[2]);
    }
    
    
    //getters & setters
    
    public Vector3d getInitialLocation() {
    	return reset[0];
    }
    
    public Vector3d getInitialVelocity() {
    	return reset[1];
    }
    
    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public Vector3d getLocation() {
        return location;
    }

    public Vector3d getVelocity() {
        return velocity;
    }
    
    public Vector3d getAcc() {
        return acc;
    }
    
    public void setLocation(Vector3d location) {
    	this.location = location;
    }
    
    public void setVelocity(Vector3d velocity) {
    	this.velocity = velocity;
    }
    
    public void setAcc(Vector3d acc) {
    	this.acc = acc;
    }
}

