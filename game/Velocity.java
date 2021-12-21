package game;

import geometry.Point;

/**
 * @author Idan Ziv
 * ID: 318175197
 * This class creates a Velocity object, supporting basic operations.
 */
public class Velocity {
    private double dx;
    private double dy;
    //constructor
    /**
     * Constructs a velocity object.
     * @param dx the want change on the X-Axis
     * @param dy the want change on the -Axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * This method changes the cordinates of a given point, according to the set
     * changes for the X-Axis and Y-Axis.
     * @param p the point which we want to change the center of.
     * @return a point with the changed center.
     */
    public Point applyToPoint(Point p) {
        Point temp = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return temp;
    }
    /**
     * Calculate the angle of the object's movement.
     * @return the angle in relation to the positive side of the X-Axis
     */
    public double getAngle() {
        double angle = Math.toDegrees(Math.atan2(this.getDy(), this.getDx()));
        return angle;
    }
    /**
     * This method returns the speed of the ball in this current movement.
     * Speed is calulated in the following formula: sqrt(dx^2+dy^2) - which are the fields of the Velocity object.
     * @return the calculated speed of the object.
     */
    public double getSpeed() {
        double x = Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2);
        return Math.sqrt(x);
    }
    /**
     * @return the wanted change on the X-Axis
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @return the wanted change on the Y-Axis.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * A method to change the wanted change on the X-Axis of a given Velocity object.
     * @param newDx the new X-Axis chanfe.
     */
    public void changeDx(double newDx) {
        this.dx = newDx;
    }
    /**
     * * A method to change the wanted change on the Y-Axis of a given Velocity object.
     * @param newDy the new Y-Axis chanfe.
     */
    public void changeDy(double newDy) {
        this.dy = newDy;
    }
    /**
     * A speed can also be calculated with angle and the speed wanted.
     * This method the speed's projections on the X-Axis and Y-Axis and creates Velocity.
     * @param angle the speed's angle
     * @param speed the desired speed
     * @return Velociity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Calculating the X-Axis projection
        double dx = speed * (Math.cos(Math.toRadians(angle)));
        //Calculating the Y-Axis projection
        double dy = speed * (Math.sin(Math.toRadians(angle)));
        Velocity v = new Velocity(dx, dy);
        return v;
    }
}