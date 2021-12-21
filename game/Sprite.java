package game;
import biuoop.DrawSurface;
/**
 * Interface of a graphic part of the game.
 */
public interface Sprite {
    /**
     * Method which creates a drawing of the object upon a DrawSurface.
     * @param d the given DrawSurface object.
     */
   void drawOn(DrawSurface d);
   /**
    * Notify the Sprite object that the set time period had passed.
    * The method invokes the proper action on the Sprite object
    */
   void timePassed();
}
