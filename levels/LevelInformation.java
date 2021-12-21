package levels;
import java.util.List;

import game.Block;
import game.Sprite;
import game.Velocity;
/**
 * @author Idan Ziv
 * ID: 318175197
 * The interface specifies the information and actions required in a level
 */
public interface LevelInformation {
    /**
     * @return the number of balls
     */
    int numberOfBalls();
    /**
     * @return list of the level's balls velocities
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle's speed
     */
    int paddleSpeed();
    /**
     * @return the paddle's width
     */
    int paddleWidth();
    /**
     * @return the level's name
     */
    String levelName();
    /**
     * @return the level's background
     */
    Sprite getBackground();
    /**
     * @return the list of the level's blocks
     */
    List<Block> blocks();
    /**
     * @return the number of blocks that the user needs to remove
     */
    int numberOfBlocksToRemove();
}
