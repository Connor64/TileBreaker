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
	
	private JButton startButton, quitButton;
	private JLabel title, developerName, year;
	
	public TitleScreen() {
		setLayout(null);
		
		title = new JLabel("Tile Breaker", SwingConstants.CENTER);
		title.setBounds(manager.WIDTH / 2 - 350, 125, 700, 100);
		title.setFont(manager.titleFont);
		
		developerName = new JLabel("Connor Colbert", SwingConstants.LEFT);
		developerName.setBounds(10, manager.HEIGHT - 70, 500, 100);
		developerName.setFont(manager.detailFont);
		
		year = new JLabel("2021", SwingConstants.RIGHT);
		year.setBounds(manager.WIDTH - 110, manager.HEIGHT - 70, 100, 100);
		year.setFont(manager.detailFont);
		
		startButton = new JButton("Start");
		startButton.setActionCommand("START");
		startButton.setBounds(manager.WIDTH / 2 - 150, manager.HEIGHT / 2 - 10, 100, 50);
		startButton.setBackground(Color.white);
		startButton.setFont(manager.buttonFont);
		startButton.setVisible(true);
		
		quitButton = new JButton("Quit");
		quitButton.setActionCommand("QUIT");
		quitButton.setBounds(manager.WIDTH / 2 + 50, manager.HEIGHT / 2 - 10, 100, 50);
		quitButton.setBackground(Color.white);
		quitButton.setFont(manager.buttonFont);
		quitButton.setVisible(true);
		
		add(startButton);
		add(quitButton);
		add(title);
		add(developerName);
		add(year);
		
		startButton.addActionListener(this);
		quitButton.addActionListener(this);
	}

	@Override
	public void loadContent() {
		
	}

	@Override
	public void unloadContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		case "START":
			manager.changeScreens(new GameplayScreen());
			break;
		case "QUIT":
			manager.running = false;
			break;
		default:
			System.err.println("Unable to parse action command: " + e.getActionCommand());
			break;
		}
	}

}
