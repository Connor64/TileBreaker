package Objects;
import javax.swing.*;

import Engine.Vector2;

import java.awt.geom.*;
import java.awt.*;
/**
 * @author jamescolbert
 */
enum Layer {
    Player,
    Enemy
}

public abstract class GameObject {
//    public String name;
	public Vector2 position, origin;
	public Color color = Color.WHITE;
	public float width, height;
    
    public abstract void update(float deltaTime);
    public abstract void draw(Graphics g);
}