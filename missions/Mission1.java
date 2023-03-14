package phase3.missions;

import java.awt.Graphics2D;

import phase3.Handler;
import phase3.calculations.Calculations;
import phase3.calculations.Constants;
import phase3.calculations.Vector3d;
import phase3.entities.SpaceShip;
import phase3.input.ArtificialSpaceObject;
import phase3.input.SpaceObject;

/*
 * 
 * A class which has projectile object(s) within for mission 1 to Titan
 * 
 * @version 1.0
 */

public class Mission1 {
	private Handler handler;
	
	private SpaceShip spaceship;
	
	public Mission1(Handler handler) {
		this.handler = handler;
		
		//setSpaceShipProperties();
		
		spaceship = new SpaceShip(handler, ArtificialSpaceObject.SpaceShip.getNetMass(), ArtificialSpaceObject.SpaceShip.getFuelMass(), 20, 20, ArtificialSpaceObject.SpaceShip.getLocation().getX(), ArtificialSpaceObject.SpaceShip.getLocation().getY(), ArtificialSpaceObject.SpaceShip.getLocation().getZ(), ArtificialSpaceObject.SpaceShip.getVelocity().getX(), ArtificialSpaceObject.SpaceShip.getVelocity().getY(), ArtificialSpaceObject.SpaceShip.getVelocity().getZ());
		System.out.println((ArtificialSpaceObject.SpaceShip.getLocation().getX()/Constants.getAU())+" "+(ArtificialSpaceObject.SpaceShip.getLocation().getY()/Constants.getAU())+" "+(ArtificialSpaceObject.SpaceShip.getLocation().getZ()/Constants.getAU()));
		

	}
	
	public void update() {
		spaceship.update();
	}
	public void render(Graphics2D g) {
		spaceship.render(g);
	}
	public SpaceShip getSpaceShip() {
		return spaceship;
	}
	
	private void calculatePosition(){
		//position = SpaceObject.Earth.getLocation().
	}
}
