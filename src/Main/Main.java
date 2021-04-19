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
    
    private static long time;
    
    private static final int TARGET_FRAME_RATE = 60;
    
    public static void initialize() {
    	time = System.nanoTime();
    	
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
            
            // Each frame should run for 1/TARGET_FRAME_RATE seconds, so I need to sleep each frame for that amount minus the time each frame takes 
            // (though technically the deltaTime accounts for the previous frame but it still works)
            long msToSleep = 1000 / (TARGET_FRAME_RATE - (long)deltaTimeInSeconds);
            
            try {
            	Thread.sleep(msToSleep);
            } catch (Exception e) {
            	System.out.println("Couldn't sleep the update thread!");
            }
        }
        System.exit(0);
    }
}