package phase3.entities;

import phase3.Handler;

/**
 * An abstract class, which is used to further specify the characteristics of a ArtificialSpaceObject within the engine
 *
 */
public abstract class Projectiles extends Entity{
	
	protected double netMass, fuelMass, dryMass;
	protected int volume;
	
	protected double width, height;

	public Projectiles(Handler handler, double netMass, double fuelMass, double width, double height, double x, double y, double z, double xv, double yv, double zv) {
		super(handler, x, y, z);
		
		this.netMass = netMass;
		this.fuelMass = fuelMass;
		this.dryMass = netMass - fuelMass;
		
		this.width = Math.round(width);
		this.height = Math.round(height);
		
		this.xv = xv;
		this.yv = yv;
		this.zv = zv;
	}

}
