package phase3.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import phase3.Handler;
import phase3.calculations.Constants;

/**
 * A class which creates the display for the GUI
 *
 */


public class Display implements MouseListener {
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	private int canvasWidth;
	
	private Handler handler;
	
	//for testing purposes
	private int[] screensize1 = {1280,720};
	private int[] screensize2 = {1920,1080};
	private int[] screensize3 = {2560,1440};
	private int[] screensize4 = {3840,2160};
	
	
	
	private Font genericFont = new Font("Monospaced", Font.BOLD, 20);
	
	public Display(Handler handler, String title, int width, int height) {
		this.handler = handler;
		this.title = title;
		createDisplay();
	}
	
	private void createDisplay() {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();
		
		//decomment to test program with different screen sizes;
		//width = screensize2[0];
		//height = screensize2[1];
		Constants.setScreenWidth(width);
		Constants.setScreenHeight(height);
		
		
		frame = new JFrame(title);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		
		canvasWidth = width;
		
		JPanel canvasPanel = new JPanel();
		canvasPanel.setFocusable(false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(canvasWidth, height));
		canvas.setMaximumSize(new Dimension(canvasWidth, height));
		canvas.setMinimumSize(new Dimension(canvasWidth, height));
		canvas.setBackground(Color.black);
		canvas.setFocusable(true);
		canvas.addMouseListener(this);
		
		canvasPanel.add(canvas);
		frame.add(canvasPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame() {
		return frame;
	}
	
	public int getCanvasWidth() {
		return canvasWidth;
	}
	
	public int getCanvasHeight() {
		return height;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
