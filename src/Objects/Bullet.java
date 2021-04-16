package Objects;

import javax.swing.*;

import Engine.Vector2;
import GameState.GameStateManager;

import java.awt.geom.*;
import java.awt.*;

/**
 * @author jamescolbert
 */
public class Bullet extends GameObject {
	private GameStateManager manager = GameStateManager.Instance();
	public Vector2 direction;
	public float speed = 700;
	public float radius;

	public Bullet(Vector2 position, float rotation, Vector2 origin, Color color, float radius) {
		this.position = position;
		this.color = color;
		this.origin = origin;
		this.width = radius * 2;
		this.height = radius * 2 + 15;
		this.radius = radius;

		// 'rotation' is in radians
		direction = new Vector2((float) Math.cos(rotation - Math.PI), (float) Math.sin(rotation - Math.PI));
		direction.normalize();
//        System.out.println(direction.x + ", " + direction.y + ", "  + (rotation - Math.PI));
	}

	public void update(float deltaTime) {
		position.x += direction.x * deltaTime * speed;
		position.y += direction.y * deltaTime * speed;
		
		if (height > radius) {
			height -= 0.000005f;
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Ellipse2D.Float circle = new Ellipse2D.Float(position.x - (origin.x * width), position.y - (origin.y * height),
				width, height);

		g2D.setColor(color);
		g2D.fill(circle);
	}

	public boolean inBounds() {
		return (position.x < manager.WIDTH && position.x > 0 && position.y < manager.HEIGHT
				&& position.y > 0);
	}

	public Tile collidesTile(Tile[][] tiles) {
		Tile tileToReturn = null;
		outerloop: for (Tile[] _tiles : tiles) {
			for (Tile tile : _tiles) {
//				if (tile.type != TileType.EMPTY) {
//					Vector2 circleDistance = new Vector2(Math.abs(position.x - tile.position.x),
//							Math.abs(position.y - tile.position.y));
//					if (circleDistance.x > (tile.width / 2) + radius || circleDistance.y > (tile.height / 2) + radius) {
//						tileToReturn = null;
//					}
//
//					if (circleDistance.x <= (tile.width / 2) || circleDistance.y <= (tile.height / 2)) {
//						tileToReturn = tile;
//						System.out.println("less than or equal");
//					}
//
//					float cornerDistance_sq = ((circleDistance.x - tile.width / 2)
//							* (circleDistance.x - tile.width / 2))
//							+ ((circleDistance.y - tile.height / 2) * (circleDistance.y - tile.height / 2));
//
//					if (cornerDistance_sq <= radius * radius) {
//						tileToReturn = tile;
//						System.out.println("last one");
//					} else if (tileToReturn == null) {
//						tileToReturn = null;
//					}
//				}
//				if (tileToReturn != null) {
//					break outerloop;
//				}
				if (tile.type != TileType.EMPTY) {
					if (position.x >= tile.position.x &&
							position.x <= tile.position.x + tile.width &&
							position.y >= tile.position.y &&
							position.y < tile.position.y + tile.height) {
							tileToReturn = tile;
							break outerloop;
						}
				}
			}
		}
		return tileToReturn;
	}
}