package Screens;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Objects.GameObject;

public class SpritePanel extends JPanel {
	private ArrayList<GameObject> objects;
	
	public SpritePanel() {
		objects = new ArrayList<GameObject>();
	}
	
//	public void addObject()
	
	public void begin(Graphics g) {
		super.paintComponent(g);
	}
	
	public void end() {
		repaint();
	}
}