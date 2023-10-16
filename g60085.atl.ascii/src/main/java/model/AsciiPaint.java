package model;

/**
 * The AsciiPaint class serves as the model's facade and contains methods for modifying the model,
 * including adding shapes, moving shapes, and changing colors. The facade also provides methods for
 * retrieving information necessary for display.
 */
public class AsciiPaint {
    private Drawing drawing;

    /**
     * Constructs an AsciiPaint instance with a default drawing.
     */
    public AsciiPaint() {
        drawing = new Drawing();
    }

    /**
     * Constructs an AsciiPaint instance with a custom drawing size specified by width and height.
     *
     * @param width  The width of the drawing.
     * @param height The height of the drawing.
     * @throws IllegalArgumentException If the provided width or height is outside the valid range
     *                                  (width and height must be between 10 and 100, inclusive).
     */
    public AsciiPaint(int width, int height) throws IllegalArgumentException {
        if (width < 10 || height < 10 || width > 100 || height > 100) {
            throw new IllegalArgumentException("The width and height must be between 10 and 100, inclusive.");
        }

        drawing = new Drawing(width, height);
    }


    /**
     * Retrieves the current drawing instance.
     *
     * @return The Drawing object representing the current drawing.
     */
    public Drawing getDrawing() {
        return drawing;
    }

    /**
     * Adds a new circle shape to the drawing.
     *
     * @param x      The x-coordinate of the circle's center.
     * @param y      The y-coordinate of the circle's center.
     * @param radius The radius of the circle.
     * @param color  The color character for the circle.
     * @throws IllegalArgumentException If any of the provided parameters are invalid.
     */
    public void newCircle(int x, int y, double radius, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        validateRadius(radius);
        validateColor(color);

        drawing.addShape(new Circle(new Point(x, y), radius, color));
    }

    /**
     * Adds a new rectangle shape to the drawing.
     *
     * @param x      The x-coordinate of the rectangle's upper-left corner.
     * @param y      The y-coordinate of the rectangle's upper-left corner.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color  The color character for the rectangle.
     * @throws IllegalArgumentException If any of the provided parameters are invalid.
     */
    public void newRectangle(int x, int y, double width, double height, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        validateDimensions(width, height);
        validateColor(color);

        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }

    /**
     * Adds a new square shape to the drawing.
     *
     * @param x     The x-coordinate of the square's upper-left corner.
     * @param y     The y-coordinate of the square's upper-left corner.
     * @param side  The length of each side of the square.
     * @param color The color character for the square.
     * @throws IllegalArgumentException If any of the provided parameters are invalid.
     */
    public void newSquare(int x, int y, double side, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        validateSideLength(side);
        validateColor(color);

        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    private void validateCoordinates(int x, int y) {
        if (x <= 0 || y <= 0 || x >= drawing.getWidth() || y >= drawing.getHeight()) {
            throw new IllegalArgumentException("The coordinates must be positive and within the bounds of the drawing.");
        }
    }

    private void validateRadius(double radius) {
        if (radius <= 0 || radius >= 25) {
            throw new IllegalArgumentException("Radius must be positive and below 25, received: " + radius);
        }
    }

    private void validateDimensions(double width, double height) {
        if (width <= 0 || width > 25 || height <= 0 || height > 25) {
            throw new IllegalArgumentException("The dimensions must be positive and below 25.");
        }
    }

    private void validateSideLength(double side) {
        if (side <= 0 || side > 25) {
            throw new IllegalArgumentException("The side length of the square must be positive and below 25.");
        }
    }

    private void validateColor(char color) {
        if (!Character.isLetter(color)) {
            throw new InvalidColorException();
        }
    }


    /**
     * Generates an ASCII representation of the drawing.
     *
     * @return A string containing the ASCII representation of the drawing.
     */
    public String asASCII() {
        StringBuilder colorShapes = new StringBuilder();
        for (int y = 0; y < drawing.getHeight(); y++) { // Traverse the height (vertical)
            for (int x = 0; x < drawing.getWidth(); x++) { // Traverse the width (horizontal)
                Shape shape = drawing.getShapeAt(new Point(x, drawing.getHeight() - y));
                if (shape != null) {
                    colorShapes.append(shape.getColor());
                } else {
                    colorShapes.append(" ");
                }
            }
        }
        return colorShapes.toString();
    }
}
