package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Block;
import game.Sprite;
import game.Velocity;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * A level information for the DirectHit level.
 */
public class DirectHitLevel implements LevelInformation {
    //Fields
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    //Constructor
    /**
     * Constructor.
     */
    public DirectHitLevel() {
        this.numberOfBalls = 1;
        //setting velocity
        this.initialBallVelocities = new ArrayList<Velocity>();
        double angle = 270;
        Velocity v = Velocity.fromAngleAndSpeed(angle, GameLevel.SPEED);
        this.initialBallVelocities.add(v);
        //setting paddle data
        this.paddleSpeed = 10;
        this.paddleWidth = 120;
        //setting the blocks
        this.blocks = new ArrayList<Block>();
        Block b = new Block(new Point(375, 250), 50, 50);
        b.getCollisionRectangle().setColor(Color.RED);
        this.blocks.add(b);
        //set the background
        this.background = new DirectHitBackground();
        //set level name
        this.levelName = "DirectHit";
        this.numberOfBlocksToRemove = this.blocks.size();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
