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
 * A level information for the ChildrensPlay level.
 */
public class ChlidrensPlay implements LevelInformation {
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
    public ChlidrensPlay() {
        Random rand = new Random();
        this.numberOfBalls = 6;
        this.initialBallVelocities = new ArrayList<>();
        double ballSpeed = 8;
        double angle = 220;
        double delta = 20;
        for (int i = 0; i < this.numberOfBalls; i++) {
            angle += i * delta;
            Velocity v = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            this.initialBallVelocities.add(v);
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 240;
        //setting up blocks
        final int gap = 10;
        int xStartGeneral = GameEnvironment.FRAME_WIDTH_STD;
        double xStartOfBlock = xStartGeneral + gap;
        double yOfBlocks = 250;
        this.blocks = new ArrayList<Block>();
        //int startPointX = GameEnvironment.FRAME_WIDTH_STD;
        //int startPointY = 250;
        for (int i = 0; i < 14; i++) {
            Point p = new Point(xStartOfBlock + gap, yOfBlocks);
            Block b = new Block(p, GameEnvironment.BLOCK_WIDTH, GameEnvironment.BLOCK_HEIGHT);
            Color c = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            b.getCollisionRectangle().setColor(c);
            this.blocks.add(b);
            xStartOfBlock += GameEnvironment.BLOCK_WIDTH;
        }
        this.background = new ChlidrensPlayBackground();
        this.levelName = "Children's Play";
        this.numberOfBlocksToRemove = this.blocks.size() - 2;
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
