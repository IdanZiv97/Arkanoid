package game;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates an object who stores Sprite objects assigned from a Game object.
 */
public class SpriteCollection {
    //Globals
    public static final int BALL_INDEX = 1;
    public static final int PADDLE_INDEX = 0;
    public static final int BLOCK_START = 2;
    //Fields
    private ArrayList<Sprite> arrSprites;
    //Constructors
    /**
     * Creates a sprite collection object.
     * The constructor initializez the Sprites List of the object
     */
    public SpriteCollection() {
        this.arrSprites = new ArrayList<Sprite>();
    }
    //Getters
    /**
     * @return the Sprite List of object
     */
    public ArrayList<Sprite> getSpriteArray() {
        return this.arrSprites;
    }
    //Public Methods
    /**
     * This method add a given sprite to the object's list of Sprite objects.
     * @param s the spirte object
     */
    public void addSprite(Sprite s) {
        this.getSpriteArray().add(s);
    }
    /**
     * This method removes a given sprite from the object's list of Sprite objects.
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.getSpriteArray().remove(s);
    }
    /**
     * This method invoke all the actions a sprite object in the object's list related to the Game.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<Sprite>(this.getSpriteArray());
        for (Sprite s : copySprites) {
            s.timePassed();
        }
    }
    /**
     * This method draws all the sprtie objects assigned to a game object on the given DrawSurface.
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSpriteArray()) {
            s.drawOn(d);
        }
    }
}
