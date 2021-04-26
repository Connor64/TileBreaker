package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import Engine.Vector2;

public class Tile extends GameObject {
	public TileType type;
	public static final int SAMPLE_SIZE = 20;
	public int health, value;
	private TileManager tileManager = TileManager.Instance();

	public Tile(Vector2 position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		type = tileManager.randomType();

		switch (type) {
		case NORMAL:
			color = Color.blue;
			value = 1;
			health = 1;
			break;
		case BOMB:
			color = Color.red;
			value = 0;
			health = 1;
			break;
		case INDESTRUCTIBLE:
			color = Color.gray;
			value = 10;
			health = 4;
			break;
		}
	}

	public Tile() {
		position = Vector2.Zero;
		width = 0;
		height = 0;
		type = TileType.EMPTY;
		color = Color.white;
		health = 0;
	}

	public Tile(Tile tile) {
		position = tile.position;
		width = tile.width;
		height = tile.height;
		type = tile.type;
		color = tile.color;
		health = tile.health;
	}

	@Override
	public void update(float deltaTime) {
	}

	public boolean damage() {
		health--;
		color = color.darker();
		return health <= 0; // returns true if health is less than or equal to 0
	}

	public int destroy(Tile[][] tiles) {
		int score = 0;
		switch (type) {
		case NORMAL:
			score = 1;
			break;
		case BOMB:
			for (Tile tile : tiles[(int) (position.y / width)]) {
				value += tile.value;
				tile.type = TileType.EMPTY;
			}
			score = 5;
			break;
		case INDESTRUCTIBLE:
			type = TileType.EMPTY;
			score = 10;
			break;
		}
		type = TileType.EMPTY;

		return score;
	}

	@Override
	public void draw(Graphics g) {
		if (type != TileType.EMPTY) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.setColor(color);
			g2D.fillRect((int) position.x, (int) position.y, (int) width, (int) height);

			g2D.setColor(Color.BLACK);
			g2D.drawRect((int) position.x, (int) position.y, (int) width, (int) height);
		}
	}
}