package model;

/**
 * The Point class represents a point with X and Y coordinates in a two-dimensional space.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a Point with the specified X and Y coordinates.
     *
     * @param x The X-coordinate of the point.
     * @param y The Y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Point by copying the coordinates of another Point.
     *
     * @param p The Point to copy coordinates from.
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Retrieves the X-coordinate of the point.
     *
     * @return The X-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieves the Y-coordinate of the point.
     *
     * @return The Y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Moves the point by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the point.
     * @param dy The vertical distance to move the point.
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other Point to calculate the distance to.
     * @return The Euclidean distance between this point and the other point.
     */
    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow((other.x - this.x), 2) + Math.pow((other.y - this.y), 2));
    }
}



