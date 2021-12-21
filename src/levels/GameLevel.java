package levels;
import java.awt.Color;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimatiom;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.Ball;
import game.BallRemover;
import game.Block;
import game.BlockRemover;
import game.Collidable;
import game.Counter;
import game.GameEnvironment;
import game.Paddle;
import game.Sprite;
import game.SpriteCollection;
import game.Velocity;
import geometry.Rectangle;
import geometry.Point;

/**
 * @author Idan Ziv
 * ID: 318175197
 */
public class GameLevel implements Animation {
    //Globals
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDLE_INDEX = 0;
    public static final Point BALL_CENTER_POINT = new Point(400, 500);
    public static final java.awt.Color BALL_COLOR = Color.WHITE;
    public static final int BALL_RADIUS = 5;
    public static final int SPEED = 7;
    public static final int TEXT_SIZE = 15;
    public static final int GAP = 20;
    //Fields
    //The Gui - to make it all happen
    private final GUI gameGUI;
    //here we will have all the graphic elements and how to activate them
    private SpriteCollection sprites;
    //here we will have all the elements and the logic of its operation
    private GameEnvironment environment;
    private Counter blockCounter;
    private BlockRemover gameBlockRemover;
    private Counter ballCounter;
    private BallRemover gameBallRemover;
    private boolean stopGame;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreTracker;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private LevelInformation metaData;
    private Counter livesCounter;
    private Counter blocksLeftToRemove;
    private Paddle paddle;
    //Constructors
    /**
     * Creates a GameLevel.
     * @param info the level's information
     * @param lives Counter object tracking the game's lives
     * @param score Counter object tracking the game's score
     * @param sensor keyboard sensor
     * @param runner animation runner
     */
    public GameLevel(LevelInformation info, Counter lives, Counter score,
        KeyboardSensor sensor, AnimationRunner runner)  {
        //Sets up a GUI
        this.gameGUI = runner.getGUI();
        //Creating a GameEnvironment to manage all the Collidables
        this.environment = new GameEnvironment();
        //Creating a GameEnvironment to manage all the Sprites
        this.sprites = new SpriteCollection();
        //Creating the Block Removal Mechanisim
        this.blockCounter = new Counter();
        this.blocksLeftToRemove = new Counter();
        this.blocksLeftToRemove.increase(info.numberOfBlocksToRemove());
        this.gameBlockRemover = new BlockRemover(this, this.blockCounter, this.blocksLeftToRemove);
        this.ballCounter = new Counter();
        this.gameBallRemover = new BallRemover(this, this.ballCounter);
        this.scoreCounter = score;
        this.scoreTracker = new ScoreTrackingListener(this.scoreCounter);
        this.stopGame = false;
        this.runner = runner;
        this.keyboardSensor = sensor;
        this.metaData = info;
        this.livesCounter = lives;
    }
    //Getters
    /**
     * @return the object's GUI
     */
    public GUI getGUI() {
        return this.gameGUI;
    }
    /**
     * @return the object's Sprites Collection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
    /**
     * @return the GameEnvironment of the Game
     */
    public GameEnvironment getEnivornment() {
        return this.environment;
    }
    //Private Methods
    /**
     * Creates the frame of the game, the frame are Block objects whhich arfe used as borders.
     */
    public void setFrames() {
         //get the width and the height
         double width = this.getGUI().getDrawSurface().getWidth();
         double height = this.getGUI().getDrawSurface().getHeight();
         //create the array
         //create the two blocks to the side
         //the left block
         Block leftFrame = new Block(new Point(0, 0), GameEnvironment.FRAME_WIDTH_STD, height);
         leftFrame.getCollisionRectangle().setColor(GameEnvironment.FRAME_COLOR);
         leftFrame.addToGame(this);
         //the right block
         Point rightP = new Point(width - GameEnvironment.FRAME_WIDTH_STD, 0);
         Block rightFrame = new Block(rightP, GameEnvironment.FRAME_WIDTH_STD, height);
         rightFrame.getCollisionRectangle().setColor(GameEnvironment.FRAME_COLOR);
         rightFrame.addToGame(this);
         //create the two blocks between the sides
         //the top block
         Point topP = new Point(GameEnvironment.FRAME_WIDTH_STD, 0);
         double blockWidth = width - GameEnvironment.FRAME_WIDTH_STD;
         Block topBlock = new Block(topP, blockWidth, GameEnvironment.FRAME_HEIGHT_STD + 10);
         topBlock.getCollisionRectangle().setColor(GameEnvironment.FRAME_COLOR);
         topBlock.addToGame(this);
        //the bottom block - the killing block
        Point bottomP = new Point(GameEnvironment.FRAME_WIDTH_STD, height);
        Block bottomBlock = new Block(bottomP, blockWidth, GameEnvironment.FRAME_HEIGHT_STD);
        bottomBlock.getCollisionRectangle().setColor(GameEnvironment.FRAME_COLOR);
        bottomBlock.addHitListener(this.gameBallRemover);
        bottomBlock.addToGame(this);
        //Set up The score Indicator - same dimensiins of the top block
        Rectangle scoreBox = new Rectangle(new Point(0, 0), WIDTH, GameEnvironment.BLOCK_HEIGHT);
        scoreBox.setColor(Color.WHITE);
        ScoreBox scoreIndicator = new ScoreBox(scoreBox, this.scoreCounter);
        scoreIndicator.addToGame(this);
        //add life indicator
        LivesIndicator lives = new LivesIndicator(this.livesCounter);
        lives.addToGame(this);
        //add level indicator
        LevelNameIndicator levelName = new LevelNameIndicator(this.metaData.levelName());
        levelName.addToGame(this);
    }
    /**
     * This method sets lines of blocks in a certain pattern.
     */
    private void setBlocks() {

        for (Block b : metaData.blocks()) {
            b.addToGame(this);
            b.addHitListener(this.gameBlockRemover);
            b.addHitListener(this.scoreTracker);
            this.blockCounter.increase();
        }
    }
    //Public Methods
    /**
     * This method set the environment of the Objects.
     */
    public void setEnivornment() {
        this.environment = new GameEnvironment();
    }
    /**
     * This method is in charge of creating all the Collidables and Sprites who are needed in order for
     * the Game to work correctly.
     */
    public void intialize() {
        //Setting Paddle
        addSprite(this.metaData.getBackground());
        /*
        Paddle gamePaddle = new Paddle(this);
        gamePaddle.addToGame(this);
        //Setting balls
        //setBalls(metaData.numberOfBalls(), metaData.initialBallVelocities());
        */
        //Creating Frames
        setFrames();
        setBlocks();
    }
    /**
     * This method is in charge of the animation of the Game.
     */
    /*
    public void run() {
        runner.run(new CountdownAnimatiom(2, 3, this.sprites));
        while (!this.stopGame) {
           this.runner.run(this);
        }
        endGame();
    }
    */
    /**
     * Adds the collidable to the Game's Collidable list.
     * @param c the Collidable object
     */
    public void addCollidable(Collidable c) {
        getEnivornment().getCollidables().add(c);
    }
    /**
     * Removes the collidable from the Game's Collidable list.
     * @param c to Collidable object
     */
    public void removeCollidavle(Collidable c) {
        getEnivornment().getCollidables().remove(c);
    }
    /**
     * Adds the Sprite to the Game's SpriteCollection.
     * @param s the Sprite object
     */
    public void addSprite(Sprite s) {
        getSprites().addSprite(s);
    }
    /**
     * Removes the Sprite from the Game's SpriteCollection.
     * @param s the Sprite object
     */
    public void removeSprite(Sprite s) {
        getSprites().removeSprite(s);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
            new PauseScreen()));
        }
        this.getSprites().drawAllOn(d);
        this.getSprites().notifyAllTimePassed();
        //checking if removed all the blocks in the leve
        if (this.blockCounter.getValue() == 0 || this.blocksLeftToRemove.getValue() <= 0) {
            this.paddle.removeFromGame(this);
            this.scoreCounter.increase(100);
            this.stopGame = true;
        }
        //Check if lost all balls
        if (this.ballCounter.getValue() == 0) {
            this.paddle.removeFromGame(this);
            this.livesCounter.decrease();
            this.stopGame = true;
        }
    }
    /**
     * Sets up the initial graphics of a game level, before it's start.
     */
    public void startLevel() {
        this.paddle = intilizeBallsOnTopOfPaddle();
        paddle.addToGame(this);
        this.runner.run(new CountdownAnimatiom(2, 3, this.sprites));
        this.stopGame = false;
        this.runner.run(this);
    }
    /**
     * Creates the game paddle and the balls.
     * @return game paddle
     */
    public Paddle intilizeBallsOnTopOfPaddle() {
        //Creating the Paddle
        int paddleWidth = this.metaData.paddleWidth();
        double paddleXValue = GameLevel.WIDTH / 2 - paddleWidth / 2;
        double paddleYValue = GameLevel.HEIGHT - GameEnvironment.FRAME_HEIGHT_STD;
        Point topLeft = new Point(paddleXValue, paddleYValue);
        int step = this.metaData.paddleSpeed();
        Paddle tempPaddle = new Paddle(topLeft, paddleWidth, Paddle.PADDLE_HEIGHT, this.keyboardSensor, step);
        //Create balls - above the middle of the paddle
        for (Velocity speed : this.metaData.initialBallVelocities()) {
            int xValue = (int) paddleXValue + paddleWidth / 2;
            int yVaule = (int) paddleYValue - Paddle.PADDLE_HEIGHT;
            Point center = new Point(xValue, yVaule);
            Ball b = new Ball(center, BALL_RADIUS, BALL_COLOR, this.getEnivornment());
            b.setVelocity(speed);
            b.addToGame(this);
            this.ballCounter.increase();
        }
        return tempPaddle;
    }
    @Override
    public boolean shouldStop() {
        return this.stopGame;
    }
    /**
     * @return the number of blocks left to remove
     */
    public int blocksLeftToRemove() {
        return this.blocksLeftToRemove.getValue();
    }
    /**
     * @return the number of balls
     */
    public int numOfBalls() {
        return this.ballCounter.getValue();
    }
    /**
     * @return the number of lives
     */
    public int numOfLives() {
        return this.livesCounter.getValue();
    }
    /**
     * @return the level information of the GameLevel
     */
    public LevelInformation getMetaData() {
        return this.metaData;
    }
}
