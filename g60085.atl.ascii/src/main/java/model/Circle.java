package model;

/**
 * The Circle class represents a colored circle and extends the ColoredShape class.
 * It provides methods to construct a circle, check if a point is inside the circle, move the circle,
 * and obtain a string representation of the circle.
 */
public class Circle extends ColoredShape {
    /**
     * The radius of the circle.
     */
    private double radius;

    /**
     * The center Point of the circle.
     */
    private Point center;

    /**
     * Constructs a Circle with the specified center, radius, and color.
     *
     * @param center The center Point of the circle.
     * @param radius The radius of the circle (must be positive and less than 25).
     * @param color  The character representing the color of the circle, e.g., 'c'.
     * @throws IllegalArgumentException if the radius is not within the valid range.
     */
    public Circle(Point center, double radius, char color) throws IllegalArgumentException {
        super(color);
        if (radius <= 0 || radius >= 25) {
            throw new IllegalArgumentException("Radius must be positive and below 25, received: " + radius);
        }
        this.radius = radius;
        this.center = new Point(center); // Using defensive copying
    }

    /**
     * Returns the Point object representing the center of the shape.
     *
     * @return The Point object representing the center of the shape.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Checks whether a given Point is inside the circle.
     *
     * @param p The Point to be checked.
     * @return true if the Point is inside the circle, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        return this.center.distanceTo(p) <= this.radius;
    }

    /**
     * Moves the circle by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the circle.
     * @param dy The vertical distance to move the circle.
     */
    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    /**
     * Returns a string representation of the circle.
     *
     * @return A formatted string representing the circle's radius, center, and color.
     */
    @Override
    public String toString() {
        return "circle: radius-> " + radius + ", center " + center.toString() + ", color-> " + getColor();
    }
}

