package phase3;

import phase3.calculations.Atmosphere;
import phase3.calculations.Calendar;
import phase3.display.Display;
import phase3.entities.Player;
import phase3.gfx.EngineCamera;
import phase3.input.KeyManager;
import phase3.input.MouseManager;

/**
 *
 *
 * a class through which other classes can handle variables from within the engine class,
 * without messing with the engine itself
 *
 */

public class Handler {
	
	private Engine engine;
	private Atmosphere atmosphere;
	private int xStart, xEnd, yStart, yEnd;
	private double scalerWorld, scalerHUD;
	
	

	public Handler(Engine engine) {
		this.engine = engine;
	}
	
	public Display getDisplay() {
		return engine.getDisplay();
	}
	
	public EngineCamera getEngineCamera() {
		return engine.getEngineCamera();
	}
	
	public KeyManager getKeyManager() {
		return engine.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return engine.getMouseManager();
	}
	
	public Calendar getCalendar() {
		return engine.getCalendar();
	}
	
	public int getWidth() {
		return engine.getWidth();
	}
	
	public int getHeight() {
		return engine.getHeight();
	}
	
	public int getCanvasWidth() {
		return engine.getDisplay().getCanvasWidth();
	}
	
	public int getCanvasHeight() {
		return engine.getDisplay().getCanvasHeight();
	}

	public Player getPlayer() {
		return engine.getPlayer();
	}
	
	public int getFPS() {
		return engine.getFPS();
	}
	
	public void setxStart(int xStart) {
		this.xStart = xStart;
	}
	
	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
	
	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}
	
	public int getxStart() {
		return xStart;
	}
	
	public int getxEnd() {
		return xEnd;
	}
	
	public int getyStart() {
		return yStart;
	}
	
	public int getyEnd() {
		return yEnd;
	}
	
	public double getScalerWorld() {
		return scalerWorld;
	}

	public void setScalerWorld(double scalerWorld) {
		this.scalerWorld = scalerWorld;
	}

	public double getScalerHUD() {
		return scalerHUD;
	}

	public void setScalerHUD(double scalerHUD) {
		this.scalerHUD = scalerHUD;
	}
	
	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}
	
	public Atmosphere getAtmosphere() {
		return this.atmosphere;
	}
}
