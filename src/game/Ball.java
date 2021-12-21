package game;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import levels.GameLevel;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates a Ball object.
 * The  class supports basic operations regarding the properties of a ball and
 * its interaction with set environment.
 */
public class Ball implements Sprite {
    //Globals
    public static final double EPSILON = 0.0000000000001;
    //Fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    //Constructors
    /**
     * This constructor creates a Ball object given a point, radius and a color.
     * @param center the center of a circle
     * @param r radius of the ball
     * @param color used when an object is created in an enviorment
     * @param gameEnvironment the game the ball exists in
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Creates a ball given two cordinates, radius and color.
     * @param x the x cordiante of the ball's center point
     * @param y the y cordinate of the ball's center point
     * @param r the radius of the ball
     * @param color used when an object is created in an enviorment
     * @param gameEnvironment the game the ball exists in
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        Point temp = new Point(x, y);
        this.center = temp;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Creates a Ball object.
     * @param p center point
     * @param r ball's radius
     * @param color ball's color
     */
    public Ball(Point p, int r, java.awt.Color color) {
        this.center = p;
        this.radius = r;
        this.color = color;
    }
    //Getters
    /**
     * @return returns the x cordinate of the ball's center point.
     */
    public double getX() {
        return this.center.getX();
    }
    /**
     * @return the y cordinate of the ball's center point.
     */
    public double getY() {
        return this.center.getY();
    }
    /**
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * @return the object's game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * @return the ball's current velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    //Public Methods
    /**
     * This method add the Ball object to a game's Sprites collections.
     * @param game the game we want the ball to be related to
     */
    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }
    /**
     * This method removes the Ball object from a game's Sprites collection.
     * @param game the game we want to remove the ball from
     */
    public void removeFromGame(GameLevel game) {
        game.getSprites().removeSprite(this);
    }
    /**
     * This method calculates the Ball's trajectory and checks if the object will collide
     * with any Collidable object in its Game Environment.
     */
    public void moveOneStep() {
        //Creates the trajectory
        Line trajectory = new Line(this, this.getVelocity());
        //Checking if any collision has occured
        CollisionInfo info = this.getGameEnvironment().getClosestCollision(trajectory);
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //check if the point is the within the impact range, if not, move the ball closer to that.
        if (!isCollisionCloseEnough(trajectory, info.getCollisionPoint())) {
            //create a new velocity that will advance the ball a little bit further.
            //The angle is the same
            double angle = this.getVelocity().getAngle();
            double speed = this.getSize();
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            this.center = v.applyToPoint(this.center);
        }
        //Now the ball is close enough to the block to create an impact
        Velocity impactVelocity = info.getCollisionObject().hit(this, info.getCollisionPoint(), this.getVelocity());
        this.setVelocity(impactVelocity);
        //this.center = impactVelocity.applyToPoint(this.center);
    }
    /**
     * This method checks if the collision point is close enough to the Ball's center.
     * @param trajectory a line from the center of the ball in the direction of the movement
     * @param collisionPoint the point in which the collision occurs
     * @return a boolean answer, depends on the result of the test
     */
    public boolean isCollisionCloseEnough(Line trajectory, Point collisionPoint) {
        double distanceOfCollision = trajectory.start().distance(collisionPoint);
        //double delta = Math.abs(this.getSize() - distanceOfCollision);
        if (distanceOfCollision == GameEnvironment.RADIUS) {
            return true;
        }
        return false;
    }
    /**
     * Given a drawsurface the method creates a circle, according to the object's dimensions and
     * color.
     * @param surface the surface on which the ball will be drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.radius);
    }
    /**
     * This method sets the ball's Velocity by given velocity.
     * @param v the desired velocity of the object.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     *This method sets the ball's velocity given the desired X-Axis and Y-Axis changes.
     * @param dx desired X-Axis change
     * @param dy desired Y-Axis change
     */
    public void setVelocity(double dx, double dy) {
        Velocity temp = new Velocity(dx, dy);
        this.velocity = temp;
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
}
