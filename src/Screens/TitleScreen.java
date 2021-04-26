package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import GameState.GameState;
import GameState.GameStateManager;

public class TitleScreen extends GameState implements ActionListener {
	
	private JButton startButton, quitButton, objectiveButton;
	private JLabel title, developerName, year;
	
	public TitleScreen() {
		setLayout(null);
		
		title = new JLabel("Tile Breaker", SwingConstants.CENTER);
		title.setBounds(gameStateManager.WIDTH / 2 - 350, 125, 700, 100);
		title.setFont(assetManager.titleFont);
		
		developerName = new JLabel("Connor Colbert", SwingConstants.LEFT);
		developerName.setBounds(10, gameStateManager.HEIGHT - 70, 500, 100);
		developerName.setFont(assetManager.detailFont);
		
		year = new JLabel("2021", SwingConstants.RIGHT);
		year.setBounds(gameStateManager.WIDTH - 110, gameStateManager.HEIGHT - 70, 100, 100);
		year.setFont(assetManager.detailFont);
		
		startButton = new JButton("Start");
		startButton.setActionCommand("START");
		startButton.setBounds(gameStateManager.WIDTH / 2 - 150, gameStateManager.HEIGHT / 2 - 10, 100, 50);
		startButton.setBackground(Color.white);
		startButton.setFont(assetManager.buttonFont);
		startButton.setVisible(true);
		
		quitButton = new JButton("Quit");
		quitButton.setActionCommand("QUIT");
		quitButton.setBounds(gameStateManager.WIDTH / 2 + 50, gameStateManager.HEIGHT / 2 - 10, 100, 50);
		quitButton.setBackground(Color.white);
		quitButton.setFont(assetManager.buttonFont);
		quitButton.setVisible(true);
		
		objectiveButton = new JButton("Help");
		objectiveButton.setActionCommand("OBJECTIVE");
		objectiveButton.setBounds(gameStateManager.WIDTH / 2 - 50, gameStateManager.HEIGHT / 2 + 75, 100, 50);
		objectiveButton.setBackground(Color.white);
		objectiveButton.setFont(assetManager.buttonFont);
		objectiveButton.setVisible(true);
		
		add(startButton);
		add(quitButton);
		add(objectiveButton);
		add(title);
		add(developerName);
		add(year);
		
		startButton.addActionListener(this);
		quitButton.addActionListener(this);
		objectiveButton.addActionListener(this);
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
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		case "START":
			gameStateManager.changeScreens(new GameplayScreen());
			break;
		case "QUIT":
			gameStateManager.running = false;
			break;
		case "OBJECTIVE":
			gameStateManager.addScreen(new HelpScreen());
			break;
		default:
			System.err.println("Unable to parse action command: " + e.getActionCommand());
			break;
		}
	}

}