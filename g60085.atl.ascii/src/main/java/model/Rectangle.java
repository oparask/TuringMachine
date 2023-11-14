package model;

/**
 * The Rectangle class represents a colored rectangle and extends the ColoredShape class.
 */
public class Rectangle extends ColoredShape {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a Rectangle with the specified upper-left point, width, height, and color.
     *
     * @param upperLeft The upper-left Point of the rectangle.
     * @param width     The width of the rectangle (must be a positive value).
     * @param height    The height of the rectangle (must be a positive value).
     * @param color     The character representing the color of the rectangle, e.g., 'c'.
     * @throws IllegalArgumentException if the width or height is not positive.
     */
    public Rectangle(Point upperLeft, double width, double height, char color) throws IllegalArgumentException {
        super(color);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The width and height must be positive values.");
        }

        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }


    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    /**
     * Checks whether a given Point is inside the rectangle.
     *
     * @param p The Point to be checked.
     * @return true if the Point is inside the rectangle, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        return p.getX() >= this.upperLeft.getX() && p.getX() <=(this.upperLeft.getX() + width)
                && p.getY() >= this.upperLeft.getY() && p.getY() <= (this.upperLeft.getY() + height);
    }

    /**
     * Moves the rectangle by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the rectangle.
     * @param dy The vertical distance to move the rectangle.
     */
    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }

    /**
     * Returns a string representation of the rectangle.
     *
     * @return The string "rectangle".
     */
    @Override
    public String toString() {
        return "rectangle: [upperLeft point: "+upperLeft+", width: " + width+ ", height: "+height+"]";
    }
}