package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;
import game.SpriteCollection;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
/**
 * @author Idan Ziv
 * ID: 318175197
 * A background object for the DirectHit Level
 */
public class DirectHitBackground implements Sprite {
    //Fields
    private SpriteCollection sprites;
    private Rectangle colorBlock;
    //Constructor
    /**
     * Constructor.
     */
    public DirectHitBackground() {
        this.sprites = new SpriteCollection();
        //set the background block - for color
        Rectangle rect = new Rectangle(new Point(0, 0), 1000, 1000);
        this.colorBlock = rect;
        //add the circles
        Point p = new Point(400, 275);
        int r = 55;
        for (int i = 1; i <= 3; i++) {
            Circle c = new Circle(p, i * r, Color.BLUE);
            this.sprites.addSprite(c);
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        //set background color
        d.setColor(Color.BLACK);
        int x = (int) this.colorBlock.getUpperLeft().getX();
        int y = (int) this.colorBlock.getUpperLeft().getY();
        int w = (int) this.colorBlock.getWidth();
        int h = (int) this.colorBlock.getHeight();
        d.fillRectangle(x, y, w, h);
        //set the circles
        for (Sprite s : this.sprites.getSpriteArray()) {
            s.drawOn(d);
        }
        d.setColor(Color.BLUE);
        double delta = 180;
        //set vertical line
        Line vertical = new Line(400, 275 - delta, 400, 275 + delta);
        d.drawLine((int) vertical.start().getX(), (int) vertical.start().getY(),
        (int) vertical.end().getX(), (int) vertical.end().getY());
        //set horizontal line
        Line horizontal = new Line(400 + delta, 275, 400 - delta, 275);
        d.drawLine((int) horizontal.start().getX(), (int) horizontal.start().getY(),
        (int) horizontal.end().getX(), (int) horizontal.end().getY());
    }

    @Override
    public void timePassed() {
    }
}
