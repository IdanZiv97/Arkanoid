package game;
import levels.GameLevel;

/**
 * @author Idan Ziv
 * ID: 318175197
 * An implemntation to the Observer design pattern.
 * This object is subscribed to many Ball objects and gets notified when a coliision occurs
 */
public class BallRemover implements HitListener {
    //Fields
    private GameLevel game;
    private Counter remainingBalls;
    //Constructor
    /**
     * Constructor.
     * @param g the level which ball's are being tracked
     * @param c the ball counter
     */
    public BallRemover(GameLevel g, Counter c) {
        this.game = g;
        this.remainingBalls = c;
    }
    //HitListener Methods
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //Remove ball from the game
        hitter.removeFromGame(this.game);
        //Decrease the number of the block
        this.remainingBalls.decrease();
    }


}
