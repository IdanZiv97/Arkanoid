package game;
import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import levels.GameLevel;
/**
 * This class is for a Paddle of the game.
 * the class supports basic movement operation of the Paddle and its interaction with other objects
 * in a set environment.
 */
public class Paddle implements Sprite, Collidable {
    //Goloblas
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public static final int GAP = 0;
    public static final int STEP = 7;
    public static final int REGIONS = 5;
    public static final java.awt.Color PADDLE_COLOR = Color.YELLOW;
    //Fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private int step;
    private ArrayList<Line> regions;
    //Constructor
    /**
     * Constructor.
     * @param topLeft top left corner
     * @param width width
     * @param height height
     * @param ks keyboard sensor
     * @param step speed
     */
    public Paddle(Point topLeft, double width, double height, KeyboardSensor ks, int step) {
        this.collisionRectangle = new Rectangle(topLeft, width, height);
        this.keyboard = ks;
        this.step = step;
        this.collisionRectangle.setColor(PADDLE_COLOR);
        this.regions = splitToRegions();
        /*
        LevelInformation data = game.getMetaData();
        this.keyboard = game.getGUI().getKeyboardSensor();
        Point p = data.paddlePoint();
        int width = data.paddleWidth();
        Rectangle rect = new Rectangle(p, width, PADDLE_HEIGHT);
        this.collisionRectangle = rect;
        rect.setColor(PADDLE_COLOR);
        this.step = data.paddleSpeed();
        this.regions = splitToRegions();
        */
        /*
        GUI guiPaddle = game.getGUI();
        this.gui = guiPaddle;
        this.keyboard = gui.getKeyboardSensor();
        double x = gui.getDrawSurface().getWidth() / 2;
        double y = gui.getDrawSurface().getHeight() - GameEnvironment.FRAME_HEIGHT_STD - PADDLE_HEIGHT;
        Point paddleUpperLeft = new Point(x, y);
        Rectangle rect = new Rectangle(paddleUpperLeft, PADDLE_WIDTH, PADDLE_HEIGHT);
        rect.setColor(PADDLE_COLOR);
        this.collisionRectangle = rect;
        this.regions = splitToRegions();
        */
    }
    //Getters
    /**
     * @return the KeyboardSensor of the Paddle
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }
    /**
     * @return the array of region on the top line.
     */
    public ArrayList<Line> getRegions() {
        return this.regions;
    }
    //Public Methods
    /**
     * Setting the regions of the paddle's top line.
     * @param list the list of the regions
     */
    public void setRegions(ArrayList<Line> list) {
        this.regions = list;
    }
    /**
     * Method to change the paddle's collision shape.
     * @param rect the new shape created
     */
    public void setCollisionShape(Rectangle rect) {
        this.collisionRectangle = rect;
    }
    /**
     * Adds the Paddle object ot the Game's Collidables and Sprites Lists.
     * @param game the Game object
     */
    public void addToGame(GameLevel game) {
        game.getEnivornment().addCollidable(this);
        game.getSprites().addSprite(this);
    }
    /**
     * This method removes the paddle from the game.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.getEnivornment().removeCollidable(this);
        game.getSprites().removeSprite(this);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle object = this.getCollisionRectangle();
        int lineIndex = Block.TOP_LINE;
        for (Line l : object.getLines()) {
            if (l.partOfSegment(collisionPoint)) {
                lineIndex = object.getLines().indexOf(l);
                break;
            }
        }
        if (lineIndex == Block.TOP_LINE) {
             Velocity v = calculatePaddleHitVelocity(collisionPoint, currentVelocity, object.getLines().get(lineIndex));
             return v;
        } else {
            double angle = Math.toDegrees(Math.atan2(currentVelocity.getDy(), -currentVelocity.getDx()));
            double speed = currentVelocity.getSpeed();
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            return v;
        }
    }
    /**
     * The method calculates the new velocity of the collision Object, depends on the region of the Paddle.
     * @param collisionPoint the point the collision has occured
     * @param currentVelocity the velocity of the collision
     * @param l the line which will be divided into regions
     * @return a new calculated velocity
     */
    public Velocity calculatePaddleHitVelocity(Point collisionPoint, Velocity currentVelocity, Line l) {
        final int farLleft = 210;
        final int left = 240;
        final int up = 270;
        final int right = 300;
        final int farRight = 330;
        int regionIndex = 0;
        double speed = currentVelocity.getSpeed();
        double angle = 0;
        for (Line region : this.getRegions()) {
            if (region.partOfSegment(collisionPoint)) {
                regionIndex = this.getRegions().indexOf(region);
                break;
            }
        }
        switch (regionIndex) {
            case 0:
                angle = farLleft;
                break;
            case 1:
                angle = left;
                break;
            case 2:
                angle = up;
            case 3:
                angle = right;
                break;
            case 4:
                angle = farRight;
                break;
            default:
                break;
        }
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        return v;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rect = this.getCollisionRectangle();
        Point upperLeft = rect.getUpperLeft();
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(PADDLE_COLOR);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }
    /**
     * This method moves the paddle to the left.
     * The paddle takes into consideration the borders of the game in the movement of the Paddle
     */
    public void moveLeft() {
        double leftBound = GameEnvironment.FRAME_WIDTH_STD;
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double w = this.getCollisionRectangle().getWidth();
        double h = this.getCollisionRectangle().getHeight();
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        if (x - this.step <= leftBound - 1) {
            return;
        } else {
            Point p = new Point(x - this.step, y);
            Rectangle newShape = new Rectangle(p, w, h);
            this.setCollisionShape(newShape);
            this.setRegions(this.splitToRegions());

        }
    }
    /**
     * This method moves the paddle to the right.
     * The paddle takes into consideration the borders of the game in the movement of the Paddle
     */
    public void moveRight() {
        double rightBound = GameLevel.WIDTH - GameEnvironment.FRAME_WIDTH_STD;
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double w = this.getCollisionRectangle().getWidth();
        double h = this.getCollisionRectangle().getHeight();
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        if (x + w + this.step >= rightBound + 1) {
            return;
        } else {
            Point p = new Point(x + this.step, y);
            Rectangle newShape = new Rectangle(p, w, h);
            this.setCollisionShape(newShape);
            this.setRegions(this.splitToRegions());
        }
    }
    /**
     * This method splits a the top line of the paddle to equal parts.
     * @return an array of the parts of the line
     */
    public ArrayList<Line> splitToRegions() {
        //Get the upper line
        Line topLine = this.getCollisionRectangle().getLines().get(Block.TOP_LINE);
        //Set the starting point of the line
        Point p = topLine.start();
        //Set the length of each r
        double r = Paddle.PADDLE_WIDTH / Paddle.REGIONS;
        ArrayList<Line> temp = new ArrayList<Line>();
        //Add r 1
        Line r1 = new Line(new Point(p.getX(), p.getY()), new Point(p.getX() + r, p.getY()));
        temp.add(r1);
        //Add r 2
        Line r2 = new Line(new Point(p.getX() + r, p.getY()), new Point((p.getX() + (2 * r)), p.getY()));
        temp.add(r2);
        //Add r 3
        Line r3 = new Line(new Point(p.getX() + (2 * r), p.getY()), new Point((p.getX() + (3 * r)), p.getY()));
        temp.add(r3);
        //Add r 4
        Line r4 = new Line(new Point(p.getX() + (3 * r), p.getY()), new Point((p.getX() + (4 * r)), p.getY()));
        temp.add(r4);
        //Add r 5
        Line r5 = new Line(new Point(p.getX() + (4 * r), p.getY()), new Point((p.getX() + (5 * r)), p.getY()));
        temp.add(r5);
        return temp;
    }
    @Override
    public void timePassed() {
        KeyboardSensor sensor = this.getKeyboard();
        if (sensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (sensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
}
