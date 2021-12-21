package levels;
import game.Ball;
import game.Block;
import game.Counter;
import game.HitListener;

public class ScoreTrackingListener implements HitListener{
    private Counter currentScore;
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the object from the subject's list
        beingHit.removeHitListener(this);
        //increase the score
        this.currentScore.increase(5);
    }
}
