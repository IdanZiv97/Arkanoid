package game;
public interface HitListener {
    /**
     * This method is called when a Block is being.
     * The function will invoke all the changes that the Listener is subjected to
     * when he gets notiffied of a hit.
     * @param beingHit the object who was hit
     * @param hitter the hit object
     */
    public void hitEvent(Block beingHit, Ball hitter);
}
