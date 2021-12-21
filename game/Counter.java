package game;
/**
 * @author Idan Ziv
 * ID: 318175197
 * An object capable of storing data, manage it and return its value.
 */
public class Counter {
    //Fields
    private int data;
    /**
     * This method increases the data stored inside to Counter by one.
     */
    public void increase() {
        this.data += 1;
    }
    /**
     * This method increases the data stored inside the counter by a given number.
     * @param nubmer the number the method  adds to the Counter's data
     */
    public void increase(int nubmer) {
        this.data += nubmer;
    }
    /**
     * This method decreases the data stored inside to Counter by one.
     */
    public void decrease() {
        this.data -= 1;
    }
    /**
     * This method decreases the data stored inside the counter by a given number.
     * @param number the number the method  reduces to the Counter's data
     */
    public void decrease(int number) {
        this.data -= number;
    }
    /**
     * @return The counter's value
     */
    public int getValue() {
        return this.data;
    }
}
