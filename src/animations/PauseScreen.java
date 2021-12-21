package animations;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Block;
import geometry.Point;
/**
 * @author Idan Ziv:
 * ID: 318175197
 * This class indicates the pause of a Game.
 * It can be called whether a user presses a designated key
 * The Pause Screen is a part of the Decorator design-pattern used in the game.
 * It is wrapping a specific behaviour of a wait-for-key animation
 */
public class PauseScreen implements Animation {
    //Fields
    private boolean stop;
    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Block backGround = new Block(new Point(0, 0), 1000, 1000);
        backGround.getCollisionRectangle().setColor(Color.BLACK);
        int x = (int) backGround.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) backGround.getCollisionRectangle().getUpperLeft().getY();
        int w = (int) backGround.getCollisionRectangle().getWidth();
        int h = (int) backGround.getCollisionRectangle().getHeight();
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.WHITE);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
