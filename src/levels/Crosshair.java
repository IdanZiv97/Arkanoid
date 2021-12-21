package levels;
import biuoop.DrawSurface;
import game.Sprite;
import game.SpriteCollection;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 */
public class Crosshair implements Sprite {
    //Fields
    private final Point center;
    private final int radius;
    private final int numberOfCircles;
    private final java.awt.Color color;
    private SpriteCollection sprites;
    /**
     * Creates a croshair object.
     * @param p the center point
     * @param r the base radius of the circles
     * @param c the color of the crosshair
     */
    public Crosshair(Point p, int r, java.awt.Color c) {
        this.center = p;
        this.radius = r;
        this.sprites = new SpriteCollection();
        this.color = c;
        this.numberOfCircles = 3;
        createCircles();
    }
    @Override
    public void drawOn(DrawSurface d) {
        //drawing the lines
        d.setColor(this.color);
        double delta = this.radius * this.numberOfCircles + 5;
        double x = this.center.getX();
        double y = this.center.getY();
        //draw vertical
        d.drawLine((int) (x + delta), (int) y, (int) (x - delta), (int) y);
        //draw horizontal
        d.drawLine((int) x, (int) (y + delta), (int) x, (int) (y - delta));
        //draw circles
        for (Sprite s : this.sprites.getSpriteArray()) {
            s.drawOn(d);
        }
    }
    @Override
    public void timePassed() {
    }
    /**
     * Creates the circles of the crosshair.
     */
    private void createCircles() {
        //create the circles
        for (int i = 1; i <= this.numberOfCircles; i++) {
                Circle c = new Circle(this.center, i * this.radius, this.color);
                this.sprites.addSprite(c);
        }
    }
}
