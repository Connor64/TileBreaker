package Main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import GameState.*;
import Screens.GameOverScreen;
import Screens.GameplayScreen;
import Screens.TitleScreen;

/**
 * @author jamescolbert
 */
public class Main {
    private static GameStateManager gameStateManager;
    private static Graphics g;
    
    private static long time = System.nanoTime();
    
    public static void initialize() {
        
        gameStateManager = GameStateManager.Instance();
        gameStateManager.addScreen(new TitleScreen());
        
    }
    
    public static void main(String[] args) {
        initialize();
        while (gameStateManager.running) {
            long currentTime = System.nanoTime();
            long deltaTime = currentTime - time;
            double deltaTimeInSeconds = deltaTime / 1_000_000_000.0;
            time = currentTime;
            gameStateManager.update((float)deltaTimeInSeconds);
            gameStateManager.draw();
            // run gamecode here
        }
        System.exit(0);
    }
}