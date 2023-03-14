package phase3.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import phase3.Handler;

/**
 * An abstract class which is the framework for any engine state
 *
 */

public abstract class State extends EngineStateManager {
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
		
}
