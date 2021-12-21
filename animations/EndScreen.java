package animations;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Block;
import game.Counter;
import geometry.Point;
/**
 * @author Idan Ziv:
 * ID: 318175197
 * This class indicates the ending of a Game.
 * It can be called whether a user ended the Game after completing all the levels or losing all of his lives.
 * The End Screen is prints a proper message, depends on the result of the game.
 * The End Screen is a part of the Decorator design-pattern used in the game.
 * It is wrapping a specific behaviour of a wait-for-key animation
 */
public class EndScreen implements Animation {
    //Fields
    private Counter score;
    private boolean wonGame;
    private boolean stop;
    /**
     * Constructor.
     * @param score the game's score counter
     * @param result the result of the game
     */
    public EndScreen(Counter score, boolean result) {
        this.score = score;
        this.wonGame = result;
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
        if (this.wonGame) {
            d.setColor(Color.WHITE);
            String toPrint = "You Win!\nYour score is " + this.score.getValue();
            d.drawText(150, d.getHeight() / 3, toPrint, 30);
            String nextPrint = "Press space tp continue";
            d.drawText(300, d.getHeight() / 2, nextPrint, 15);
        } else {
            d.setColor(Color.WHITE);
            String toPrint = "Game Over.\nYour score is " + this.score.getValue();
            d.drawText(150, d.getHeight() / 3, toPrint, 30);
            String nextPrint = "Press space tp continue";
            d.drawText(300, d.getHeight() / 2, nextPrint, 15);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
