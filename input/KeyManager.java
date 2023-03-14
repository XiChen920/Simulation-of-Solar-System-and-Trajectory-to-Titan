package phase3.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class which has all the keyboard keys registered. This is used to let the user interact with the engine
 *
 */
public class KeyManager implements KeyListener {
	private boolean[] keys;
	public boolean leftarrow, rightarrow, zero, upperarrow, up, down, left, right, e, q, one, two, three, delete, space, plus, min, openbracket, closebracket, shift, ctrl, f, tab;
	
	public KeyManager() {
		keys = new boolean[1024];
	}
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		e = keys[KeyEvent.VK_E];
		q = keys[KeyEvent.VK_Q];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		delete = keys[KeyEvent.VK_DELETE];
		space = keys[KeyEvent.VK_SPACE];
		plus = keys[KeyEvent.VK_EQUALS];
		min = keys[KeyEvent.VK_MINUS];
		openbracket = keys[KeyEvent.VK_OPEN_BRACKET];
		closebracket = keys[KeyEvent.VK_CLOSE_BRACKET];
		shift = keys[KeyEvent.VK_SHIFT];
		ctrl = keys[KeyEvent.VK_CONTROL];
		f = keys[KeyEvent.VK_F];
		upperarrow = keys[KeyEvent.VK_UP];
		zero = keys[KeyEvent.VK_0];
		leftarrow = keys[KeyEvent.VK_LEFT];
		rightarrow = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
