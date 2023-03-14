package phase3.entities;

import java.awt.Color;

import phase3.Handler;

/**
 *
 * 
 * an abstract class, which is meant to further specify the properties of a SpaceObject within the engine
 *
 */

public abstract class CelestialObjects extends Entity{
	
	protected double mass; //in kg
	protected double volume; //in m3
	protected double diameter; //in meters
	protected double radius; 
	protected double width;
	protected double height;
		
	//speed per dimension
	protected double xv, yv, zv;
	
	protected Color color;
	protected Handler handler;

	public CelestialObjects(Handler handler, double mass, double radius, double x, double y, double z, double xv, double yv, double zv) {
		super(handler, x, y, z);
		this.handler = handler;
		this.mass = mass;
		this.radius = radius;
		this.diameter = radius*2;
		this.width = diameter;
		this.height = width;
		
		this.xv = xv;
		this.yv = yv;
		this.zv = zv;
	}
	public void setColor(Color c) {
		color = c;
	}
	

	


}
