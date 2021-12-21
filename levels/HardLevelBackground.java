package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Block;
import game.Sprite;
import geometry.Point;
/**
 * A background object for the ChildrensPlay level.
 */
public class HardLevelBackground implements Sprite {
    //Fields
    private Block colorBlock;
    /**
     * Constructor.
     */
    public HardLevelBackground() {
        this.colorBlock = new Block(new Point(0, 0), 1000, 1000);
        this.colorBlock.getCollisionRectangle().setColor(Color.BLUE.brighter());
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.colorBlock.getCollisionRectangle().getColor());
        int x = (int) this.colorBlock.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.colorBlock.getCollisionRectangle().getUpperLeft().getY();
        int w = (int) this.colorBlock.getCollisionRectangle().getWidth();
        int h = (int) this.colorBlock.getCollisionRectangle().getHeight();
        d.fillRectangle(x, y, w, h);
    }
    @Override
    public void timePassed() {
    }
}
