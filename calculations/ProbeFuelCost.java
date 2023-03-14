package phase3.calculations;
/**
 * class calculate the fuel cost
 * 
 * CURRENTLY NOT USED!
 * 
 * the main formual:
 * (M+m)*v0=M*v(t)+(v0-ve)*m
 * Thurst=F=M*a=ve*mass flow rate
 * 
 *
 * 
 *
 */
public class ProbeFuelCost {

	//mass
	private static final double MASS_ROCKET = 7.8e4;//dry mass of craft in kg
	private static double current_mass_fuel=7.8e4; //Assume we bring 7.8e4 kg fuel
	private static double current_mass_all=7.8e4*2; //mass of craft + fuel
	private double mass_fuel_cost;// mass that lost this time
	
	
	//As an extra, you could try a two-fuel solution. 
	//For take-off use combustion fuel thrusters, and for in-flight corrections, 
	//use ion-fuel or magnetoplasmadynamic thrusters.
	/**
	 * from project announcement Rocket Specifics
	 * 
	 * 1.mode 1
	 * Combustion fuel:
		Effective exhaust velocity 4 km/s = 4e3 m/s
		Maximum thrust 30 MN = 3e7 N
		Mass flow rate = 7500 kg/s
		
		2.mode 2
		Ion fuel:
		Effective exhaust velocity 20km/s = 2e4 m/s
		Maximum thrust 0.1N = 1e-1 N
		Mass flow rate = 0.000005 kg/s

		3.mode 3
		Magnetoplasmadynamic:
		Effective exhaust velocity 15-60 km/s
		Maximum thrust 2.5-25 N
		Mass flow rate = ve/f
	 */
	//mode 1
	private static final double effective_exhaust_velocity_1=4e3;//4000m/s
	private static final double thrust_1=3e7;//30000000N
	//mode 2
	private static final double effective_exhaust_velocity_2=2e4;//20000m/s
	private static final double thrust_2=1e-1;//0.1N
	//mode 3
	private static final double effective_exhaust_velocity_3=6e4;//60000m/s
	private static final double thrust_3=1e1;//10N
	
	
	//engine data 
	private double effective_exhaust_velocity;//(m/s)
	private double thrust;//(N)
	private double mass_flow_rate;//kg/s
	
	//velocity
	private Vector3d current_probe_velocity;//vel before
	private Vector3d final_probe_velocity;//vel after
	private Vector3d velocity_change;//delta vel
		
	//acceleration
	private Vector3d acceleration;
	private double acc_value; //the value of acceleration. using .norm()
	
	private double time;  //the time length that burn the fuel
	private double modeNum;//mode number 1,2,3


	public ProbeFuelCost(Vector3d currentVelocity, Vector3d finalVelocity, int modeNum) {//constructor	
		//velocity before using fuel
		this.current_probe_velocity=currentVelocity;
		
		//velocity we want to reach at the end
		this.final_probe_velocity=finalVelocity;
		
		//choose between the 3 different kind of engine
		this.modeNum=modeNum;
	
		//to see if there's fuel left
		if(current_mass_fuel<0) {		
			throw new RuntimeException("NO FUEL :((!!");
		}
		
		
		
		//fetch the data according to what fuel you choose
		
		if(modeNum==1) {//1.Combustion fuel
			System.out.println("Fuel chosen:");
			System.out.println("1.Combustion fuel");
			this.effective_exhaust_velocity=4e3;
			this.thrust=3e7;
			this.mass_flow_rate=thrust_1/effective_exhaust_velocity_1;
		}
		else if(modeNum==2) {//2.Ion fuel
			System.out.println("Fuel chosen:");
			System.out.println("2.Ion fuel");
			this.effective_exhaust_velocity=effective_exhaust_velocity_2;
			this.thrust=thrust_2;
			this.mass_flow_rate=thrust_2/effective_exhaust_velocity_2;
		}
		else if(modeNum==3) {//3.Magnetoplasmadynamic
			System.out.println("Fuel chosen:");
			System.out.println("3.Magnetoplasmadynamic");
			this.effective_exhaust_velocity=effective_exhaust_velocity_3;
			this.thrust=thrust_3;
			this.mass_flow_rate=thrust_3/effective_exhaust_velocity_3;
		}
		else {//error report for input other than 1,2,3
			System.out.println("Invalid mode number.");
			System.out.println("please choose between \n1.Combustion fuel\n2.Ion fuel\n3.Magnetoplasmadynamic");
			throw new RuntimeException("Invalid mode number");
		}
		
		this.time=0;
		this.velocity_change=new Vector3d(0,0,0);
		
	}
	
	
	
