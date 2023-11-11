package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public void newCircle(double x, double y, double radius, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
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
    public void newRectangle(double x, double y, double width, double height, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The dimensions must be positive.");
        }
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
    public void newSquare(double x, double y, double side, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        if (side <= 0) {
            throw new IllegalArgumentException("The side length of the square must be positive.");
        }
        validateColor(color);

        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    public void newLine(double aX, double aY, double bX, double bY, char color) throws IllegalArgumentException {
        validateCoordinates(aX, aY);
        validateCoordinates(bX, bY);
        validateColor(color);
        drawing.addShape(new Line(new Point(aX, aY), new Point(bX, bY), color));
    }

    public void newGroup(List<Integer> shapeIndex, char color) throws IllegalArgumentException {
        Collections.sort(shapeIndex);
        for (int i = 0; i < shapeIndex.size() - 1; i++) {
            if (Objects.equals(shapeIndex.get(i), shapeIndex.get(i + 1))) {
                shapeIndex.remove(i + 1);
                i--;
            }
        }
        for (int i : shapeIndex) {
            if (i < 0 || i >= drawing.getShapes().size()) {
                throw new IllegalArgumentException("Invalid shape index.");
            }
        }
        List<Shape> groupShapes = new ArrayList<>();
        for (Integer i : shapeIndex) {
            groupShapes.add(drawing.getShapes().get(i));
        }

        Group group = new Group(color);
        group.addShapes(groupShapes);

        drawing.addShape(group);

    }

    public void setColor(int indexShape, char color) throws IllegalArgumentException {
        validateColor(color);
        this.drawing.getShapes().get(indexShape).setColor(color);
    }

    public void deleteShape(int shapeIndex) throws IllegalArgumentException {
        if (shapeIndex < 0 || shapeIndex >= drawing.getShapes().size()) {
            throw new IllegalArgumentException("You must specify an index from the shape list.");
        }
        drawing.deleteShape(shapeIndex);
    }

    public void moveShape(int shapeIndex, double dx, double dy)  throws IllegalArgumentException {
        if (shapeIndex < 0 || shapeIndex >= drawing.getShapes().size()) {
            throw new IllegalArgumentException("You must specify an index from the shape list.");
        }
        drawing.getShapes().get(shapeIndex).move(dx, dy);
    }

    /**
     * Validates the provided coordinates to ensure they are within the bounds of the drawing area.
     *
     * @param x The X-coordinate to be validated.
     * @param y The Y-coordinate to be validated.
     * @throws IllegalArgumentException if the coordinates are not positive or are outside the bounds of the drawing area.
     */
    private void validateCoordinates(double x, double y) throws IllegalArgumentException {
        if (x < 0 || y < 0 || x > drawing.getWidth() || y > drawing.getHeight()) {
            throw new IllegalArgumentException("The coordinates must be positive and within the bounds of the drawing.");
        }
    }

    /**
     * Validates the provided color to ensure it is a valid color identifier.
     *
     * @param color The character representing the color to be validated (e.g., 'c').
     * @throws IllegalArgumentException if the color is not a valid letter of the alphabet.
     */
    private void validateColor(char color) throws IllegalArgumentException {
        if (!Character.isLetter(color)) {
            throw new IllegalArgumentException("Invalid color. The color must be a letter of the alphabet.");
        }
    }


    /**
     * Generates an ASCII representation of the drawing.
     *
     * @return A string containing the ASCII representation of the drawing.
     */
    public String asASCII() {
        StringBuilder colorShapes = new StringBuilder();
        for (int y = 0; y <= drawing.getHeight(); y++) { // Traverse the height (vertical)
            for (int x = 0; x <= drawing.getWidth(); x++) { // Traverse the width (horizontal)
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
