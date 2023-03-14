package phase3.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import phase3.Handler;
import phase3.calculations.Calculations;
import phase3.calculations.Constants;

import phase3.calculations.ThrustLander;
import phase3.controllers.FeedbackController;
import phase3.controllers.OpenLoopController;
import phase3.input.ArtificialSpaceObject;
import phase3.solvers.EulerSolver;

public class Lander extends Projectiles{
	
	protected ArrayList<Double> xArr, yArr;
	private int counter;
	private FeedbackController fc;
	private OpenLoopController olc;

	public Lander(Handler handler, double netMass, double fuelMass, double width, double height, double x, double y, double z, double xv, double yv, double zv) {
		super(handler, netMass, fuelMass, width*10, height*10, x, y, z, xv, yv, zv);
		fc = new FeedbackController(handler);
		olc = new OpenLoopController(handler);
		
		xArr = new ArrayList<Double>();
		yArr = new ArrayList<Double>();
		counter = 0;
		xArr.add(x);
		yArr.add(y);
		
	}

	@Override
	public void update() {
		if(ArtificialSpaceObject.Lander.getLocation().getY() > 0) {
			switch(Constants.getController()) {
			case 0:
				fc.solve();
				break;
			case 1:
				olc.solve();
				break;
			}
		}
		
		
		x = ArtificialSpaceObject.Lander.getLocation().getX();
		y = ArtificialSpaceObject.Lander.getLocation().getY();
		y+=1;
		
		if(Math.sqrt(Math.pow(x-xArr.get(counter), 2) + Math.pow(y-yArr.get(counter), 2)) >= Constants.getDrawPrecision()) {
			counter++;
			xArr.add(x);
			yArr.add(x);
		}
	}

	@Override
	public void render(Graphics2D g) {
		
		
		if(y <= 0) {
			g.setColor(Color.GREEN);
			g.drawString("You have Landed!", Calculations.fitToScreenWidth(handler.getCanvasWidth()/2), Calculations.fitToScreenHeight(handler.getCanvasHeight()/2));
		}
		
		int xWidth = (int) Math.round(x-width/2);
		int yHeight = (int) Math.round(-y-height);
		int stringOffset = 20;
		
		int xPositionLander = (int) Math.round((xWidth-handler.getEngineCamera().getxOffset()));
		int yPositionLander = (int) Math.round((yHeight-handler.getEngineCamera().getyOffset()));
		
		//centre part lander			
		g.setColor(Color.RED);
		g.fillOval(xPositionLander, yPositionLander,(int) (width),(int) (height));
		g.drawString("Landing Module",xPositionLander ,  (int) ((yHeight)-stringOffset-handler.getEngineCamera().getyOffset()));
		
		if(y <= 1) {
			handler.getCalendar().isPaused(true);
			g.setColor(Color.GREEN);
			g.drawString("OBJECT LANDED AT T = "+handler.getCalendar().getPassedTimeExact(), Calculations.fitToScreenWidth(handler.getCanvasWidth()/2+20), Calculations.fitToScreenHeight(handler.getCanvasHeight()/2+100));
			g.drawString("Fuel burned = "+(6000-ArtificialSpaceObject.Lander.getFuelMass())+" kg's", Calculations.fitToScreenWidth(handler.getCanvasWidth()/2+20), Calculations.fitToScreenHeight(handler.getCanvasHeight()/2+150));
		}

	}
	

}