	/**
	 * calculate the change of speed
	 */
	public Vector3d calVelChange() {	
		
		//delta velocity
		velocity_change=(Vector3d) final_probe_velocity.sub(current_probe_velocity);
		//System.out.println(final_probe_velocity);
		//System.out.println(current_probe_velocity);
		//System.out.println("1");
		return velocity_change;	
		
	}
	

	
	/**
	 * calculate the value of acceleration (undirected)
	 * 
	 * M*a=Ve*(mass flow rate)
	 * M=rocket mass
	 * Ve=exhaust velocity
	 * 
	 * a=Ve*mass flow rate/M
	 */
	public double calAccValue() {
		
		acc_value=effective_exhaust_velocity*mass_flow_rate/MASS_ROCKET;
		//System.out.println("2");
		return acc_value;	
		
	}
	
	

	/**
	 * calculate the time used
	 * 
	 * a=(V(t)-V(0))/t
	 * t=(delta v)/a
	 */
	public double calTime() {
		time=(velocity_change.norm())/acc_value;
		//System.out.println("3");
		//System.out.println("vel change"+velocity_change.norm());
		//System.out.println("acc value"+acc_value);
		//System.out.println("time"+time);
		return time;
	}
	
	
	
	/**
	 * calculate the acceleration with direction
	 */
	public Vector3d calAcc() {
		acceleration=(Vector3d)velocity_change.mul(1/time);
		//System.out.println("4");
		return acceleration;		
	}
	
	
	
	/**
	 * calculate the fuel cost
	 * 
	 * fuel cost=t* mass flow rate
	 */
	public void useFuel() {
		
		calVelChange();
		calAccValue();
		calTime();
		calAcc();
		
		//prints
		System.out.println("exhaust velocity: "+effective_exhaust_velocity);
		System.out.println("thrust: "+thrust);
		System.out.println("mass flow rate: "+mass_flow_rate);
		System.out.println("-----------");
		//System.out.println("time used for speeding up: "+time);
		
		
		mass_fuel_cost=time*mass_flow_rate;//the mass cost
		
		double temp=current_mass_fuel;//record the fuel left before this speed up
		
		
		//how many fuel still left after
		current_mass_fuel=(current_mass_fuel)-(mass_fuel_cost);
		
		//throw an error if there's not enough fuel
		if(current_mass_fuel<0) {	
			System.out.println("Fuel left before using fuel: "+temp);
			System.out.println("Fuel actually need : "+mass_fuel_cost);
			throw new RuntimeException("!!!!!NOT ENOUGH FUEL:((((!!!!!");
		}
		
		//the current total mass 
		current_mass_all=(current_mass_all)-(mass_fuel_cost);		
		
	}
	
	
	
	/**
	 * reset the fuel to un-used condition (7.8e4 by default)
	 */
	public void resetFuel() {
		this.current_mass_fuel=7.8e4; //Assume we bring 7.8e4 kg fuel
		this.current_mass_all=7.8e4*2; //mass of craft + fuel
		System.out.println("---------");
		System.out.println("The fuel amount has been reset to: "+current_mass_fuel+" kg by default");
		System.out.println("---------");
	}
	
	
	
	
	/**
	 * set the fuel amount as you want
	 */
	public void setFuelAmountTo(double newAmount) {
		this.current_mass_fuel=newAmount; 
		this.current_mass_all=7.8e4+newAmount; //mass of craft + fuel
		System.out.println("---------");
		System.out.println("The fuel amount has been reset to: "+current_mass_fuel+" kg");
		System.out.println("---------");
	}
	
	
	public double getFuelCost() {//the mass of fuel cost in this speed up
		return mass_fuel_cost;
	}
	
