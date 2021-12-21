package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Counter;
import game.Sprite;
import geometry.Rectangle;
/**
 * This class is a Sprtie element meeant to dispaly information about the game score.
 */
public class ScoreBox implements Sprite {
    //Fields
    private final int fontSize = 15;
    private final Rectangle box;
    private Counter score;
    //Constructor
    /**
     * Constructor.
     * @param box the shape of the Sprite
     * @param score information about the game score
     */
    public ScoreBox(Rectangle box, Counter score) {
        this.box = box;
        this.score = score;
    }
    /**
     * Add the Sprite to the current game.
     * @param g the game the Sprtie is added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.box.drawOn(d);
        int x = (int) (this.box.getUpperLeft().getX() + (this.box.getWidth()) / 2.2);
        int y = (int) (this.box.getUpperLeft().getY() + ((this.box.getHeight()) - 2));
        String s = "Score is" + this.score.getValue();
        d.setColor(Color.BLACK);
        d.drawText(x, y, s, this.fontSize);
    }

    @Override
    public void timePassed() {
    }
}
