package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Decoator class for any Animation intilized by a Keyboard input from the user.
 */
public class KeyPressStoppableAnimation implements Animation {
    //Fields
    private Animation decoratedAnimation;
    private boolean isAlreadyPressed;
    private KeyboardSensor sensor;
    private String waitForKey;
    private boolean stopAnimation;
    /**
     * Constructor.
     * @param sensor keyboard sensor
     * @param key wait-on-key
     * @param animation animation to be displayed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.decoratedAnimation = animation;
        this.stopAnimation = false;
        this.waitForKey = key;
        this.isAlreadyPressed = true;
        this.sensor = sensor;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //Making sure the animation won't stop until the check of whether the key is pressed.
        this.stopAnimation = false;
        if (this.sensor.isPressed(this.waitForKey)) {
            //check if the key was already pressed - if so we can ignore the key press.
            if (this.isAlreadyPressed) {
                return;
            }
            //The wait-for-key  was pressed - time to stop the animation
            this.stopAnimation = true;
        }
    this.isAlreadyPressed = false;
    this.decoratedAnimation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stopAnimation;
    }
}
