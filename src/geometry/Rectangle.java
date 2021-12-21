package geometry;
import java.util.ArrayList;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Idan Ziv
 */
public class Rectangle {
    //Global Variables
    public static final java.awt.Color LINES_COLOR = Color.BLACK;
    //Fields
    private final Point upperLeftPoint;
    private final double width;
    private final double height;
    private final ArrayList<Line> shapeLines;
    private final ArrayList<Point> shapeEdges;
    private java.awt.Color color = Color.ORANGE;

    //Constructors
    /**
     * Creates a Rectangle object.
     * @param upperLeft the upper left edge of the shape
     * @param width of the shape
     * @param height of the shape
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
        this.shapeEdges = this.createShapeEdges();
        this.shapeLines = this.createShapeLines();
    }
    //Getters
    /**
     * @return the Rectangle's upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }
    /**
     * @return the width of the Rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return the height of the Rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return the Color of the Rectangle
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * @return a list of all the Rectangle's lines
     */
    public ArrayList<Line> getLines() {
        return this.shapeLines;
    }
    /**
     * @return a list of all the Rectangle's edges
     */
    public ArrayList<Point> getEdges() {
        return this.shapeEdges;
    }
    //Private Methods
    /**
     * This method creates the shape of the rectangle.
     * @return a list of The rectangles Edges
     */
    private ArrayList<Point> createShapeEdges() {
        ArrayList<Point> temp = new ArrayList<Point>();
        double x = this.upperLeftPoint.getX();
        double y = this.upperLeftPoint.getY();
        double w = this.getWidth();
        double h = this.getHeight();
        //the start point is always the upper left, so it is the first index;
        temp.add(this.getUpperLeft());
        Point upperRight = new Point(x + w, y);
        temp.add(upperRight);
        Point bottomRight = new Point(x + w, y + h);
        temp.add(bottomRight);
        Point bottomLeft = new Point(x, y + h);
        temp.add(bottomLeft);
        return temp;
    }
    /**
     * This method creates the Lines of the Rectangle.
     * @return a list of the rectangle's lines
     */
    private ArrayList<Line> createShapeLines() {
        ArrayList<Line> temp = new ArrayList<Line>();
        double x = this.upperLeftPoint.getX();
        double y = this.upperLeftPoint.getY();
        double w = this.getWidth();
        double h = this.getHeight();
        //Top line:
        Line topLine = new Line(x, y, x + w, y);
        temp.add(topLine);
        Line rightLine = new Line(x +  w, y, x + w, y + h);
        temp.add(rightLine);
        Line bottomLine = new Line(x + w, y + h, x, y + h);
        temp.add(bottomLine);
        Line leftLine = new Line(x, y + h, x, y);
        temp.add(leftLine);
        return temp;
    }
    //Public Methods
    /**
     * Draws the Rectangle upon a DrawSurface.
     * @param d the given DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int w = (int) this.getWidth();
        int h = (int) this.getHeight();
        d.setColor(LINES_COLOR);
        d.drawRectangle(x, y, w, h);
        d.setColor(this.getColor());
        d.fillRectangle(x, y, w, h);
    }
    /**
     * Given a line, the methods returns al the intersection points with the Rectangle.
     * @param line the line in question
     * @return a list of points, null if there are any
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> temp = new ArrayList<Point>();
        //You have 4 lines, get the array.
        ArrayList<Line> lines = this.getLines();
        //Check each line if intersection.
        for (Line l : lines) {
            //If there is an intersection point, add it to the list
            if (line.intersectionWith(l) != null) {
                Point p = line.intersectionWith(l);
                temp.add(p);
            }
        }
        return temp;
    }
    /**
     * This method checks if the object is intersecting with a line.
     * @param trajectory the line
     * @return the result of the test, true or false
     */
    public boolean isIntersectingWith(Line trajectory) {
        java.util.List<Point> temp = this.intersectionPoints(trajectory);
        if (temp.isEmpty()) {
            return false;
        }
        return true;
    }
    /**
     * Sets the color of the Rectangle.
     * @param c the desired color
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }
}
