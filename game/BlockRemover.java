package game;
import levels.GameLevel;

/**
 * @author Idan Ziv
 * ID: 318175197
 * An implemntation to the Observer design pattern.
 * This object is subscribed to many Block objects and gets notified when a coliision occurs
 */
public class BlockRemover implements HitListener {
    //Fields
    private GameLevel game;
    private Counter remainingBlocks;
    private Counter blocksLeftToRemove;
    //Constructor
    /**
     * Constructor.
     * @param game game level
     * @param removedBlocks the number of blocks we need to remove
     * @param blocksLeftToRemove how many blocks are needed to remove
     */
    public BlockRemover(GameLevel game, Counter removedBlocks, Counter blocksLeftToRemove) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.blocksLeftToRemove = blocksLeftToRemove;
    }
    //HitListener Methods
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the block from the game
        beingHit.removeFromGame(this.game);
        //remove the BlockRemover as a listener to the subject who was hit
        beingHit.removeHitListener(this);
        //decrease the number of blocks
        this.remainingBlocks.decrease();
        this.blocksLeftToRemove.decrease();
    }
}
