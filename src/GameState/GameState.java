package GameState;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

import Engine.AssetManager;
import Objects.GameObject;
/**
 * @author jamescolbert
 */
public abstract class GameState extends JPanel {
	public GameStateManager gameStateManager = GameStateManager.Instance();
	public AssetManager assetManager = AssetManager.Instance();
    
    public abstract void loadContent();
    public abstract void unloadContent();
    public abstract void update(float deltaTime);
    public abstract void draw(Graphics g);
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
//    	for (GameObject GO : objects) {
//    		GO.draw(g);
//    	}
    	draw(g);
    }
    
    @Override
	public Dimension getPreferredSize() {
		return new Dimension(gameStateManager.WIDTH, gameStateManager.HEIGHT);
	}
}