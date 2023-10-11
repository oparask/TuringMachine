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
     */
    public AsciiPaint(int width, int height) {
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
     */
    public void newCircle(int x, int y, double radius, char color) {
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
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }

    /**
     * Adds a new square shape to the drawing.
     *
     * @param x     The x-coordinate of the square's upper-left corner.
     * @param y     The y-coordinate of the square's upper-left corner.
     * @param side  The length of each side of the square.
     * @param color The color character for the square.
     */
    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y), side, color));
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
                Shape shape = drawing.getShapeAt(new Point(x, drawing.getHeight()- y));
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
