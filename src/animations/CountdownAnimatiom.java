package animations;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;
/**
 * @author Idan Ziv
 * ID: 318175197
 * This animation object commences a countdown and shows it upon the screen.
 */
public class CountdownAnimatiom implements Animation {
    public static final double TIME = 1000;
    public static final int FONT_SIZE = 50;
    //Fields
    private int countFrom;
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private boolean stopCountdown;
    private int timeLeft;
    private Sleeper sleeper;
    /**
     * A countdown sequence which is shown at the start of each level.
     * @param numOfSeconds number of seconds of the countdown
     * @param countFrom the number we are counting from
     * @param gameScreen the game Sprites to be displayed in the background
     */
    public CountdownAnimatiom(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.timeLeft = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.stopCountdown = false;
        this.sleeper = new Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //drawing the gameScreen
        this.gameScreen.drawAllOn(d);
        //In the count of zero we want the game to begin
        if (this.timeLeft > 0) {
            d.setColor(Color.decode("#BCFAFF"));
            String print = Integer.toString(this.timeLeft);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, print, FONT_SIZE);
            long timeToSleep = (long) ((this.numOfSeconds / this.countFrom)  * TIME);
            sleeper.sleepFor(timeToSleep);
        }
        //In the first time we initiate the countdown we don't need to pause because nothing has been drawn yet.
        if (this.timeLeft != this.countFrom) {
            long timeToSleep = (long) ((this.numOfSeconds / this.countFrom) * TIME);
            sleeper.sleepFor(timeToSleep);
        }
        this.timeLeft--;
    }

    @Override
    public boolean shouldStop() {
        if (this.timeLeft < 0) {
            this.stopCountdown = true;
        }
        return this.stopCountdown;
    }
}
