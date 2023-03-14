package phase3.test;
import phase3.input.SpaceObject;
import phase3.solvers.EulerSolver;
import phase3.solvers.RungeKuttaSolver;
import phase3.solvers.VerletSolver;


/**
 * 
 * @author Xi Chen, Sitong Chen
 *
 */
public class SolverTest {
	
	
	/**
	 * 	test 1.1
	 * 
	 * 	RungeKutta solver
	 * 	step size = 1 seconds
	 */
	public static void testRungeK_Onesecond() {

		double stepsize=1;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i++) {//one year

				RungeKuttaSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}
	}
	
	/**
	 * 	test 1.2
	 * 
	 * 	Euler solver
	 * 	step size = 1 seconds
	 */
	public static void testEuler_OneSecond() {
		
		double stepsize=1;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i++) {//one year

				EulerSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
	
	
	/**
	 * 	test 1.3
	 * 
	 * 	Verlet solver
	 * 	step size = 1 seconds
	 */
	public static void testVerlet_OneSecond() {
		double stepsize=1;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i++) {//one year

				VerletSolver.solve(stepsize,SpaceObject.values()[j],i);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}
	}
	/**
	 * 	test 2.1
	 * 
	 * 	RungeKutta solver
	 * 	step size = 60 seconds= 1 minutes
	 */
	public static void testRungeK_OneMin() {
		double stepsize=60;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=60) {//one year

				RungeKuttaSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
		
	/**
	 * 	test 2.2
	 * 
	 * 	Euler solver
	 * 	step size = 60 seconds= 1 minutes
	 */
	public static void testEuler_OneMin() {
		double stepsize=60;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=60) {//one year

				EulerSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
	/**
	 * 	test 2.3
	 * 
	 * 	Verlet solver
	 * 	step size = 60 seconds= 1 minutes
	 */
	public static void testVerlet_OneMin() {
		double stepsize=60;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=60) {//one year

				VerletSolver.solve(stepsize,SpaceObject.values()[j],i);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}			
	}
	/**
	 * 	test 3.1
	 * 
	 * 	RungeKutta solver
	 * 	step size = 3600 seconds= 1 hour 
	 */
	public static void testRungeK_OneHour() {
		double stepsize=3600;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=3600) {//one year

				RungeKuttaSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
		
	/**
	 * 	test 3.2
	 * 
	 * 	Euler solver
	 * 	step size = 3600 seconds= 1 minutes
	 */
	public static void testEuler_OneHour() {
		double stepsize=3600;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=3600) {//one year

				EulerSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
	/**
	 * 	test 3.3
	 * 
	 * 	Verlet solver
	 * 	step size = 3600 seconds= 1 minutes
	 */
	public static void testVerlet_OneHour() {
		double stepsize=3600;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=3600) {//one year

				VerletSolver.solve(stepsize,SpaceObject.values()[j],i);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}			
	}
	/**
	 * 	test 4.1
	 * 
	 * 	RungeKutta solver
	 * 	step size = 86400 seconds= 1 day
	 */
	public static void testRungeK_OneDay() {
		double stepsize=86400;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=86400) {//one year

				RungeKuttaSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
		
	/**
	 * 	test 4.2
	 * 
	 * 	Euler solver
	 * 	step size = 86400 seconds= 1 day
	 */
	public static void testEuler_OneDay() {
		double stepsize=86400;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=86400) {//one year

				EulerSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
	/**
	 * 	test 4.3
	 * 
	 * 	Verlet solver
	 * 	step size = 86400 seconds= 1 day
	 */
	public static void testVerlet_OneDay() {
		double stepsize=86400;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=86400) {//one year

				VerletSolver.solve(stepsize,SpaceObject.values()[j],i);//use the solver
				
				if(i % 2592000==0) {//print results every 30 days
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}			
	}
	
	/**
	 * 	test 5.1
	 * 
	 * 	RungeKutta solver
	 * 	step size = 604800 seconds= 7 day= 1 week
	 */
	public static void testRungeK_OneWeek() {
		double stepsize=604800;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=604800) {//one year

				RungeKuttaSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2419200==0) {//print results every 4 weeks
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
		
	/**
	 * 	test 5.2
	 * 
	 * 	Euler solver
	 * 	step size = 604800 seconds= 7 day= 1 week
	 */
	public static void testEuler_OneWeek() {
		double stepsize=604800;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=604800) {//one year

				EulerSolver.solve(stepsize,SpaceObject.values()[j]);//use the solver
				
				if(i % 2419200==0) {//print results every 4 weeks
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}	
	}
	/**
	 * 	test 5.3
	 * 
	 * 	Verlet solver
	 * 	step size = 604800 seconds= 7 day= 1 week
	 */
	public static void testVerlet_OneWeek() {
		double stepsize=604800;

		for(int j=0;j<11;j++) {//iterate all 11 planets
			for(int i=0;i<31536000;i+=604800) {//one year

				VerletSolver.solve(stepsize,SpaceObject.values()[j],i);//use the solver
				
				if(i % 2419200==0) {//print results every 4 weeks
					double x = SpaceObject.values()[j].getLocation().getX();
					double y = SpaceObject.values()[j].getLocation().getY();
					double z = SpaceObject.values()[j].getLocation().getZ();
					System.out.println(i+" seconds: "+SpaceObject.values()[j].name()+" (X: "+x+", Y: "+y+", Z: "+z+")");
				}
			}
		}			
	}
	
	
	public static void main(String args[]) {
		
		/**1
		 * tests for step size = 1 s
		 * print every 30 days
		 */
		//testRungeK_Onesecond();
		//testEuler_OneSecond();
		//testVerlet_OneSecond();
		
		/**2
		 * tests for step size= 1 minutes=60 s
		 * print every 30 days
		 */
		//testRungeK_OneMin();
		//testEuler_OneMin();
		//testVerlet_OneMin();
		
		/**3
		 * tests for step size= 1 hour =3600 s
		 * print every 30 days
		 */
		//testRungeK_OneHour();
		//testEuler_OneHour();
		//testVerlet_OneHour();
			
		/**4
		 * tests for step size= 1 day=86400 s
		 * print every 30 days
		 */
		testRungeK_OneDay();
		//testEuler_OneDay();
		//testVerlet_OneDay();
		
		/**5
		 * tests for step size=1 week=604800 s
		 * print every 4 weeks
		 */
		//testRungeK_OneWeek();
		//testEuler_OneWeek();
		//testVerlet_OneWeek();
		
	}	
}

