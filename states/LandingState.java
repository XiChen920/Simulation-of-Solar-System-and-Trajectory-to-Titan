package phase3.states;

import java.awt.BasicStroke; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import phase3.Engine;
import phase3.Handler;
import phase3.calculations.Atmosphere;
import phase3.calculations.Calculations;
import phase3.calculations.Calendar;
import phase3.calculations.Constants;
import phase3.calculations.ThrustLander;
import phase3.entities.Lander;
import phase3.entities.LandingObjects;
import phase3.entities.Player;
import phase3.entities.Satellites;
import phase3.galaxies.AUSolarSystem;
import phase3.galaxies.TitanSurfaceWorld;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;
import phase3.missions.Mission1;

/**
 *
 *	
 *	A class, which is the state that models the solar system 
 */

public class LandingState extends State {
	
	private Handler handler;
	private Player player;
	private TitanSurfaceWorld tsw;
	private int delayedEffect;
	private LandingObjects lo;
	private Atmosphere atmosphere;
	
	//used for rendering efficiency
	private int xStart, xEnd, yStart, yEnd;
	
	//used for zooming in and out
	public double scalerWorld, scalerHUD, scaler;
	
	public LandingState(Handler handler) {
		super(handler);
		this.handler = handler;
		scalerWorld = 1;
		scalerHUD = 1;
		tsw = new TitanSurfaceWorld(handler);
		player = handler.getPlayer();
		lo = new LandingObjects(handler);
		atmosphere = new Atmosphere(handler);
		handler.setAtmosphere(atmosphere);
		handler.getCalendar().setDate(1, 4, 2021);
		Constants.setEngineSpeed(1);
		Constants.setStepSize(1.0/60);
	}

	@Override
	public void update() {
		getInput();
		handler.setScalerWorld(scalerWorld);
		handler.setScalerHUD(scalerHUD);
		tsw.update(scalerWorld, scalerHUD);
		
		if(!handler.getKeyManager().space) {
			tsw.update(scalerWorld, scalerHUD);
			for(double i = 0; i < Constants.getEngineSpeed(); i++) {
				getInputReset();
				atmosphere.update();
				lo.update();
				if(!handler.getKeyManager().delete) {
					handler.getCalendar().update();
				}	
			}
		} 
		player.update();
	}

	@Override
	public void render(Graphics2D g) {
		handler.setxStart((int) (handler.getPlayer().getX() - handler.getEngineCamera().getxOffset() - handler.getWidth()/2));
		handler.setxEnd((int) (handler.getPlayer().getX() - handler.getEngineCamera().getxOffset() + handler.getWidth()/2));
		handler.setyStart((int) (handler.getPlayer().getY() - handler.getEngineCamera().getyOffset() - handler.getHeight()/2));
		handler.setyEnd((int) (handler.getPlayer().getY() - handler.getEngineCamera().getyOffset() + handler.getHeight()/2));
		
		g.scale(scalerWorld, scalerWorld);
		g.setStroke(new BasicStroke(20));
		
		//CORRECT SCALE
		g.drawRect(handler.getxStart(), handler.getyStart(), (int) Math.round(scalerHUD*(handler.getxEnd()-handler.getxStart())), (int) Math.round(scalerHUD*(handler.getyEnd()-handler.getyStart())));
		
		g.setStroke(new BasicStroke(2));
		tsw.render(g);
		lo.render(g);
		g.scale(scalerHUD, scalerHUD);
		player.render(g);
		
		//xy labels
		g.setColor(Color.white);
		g.setFont(new Font("monospaced", Font.BOLD, Calculations.fitToScreenWidth(18)));
		g.drawString("Y", Calculations.fitToScreenWidth(100), Calculations.fitToScreenWidth(400));
		g.drawString("X", Calculations.fitToScreenWidth(2400), Calculations.fitToScreenHeight(1260));
		g.drawString("Lander: (X:"+ArtificialSpaceObject.Lander.getLocation().getX()+", Y: "+ArtificialSpaceObject.Lander.getLocation().getY()+")", Calculations.fitToScreenWidth(1800), Calculations.fitToScreenHeight(50));
		g.drawString("Lander velocity "+ArtificialSpaceObject.Lander.getVelocity().toString(), Calculations.fitToScreenWidth(1800), Calculations.fitToScreenHeight(100));
		g.drawString("Lander angular velocity: "+ArtificialSpaceObject.Lander.getdTheta(), Calculations.fitToScreenWidth(1800), Calculations.fitToScreenHeight(150));
		g.drawString("Lander theta mod 2PI: "+(ArtificialSpaceObject.Lander.getTheta()%2*Math.PI), Calculations.fitToScreenWidth(1800), Calculations.fitToScreenHeight(200));

		UItext(g);		
		
	}
	
