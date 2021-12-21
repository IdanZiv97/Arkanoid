package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Block;
import game.GameEnvironment;
import game.Sprite;
import game.Velocity;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * A level information for the HardLevel level.
 */
public class HardLevel implements LevelInformation {
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
    public HardLevel() {
        //Setting the number of blocks
        this.numberOfBalls = 2;
        //Setting level's velocity
        this.initialBallVelocities = new ArrayList<Velocity>();
        double angle1 = 255;
        double angle2 = 285;
        double ballSpeed = 10;
        for (int i = 0; i < this.numberOfBalls; i++) {
            if (i % 2 == 0) {
                Velocity v = Velocity.fromAngleAndSpeed(angle1, ballSpeed);
                this.initialBallVelocities.add(v);
            } else {
                Velocity v = Velocity.fromAngleAndSpeed(angle2, ballSpeed);
                this.initialBallVelocities.add(v);
            }
        }
        //Settting the paddle
        this.paddleSpeed = 10;
        this.paddleWidth = 120;
        //setting up blocks
        final int gap = 18;
        int xStartGeneral = GameEnvironment.FRAME_WIDTH_STD;
        double xStartOfBlock = xStartGeneral;
        double yOfBlocks = 100;
        this.blocks = new ArrayList<Block>();
        Color c = Color.PINK.darker().darker().darker().darker();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 14; i++) {
                Point p = new Point(xStartOfBlock + gap, yOfBlocks);
                Block b = new Block(p, GameEnvironment.BLOCK_WIDTH, GameEnvironment.BLOCK_HEIGHT);
                b.getCollisionRectangle().setColor(c);
                this.blocks.add(b);
                xStartOfBlock += GameEnvironment.BLOCK_WIDTH;
            }
            c = c.brighter();
            yOfBlocks += GameEnvironment.BLOCK_HEIGHT;
            xStartOfBlock = xStartGeneral;
        }
        //Setting the background
        this.background = new HardLevelBackground();
        this.levelName = "Hard Level";
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
