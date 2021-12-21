package game;
/**
 * This interface indicates that the object implementing it reports about its activity and status changes
 * to a known list of "observers".
 * Any object implementing this interface is capable of managing its "observers" list.
 */
public interface HitNotifier {
    /**
     * Adds an observer to the "observers" list
     * @param hl the "observer" object
     */
    public void addHitListener(HitListener hl);
    /**
     * Removes an observer to the "observers" list
     * @param hl the "observer" object
     */
    public void removeHitListener(HitListener hl);
}
