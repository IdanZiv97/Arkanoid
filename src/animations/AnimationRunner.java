package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Idan Ziv
 * ID: 318175197
 * AnimationRunner object is used for running any type of animation object regardless to the Animation's
 * inner logic
 */
public class AnimationRunner {
    //Fields
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     * Constructor of an animation runner.
     * @param g the animation GUI
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * @return the AnimationRunner's GUI
     */
    public GUI getGUI() {
        return this.gui;
    }
    /**
     * Performs a run of an a specific animation logic.
     * @param animation a general animation object
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        //will run as long we can still play the animation
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            // timing
           if (animation.shouldStop()) {
               return;
           }
           gui.show(d);
           long usedTime = System.currentTimeMillis() - startTime;
           long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
           if (milliSecondLeftToSleep > 0) {
               sleeper.sleepFor(milliSecondLeftToSleep);
           }
        }
    }
}
