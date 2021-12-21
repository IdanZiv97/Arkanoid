package game;
import java.awt.Color;
import java.util.ArrayList;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates a GameEnvironment object.
 * This class supports basic operation regarding the properties of the Object and methods to help
 * with the elements inside the Environment.
 */
public class GameEnvironment {
    //Globals
    public static final int FRAME_WIDTH_STD = 30;
    public static final int FRAME_HEIGHT_STD = 20;
    public static final java.awt.Color FRAME_COLOR = Color.GRAY;
    public static final Point BALL_CENTER_POINT = new Point(350, 500);
    public static final int MAX_BALLS = 1;
    public static final int RADIUS = 5;
    public static final int SPEED = 5;
    public static final double BLOCK_WIDTH = 50;
    public static final double BLOCK_HEIGHT = 20;
    public static final int ROWS = 6;
    public static final int COLUMNS = 12;
    //Fields
    private ArrayList<Collidable> gameCollidables;
    //Constructor
    /**
     * Creates a game environment given the GUI upon it will exist.
     */
    public GameEnvironment() {
        this.gameCollidables = new ArrayList<Collidable>();
    }
    //Getters
    /**
     * @return a list of all the Objects' Collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return this.gameCollidables;
    }
    //Public Methods
    /**
     * Adds to the object list of Collidables a collidable object.
     * @param c the new collidable object
     */
    public void addCollidable(Collidable c) {
        this.getCollidables().add(c);
    }
    /**
     * Removes from the object's list of Collidables a collidable object.
     * @param c Collidable object
     */
    public void removeCollidable(Collidable c) {
        this.getCollidables().remove(c);
    }
    /**
     * This class will notify the the user if any collision has occured with one of the Collidables inside the object.
     * @param trajectory the line in which an element is moving in the environment
     * @return a collisionInfo object with the collision data or null if no collision has occured
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //Get collidable objects
        ArrayList<Collidable> arr = this.getCollidables();
        //set a variable to store the collidable index with -1
        int collidableIndex = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getCollisionRectangle().isIntersectingWith(trajectory)) {
                collidableIndex = i;
                break;
            }
        }
        if (collidableIndex == -1) {
            return null;
        }
        Collidable collisionObject = arr.get(collidableIndex);
        Rectangle collisionRectangle = collisionObject.getCollisionRectangle();
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collisionRectangle);
        CollisionInfo temp = new CollisionInfo(collisionPoint, collisionObject);
        return temp;
    }
}
