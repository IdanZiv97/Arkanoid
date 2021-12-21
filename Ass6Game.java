
import java.util.ArrayList;
import java.util.List;

import animations.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.ChlidrensPlay;
import levels.DirectHitLevel;
import levels.GameLevel;
import levels.HardLevel;
import levels.LevelInformation;
import levels.WarmUpLevel;

/**
 * @author Idan Ziv
 * ID: 318175197
 * This class intializez a Game session and runs it.
 */
public class Ass6Game {
    /**
     * Recives input from the console and creates levels accordingly.
     * Runs created levels
     * @param args input from the consoles
     */
    public static void main(String[] args) {
        //create the gui
        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        //create the runner
        AnimationRunner runner = new AnimationRunner(gui);
        //create the keyboard sensor
        KeyboardSensor ks = gui.getKeyboardSensor();
        //check the input - create an array of nubmer
        List<Integer> input = checkInput(args);
        //create levels depends on the input
        List<LevelInformation> levels = createLevels(input);
        //create GameFlow object
        GameFlow mannager = new GameFlow(runner, ks, 1);
        //run the levels
        mannager.runLevels(levels);
    }
    /**
     * Creates game levels, given valid key values.
     * @param input an array of valid keys
     * @return a list of levels
     */
    public static List<LevelInformation> createLevels(List<Integer> input) {
        List<LevelInformation> temp = new ArrayList<LevelInformation>();
        for (int i : input) {
            switch (i) {
                case 1:
                    LevelInformation lvl1 = new DirectHitLevel();
                    temp.add(lvl1);
                    break;
                case 2:
                    LevelInformation lvl2 = new ChlidrensPlay();
                    temp.add(lvl2);
                    break;
                case 3:
                    LevelInformation lvl3 = new WarmUpLevel();
                    temp.add(lvl3);
                    break;
                default:
                    LevelInformation lvl4 = new HardLevel();
                    temp.add(lvl4);
                    break;
            }
        }
        return temp;
    }
    /**
     * Checks the input and creates a list of valid keys.
     * @param args input
     * @return list of valid level keys
     */
    public static List<Integer> checkInput(String[] args) {
        List<Integer> temp = new ArrayList<Integer>();
        //When there are no arguments we need to start with default level layout
        if (args.length == 0) {
            for (int i = 1; i <= 4; i++) {
                temp.add(i);
            }
            return temp;
        }
        for (String s : args) {
            if (s.matches("[1-4]")) {
                temp.add(Integer.parseInt(s));
            }
        }
        if (temp.size() != 0) {
            return temp;
        } else {
            for (int i = 1; i <= 4; i++) {
                temp.add(i);
            }
            return temp;
        }
    }
}
