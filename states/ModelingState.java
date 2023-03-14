package phase3.states;

import java.awt.BasicStroke; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import phase3.Engine;
import phase3.Handler;
import phase3.calculations.Calculations;
import phase3.calculations.Calendar;
import phase3.calculations.Constants;
import phase3.entities.Player;
import phase3.entities.Satellites;
import phase3.galaxies.AUSolarSystem;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;
import phase3.missions.Mission1;

/**
 *
 *	
 *	A class, which is the state that models the solar system 
 */

public class ModelingState extends State {
	
	private Handler handler;
	private Player player;
	private AUSolarSystem auss;
	private Satellites satellites;
	private Mission1 mission1;
	private int delayedEffect;
	
	//used for rendering efficiency
	private int xStart, xEnd, yStart, yEnd;
	
	//used for zooming in and out
	public double scalerWorld, scalerHUD, scaler;
	
	public ModelingState(Handler handler) {
		super(handler);
		this.handler = handler;
		scalerWorld = 1;
		scalerHUD = 1;
		auss = new AUSolarSystem(handler);
		satellites = new Satellites(handler);
		mission1 =  new Mission1(handler);
		player = handler.getPlayer();
		
	}

	@Override
	public void update() {
		getInput();
		handler.setScalerWorld(scalerWorld);
		handler.setScalerHUD(scalerHUD);
		if(!handler.getKeyManager().space) {
			auss.update(scalerWorld, scalerHUD);
			for(int i = 0; i < Constants.getEngineSpeed(); i++) {
				getInputReset();
				satellites.update();
				mission1.update();
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
		auss.render(g);
		mission1.render(g);
		satellites.render(g);
		g.scale(scalerHUD, scalerHUD);
		player.render(g);
		
		//xy labels
		g.setColor(Color.white);
		g.setFont(new Font("monospaced", Font.BOLD, Calculations.fitToScreenWidth(18)));
		g.drawString("Y", Calculations.fitToScreenWidth(100), Calculations.fitToScreenWidth(400));
		g.drawString("X", Calculations.fitToScreenWidth(2400), Calculations.fitToScreenHeight(1260));
		
		UItext(g);
		coordinateStringRender(g);
		
		
	}
	
	//renders live information about the solar system
	private void UItext(Graphics2D g) {
		g.drawString("Current solver used: "+Constants.getSolverName()+"     Current state: "+this.getClass().getName(), Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(50));
		g.drawString(handler.getCalendar().getDate()+"     Stepsize: "+Constants.getStepSize()+"s,    FPS: "+handler.getFPS()+",    Engine Speed: "+Constants.getEngineSpeed()+"x,    Total Speed: "+Constants.getRealSpeed()+"x normal", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(100));
		g.drawString(handler.getCalendar().getPassedTime() , Calculations.fitToScreenWidth(50),  Calculations.fitToScreenHeight(150));
		
		g.drawString("w = up, s = down, a = left, d = right, e = zoom in, q = zoom out", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(240));
		g.drawString("1 = Euler, 2 = Runge Kutta, 3 = Verlet", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(260));
		g.drawString("+ = increase engine speed, - = decrease engine speed", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(280));
		g.drawString("[ = increase stepsize, ] = decrease stepsize", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(300));
		g.drawString("hold shift = increment with 10, hold ctrl = increment with 100", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(320));
		g.drawString("space = pauze, delete = reset", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(340));
		g.drawString("upper arrow = go to lander state, 0 = go to (0,0) coordinates", Calculations.fitToScreenWidth(50), Calculations.fitToScreenHeight(360));


		
		g.drawString("Co�rdinates in meters", Calculations.fitToScreenWidth(1500), Calculations.fitToScreenHeight(50));
	}
	
	
	/**
	 * A method which renders the current coordinates upon the screen of each planet
	 * @param g	graphics object
	 */
	private void coordinateStringRender(Graphics2D g) {
		int spacing = Calculations.fitToScreenHeight(100);
		for(int i = 0; i<11; i++) {
			g.drawString(SpaceObject.values()[i].name()+" (X: "+satellites.planets[i].getX()+", Y: "+satellites.planets[i].getY()+", Z: "+satellites.planets[i].getZ()+")", Calculations.fitToScreenWidth(1500), spacing);
			spacing+=Calculations.fitToScreenHeight(30);
		}
		spacing+=Calculations.fitToScreenHeight(50);
	
		g.drawString("Projectile (X: "+mission1.getSpaceShip().getXAU()/1000+", Y: "+mission1.getSpaceShip().getYAU()/1000+", Z: "+mission1.getSpaceShip().getZAU()/1000+")", Calculations.fitToScreenWidth(1500), spacing);
	}
	
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
						Constants.addStepSize(100);
						delayedEffect = 0;
					} else if(handler.getKeyManager().shift){
						Constants.addStepSize(10);
						delayedEffect = 0;
					} else {
						Constants.addStepSize(1);
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
						Constants.subStepSize(100);
						delayedEffect = 0;	
					} else if(handler.getKeyManager().shift){
						Constants.subStepSize(10);
						delayedEffect = 0;
					} else {
						Constants.subStepSize(1);
						delayedEffect = 0;
					}
				}
			}
			delayedEffect++;
		} else if (handler.getKeyManager().upperarrow) {
			Engine.setEngineState(new LandingState(handler));
		} else if (handler.getKeyManager().left) {
			
		}
	}
	
	
}
