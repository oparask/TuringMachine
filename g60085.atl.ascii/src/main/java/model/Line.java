package model;
/**
 * The Line class represents a colored line and extends the ColoredShape class.
 */
public class Line extends ColoredShape {
    /** The starting Point (a) of the line. */
    private Point a;

    /** The ending Point (b) of the line. */
    private Point b;

    /** The slope of the line. */
    private double slope;

    /**
     * Constructs a Line with the specified starting and ending points, and color.
     *
     * @param a The starting Point of the line.
     * @param b The ending Point of the line.
     * @param color The character representing the color of the line, e.g., 'c'.
     */
    public Line(Point a, Point b, char color) {
        super(color);
        // Using defensive copying
        this.a = new Point(a);
        this.b = new Point(b);
        this.slope = (b.getY() - a.getY()) / (b.getX() - a.getX());
    }

    //For tests
    /**
     * Returns the Point object representing point A.
     *
     * @return The Point object representing point A.
     */
    public Point getA() {
        return a;
    }

    //For tests
    /**
     * Returns the Point object representing point B.
     *
     * @return The Point object representing point B.
     */
    public Point getB() {
        return b;
    }

    /**
     * Checks whether a given Point is approximately on the line.
     *
     * @param p The Point to be checked.
     * @return true if the Point is approximately on the line, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        double distance = (Math.abs(slope * p.getX() - p.getY() - slope * a.getX() + a.getY()))
                / (Math.sqrt(Math.pow(slope, 2) + 1));
        return distance < 0.5;
    }

    /**
     * Moves the line by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the line.
     * @param dy The vertical distance to move the line.
     */
    @Override
    public void move(double dx, double dy) {
        this.a.move(dx, dy);
        this.b.move(dx, dy);
    }

    /**
     * Returns a string representation of the line.
     *
     * @return The string representation of the line.
     */
    @Override
    public String toString() {
        return "line: (a" + a.toString() + ", b" + b.toString() + ", color-> " + getColor() + ")";
    }
}
