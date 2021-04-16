package Objects;
import javax.swing.*;
import java.util.*;
import Engine.*;
import GameState.GameStateManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;

/**
 * @author jamescolbert
 */
public class Player extends GameObject implements KeyListener {
	private float speed = 200;
	private boolean canShoot = true;
	
	public final float SHOOT_DELAY = 0.17f;	// How many seconds it takes to shoot a regular cyan laser pulse
	public final float BLAST_DELAY = 15;	// How many seconds it takes to shoot a blast from the start/after one is shot
	private final int BLAST_COST = 50;		// How much each magenta blast costs in terms of player score
	
	private float lastShoot = 0;			// The time since the last regular cyan laser pulse was launched
	public float lastBlast = 0;				// The time since the last magenta blast was shot
	
	public int score = 0;					// The player's score
	
	private Vector2 defaultPosition;
	
    private GameStateManager manager = GameStateManager.Instance();
    
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    
    public Player(Vector2 position, Vector2 origin, Color color, float width, float height) {
        this.position = position;
        defaultPosition = new Vector2(position);
        this.color = color;
        this.origin = origin;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void update(float deltaTime) {
    	lastShoot += deltaTime;
    	lastBlast += deltaTime;
    	
    	if (position.y > defaultPosition.y) {
    		if (position.y - (100 * deltaTime) <= defaultPosition.y) {
    			position.y = defaultPosition.y;
    		} else {
    			position.y -= (100 * deltaTime);
    		}
    	} else if (position.y < defaultPosition.y) {
    		position.y = defaultPosition.y;
    	}
    	
    	if (manager.pressedKeys.contains(KeyEvent.VK_A) && position.x - (origin.x * width) > 0) {
    		position.x -= speed * deltaTime;
    	}
    	
    	if (manager.pressedKeys.contains(KeyEvent.VK_D) && position.x + (origin.x * width) < manager.WIDTH) {
    		position.x += speed * deltaTime;
    	}
    	
    	if (manager.pressedKeys.contains(KeyEvent.VK_SPACE) && canShoot && lastShoot > SHOOT_DELAY) {
    		bullets.add(new Bullet(new Vector2(position.x, position.y - (origin.y * height)), (float)Math.PI / 2, new Vector2(0.5f, 0.5f), Color.CYAN, 5));
    		canShoot = false;
    		lastShoot = 0;
    		position.y = defaultPosition.y + 5;
    	} else if (manager.pressedKeys.contains(KeyEvent.VK_SHIFT) && canShoot && lastShoot > SHOOT_DELAY && lastBlast > BLAST_DELAY && score >= BLAST_COST) {
    		bullets.add(new Bullet(new Vector2(position.x, position.y - (origin.y * height)), (float)Math.PI / 2, new Vector2(0.5f, 0.5f), Color.MAGENTA, 10));
    		score -= BLAST_COST;
    		canShoot = false;
    		lastShoot = 0;
    		lastBlast = 0;
    		position.y = defaultPosition.y + 10;
    	}
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        
        Path2D path = new Path2D.Float();
        
        path.moveTo(position.x - (origin.x * width), position.y + (origin.y * height));
        path.lineTo(position.x, position.y - (origin.y * height));
        path.lineTo(position.x + (origin.x * width), position.y + (origin.y * height));
        
        path.closePath();
        g2D.setColor(color);
        g2D.fill(path);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_SHIFT) {
			System.out.println("pressed blast thing");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_SHIFT) {
			canShoot = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}