	//renders live information about the solar system
	private void UItext(Graphics2D g) {
		g.drawString("Current solver used: "+Constants.getSolverName()+"     Current state: "+this.getClass().getName(), Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(50));
		g.drawString(handler.getCalendar().getDate()+"     Stepsize: "+Constants.getStepSize()+"s,    FPS: "+handler.getFPS()+",    Engine Speed: "+Constants.getEngineSpeed()+"x,    Total Speed: "+Constants.getRealSpeed()+"x normal", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(100));
		g.drawString(handler.getCalendar().getPassedTimeExact() , Calculations.fitToScreenWidth(50),  Calculations.fitToScreenHeight(150));

		
		g.drawString("w = up, s = down, a = left, d = right, e = zoom in, q = zoom out", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(240));
		g.drawString("1 = Euler, 2 = Runge Kutta, 3 = Verlet", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(260));
		g.drawString("+ = increase engine speed, - = decrease engine speed", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(280));
		g.drawString("[ = increase stepsize, ] = decrease stepsize", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(300));
		g.drawString("hold shift = increment with 10, hold ctrl = increment with 100", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(320));
		g.drawString("space = pause, delete = reset", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(340));
		g.drawString("upper arrow = go to modelling state, 0 = go to (0,0) coordinates", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(360));
		g.drawString("f to focus on lander", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(380));

		
		g.drawString("Theta: "+(ArtificialSpaceObject.Lander.getTheta()*180/Math.PI)+"degrees ("+ArtificialSpaceObject.Lander.getTheta()+"rad)     is upside down: "+ArtificialSpaceObject.Lander.isUpsideDown(), Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(1200));
		g.drawString("Thrust left: "+ThrustLander.getCurrentForceThrusters()[0]+"N, Thrust main: "+ThrustLander.getCurrentForceThrusters()[1]+"N, Thrust right: "+ThrustLander.getCurrentForceThrusters()[2]+"N", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(1220));
		g.drawString("Acceleration due to thrust: "+Math.round(ArtificialSpaceObject.Lander.getut()*100)*0.01, Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(1240));
		g.drawString("Current mass flow rate"+Arrays.toString(ThrustLander.getCurrentMassFlowrate()), Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(1260));
		g.drawString("Net Mass,"+ArtificialSpaceObject.Lander.getNetMass()+" Dry Mass,"+ArtificialSpaceObject.Lander.getDryMass()+" Fuel Mass: "+ArtificialSpaceObject.Lander.getFuelMass(), Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(1280));
		
		
	}
	
	
	/**
	 * A method which renders the current coordinates upon the screen of each planet
	 * @param g	graphics object
	 */
		
	public void getInputReset() {
		if(handler.getKeyManager().delete) {
			delete();
		//sets solver to euler
		} else if (handler.getKeyManager().one) {
			Constants.setSolver(0);
			delete();
		//sets solver to runge kutta
		} else if (handler.getKeyManager().two) {
			Constants.setSolver(1);
			delete();
		//sets solver to verlet
		} else if (handler.getKeyManager().three) {
			Constants.setSolver(2);
			delete();
		}
	}
	
	public void delete() {
		if(!handler.getKeyManager().space) {
			handler.getCalendar().reset();
			for(int i = 0; i<SpaceObject.values().length; i++) {
				SpaceObject.values()[i].reset();
			}
			ArtificialSpaceObject.SpaceShip.setSpaceShipProperties();			
		}
	}
	
	                                                                        
	
	
	
	
	
	
	
	public void getInput() {
		
		//zoom in
		if(handler.getKeyManager().e) {
			if(scalerWorld<=10) {
				scalerWorld = scalerWorld*1.02; 
				scalerHUD = scalerHUD/1.02;
			}
			player.setVelocity(player.getXV()/1.02, player.getYV()/1.02, player.getZV()/1.02);
		//zoom out	
		} else if (handler.getKeyManager().q) {
			if(scalerHUD<=6) {
				scalerWorld = scalerWorld/1.02;
				scalerHUD = scalerHUD*1.02;
			}
			player.setVelocity(player.getXV()*1.02, player.getYV()*1.02, player.getZV()*1.02);
		//increase engine speed
		} else if (handler.getKeyManager().plus) {
			if(delayedEffect >= 10) {
				if(Constants.getEngineSpeed()<3200) {
					
					if(handler.getKeyManager().ctrl) {
						Constants.addEngineSpeed(100);
						delayedEffect = 0;
					} else if(handler.getKeyManager().shift) {
						Constants.addEngineSpeed(10);
						delayedEffect = 0;
					} else {
						Constants.addEngineSpeed(1);
						delayedEffect = 0;
					}	
				}
			}
			delayedEffect++;
		//decrease engine speed
		} else if (handler.getKeyManager().min) {
			if(delayedEffect >= 10) {
				if(Constants.getEngineSpeed()>0) {
					if(handler.getKeyManager().ctrl) {
						Constants.subEngineSpeed(100);
						delayedEffect = 0;
					} else if(handler.getKeyManager().shift) {
						Constants.subEngineSpeed(10);
						delayedEffect = 0;
					} else {
						Constants.subEngineSpeed(1);
						delayedEffect = 0;
					}
				}	
			}
			delayedEffect++;
		//increase stepsize
		} else if (handler.getKeyManager().closebracket) {
			if(delayedEffect >= 10) {
				if(Constants.getStepSize()<3200) {
					if(handler.getKeyManager().ctrl) {
						Constants.addStepSize(100.0/60.0);
						delayedEffect = 0;
					} else if(handler.getKeyManager().shift){
						Constants.addStepSize(10.0/60.0);
						delayedEffect = 0;
					} else {
						Constants.addStepSize(1.0/60.0);
						delayedEffect = 0;
					}
				}
			}
			delayedEffect++;
		//decrease stepsize
		} else if (handler.getKeyManager().openbracket) {
			if(delayedEffect >= 10) {
				if(Constants.getStepSize()>0) {
					if(handler.getKeyManager().ctrl) {
						Constants.subStepSize(100.0/60.0);
						delayedEffect = 0;	
					} else if(handler.getKeyManager().shift){
						Constants.subStepSize(10.0/60.0);
						delayedEffect = 0;
					} else {
						Constants.subStepSize(1.0/60.0);
						delayedEffect = 0;
					}
				}
			}
			delayedEffect++;
		} else if (handler.getKeyManager().f) {
			player.setPosition(lo.getLander().getX(), -lo.getLander().getY());
			//System.out.println(lo.getLander().getX()+"   "+lo.getLander().getY());
		} else if (handler.getKeyManager().upperarrow) {
			Engine.setEngineState(new ModelingState(handler));
		} else if (handler.getKeyManager().zero) {
			player.setPosition(0, 0);
		}
	}
	

}