	public double getCurrentMass() {//how much fuel left
		return current_mass_fuel;
	}
	
	public Vector3d getAcc() {//the acc
		return acceleration;
	}
	
	public double getAccValue() {//acc.norm
		return acc_value;
	}
	
	public Vector3d getFinalVel() {//the vel after using fuel
		return final_probe_velocity;
	}
	
	public double timeUsed() {
		return time;
	}
	
	
	
	
	
	//testing
	public static void main(String[] args) {
		
		//first time using fuel
		Vector3d v0=new Vector3d(400,60000,1);//vel before using fuel
		Vector3d v1=new Vector3d(500,60000,1);//vel after
		
		//second time using fuel
		Vector3d v2=new Vector3d(400,60000,1);//vel before using fuel
		Vector3d v3=new Vector3d(500,60000,1);//vel after
		
		//third time using fuel
		Vector3d v4=new Vector3d(1,1,1);//vel before using fuel
		Vector3d v5=new Vector3d(60000,3000,50);//vel after
		
		
		
		/**
		 * first time using fuel
		 */
		System.out.println("------------------first time using fuel------------------");
		ProbeFuelCost p=new ProbeFuelCost(v0,v1,1);
		p.useFuel();
		
		//get results (first time)
		double fuelCost=p.getFuelCost();//the fuel cost
		Vector3d acc=p.getAcc();//acc
		double accv=p.getAccValue();//acc.norm()
		double fuelLeft=p.getCurrentMass();
		double t=p.timeUsed();
		System.out.println("fuel cost : "+fuelCost+" kg");
		System.out.println("fuel left : "+fuelLeft+" kg");
		System.out.println("accleration: "+acc+"  "+accv);
		System.out.println("time used for speeding up: "+t);
		System.out.println("");
		System.out.println("");

		
		/**
		 * Second time using fuel (the same trajectory)
		 */
		System.out.println("------------------Second time using fuel------------------");
		ProbeFuelCost p2=new ProbeFuelCost(v2,v3,1);
		//p2.resetFuel();
		p2.setFuelAmountTo(1950);
		p2.useFuel();
		
		double fuelCost2=p2.getFuelCost();//the fuel cost
		Vector3d acc2=p2.getAcc();//acc
		double accv2=p2.getAccValue();//acc.norm()
		double fuelLeft2=p2.getCurrentMass();
		double t2=p2.timeUsed();
		System.out.println("fuel cost : "+fuelCost2+" kg");
		System.out.println("fuel left : "+fuelLeft2+" kg");
		System.out.println("accleration: "+acc2+"  "+accv2);
		System.out.println("time used for speeding up: "+t2);
		//System.out.println("v3  "+v3);
		//System.out.println("v2  "+v2);
		System.out.println("");
		System.out.println("");
		
		
		/**
		 * Third time using fuel (the same trajectory)
		 */
		System.out.println("------------------third time using fuel------------------");
		ProbeFuelCost p3=new ProbeFuelCost(v4,v5,3);
		p3.resetFuel();
		//p3.setFuelAmountTo(1950);
		p3.useFuel();
		
		double fuelCost3=p3.getFuelCost();//the fuel cost
		Vector3d acc3=p3.getAcc();//acc
		double accv3=p3.getAccValue();//acc.norm()
		double fuelLeft3=p3.getCurrentMass();
		double t3=p3.timeUsed();
		System.out.println("fuel cost : "+fuelCost3+" kg");
		System.out.println("fuel left : "+fuelLeft3+" kg");
		System.out.println("accleration: "+acc3+"  "+accv3);
		System.out.println("time used for speeding up: "+t3);

	}
}
