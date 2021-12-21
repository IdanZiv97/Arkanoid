package animations;
import biuoop.DrawSurface;
/**
 * @author Idan Ziv
 * ID: 318175197
 * Describes an animation object and its basic abilities:
 * drawing the animation and giving an indiction on his running status
 */
public interface Animation {
    /**
     * In charge of game-specific logic and and stopping conditions.
     * @param d the DrawSurface the animation is being shown on
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method indicates whether one of the stopping conditions of the animation was applied or not.
     * @return a boolean value indicating whether the animation should stop or not
     */
    boolean shouldStop();
}
