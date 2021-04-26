package Engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import GameState.GameStateManager;

public class AssetManager {
	public Font titleFont, messageFont, buttonFont, scoreFont, detailFont;
	public Color defaultColor = new Color(51, 51, 51);
	public BufferedImage controlsImage;
	private GameStateManager gameStateManager = GameStateManager.Instance();
	
	private static AssetManager instance;
	public static AssetManager Instance() {
		if (instance == null) {
			instance = new AssetManager();
		}
		return instance;
	}
	
	public AssetManager() {
		try {
			// Font used: "Minecraftia" by Andrew Tyler - https://textcraft.net/download-fonts.php
			
			InputStream stream = getClass().getResourceAsStream("/8Bit.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
			
			titleFont = font.deriveFont(Font.BOLD, 75);
			messageFont = font.deriveFont(Font.BOLD, 30);
			buttonFont = font.deriveFont(Font.BOLD, 18);
			scoreFont = font.deriveFont(Font.BOLD, 24);
			detailFont = font.deriveFont(Font.BOLD, 18);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			controlsImage = ImageIO.read(getClass().getResourceAsStream("/controls_highlighted_small.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			GameStateManager.frame.setIconImage(ImageIO.read(getClass().getResourceAsStream("/tile.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}