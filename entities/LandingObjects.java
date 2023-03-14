package phase3.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import phase3.Handler;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;

/**
 * A class which carries all the landing objects
 *
 */

public class LandingObjects {
	 
	private Handler handler;
	private Lander lander;
	
	
	public LandingObjects(Handler handler) {
		this.handler = handler;
		lander = new Lander(handler, 
				ArtificialSpaceObject.Lander.getNetMass(), 
				ArtificialSpaceObject.Lander.getFuelMass(),
				ArtificialSpaceObject.Lander.getWidth(), 
				ArtificialSpaceObject.Lander.getHeight(), 
				ArtificialSpaceObject.Lander.getLocation().getX(), 
				ArtificialSpaceObject.Lander.getLocation().getY(),
				ArtificialSpaceObject.Lander.getLocation().getZ(),
				ArtificialSpaceObject.Lander.getVelocity().getX(),
				ArtificialSpaceObject.Lander.getVelocity().getY(),
				ArtificialSpaceObject.Lander.getVelocity().getZ()
				);
		
	}

	public void update() {
		lander.update();
	}
	
	
	public void render(Graphics2D g) {
		lander.render(g);
	}
	
	public Lander getLander() {
		return lander;
	}
		
}
