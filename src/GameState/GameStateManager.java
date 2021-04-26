// Modified from tutorial by cmason at Rare Element Games: https://rareelementgames.wordpress.com/2017/04/21/game-state-management/
package GameState;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jamescolbert
 */
public class GameStateManager implements KeyListener {

	private Stack<GameState> screens = new Stack<GameState>();

	public static JFrame frame;
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	public boolean running = true;
	
	public int highScore = 0;

	public SimpleAttributeSet centerStyle;

	public Stack<Integer> pressedKeys = new Stack<Integer>();
//    public Stack<Integer> oldKeys = new Stack<Integer>();

	private static GameStateManager instance = null;

	public static GameStateManager Instance() {
		if (instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}

	public GameStateManager() {
		frame = new JFrame("Tile Breaker");
		frame.setVisible(true);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				running = false;
				System.exit(0);
			}
		});
		frame.addKeyListener(this);
		frame.repaint();
		
		centerStyle = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);
	}

	public void addScreen(GameState screen) {
		try {
			if (screens.size() > 0) {
				frame.remove(screens.peek());
			}
			frame.add(screen);
			frame.pack();
			screens.push(screen);
			screens.peek().loadContent();
			screens.peek().setVisible(true);
			frame.revalidate();
			frame.repaint();
			frame.requestFocusInWindow();
		} catch (Exception e) {
			System.out.println("Error upon adding new screen to stack: ");
			e.printStackTrace();
		}
	}

	public void removeTopScreen() {
		if (screens.size() > 0) {
			try {
				screens.peek().unloadContent();
				frame.remove(screens.peek());
				screens.pop();
				if (screens.size() > 0) {
					frame.add(screens.peek());
					frame.pack();
					frame.revalidate();
					frame.repaint();
					frame.requestFocusInWindow();
				}
			} catch (Exception e) {
				System.out.println("Error upon removing top screen from stack: ");
				e.printStackTrace();
			}
		}
	}

	public void clearScreens() {
		try {
			while (screens.size() > 0) {
				screens.peek().unloadContent();
				screens.peek().setVisible(false);
				frame.remove(screens.peek());
				screens.pop();
			}
		} catch (Exception e) {
			System.out.println("Error upon clearing the screens from stack: ");
			e.printStackTrace();
		}
	}

	public void changeScreens(GameState screen) {
		try {
			clearScreens();
			addScreen(screen);
		} catch (Exception e) {
			System.out.println("Error upon changing screens: ");
			e.printStackTrace();
		}
	}

	public void update(float deltaTime) {
		try {
			if (screens.size() > 0) {
				screens.peek().update(deltaTime);
			}
		} catch (Exception e) {
			System.out.println("Error upon updating top screen: ");
			e.printStackTrace();
		}
	}

	public void draw() {
		try {
			if (screens.size() > 0) {
				screens.peek().repaint();
			}
		} catch (Exception e) {
			System.out.println("Error upon drawing top screen: ");
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!pressedKeys.contains(e.getExtendedKeyCode())) {
			pressedKeys.add(e.getExtendedKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.removeElement(e.getExtendedKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}