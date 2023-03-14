package phase3.galaxies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import phase3.Handler;

/**
 * A class which sets the scale for surface visualisation of titan
 *
 */

public class TitanSurfaceWorld {
	
	//1 pixel = 1AU = 149.597.870.691 meters = 149.597.871 kilometers
	private final int DEFAULT_WIDTH = 70000;
	private final int DEFAULT_HEIGHT = 70000;
	
	private int width, height;
	
	private Font gridFont;
	private int fontSize;
	
	//screen settings
	private double scalerHUD, scalerRangeStart, scalerRangeEnd;
	private double interval;
	private int repetitions;
	
	private Handler handler;
	
	public TitanSurfaceWorld(Handler handler) {
		this.handler = handler;
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		scalerRangeStart = 2.2;
		scalerRangeEnd = 1.2;
		interval = 100;	
		fontSize = 20;
		repetitions = 350;
		}
	
	public void update(double scalerWorld, double scalerHUD) {
		this.scalerHUD = scalerHUD;
	}
	
	public void render(Graphics2D g) {
		
		g.setColor(Color.black);		
		gridMaker(g);
	}
	
	private void gridMaker(Graphics2D g) {
			
		Color fadedWhite = new Color(1, 1, 1, 0.3f);
		Color strongWhite = new Color(1, 1, 1, 1f);
		g.setColor(fadedWhite);
		
		//grid spacing while zooming out
		if(scalerHUD>scalerRangeEnd && scalerHUD < 6) {
			interval = interval*2;
			scalerRangeStart++;
			scalerRangeEnd++;
			if(fontSize>=20) {
				fontSize*=2;
				repetitions/=1.5;
			}
				
				
			gridFont = new Font("Consolas", Font.PLAIN, fontSize);
		}
		//grid spacing while zooming in
		if (scalerHUD<scalerRangeStart) {
			interval = interval/2;
			scalerRangeStart--;
			scalerRangeEnd--;
			if(fontSize>=40) {
				fontSize/= 2;
				if(repetitions<250) {
					repetitions*=1.5;
				}
				
			}
				
			gridFont = new Font("Consolas", Font.PLAIN, fontSize);	
		} 
		//coordinate system drawing
		for(int i = -repetitions; i <= repetitions; i++) {
			g.setFont(gridFont);
			if(i == 0)
				g.setStroke(new BasicStroke(4));
			
			//Horizontal
			g.setColor(strongWhite);
			g.drawString((2*i*interval/1)+"m",(int) (5 + (2*i*interval) - handler.getEngineCamera().getxOffset()), (int) (-10 - handler.getEngineCamera().getyOffset())); 
			
			if(i<0 || i>0)
				g.setColor(fadedWhite);
			g.drawLine((int)((2*i*interval) - handler.getEngineCamera().getxOffset()), -35000, (int)((2*i*interval)-handler.getEngineCamera().getxOffset()), width);
			
			//Vertical
			g.setColor(strongWhite);
			g.drawString((-2*i*interval/1)+"m",(int) (5 - handler.getEngineCamera().getxOffset()), (int) ((2*i*interval) - 10 - handler.getEngineCamera().getyOffset()));
			
			if(i<0 || i>0)
				g.setColor(fadedWhite);
			g.drawLine(-35000,(int)((2*i*interval) - handler.getEngineCamera().getyOffset()), width, (int)((2*i*interval) - handler.getEngineCamera().getyOffset()));
			
			g.setStroke(new BasicStroke(1));
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
