package Screens;

import GameState.GameState;
import GameState.GameStateManager;
import Objects.Bullet;
import Objects.GameObject;
import Objects.Player;
import Objects.Tile;
import Objects.TileManager;
import Objects.TileType;
import Engine.Vector2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author jamescolbert
 */

public class GameplayScreen extends GameState {

	private final int TILE_SIZE = 40;	// The size (in pixels) of each tile's width and height
	private float tileDropDelay = 0; 	// The time (in seconds) since the last drop
	private Tile[][] tiles;
	
	private Player player;
	private JLabel currentScore, blastCountdown, currentLevel;
	private int level = 0;
	private int levelThreshold = 150;
	
	private float[] levels = new float[] {
		5.0f, 4.5f, 4.0f, 3.5f, 3.0f, 2.5f, 2.0f, 1.5f, 1.0f
		
	 // 150   300   450   600   750   900   1050  1200  1350
	};
	
	public GameplayScreen() {
		player = new Player(new Vector2(manager.WIDTH / 2, manager.HEIGHT - 100), new Vector2(0.5f, 0.5f), Color.red, 30, 40);
		GameStateManager.frame.addKeyListener(player);
		
		setLayout(null);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		currentScore = new JLabel("Score: " + player.score, SwingConstants.LEFT);
		currentScore.setFont(manager.scoreFont);
		currentScore.setBounds(10, manager.HEIGHT - 70, 400, 100);
		
		blastCountdown = new JLabel("Next Blast: " + (player.BLAST_DELAY), SwingConstants.RIGHT);
		blastCountdown.setFont(manager.scoreFont);
		blastCountdown.setBounds(manager.WIDTH - 410, manager.HEIGHT - 70, 400, 100);
		
		currentLevel = new JLabel("Level " + level, SwingConstants.CENTER);
		currentLevel.setFont(manager.scoreFont);
		currentLevel.setBounds(manager.WIDTH / 2 - 200, manager.HEIGHT -70, 400, 100); 

		add(currentScore);
		add(blastCountdown);
		add(currentLevel);
	}
	
	@Override
	public void loadContent() {
		tiles = new Tile[manager.HEIGHT / TILE_SIZE][manager.WIDTH / TILE_SIZE];
		
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (y < 5) {
					tiles[y][x] = new Tile(new Vector2(x * TILE_SIZE, y * TILE_SIZE), TILE_SIZE, TILE_SIZE);
				} else {
					tiles[y][x] = new Tile();
				}
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		tileDropDelay += deltaTime;
		player.update(deltaTime);
		
		int countdown = (int)(player.BLAST_DELAY - player.lastBlast > 0 ? player.BLAST_DELAY - player.lastBlast : 0);
		blastCountdown.setText("Next Blast: " + countdown);
		
		if (countdown <= 0 && blastCountdown.getForeground() != Color.MAGENTA) {
			blastCountdown.setForeground(Color.MAGENTA);
		} else if (countdown > 0 && blastCountdown.getForeground() != manager.defaultColor) {
			blastCountdown.setForeground(manager.defaultColor);
		}
		
    	ArrayList<Bullet> newBullets = new ArrayList<Bullet>(player.bullets);
    	for(Bullet bullet : player.bullets) {
    		bullet.update(deltaTime);
    		Tile tile = bullet.collidesTile(tiles);
    		if (tile != null) {
    			// If it's not an empty tile
    			newBullets.remove(bullet);
    			player.score += tile.destroy(tiles, bullet.radius == 5);;
    			currentScore.setText("Score: " + player.score);
    		}
    		if (!bullet.inBounds()) {
    			addLine(); // punishes player for acting carelessly
    			newBullets.remove(bullet);
    		}
    	}
    	player.bullets = new ArrayList<Bullet>(newBullets);
    	
    	// Move tiles down for the according time value in the "levels" array
    	if (tileDropDelay >= levels[level]) {
//    		System.out.println("Time it takes: " + levels[level]);
    		addLine();
    		tileDropDelay = 0;
    	}
    	
    	if ((player.score >= (level + 1) * levelThreshold && level < levels.length - 1)) {
    		level++;
    		currentLevel.setText("Level " + level);
    	}
    }

	public void addLine() {
		// Starts at the bottom row
		outerloop:
		for (int y = tiles.length - 1; y >= 0; y--) {
			if (y != 0) {
				for (int x = 0; x < tiles[y].length; x++) {
					tiles[y][x] = new Tile(tiles[y - 1][x]); // "Moves" the row above down to the current row
					tiles[y][x].position.add(0, TILE_SIZE);
					if (tiles[y][x].type != TileType.EMPTY && tiles[y][x].position.y >= manager.HEIGHT - 150) {
						// If a non-empty tile is below position 450 (default height minus 150), the player loses
						manager.changeScreens(new GameOverScreen(player.score));
						break outerloop;
					}
				}
			} else {
				for (int x = 0; x < tiles[0].length; x++) {
					tiles[0][x] = new Tile(new Vector2(TILE_SIZE * x, 0), TILE_SIZE, TILE_SIZE);
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		for (Bullet bullet : player.bullets) {
			bullet.draw(g);
		}

		for (Tile[] _tile : tiles) {
			for (Tile tile : _tile) {
				tile.draw(g);
			}
		}
		player.draw(g);
	}

	@Override
	public void unloadContent() {
		GameStateManager.frame.removeKeyListener(player);
	}
}