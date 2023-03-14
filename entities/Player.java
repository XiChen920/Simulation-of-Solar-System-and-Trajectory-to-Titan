package phase3.entities;

import java.awt.Graphics2D;

import phase3.Handler;

/**
 * an invisibile entity on which the camera is focussed and which the user can move
 *
 */

public class Player extends Entity {
	
	private Handler handler;
	
	public Player(Handler handler) {
		super(handler, 0, 0, 0);
		this.handler = handler;
		xv = 40;
		yv = 40;
		zv = 0;
	}

	@Override
	public void update() {
		getInput();
		move();
		handler.getEngineCamera().centerOnEntity(this);
	}
	
	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -yv;
		} else if(handler.getKeyManager().down) {
			yMove = yv;
		} else if(handler.getKeyManager().left) {
			xMove = -xv;
		} else if(handler.getKeyManager().right) {
			xMove = xv;
		}
	}

	@Override
	public void render(Graphics2D g) {
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
