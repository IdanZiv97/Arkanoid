package geometry;

/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates a Point object.
 * The class supprort basic operations on the object and its interactions with others.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructs a point given x and y values.
     * @param x x value of the point
     * @param y y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * This method calculates the using the formula as shown below.
     * distance = sqrt((x1^2-x2^2)+(y1^2-y2^2))
     * @param other the point which we want to determine the distance from
     * @return a double variable or 0 when the points are the same
     */
    public double distance(Point other) {
        if (this.equals(other)) {
            return 0;
        }
        double xDelta = Math.pow(this.x - other.getX(), 2);
        double yDelta = Math.pow(this.y - other.getY(), 2);
        return Math.sqrt(xDelta + yDelta);
    }
    /**
     * The method comapers the x and y value of the two points.
     * @param other the point which the method comapres
     * @return true if the x and y values are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (this.x == other.getX()) && (this.y == other.getY());
    }
    /**
     * @return the x value of the point in question.
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the x value of the point in question.
     */
    public double getY() {
        return this.y;
    }
    /**
     * The method calculates the slope between 2 points, using the slope formula, shown below.
     * slope = (y2-y1) / (x2-x1)
     * @param other the point which want to check the slope
     * @return the result of the devision between dy and dx or 0 when dy = 0 or the points are equal.
     */
    public double slope(Point other) {
        if (this.equals(other)) {
            return 0;
        }
        double dy = other.getY() - this.getY();
        if (dy == 0) {
            return 0;
        }
        double dx = other.getX() - this.getX();
        double slope = dy / dx;
        return slope;
    }
}