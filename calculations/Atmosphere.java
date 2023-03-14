package phase3.calculations;

import phase3.Handler;
import phase3.input.ArtificialSpaceObject;

/**
 * a stochastic model of wind
 * 
 * The wind speed is mainly based on the current altitude of the lander
 * To simulate the changeable atmosphere, 
 * the wind speed can deviate randomly at most 20% based on the speed calculated.
 * 
 * And the direction of wind will also be generated randomly 
 * either to be right to left/left to right horizontally
 * 
 * @author Giulia Ciattaglia, Xi Chen
 *  
 */
public class Atmosphere {
	
	private Handler handler;
	private double wind; //m/s
	private static Vector2d windvector;
    private static final double airDensityTitan = calculateAirDensity(); //kg/m3
    private static double terminalVelocity;
    private final double area = 4 * 3.14 * 4;//4 * pi * r^2
    
    private double height;//height of lander to titan surface
    private static int createWindTime;//how many times we create a wind
    private static int windDirection;// 1  if left to right
    								 // -1 if right to left
    private static Vector2d windForce;
    
    private static double currentRVelocity = 0;
    
  
   


	public Atmosphere(Handler handler){
    	this.handler = handler;
    	terminalVelocity = calculateTerminalVelocity();
    	
    	//decide direction of wind when first creating wind
    	//so all the wind are same in direction but different in value
    	if(createWindTime==0) {
    		double randomDirection=Math.random();
    		if(randomDirection<0.5) {//left to right wind
    			windDirection= 1;
    		}
    		else {//right to left wind
    			windDirection= -1;
    		}
    	}
    	createWindTime++;
    	
    	this.wind=0;
    	this.height=ArtificialSpaceObject.Lander.getLocation().getY();
    	this.windForce=new Vector2d(0,0);
    }    
	
	public void update() {
		this.terminalVelocity = calculateTerminalVelocity();
		this.windForce = calculateWindForce();
	}
    
    
	/**
	 *  The maximum wind speed was recorded from an altitude of about 120 Km, with 432 Km/h
	 *	max wind speed = 432 km/h= 120 m/s
	 *
	 *	When the final seven Km were reached, it was able to drop in an almost straight line 
	 *	to breeze of 0.3 m/s
	 */
    public Vector2d calcWindSpeed() {   
    	
    	//Linear formula based on two points (0,0.3) and (60,120)
        // x = height (km)
        // y = wind speed (m/s)
		double windSpeed= ((120-0.3) / 60) * height + 0.3;
		
		//the max wind speed is 120m/s
    	if(windSpeed>120) {
    		windSpeed=120;
    	}
    	
        //Add a <=20% difference from calculated wind speed
    	//so we have a stochastic model
        double range = 1.2 - 0.8;//it can be 80%~120% of the original speed
        windSpeed = windSpeed * (0.8 + (Math.random() * range) );

    	//direction=  1,wind blows from left to right
    	//direction= -1,wind blows from right to left
    	if (windDirection==1) {
    		wind= windSpeed;
    	}
    	if (windDirection==-1) {
    		wind= -windSpeed;
    	}    	
    	
        return new Vector2d(wind, 0);//negative if right to left, vice versa
        
    }
    
    
    /**
     *	Fw = = 1/2 ¦Ñ * v^2 * A 
     *
	 *	Fw = wind force (N)
	 *  A = surface area (m2)
	 *  ¦Ñ = density of air (kg/m3)
	 *	v = wind speed (m/s)
     *  
     */
    public Vector2d calculateWindForce(){
    	
    	if(wind==0) {
    		windvector = calcWindSpeed();
    	}
    	
    	//calculate the wind force
    	double forceValue= 0.5 * airDensityTitan * wind * wind * area;
    	
    	//add direction
    	if(windDirection==1) {
    		forceValue=forceValue*1;
    	}
    	else if(windDirection==-1) {
    		forceValue=forceValue*-1;
    	}
    	
    	//rewrite it in vector2d
    	windForce =new Vector2d(forceValue,0);
    	

        return windForce;
    }  
    
    
    private static double calculateAirDensity() {
    	double nitrogen = 97;
    	double methane = 2.8;
    	double hydrogen = 0.2;
    	double nitrogenDensity = 1.145;
    	double methaneDensity = 0.656;
    	double hydrogenDensity = 0.0898;
    	
    	return ((nitrogen*nitrogenDensity + methane*methaneDensity + hydrogen*hydrogenDensity)/100);
    }
    
    private static double calculateTerminalVelocity() {
    	double mass = ArtificialSpaceObject.Lander.getNetMass();
    	double g = 1.352;
    	double drag = 2.09;
    	double r = 2; //radius of lander
    	double area = Math.PI*r*r;
    	return Math.sqrt((2*mass*g)/(airDensityTitan*area*drag));
    }
    
    
    
    //getters & setters
    public double getWind() {
    	return wind;
    }
    public void setWind(double windValue) {
    	this.wind=windValue;
    }
    public double getArea() {
    	return area;
    }
    public double getAirDensityTitan() {
    	return airDensityTitan;
    }
    public double getHeight() {
    	return height;
    }
    public void setHeight(double heightValue) {
    	this.height=heightValue;
    }
    public int getCreateWindTime() {
    	return createWindTime;//how many times we call the wind 
    }
    public int getWindDirection() {
    	return windDirection;
    }
    public void setWindDirection(int d) {
    	this.windDirection=d;
    }
    public static Vector2d getWindForce() {
    	return windForce;
    }
    
    public static Vector2d getWindVector() {
    	return Atmosphere.windvector;
    }
    
    public void setWindVector(Vector2d windvector) {
    	this.windvector = windvector;
    }
    
    public static double getTerminalVelocity() {
		return terminalVelocity;
	}
    
    public static void setCurrentRVelocity(double currentRVelocity) {
    	Atmosphere.currentRVelocity = currentRVelocity;
    }
    public static double setCurrentRVelocity() {
    	return currentRVelocity;
    }
    
   }
