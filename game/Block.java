package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import levels.GameLevel;

/**
 * @author Idan Ziv
 * This class creates a Block object.
 * * The  class supports basic operations regarding the properties of a Block and
 * its interaction with set environment.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Globals
    public static final int TOP_LINE = 0;
    public static final int RIGHT_LINE = 1;
    public static final int BOTTOM_LINE = 2;
    public static final int LEFT_LINE = 3;
    public static final double CAHNGE_ANGLE_HORIZONTAL = 90;
    public static final double CAHNGE_ANGLE_VERTICAL = 180;
    //Fields
    private final Rectangle collisionRectangle;
    private List<HitListener> hitListeners;
    //Constructor
    /**
     * Creates a Block element, given a Rectangle.
     * @param collisRectangle of the Block
     */
    public Block(Rectangle collisRectangle) {
        this.collisionRectangle = collisRectangle;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Creatse a block element, given the desired properties of it collision rectangle.
     * @param upperLeft the start point of the rectangle
     * @param width the desired width of the block
     * @param height the desired height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        Rectangle rect = new Rectangle(upperLeft, width, height);
        this.collisionRectangle = rect;
        this.hitListeners = new ArrayList<HitListener>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }
    //Public Methods
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        Rectangle object = this.getCollisionRectangle();
        //check if the hit it in one of the edges.
        int edgeIndex = -1;
        for (int i = 0; i < object.getEdges().size(); i++) {
            if (object.getEdges().get(i).equals(collisionPoint)) {
                edgeIndex = i;
                break;
            }
        }
        if (edgeIndex != -1) {
            double newDx = -(currentVelocity.getDx());
            double newDy = -(currentVelocity.getDy());
            Velocity v = new Velocity(newDx, newDy);
            return v;
        }
        //find the line it hit
        int lineIndex = Block.TOP_LINE;
        for (Line line : object.getLines()) {
            if (line.partOfSegment(collisionPoint)) {
                lineIndex = object.getLines().indexOf(line);
                break;
            }
        }
        Velocity newVelocity = calculateNewVelocity(lineIndex, currentVelocity);
        return newVelocity;
    }
    /**
     * This method adds the Block object to a game's Collidables and Sprites collections.
     * @param game the game we want the Block to be related to
     */
    public void addToGame(GameLevel game) {
        game.getEnivornment().addCollidable(this);
        game.getSprites().addSprite(this);
    }
    /**
     * This method removes the Block object from a game's Collidables and Sprite collection.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.getEnivornment().removeCollidable(this);
        game.getSprites().removeSprite(this);
    }
    /**
     * The method checks where the collision happend on the Block.
     * Depends on the answer the velocity of the hitting object will change
     * @param lineIndex the line of the Block the collision occoured
     * @param currenrVelocity the velocity of the collision
     * @return a new calculated velocity
     */
    public Velocity calculateNewVelocity(int lineIndex, Velocity currenrVelocity) {
        double angle = currenrVelocity.getAngle();
        double speed = currenrVelocity.getSpeed();
        //Check which Line was hit
        switch (lineIndex) {
            case Block.TOP_LINE:
                //angle = Math.toDegrees(Math.atan2(-currenrVelocity.getDy(), currenrVelocity.getDx()));
                //break;
            case Block.BOTTOM_LINE:
                angle = Math.toDegrees(Math.atan2(-currenrVelocity.getDy(), currenrVelocity.getDx()));
                break;
            case Block.LEFT_LINE:
                //angle = Math.toDegrees(Math.atan2(currenrVelocity.getDy(), -currenrVelocity.getDx()));
                //break;
            //The right line
            default:
                angle = Math.toDegrees(Math.atan2(currenrVelocity.getDy(), -currenrVelocity.getDx()));
                break;
        }
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        return v;
    }
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rect = this.getCollisionRectangle();
        Point rectStart = rect.getUpperLeft();
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectStart.getX(), (int) rectStart.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(rect.getColor());
        d.fillRectangle((int) rectStart.getX(), (int) rectStart.getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }
    @Override
    public void timePassed() {
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        try {
            this.hitListeners.remove(hl);
        } catch (Exception e) {
            System.out.println("There are no HitListeners assigned to this Object");
        }
    }
    /**
     * This method apply the principle of the Observer pattern we have used.
     * This method is called when this object - who acts as a notifier - notifies all its assigned listeners it was
     * hit.
     * @param hitter the hit object - who may invoke changes on the Listener object
     */
    public void notifyHit(Ball hitter) {
        //We use a copy of the listeners list because we may alterate it during the process
        List<HitListener> copy = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : copy) {
            hl.hitEvent(this, hitter);
        }
    }
}
