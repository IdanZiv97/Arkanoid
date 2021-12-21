package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Counter;
import game.GameEnvironment;
import game.Sprite;

public class LivesIndicator implements Sprite {
    //Fields
    private Counter numOfLives;
    public LivesIndicator(Counter lives) {
        this.numOfLives = lives;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(GameEnvironment.FRAME_WIDTH_STD, 18, "Lives: " + this.numOfLives.getValue(), GameLevel.TEXT_SIZE);
    }

    @Override
    public void timePassed() {
    }
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    
}
