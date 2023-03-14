package phase3.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import phase3.Handler;
import phase3.calculations.Constants;
import phase3.input.ArtificialSpaceObject;
import phase3.solvers.EulerSolver;
import phase3.solvers.RungeKuttaSolver;
import phase3.solvers.VerletSolver;

/*
 * 
 * A class which carries a SpaceShip visual object for phase 2
 * It renders a circle  
 */


public class SpaceShip extends Projectiles {
	
	protected ArrayList<Double> xAUArr, yAUArr;
	protected int counter;
	
	public SpaceShip(Handler handler, double netMass, double fuelMass, double width, double height, double x, double y, double z, double xv, double yv, double zv) {
		super(handler, netMass, fuelMass, width, height, x, y, z, xv, yv, zv);
		
		xAUArr = new ArrayList<Double>();
		yAUArr = new ArrayList<Double>();
		
		counter = 0;
		auConverter();
		xAUArr.add(xAU);
		yAUArr.add(yAU);
		
	}
	
	public void update() {
		getInput();

		if(!handler.getKeyManager().delete) {
			switch(Constants.getSolver()) {
				case 0:
					EulerSolver.solve(Constants.getStepSize(), ArtificialSpaceObject.SpaceShip);
					break;
				case 1:
					RungeKuttaSolver.solve(Constants.getStepSize(), ArtificialSpaceObject.SpaceShip);
					break;
				case 2:
					VerletSolver.solve(Constants.getStepSize(), ArtificialSpaceObject.SpaceShip, handler.getCalendar().getSecondsPassed());
					break;
			}
		}
	
	
		x = ArtificialSpaceObject.SpaceShip.getLocation().getX();
		y = ArtificialSpaceObject.SpaceShip.getLocation().getY();
		z = ArtificialSpaceObject.SpaceShip.getLocation().getZ();
		auConverter();
		
		if(Math.sqrt(Math.pow(xAU-xAUArr.get(counter), 2) + Math.pow(yAU-yAUArr.get(counter), 2)) >= Constants.getDrawPrecision()) {
			counter++;
			xAUArr.add(xAU);
			yAUArr.add(yAU);
		}
	}
	public void render(Graphics2D g) {

		g.setColor(Color.white);
		
		g.setStroke(new BasicStroke(4));
		for(int i = 1; i<counter; i++) {
			g.drawLine((int) (xAUArr.get(i-1) - handler.getEngineCamera().getxOffset()),(int) (-yAUArr.get(i-1) - handler.getEngineCamera().getyOffset()),(int) (xAUArr.get(i) - handler.getEngineCamera().getxOffset()),(int) (-yAUArr.get(i) -handler.getEngineCamera().getyOffset()));	
		}

		g.fillOval( (int) ((Math.round(xAU)) - (width/2) - handler.getEngineCamera().getxOffset()),(int) (-(Math.round(yAU)) - (height/2) - handler.getEngineCamera().getyOffset()),(int) width,(int) height);
		g.drawString("SpaceShip",(int) (Math.round(xAU) - handler.getEngineCamera().getxOffset()), (int) (-(Math.round(yAU)) - (height/2) - 20 - handler.getEngineCamera().getyOffset()));

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
	
	public void delete() {
		xAUArr.clear();
		yAUArr.clear();
		counter = 0;
		auConverter();
		xAUArr.add(xAU);
		yAUArr.add(yAU);
	}
}
