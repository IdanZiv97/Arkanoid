package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.Sprite;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * Creates a sun object
 */
public class Sun implements Sprite {
    private List<Circle> collection;
    /**
     * Constructor.
     */
    public Sun() {
        this.collection = new ArrayList<Circle>();
        Point p = new Point(150, 150);
        Color c = Color.decode("#fafd0f");
        Circle c3 = new Circle(p, 70, c);
        c = c.brighter();
        this.collection.add(c3);
        Circle c2 = new Circle(p, 60, c);
        this.collection.add(c2);
        c = c.brighter();
        Circle c1 = new Circle(p, 50, c);
        this.collection.add(c1);
    }
    @Override
    public void drawOn(DrawSurface d) {
        for (Circle c : this.collection) {
            c.drawFullCircle(d);
        }
        Point start = new Point(150, 150);
        Point end = new Point(GameEnvironment.FRAME_WIDTH_STD, 250);
        for (int i = 0; i < 40; i++) {
            d.setColor(Color.decode("#fafd0f"));
            d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
            end = new Point(end.getX() + 40, end.getY());
        }
    }

    @Override
    public void timePassed() {

    }
}
