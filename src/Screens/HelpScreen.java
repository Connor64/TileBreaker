package Screens;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import GameState.GameState;

public class HelpScreen extends GameState implements ActionListener {
	
	private JTextArea objective, controls;
	private JButton backButton;
	private JLabel controlsIcon; 
	
	public HelpScreen() {
		setLayout(null);
		objective = new JTextArea(
				"The objective of Tile Breaker is to get the highest score you possibly can. Don't let the tiles reach your ship and be careful not to shoot above the tiles or more will come in!"
				+ "\n\nBlue tiles are worth 1 point."
				+ "\n\nRed tiles are worth 5 points."
				+ "\n\nGray tiles are worth 10 points but take 4 hits to destroy.");
		objective.setFont(assetManager.detailFont);
		objective.setBounds(10, 10, gameStateManager.WIDTH / 2, 400);
		objective.setLineWrap(true);
		objective.setWrapStyleWord(true);
		objective.setBackground(new Color(0, 0, 0, 0));
		objective.setEditable(false);
		
		controls = new JTextArea(
				"Move Left: '<' or 'A'"
				+ "\n\nMove Right: '>' or 'D'"
				+ "\n\nShoot: 'Spacebar'"
				+ "\n\nRelease Energy: 'Shift'");
		controls.setFont(assetManager.detailFont);
		controls.setBounds(50 + gameStateManager.WIDTH/2, 150, gameStateManager.WIDTH/2 - 10, 300);
		controls.setLineWrap(true);
		controls.setWrapStyleWord(true);
		controls.setBackground(new Color(0, 0, 0, 0));
		controls.setForeground(Color.red);
		controls.setEditable(false);
		
		backButton = new JButton("Back");
		backButton.setActionCommand("BACK");
		backButton.setBounds(10, gameStateManager.HEIGHT - 60, 100, 50);
		backButton.setBackground(Color.white);
		backButton.setFont(assetManager.buttonFont);
		backButton.setVisible(true);
		
		int width = assetManager.controlsImage.getWidth() * 4;
		int height = assetManager.controlsImage.getHeight() * 4;
		controlsIcon = new JLabel(new ImageIcon(assetManager.controlsImage.getScaledInstance(width, height, Image.SCALE_FAST)));
		controlsIcon.setBounds(controls.getX(), 10, width, height);
		
		add(objective);
		add(controls);
		add(backButton);
		add(controlsIcon);
		
		backButton.addActionListener(this);
	}

	@Override
	public void loadContent() {
		
	}

	@Override
	public void unloadContent() {
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "BACK":
			gameStateManager.removeTopScreen();
			break;
		default:
			System.err.println("Unable to parse action command: " + e.getActionCommand());
			break;
		}
		
	}
}