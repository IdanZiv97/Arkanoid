package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;
import game.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
/**
 * A background object for the ChildrensPlay level.
 */
public class ChlidrensPlayBackground implements Sprite {
    //Fields
    private SpriteCollection sprites;
    private Rectangle colorBlock;
    /**
     * Constructor.
     */
    public ChlidrensPlayBackground() {
        this.sprites = new SpriteCollection();
        this.colorBlock = new Rectangle(new Point(0, 0), 1000, 1000);
        Sun sun = new Sun();
        this.sprites.addSprite(sun);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#87ceeb"));
        int x = (int) this.colorBlock.getUpperLeft().getX();
        int y = (int) this.colorBlock.getUpperLeft().getY();
        int w = (int) this.colorBlock.getWidth();
        int h = (int) this.colorBlock.getHeight();
        d.fillRectangle(x, y, w, h);
        for (Sprite s : this.sprites.getSpriteArray()) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }

}
