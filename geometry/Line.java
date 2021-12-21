package geometry;


import game.Ball;
import game.Velocity;

/**
 * @author Idan Ziv
 * ID: 318175197
 * This class create a line segment object.
 * The class supports basic operation regarding the line itself and how it interacts with other line objects.
 */
public class Line {
    //Fields
    private Point startPoint;
    private Point endPoint;
    private double length;
    //Constructors
    /**
     * This method creates a line give a ball and its velocity.
     * This method is taking in consdieration the direction of the line, basically creating a
     * trajectory
     * @param b the ball object
     * @param v the ball's velocity
     */
    public Line(Ball b, Velocity v) {
        this.startPoint = new Point(b.getX(), b.getY());
        Point end = b.getVelocity().applyToPoint(startPoint);
        end = b.getVelocity().applyToPoint(end);
        //end = b.getVelocity().applyToPoint(end);
        this.endPoint = end;
    }
    /**
     * Constructs a line object given a start point and an end point.
     * @param start start point of the line segment
     * @param end end point of the line segment.
     */
    public Line(Point start, Point end) {
        //Makes sure that the start point's x cordinate is smaller than the end's
        if (start.getX() <= end.getX()) {
            this.startPoint = new Point(start.getX(), start.getY());
            this.endPoint = new Point(end.getX(), end.getY());
            this.length = start.distance(end);
        } else {
            this.startPoint = new Point(end.getX(), end.getY());
            this.endPoint = new Point(start.getX(), start.getY());
            this.length = end.distance(start);
        }
    }
    /**
     * Creates a line segment from two sets of point cordinates.
     * @param x1 start point x cordinate
     * @param y1 start point x cordinate
     * @param x2 end point x cordinate
     * @param y2 end point y cordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        //Makes sure that the start point's x cordinate is smaller than the end's
        if (x1 <= x2) {
            this.startPoint = new Point(x1, y1);
            this.endPoint = new Point(x2, y2);
            length = this.startPoint.distance(this.endPoint);
        } else {
            this.startPoint = new Point(x2, y2);
            this.endPoint = new Point(x1, y1);
            this.length = this.startPoint.distance(this.endPoint);
        }
    }
    /**
     * @return the length of a line segment.
     */
    public double length() {
        return this.length;
    }
    /**
     * The middle points cordinate is the average of the x and y values of the line's
     * start and end points.
     * @return the middle point of a line segment
     */
    public Point middle() {
        //Calculating the x cordinates average of the line's start and end points.
        double midXValue = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        //Calculating the y cordinates average of the line's start and end points.
        double midYValue = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        //Create the point from the calculated cordinates.
        Point midPoint = new Point(midXValue, midYValue);
        return midPoint;
    }
    /**
     * @return the start point of a line segment.
     */
    public Point start() {
        return this.startPoint;
    }
    /**
     * @return the end point of a line segment.
     */
    public Point end() {
        return this.endPoint;
    }
    /**
     * Two line segments are equal if they start and end at the same point.
     * @param other the line segment which we compare to.
     * @return a bollean value, depends on the result of the comparison.
     */
    public boolean equals(Line other) {
        if ((this.startPoint.getX() == other.startPoint.getX()) && (this.endPoint.getY() == other.endPoint.getY())) {
            return true;
        }
        return false;
    }
    /**
     * This method returns the intersection points between two line segments.
     * First the method checks for intesection of any sort.
     * Depends on the result of the intersction check, we will calculate the intersection using
     * the line equation: Ax+By=C [A = dy, B = -dx, C = (dy * p1.x) + (dy * p1.y)]
     * When lines(l1, l2) are collinear than the result of: A1*B2 - A2*B1 = 0.
     * This expresion is the shared denominator when calculating the x and y value of the intersection point.
     * If the denominator != 0, then we can proceed to caculate the x and y values of the interscection point.
     * @param other the line which we want to find the intersection point with.
     * @return The intersection point - if exists.
     */
    public Point intersectionWith(Line other) {
        //firstly we will check if the lines intersect
        if (!(this.isIntersecting(other))) {
            return null;
        }
        //calculation of the A,B,C parameters of each line
        double a1 = this.endPoint.getY() - this.startPoint.getY();
        double b1 = this.startPoint.getX() - this.endPoint.getX();
        double c1 = (a1 * this.startPoint.getX()) + (b1 * this.startPoint.getY());
        double a2 = other.endPoint.getY() - other.startPoint.getY();
        double b2 = other.startPoint.getX() - other.endPoint.getX();
        double c2 = (a2 * other.startPoint.getX()) + (b2 * other.startPoint.getY());
        //calculating the denominator
        double denominator = (a1 * b2) - (a2 * b1);
        //if collinear
        if (denominator == 0) {
            return null;
        }
        //calculating the x and y values of the intersection point
        double intersectionX = ((b2 * c1) - (b1 * c2)) / denominator;
        double intersectionY = ((a1 * c2) - (a2 * c1)) / denominator;
        Point intersctionPoint = new Point(intersectionX, intersectionY);
        return intersctionPoint;
    }
    /**
     * The method will detemrine if two lines intersect using all the possible orientation between their points.
     * Lines intersect only if the two possible orientation with the other line's points are different,
     * or the orienation is collinear and the lines are part of each other.
     * @param other - the line in which we want to check intersection with.
     * @return true if the lines are intersecting or collinear, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //Calculating all 4 possible cases for general case.
        int orientation1 = this.checkOrientation(this.startPoint, other.startPoint, this.endPoint);
        int orientation2 = this.checkOrientation(this.startPoint, other.endPoint, this.endPoint);
        int orientation3 = other.checkOrientation(other.startPoint, this.startPoint, other.endPoint);
        int orientation4 = other.checkOrientation(other.startPoint, this.endPoint, other.endPoint);
        //General case of intersection
        if ((orientation1 != orientation2) && (orientation3 != orientation4)) {
            return true;
        }
        //checking each case of collinear points if the 3rd point is part of the segment
        if (orientation1 == 0 && this.partOfSegment(other.startPoint)) {
            return true;
        }
        if (orientation2 == 0 && this.partOfSegment(other.endPoint)) {
            return true;
        }
        if (orientation3 == 0 && other.partOfSegment(this.startPoint)) {
            return true;
        }
        if (orientation4 == 0 && other.partOfSegment(this.endPoint)) {
            return false;
        }
        return false;
    }
    /**
     * The method will check the orrientation of 3 points.
     * It will use the relation between the slopes of the lines created form the 3 points.
     * We will moddify the the standart formula of a line slope to deal with the cases of vertical/horizontal lines.
     * dy_l1/dx_l1 = dy_l2 / dxl2 --> dy_l1 * dx_l2 - dy_l2 * dx_l1
     * @param p1 construct the orignal line
     * @param p2 construct one end of the line we want to check for intersction with the original line.
     * @param p3 construct the original line
     * @return 0 - collinear orientation, 1 - clockwise orientation, 2 - counterclockwise orientation.
     */
    public int checkOrientation(Point p1, Point p2, Point p3) {
        double dxL1 = p2.getX() - p1.getX();
        double dyL1 = p2.getY() - p1.getY();
        double dxL2 = p3.getX() - p2.getX();
        double dyL2 = p3.getY() - p2.getY();
        double result = (dyL1 * dxL2) - (dyL2 * dxL1);
        if (result == 0) {
            return 0;
        } else if (result > 0) {
            return 1;
        } else {
            return 2;
        }
    }
    /**
     * This methd checks if a point is a part of a line segment.
     * It uses the logic that the sum of the distances from the point to the two ends of the segment is
     * equal to the length of the segment itself.
     * @param other the point in which we want to determine is part of the segement
     * @return true if the lengths are equal, false otherwise.
     */
    public boolean partOfSegment(Point other) {
        double segment1 = this.startPoint.distance(other);
        double segment2 = this.endPoint.distance(other);
        return segment1 + segment2 == this.length();
    }
    /**
     * This method determines which point is closer to the start point of a line segment.
     * @param rect the collision object which the line intersects with
     * @return the closest point found, if exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //check if there is a list of points
        java.util.List<Point> temp = rect.intersectionPoints(new Line(this.start(), this.end()));
        if (temp.isEmpty()) {
            return null;
        }
        Point start = this.start();
        //Sort the points
        Point closestPoint = temp.get(0);
        //Compare each point and if you find any smaller change it
        for (int i = 1; i < temp.size(); i++) {
            if (start.distance(temp.get(i)) < start.distance(closestPoint)) {
                closestPoint = temp.get(i);
            }
        }
        return closestPoint;
    }
}
