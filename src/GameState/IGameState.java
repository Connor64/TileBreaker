package GameState;

import java.awt.*;
/**
 * @author jamescolbert
 */
public interface IGameState {
    void initialize();
    
    void loadContent();
    
    void update(float deltaTime);
    
    void draw(Graphics g);
}
