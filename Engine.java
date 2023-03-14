package phase3;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import phase3.calculations.Atmosphere;
import phase3.calculations.Calendar;
import phase3.display.Display;
import phase3.entities.Player;
import phase3.gfx.EngineCamera;
import phase3.input.KeyManager;
import phase3.input.MouseManager;
import phase3.states.LandingState;
import phase3.states.ModelingState;
import phase3.states.State;

/**
 *
 *
 *	The main class which starts the engine and contains the engine loop
 *
 */

public class Engine implements Runnable{
	
	private Display display;
	
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics2D g;
	
	private Calendar calendar;
	
	private Player player;

	private int fps;
	
	//Camera
	private EngineCamera engineCamera;
	
	//States
	private State engineState;
	
	//Handler
	private Handler handler;
	
	//input
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	//atmosphere
	private Atmosphere atmosphere;
	
	public Engine() {
		this.title = "Solar System - Group 21";
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		this.width = gd.getDisplayMode().getWidth();
		this.height = gd.getDisplayMode().getHeight();
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}
	
	/**
	 * initializes the engine
	 * 
	 * creates a handler through which all classes can access variables within the engine (this) class
	 * creates the display 
	 * links keymanager/mousemanager to the display
	 * creates a calendar object
	 * creates a player object (this will not be rendered, but will just be the center of the camera)
	 * creates the camera centered on the player
	 * creates the first state
	 * sets the initial state
	 * 
	 */
	private void init() {
		System.out.println("w = up, s = down, a = left, d = right, q = zoom out, e = zoom in");
		handler = new Handler(this);
		display = new Display(handler, title, width, height);
		display.getCanvas().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		calendar = new Calendar();
		player = new Player(handler);
		
		
		engineCamera = new EngineCamera(handler, handler.getCanvasWidth()/2, handler.getCanvasHeight()/2); //these make sure that the starting focus is the ssb
		
		
		engineState = new ModelingState(handler);
		engineState = new LandingState(handler);

		State.setState(engineState);
		
	}
	
	/**
	 * updates variables of the current state
	 * 
	 * continuously asks for input from keys and checks what the current state is
	 */
	private void update() {
		keyManager.update();
		
		if(State.getState() != null) {
			State.getState().update();
		}
	}
	
	/**
	 * renders the updated variables of the current state
	 * 
	 * sets a bufferstrategy to prefend any flickering issues
	 * gets the drawgraphics object of the bufferstrategy
	 * clears everything within the selected borders
	 * calls the render method of the current state
	 * shows the rendered stuff on the bufferedimage
	 * disposes of the graphics object
	 * 
	 */
	private void render() {	
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		} 
		
		g = (Graphics2D) bs.getDrawGraphics();
		
		//clear screen
		g.clearRect(0,0, 70000, 70000);
		
		//Draw here!
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//End drawing!
		bs.show();
		g.dispose();
		
	}
	
	
	/**
	 * main engine loop
	 * 
	 * at first it initializes everything
	 * then it makes sure that the engine runs at a specific fps
	 * then it loops throught the update and render method until the program closes
	 */
	@Override
	public void run() {
		init();
		
		fps = 60;
		double timePerTick = 1000000000 / fps; //1 second divided by fps
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		//fps checker
		long timer = 0;
		int updates = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/ timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				updates++;
				delta--;
			}
			if(timer>=1000000000) {
				//System.out.println("Updates and frames: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	/**
	 * starts the program
	 */
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * stops the program
	 */
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	//getters
	public static void setEngineState(State enginestate) {
		 enginestate.setState(enginestate);
	}
	
	public EngineCamera getEngineCamera() {
		return engineCamera;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getFPS() {
		return fps;
	}
}

