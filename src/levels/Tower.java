package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.Sprite;
import geometry.Rectangle;
import geometry.Point;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates a drawing of a tower as a background
 */
public class Tower implements Sprite {
    private Rectangle base;
    private List<Rectangle> windows;
    private List<Rectangle> antena;
    private List<Circle> topOfAntena;
    /**
     * Constructor.
     */
    public Tower() {
        Rectangle b = new Rectangle(new Point(100, 300), 150, 300);
        b.setColor(Color.BLACK.brighter());
        this.base = b;
        //creating windows
        this.windows = new ArrayList<Rectangle>();
        double xStartOfWindow = this.base.getUpperLeft().getX() + 10;
        double yStartOfWindow = this.base.getUpperLeft().getY() + 10;
        double gap = 30;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Point p = new Point(xStartOfWindow + (j * gap), yStartOfWindow + (i * gap));
                Rectangle rect = new Rectangle(p, 10, 20);
                rect.setColor(Color.WHITE);
                this.windows.add(rect);
            }
            yStartOfWindow += GameEnvironment.BLOCK_HEIGHT;
        }
        //creating antenta
        this.antena = new ArrayList<Rectangle>();
        Rectangle antenaBase = new Rectangle(new Point(160, 269), 30, 30);
        antenaBase.setColor(Color.lightGray.darker());
        this.antena.add(antenaBase);
        Rectangle antenaPillar = new Rectangle(new Point(170, 230), 10, 50);
        antenaPillar.setColor(Color.lightGray.darker());
        this.antena.add(antenaPillar);
        //creating the ball on top
        this.topOfAntena = new ArrayList<Circle>();
        Circle bigCircle = new Circle(new Point(175, 230), 10, Color.RED);
        this.topOfAntena.add(bigCircle);
        Circle smallCircle = new Circle(new Point(175, 230), 5, Color.ORANGE.brighter());
        this.topOfAntena.add(smallCircle);
    }
    @Override
    public void drawOn(DrawSurface d) {
        //Draw Base
        d.setColor(this.base.getColor());
        int x = (int) this.base.getUpperLeft().getX();
        int y = (int) this.base.getUpperLeft().getY();
        int w = (int) this.base.getWidth();
        int h = (int) this.base.getHeight();
        d.fillRectangle(x, y, w, h);
        d.drawRectangle(x, y, w, h);
        //Draw windows
        d.fillRectangle(x, y, w, h);
        for (Rectangle r : this.windows) {
            d.setColor(r.getColor());
            int xWindow = (int) r.getUpperLeft().getX();
            int yWindow = (int) r.getUpperLeft().getY();
            int wWindow = (int) r.getWidth();
            int hWindow = (int) r.getHeight();
            d.fillRectangle(xWindow, yWindow, wWindow, hWindow);
        }
        //Draw Antena
        for (Rectangle r : this.antena) {
            d.setColor(r.getColor());
            int xAntena = (int) r.getUpperLeft().getX();
            int yAntena = (int) r.getUpperLeft().getY();
            int wAntena = (int) r.getWidth();
            int hAntena = (int) r.getHeight();
            d.fillRectangle(xAntena, yAntena, wAntena, hAntena);
        }
        for (Circle c : this.topOfAntena) {
            c.drawFullCircle(d);
        }
    }

    @Override
    public void timePassed() {
    }

}
