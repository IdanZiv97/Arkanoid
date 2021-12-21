package game;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Idan Ziv
 * ID: 318175197
 * This interface describes the basic properties of an object who is able
 * to identify when a he is being hit by another object
 */
public interface Collidable {
    /**
     * Each collidable object has a shape, this method returns it.
     * @return the assigned Rectangle of the Collidable object.
     */
   Rectangle getCollisionRectangle();
   /**
    * Notify the object that we collided with it at collisionPoint with
    * a given velocity.
    * The return is the new velocity expected after the hit (based on
    * the force the object inflicted on us)
    * @param collisionPoint the point which the object was hit in
    * @param currentVelocity the velocity of the impact
    * @param hitter the ball commited the hit
    * @return a new calculated velocity
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
