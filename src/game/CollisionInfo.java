package game;

import geometry.Point;
/**
 * A class to calculate and store the case of collision with a Collidable object.
 */
public class CollisionInfo {
    //Fields
    private final Point collisionPoint;
    private final Collidable collisionObject;
    //Constructor
    /**
     * Creates an object of CollisionInfo.
     * @param collisionPoint the collishion point
     * @param collisionObject collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    //Getters
    /**
     * @return the collision point assigned to this object
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @return the collision object assigned to this object
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}
