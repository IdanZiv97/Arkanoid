package levels;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * Circle object in the game - has the properties of a circle and abilities to be drawn.
 */
public class Circle implements Sprite {
    //Fields
    private final Point center;
    private final int radius;
    private final java.awt.Color color;
    //Constructor
    /**
     * Constructor.
     * @param p center point
     * @param r circle's radius
     * @param c circle's color
     */
    public Circle(Point p, int r, java.awt.Color c) {
        this.center = p;
        this.radius = r;
        this.color = c;
    }
    /**
     * @return the circle's center
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * This method draws a full circle.
     * @param d the draw surface
     */
    public void drawFullCircle(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    @Override
    public void timePassed() {

    }
}
