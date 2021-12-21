package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Block;
import game.Sprite;
import geometry.Point;

/**
 *
 */
public class WarmUpLevelBackground implements Sprite {
    //Fields
    private Block colorBlock;
    private Tower tower;
    /**
     * Constructor.
     */
    public WarmUpLevelBackground() {
        this.tower = new Tower();
        Block b = new Block(new Point(0, 0), 1000, 1000);
        b.getCollisionRectangle().setColor(Color.GREEN.darker().darker());
        this.colorBlock = b;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.colorBlock.getCollisionRectangle().getColor());
        int x = (int) this.colorBlock.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.colorBlock.getCollisionRectangle().getUpperLeft().getY();
        int w = (int) this.colorBlock.getCollisionRectangle().getWidth();
        int h = (int) this.colorBlock.getCollisionRectangle().getHeight();
        d.fillRectangle(x, y, w, h);
        this.tower.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

}
