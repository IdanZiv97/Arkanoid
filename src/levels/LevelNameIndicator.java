package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;

public class LevelNameIndicator implements Sprite {
    //Fields
    private String name;
    //Constructor
    public LevelNameIndicator(String s) {
        this.name = s;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(d.getHeight(), 18, "Level Name: " + this.name , GameLevel.TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }
    
}
