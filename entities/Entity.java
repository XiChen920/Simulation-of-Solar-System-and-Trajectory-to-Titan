package phase3.entities;

import java.awt.Graphics2D;

import phase3.Handler;
import phase3.calculations.Constants;

/**
 * An abstract class which creates the framework for any object which needs to be updated and rendered on the screen
 *
 */

public abstract class Entity {
	
	protected Handler handler;
	//object location in meters
	protected double x, y, z;
	
	//object speed in meters
	protected double xv, yv, zv;
	
	//location & speed converted to AU units
	protected double xAU, yAU, zAU;
	protected double xvAU, yvAU, zvAU;
	
	
	
	//movement of object
	protected double xMove, yMove, zMove;
	
	
	public Entity(Handler handler, double x, double y, double z) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.z = z;
		
		//default velocity
		xv = 20.0;
		yv = 20.0;
		zv = 20.0;
		
		//zero movement initially
		xMove = 0;
		yMove = 0;
		zMove = 0;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public void move() {
		x += xMove;
		y += yMove;
		z += zMove;
	}
	
	/**
	 * converts the coordinates of the real life data to AU and multiplies it by 1000 to scale it onto the screen
	 * 
	 * this means that xAU, yAU,... is not in astronomical units
	 */
	public void auConverter() {
		xAU = x/Constants.getAU()*1000;
		yAU = y/Constants.getAU()*1000;
		zAU = z/Constants.getAU()*1000;
		xvAU = xv/Constants.getAU()*1000;
		yvAU = yv/Constants.getAU()*1000;
		zvAU = zv/Constants.getAU()*1000;
	}
	
	//getters & setters
	
	public double getxMove() {
		return xMove;
	}
	
	public double getyMove() {
		return yMove;
	}
	
	public double getzMove() {
		return zMove;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getXV() {
		return xv;
	}
	
	public double getYV() {
		return yv;
	}
	
	public double getZV() {
		return zv;
	}
	public double getXAU() {
		return xAU;
	}
	
	public double getYAU() {
		return yAU;
	}
	
	public double getZAU() {
		return zAU;
	}
	
	public double getXVAU() {
		return xvAU;
	}
	
	public double getYVAU() {
		return yvAU;
	}
	
	public double getZVAU() {
		return zvAU;
	}
	public void setVelocity(double xv, double yv, double zv) {
		this.xv = xv;
		this.yv = yv;
		this.zv = zv;
	}
	
	
}
