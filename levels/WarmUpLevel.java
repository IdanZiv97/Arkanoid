package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.Block;
import game.GameEnvironment;
import game.Sprite;
import game.Velocity;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * A level information for the WarmUpLevel
 */
public class WarmUpLevel implements LevelInformation {
    //Fields
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    /**
     * Constructor.
     */
    public WarmUpLevel() {
        Random rand = new Random();
        this.numberOfBalls = 3;
        this.initialBallVelocities = new ArrayList<>();
        double ballSpeed = 10;
        double angle =  240;
        double delta = 30;
        for (int i = 0; i < numberOfBalls; i++) {
            angle += i * delta;
            Velocity v = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            this.initialBallVelocities.add(v);
        }
        this.paddleSpeed = 7;
        this.paddleWidth = 100;
        //setting up blocks
        this.blocks = new ArrayList<Block>();
        final int gap = 20;
        final int xStartGeneral = (int) (GameLevel.WIDTH - GameEnvironment.BLOCK_WIDTH
         - GameEnvironment.FRAME_WIDTH_STD) - gap - 1;
        final int yStartGeneral = (int) (5 * GameEnvironment.BLOCK_HEIGHT);
        double xStartOfBlocks = xStartGeneral;
        double yStartOfBlocks = yStartGeneral;
        int counter =  GameEnvironment.COLUMNS;
        for (int i = 0; i < GameEnvironment.ROWS; i++) {
            Color c = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            for (int j = 0; j < counter; j++) {
                Point p = new Point(xStartOfBlocks + gap, yStartOfBlocks);
                Block b = new Block(p, GameEnvironment.BLOCK_WIDTH, GameEnvironment.BLOCK_HEIGHT);
                b.getCollisionRectangle().setColor(c);
                this.blocks.add(b);
                //Moving the x Cordiante to the next block on the row;
                xStartOfBlocks -= GameEnvironment.BLOCK_WIDTH;
            }
            counter--;
            //Increasing the Y axis cordiante to start a new row
            yStartOfBlocks += GameEnvironment.BLOCK_HEIGHT;
            //Resteting the X Axis cordinate
            xStartOfBlocks = xStartGeneral;
        }
        this.background = new WarmUpLevelBackground();
        this.levelName = "Warm Up";
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
