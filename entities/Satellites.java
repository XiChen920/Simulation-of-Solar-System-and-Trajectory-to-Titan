package phase3.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import phase3.Handler;
import phase3.input.SpaceObject;

/**
 * A class which carries all the planet objects
 *
 */

public class Satellites {
	 
	private Handler handler;
	
	
	//planet objects
	public Planets[] planets;
	
	//celestial objects
	private Planets sun;
	private Planets mercury;
	private Planets venus;
	private Planets earth;
	private Planets moon;
	private Planets mars;
	private Planets jupiter;
	private Planets saturn;
	private Planets titan;
	private Planets uranus;
	private Planets neptune;
	
	
	public Satellites(Handler handler) {
		this.handler = handler;
		planets = new Planets[11];
		celestialObjectCreator();
	}

	public void update() {
		sun.update();
		mercury.update();
		venus.update();
		earth.update();
		moon.update();
		mars.update();
		jupiter.update();
		saturn.update();
		titan.update();
		uranus.update();
		neptune.update();
	}
	
	
	public void render(Graphics2D g) {
		
		g.setColor(Color.orange);
		g.setFont(new Font("monospaced", Font.BOLD, 20));
		sun.render(g);
		g.setColor(Color.darkGray);
		mercury.render(g);
		g.setColor(Color.yellow);
		venus.render(g);
		g.setColor(Color.green);
		earth.render(g);
		g.setColor(Color.gray);
		moon.render(g);
		g.setColor(Color.red);
		mars.render(g);
		g.setColor(Color.lightGray);
		jupiter.render(g);
		g.setColor(Color.darkGray);
		saturn.render(g);
		g.setColor(Color.yellow);
		titan.render(g);
		g.setColor(Color.cyan);
		uranus.render(g);
		g.setColor(Color.white);		
		neptune.render(g);
	}
	
	private void celestialObjectCreator() {
		sun = new Planets(0, handler, SpaceObject.Sun.getMass(), Math.round(SpaceObject.Sun.getRadius()/5000000), SpaceObject.Sun.getLocation().getX(), SpaceObject.Sun.getLocation().getY(), SpaceObject.Sun.getLocation().getZ(), SpaceObject.Sun.getVelocity().getX(), SpaceObject.Sun.getVelocity().getY(), SpaceObject.Sun.getVelocity().getZ());
		mercury = new Planets(1, handler, SpaceObject.Mercury.getMass(), Math.round(SpaceObject.Mercury.getRadius()/1000000), SpaceObject.Mercury.getLocation().getX(), SpaceObject.Mercury.getLocation().getY(), SpaceObject.Mercury.getLocation().getZ(), SpaceObject.Mercury.getVelocity().getX(), SpaceObject.Mercury.getVelocity().getY(), SpaceObject.Mercury.getVelocity().getZ());
		venus = new Planets(2, handler, SpaceObject.Venus.getMass(), Math.round(SpaceObject.Venus.getRadius()/1000000), SpaceObject.Venus.getLocation().getX(), SpaceObject.Venus.getLocation().getY(), SpaceObject.Venus.getLocation().getZ(), SpaceObject.Venus.getVelocity().getX(), SpaceObject.Venus.getVelocity().getY(), SpaceObject.Venus.getVelocity().getZ());
		earth = new Planets(3, handler, SpaceObject.Earth.getMass(), Math.round(SpaceObject.Earth.getRadius()/1000000), SpaceObject.Earth.getLocation().getX(), SpaceObject.Earth.getLocation().getY(), SpaceObject.Earth.getLocation().getZ(), SpaceObject.Earth.getVelocity().getX(), SpaceObject.Earth.getVelocity().getY(), SpaceObject.Earth.getVelocity().getZ());
		moon = new Planets(4, handler, SpaceObject.Moon.getMass(), Math.round(SpaceObject.Moon.getRadius()/1000000), SpaceObject.Moon.getLocation().getX(), SpaceObject.Moon.getLocation().getY(), SpaceObject.Moon.getLocation().getZ(), SpaceObject.Moon.getVelocity().getX(), SpaceObject.Moon.getVelocity().getY(), SpaceObject.Moon.getVelocity().getZ());
		mars = new Planets(5, handler, SpaceObject.Mars.getMass(), Math.round(SpaceObject.Mars.getRadius()/1000000), SpaceObject.Mars.getLocation().getX(), SpaceObject.Mars.getLocation().getY(), SpaceObject.Mars.getLocation().getZ(), SpaceObject.Mars.getVelocity().getX(), SpaceObject.Mars.getVelocity().getY(), SpaceObject.Mars.getVelocity().getZ());
		jupiter = new Planets(6, handler, SpaceObject.Jupiter.getMass(), Math.round(SpaceObject.Jupiter.getRadius()/1000000), SpaceObject.Jupiter.getLocation().getX(), SpaceObject.Jupiter.getLocation().getY(), SpaceObject.Jupiter.getLocation().getZ(), SpaceObject.Jupiter.getVelocity().getX(), SpaceObject.Jupiter.getVelocity().getY(), SpaceObject.Jupiter.getVelocity().getZ());
		saturn = new Planets(7, handler, SpaceObject.Saturn.getMass(), Math.round(SpaceObject.Saturn.getRadius()/1000000), SpaceObject.Saturn.getLocation().getX(), SpaceObject.Saturn.getLocation().getY(), SpaceObject.Saturn.getLocation().getZ(), SpaceObject.Saturn.getVelocity().getX(), SpaceObject.Saturn.getVelocity().getY(), SpaceObject.Saturn.getVelocity().getZ());
		titan = new Planets(8, handler, SpaceObject.Titan.getMass(), Math.round(SpaceObject.Titan.getRadius()/1000000), SpaceObject.Titan.getLocation().getX(), SpaceObject.Titan.getLocation().getY(), SpaceObject.Titan.getLocation().getZ(), SpaceObject.Titan.getVelocity().getX(), SpaceObject.Titan.getVelocity().getY(), SpaceObject.Titan.getVelocity().getZ());
		uranus = new Planets(9, handler, SpaceObject.Uranus.getMass(), Math.round(SpaceObject.Uranus.getRadius()/1000000), SpaceObject.Uranus.getLocation().getX(), SpaceObject.Uranus.getLocation().getY(), SpaceObject.Uranus.getLocation().getZ(), SpaceObject.Uranus.getVelocity().getX(), SpaceObject.Uranus.getVelocity().getY(), SpaceObject.Uranus.getVelocity().getZ());
		neptune = new Planets(10, handler, SpaceObject.Neptune.getMass(), Math.round(SpaceObject.Neptune.getRadius()/1000000), SpaceObject.Neptune.getLocation().getX(), SpaceObject.Neptune.getLocation().getY(), SpaceObject.Neptune.getLocation().getZ(), SpaceObject.Neptune.getVelocity().getX(), SpaceObject.Neptune.getVelocity().getY(), SpaceObject.Neptune.getVelocity().getZ());
		
		planets[0] = sun;
		planets[1] = mercury;
		planets[2] = venus;
		planets[3] = earth;
		planets[4] = moon;
		planets[5] = mars;
		planets[6] = jupiter;
		planets[7] = saturn;
		planets[8] = titan;
		planets[9] = uranus;
		planets[10] = neptune;
	}	
}
