package phase3.entities;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

import phase3.Handler;
import phase3.calculations.Constants;
import phase3.input.SpaceObject;
import phase3.solvers.EulerSolver;
import phase3.solvers.RungeKuttaSolver;
import phase3.solvers.VerletSolver;

/**
 * A class used to update and render SpaceObjects to the screen
 *
 *
 */

public class Planets extends CelestialObjects {
	protected ArrayList<Double> xAUArr, yAUArr;
	protected int counter;
	protected int id;
	protected String name;

	
	public Planets(int id, Handler handler, double mass, double radius, double x, double y, double z, double xv, double yv, double zv) {
		super(handler, mass, radius, x, y, z, xv, yv, zv);
		this.id = id;
		name = SpaceObject.values()[id].name();
		
		xAUArr = new ArrayList<Double>();
		yAUArr = new ArrayList<Double>();

		counter = 0;
		auConverter();
		xAUArr.add(xAU);
		yAUArr.add(yAU);
	}
	

	@Override
	public void update() {
		
		getInput();
		
		//use the specified solver within the Constants class
		if(!handler.getKeyManager().delete) {
			if(id>0) {
				switch(Constants.getSolver()) {
					case 0:
						EulerSolver.solve(Constants.getStepSize(), SpaceObject.values()[id]);
						break;
					case 1:
						RungeKuttaSolver.solve(Constants.getStepSize(), SpaceObject.values()[id]);
						break;
					case 2:
						VerletSolver.solve(Constants.getStepSize(), SpaceObject.values()[id], handler.getCalendar().getSecondsPassed());
						break;
				}
			}
			
		}
		
		//apply the coordinates of the SpaceObjects to this class
		x = SpaceObject.values()[id].getLocation().getX();
		y = SpaceObject.values()[id].getLocation().getY();
		z = SpaceObject.values()[id].getLocation().getZ();	
			
			
			
		auConverter(); 
		if(Math.sqrt(Math.pow(xAU-xAUArr.get(counter), 2) + Math.pow(yAU-yAUArr.get(counter), 2)) >= Constants.getDrawPrecision()) {
			counter++;
			xAUArr.add(xAU);
			yAUArr.add(yAU);
		}
		
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		
		g.setStroke(new BasicStroke(4));
		for(int i = 1; i<counter; i++) {
			g.drawLine((int) (xAUArr.get(i-1) - handler.getEngineCamera().getxOffset()),(int) (-yAUArr.get(i-1) - handler.getEngineCamera().getyOffset()),(int) (xAUArr.get(i) - handler.getEngineCamera().getxOffset()),(int) (-yAUArr.get(i) -handler.getEngineCamera().getyOffset()));
		}
		g.fillOval( (int) ((Math.round(xAU)) - radius - handler.getEngineCamera().getxOffset()),(int) (-(Math.round(yAU)) - radius - handler.getEngineCamera().getyOffset()),(int) width,(int) height);
		if(id!=4) {
			g.drawString(name,(int) (Math.round(xAU) - handler.getEngineCamera().getxOffset()), (int) (-(Math.round(yAU)) - radius - 20 - handler.getEngineCamera().getyOffset()));
		} else {
			g.drawString(name,(int) (Math.round(xAU) - handler.getEngineCamera().getxOffset()), (int) (-(Math.round(yAU)) + radius + 20 - handler.getEngineCamera().getyOffset()));
		}

	}
	
	
	public void getInput() {
		if (handler.getKeyManager().delete) {
			delete();
		} else if(handler.getKeyManager().one) {
			delete();
		} else if(handler.getKeyManager().two) {
			delete();
		} else if(handler.getKeyManager().three) {
			delete();
		}
	}
	
	//resets all variables
	public void delete() {
		xAUArr.clear();
		yAUArr.clear();
		counter = 0;
		auConverter();
		xAUArr.add(xAU);
		yAUArr.add(yAU);
	}

}
