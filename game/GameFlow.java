package game;
import java.util.List;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.GameLevel;
import levels.LevelInformation;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This class is responsible of running and managing the GameLevel objects
 */
public class GameFlow {
    //Fields
    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private Counter score;
    private Counter lives;
    /**
     * This class is responisble of managing the game - running levels, keeping count on the
     * score and lives and ending the game.
     * @param runner - the object who runs all the Levels' animations
     * @param k the input from the client
     * @param numOfLives number of lives
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor k, int numOfLives) {
        this.runner = runner;
        this.sensor = k;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(numOfLives);
    }
    /**
     * Given a list of GameLevel objects, this method is respoinsible on initializing each level
     * and keep track of the genreal game logic of the game.
     * The method is responsible of determning whether a game is over or not.
     * @param levels the list of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {
        //iterate throguh levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.lives, this.score, this.sensor, this.runner);
            level.intialize();
            //run countdown animation
            //Run as long you have lives left and blocks to destroy
            while (level.blocksLeftToRemove() > 0 && level.numOfLives() > 0) {
                level.startLevel();
            }
            //
            if (level.numOfLives() <= 0) {
                this.runner.run(new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
                 new EndScreen(this.score, false)));
                break;
            }
        }
        //at the end of all levels - win or lose
        if (this.lives.getValue() > 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
             new EndScreen(this.score, true)));
        }
        runner.getGUI().close();
    }
}
