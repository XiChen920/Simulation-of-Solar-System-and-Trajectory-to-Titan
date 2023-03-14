package phase3.gfx;

import phase3.Handler;
import phase3.entities.Entity;

/**
 * The actual engine camera used for being focussed upon a specific object
 *
 */

public class EngineCamera {
	private double xOffset, yOffset;
	private Handler handler;
	
	public EngineCamera(Handler handler, double xOffset, double yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getDisplay().getCanvasWidth() / 2;
		yOffset = e.getY() - handler.getDisplay().getCanvasHeight() / 2;
		//System.out.println(e.getX()+" "+e.getY()+" "+handler.getDisplay().getCanvasWidth()+" "+handler.getDisplay().getCanvasHeight());
	}
	
	public void move(double xAmt, double yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
	}

	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
}
