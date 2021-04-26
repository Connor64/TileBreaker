package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import GameState.GameState;
import GameState.GameStateManager;
import Objects.TileManager;

public class GameOverScreen extends GameState implements ActionListener {
	private JLabel losingText, scoreText, highScoreText;
	private JButton retryButton, quitButton;
	
	String[] lowMessages = {
			"You suck.",
			"You lost! Loser!",
			"Please give up.",
			"I've seen, like, WAY better.",
			};
	
	String[] midMessages = {
			"Not the worst.",
			"That wasn't awful.",
			"You did okay... ish.",
			"There's a little potential.",
			};
	
	String[] highMessages = {
			"Wow, you're not too bad.",
			"I'm impressed.",
			"You have my attention...",
			};
	
	String[] superMessages = {
			"That was amazing...",
			"How did you do that??",
			"You're amazing at this.",
			"Are you cheating??",
			"Keep it up!"
			};
	
	public GameOverScreen(int scoreVal, int level) {
		setLayout(null);
		gameStateManager.highScore = scoreVal > gameStateManager.highScore ? scoreVal : gameStateManager.highScore;
		
		String[] currentMessages;
		
		if (level >= 0 && level <= 2) {
			currentMessages = lowMessages;
		} else if (level >= 3 && level <= 5) {
			currentMessages = midMessages;
		} else if (level >= 6 && level <= 8) {
			currentMessages = highMessages;
		} else {
			currentMessages = superMessages;
		}
		
		retryButton = new JButton("Try Again");
		retryButton.setActionCommand("RETRY");
		retryButton.setBounds(gameStateManager.WIDTH / 2 - 100, gameStateManager.HEIGHT / 2 + 50, 200, 50);
		retryButton.setBackground(Color.white);
		retryButton.setFont(assetManager.buttonFont);
		retryButton.setVisible(true);

		quitButton = new JButton("Quit to Menu");
		quitButton.setActionCommand("QUIT");
		quitButton.setBounds(gameStateManager.WIDTH / 2 - 100, gameStateManager.HEIGHT / 2 + 110, 200, 50);
		quitButton.setBackground(Color.white);
		quitButton.setFont(assetManager.buttonFont);
		quitButton.setVisible(true);

		try {
			losingText = new JLabel(currentMessages[new Random().nextInt(currentMessages.length)], SwingConstants.CENTER);
		} catch (Exception e) {
			losingText = new JLabel("The message didn't load correctly and it's all your fault.");
		}
		losingText.setBounds(gameStateManager.WIDTH / 2 - 400, 100, 800, 100);
		losingText.setFont(assetManager.messageFont);
		
		scoreText = new JLabel("Total Score: " + scoreVal, SwingConstants.CENTER);
		scoreText.setBounds(gameStateManager.WIDTH / 2 - 400, 200, 800, 100);
		scoreText.setFont(assetManager.scoreFont);
		
		highScoreText = new JLabel("High Score: " + gameStateManager.highScore, SwingConstants.CENTER);
		highScoreText.setBounds(gameStateManager.WIDTH / 2 - 400, 250, 800, 100);
		highScoreText.setFont(assetManager.scoreFont);
		
		add(retryButton);
		add(quitButton);
		add(losingText);
		add(scoreText);
		add(highScoreText);
		
		retryButton.addActionListener(this);
		quitButton.addActionListener(this);
	}
	
	@Override
	public void loadContent() {}

	@Override
	public void unloadContent() {}

	@Override
	public void update(float deltaTime) {}

	@Override
	public void draw(Graphics g) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "RETRY":
			System.out.println("Play Again");
			TileManager.Instance().reset();
			gameStateManager.changeScreens(new GameplayScreen());
			break;
		case "QUIT":
			System.out.println("Quit");
			gameStateManager.changeScreens(new TitleScreen());
//			manager.running = false;
			break;
		default:
			System.err.println("Unable to parse action command: " + e.getActionCommand());
			break;
		}
	}
}