package model;

import DP.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The AsciiPaint class serves as the model's facade and contains methods for modifying the model,
 * including adding shapes, moving shapes, changing colors, group and ungroup shapes and managing commands. The facade also
 * provides methods for retrieving information necessary for display and interacting with the drawing.
 */
public class AsciiPaint {

    /**The Drawing object representing the canvas of the AsciiPaint instance. */
    private Drawing drawing;

    /**The CommandManager responsible for managing commands (undo and redo) in the AsciiPaint instance. */
    private CommandManager commandManager;

    /**
     * Constructs an AsciiPaint instance with a default drawing.
     */
    public AsciiPaint() {
        drawing = new Drawing();
        commandManager = new CommandManager();
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
     *                                  The radius must be positive, and the color must be valid.
     */
    public void newCircle(double x, double y, double radius, char color) throws IllegalArgumentException {
        validateCoordinates(x, y);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        validateColor(color);

        Shape shape = new Circle(new Point(x, y), radius, color);

        // Create the command and ask commandManager to execute it
        AddCommand command = new AddCommand(drawing, shape);
        commandManager.execute(command);
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

        Shape shape = new Rectangle(new Point(x, y), width, height, color);

        // Create the command and ask commandManager to execute it
        AddCommand command = new AddCommand(drawing, shape);
        commandManager.execute(command);
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

        Shape shape = new Square(new Point(x, y), side, color);

        // Create the command and ask commandManager to execute it
        AddCommand command = new AddCommand(drawing, shape);
        commandManager.execute(command);
    }

    /**
     * Creates a new line and adds it to the drawing.
     * <p>
     * This method takes the coordinates of two points (a and b), validates the coordinates and color,
     * creates a new Line object, and then adds it to the drawing. The operation is encapsulated in an
     * AddCommand, which is executed by the command manager.
     *
     * @param aX    The x-coordinate of point a.
     * @param aY    The y-coordinate of point a.
     * @param bX    The x-coordinate of point b.
     * @param bY    The y-coordinate of point b.
     * @param color The color of the new line.
     * @throws IllegalArgumentException If the coordinates or color are not valid.
     */
    public void newLine(double aX, double aY, double bX, double bY, char color) throws IllegalArgumentException {
        validateCoordinates(aX, aY);
        validateCoordinates(bX, bY);
        validateColor(color);

        Shape shape = new Line(new Point(aX, aY), new Point(bX, bY), color);

        // Create the command and ask commandManager to execute it
        AddCommand command = new AddCommand(drawing, shape);
        commandManager.execute(command);
    }

    /**
     * Moves a shape in the drawing by the specified delta values.
     * <p>
     * This method takes the index of a shape, validates the index, and then
     * creates and executes a MoveCommand to move the shape by the specified delta values.
     *
     * @param shapeIndex The index of the shape to be moved.
     * @param dx         The change in the x-coordinate (horizontal displacement).
     * @param dy         The change in the y-coordinate (vertical displacement).
     * @throws IllegalArgumentException If the shape index is invalid (negative or beyond the drawing's size).
     */
    public void moveShape(int shapeIndex, double dx, double dy) throws IllegalArgumentException {
        if (shapeIndex < 0 || shapeIndex >= drawing.getShapes().size()) {
            throw new IllegalArgumentException("You must specify an index from the shape list.");
        }

        // Create the command and ask commandManager to execute it
        MoveCommand command = new MoveCommand(drawing, shapeIndex, dx, dy);
        commandManager.execute(command);
    }

    /**
     * Changes the color of a shape in the drawing.
     * <p>
     * This method takes the index of a shape, validates the color, and then
     * creates and executes a ColorCommand to change the color of the shape.
     *
     * @param shapeIndex The index of the shape to have its color changed.
     * @param color      The new color for the shape.
     * @throws IllegalArgumentException If the shape index is invalid (negative or beyond the drawing's size)
     *                                  or if the provided color is not valid.
     */
    public void changeShapeColor(int shapeIndex, char color) throws IllegalArgumentException {
        validateColor(color);

        // Create the command and ask commandManager to execute it
        ColorCommand command = new ColorCommand(drawing, shapeIndex, color);
        commandManager.execute(command);
    }

    /**
     * Deletes a shape from the drawing.
     * <p>
     * This method takes the index of a shape, validates the index, and then
     * creates and executes a DeleteCommand to remove the shape from the drawing.
     *
     * @param shapeIndex The index of the shape to be deleted.
     * @throws IllegalArgumentException If the shape index is invalid (negative or beyond the drawing's size).
     */
    public void deleteShape(int shapeIndex) throws IllegalArgumentException {
        if (shapeIndex < 0 || shapeIndex >= drawing.getShapes().size()) {
            throw new IllegalArgumentException("You must specify an index from the shape list.");
        }

        // Create the command and ask commandManager to execute it
        DeleteCommand command = new DeleteCommand(drawing, shapeIndex);
        commandManager.execute(command);
    }

    /**
     * Groups a set of shapes based on their indexes.
     * <p>
     * This method takes a list of shape indexes, removes duplicates, validates the indexes,
     * retrieves the corresponding shapes, and then creates and executes a GroupCommand
     * to group the selected shapes.
     *
     * @param shapeIndexes The list of shape indexes to be grouped.
     * @throws IllegalArgumentException If any of the shape indexes are invalid (negative or beyond the drawing's size).
     */
    public void groupShapes(List<Integer> shapeIndexes) throws IllegalArgumentException {
        // Remove duplicates
        Set<Integer> uniqueSet = new HashSet<>();
        List<Integer> shapeIndexBis = new ArrayList<>();

        for (Integer shapeIndex : shapeIndexes) {
            if (uniqueSet.add(shapeIndex)) {
                //If the element is not already in the list, we add it to the resulting list.
                shapeIndexBis.add(shapeIndex);
            }
        }

        // Check indexes
        for (Integer i : shapeIndexes) {
            if (i < 0 || i >= drawing.getShapes().size()) {
                throw new IllegalArgumentException("Invalid shape index.");
            }
        }

        // Search the shapes
        List<Shape> shapes = new ArrayList<>();
        for (Integer i : shapeIndexBis) {
            shapes.add(drawing.getShape(i));
        }

        // Create the command and ask commandManager to execute it
        GroupCommand groupCommand = new GroupCommand(drawing, shapes);
        commandManager.execute(groupCommand);
    }

    /**
     * Ungroups a previously grouped shape (Group).
     * <p>
     * This method takes the index of a grouped shape, validates the index, and then
     * creates and executes an UngroupCommand to ungroup the selected shape.
     *
     * @param groupIndex The index of the grouped shape to be ungrouped.
     * @throws IllegalArgumentException If the group index is invalid (negative, beyond the drawing's size, or not a Group).
     */
    public void ungroupShapes(int groupIndex) throws IllegalArgumentException {
        if (groupIndex < 0 || groupIndex >= drawing.getShapes().size() || !(drawing.getShapes().get(groupIndex) instanceof Group)) {
            throw new IllegalArgumentException("Invalid group index.");
        }

        // Create the command and ask commandManager to execute it
        UngroupCommand groupCommand = new UngroupCommand(drawing, groupIndex);
        commandManager.execute(groupCommand);
    }

    /**
     * Undoes the last executed command.
     * <p>
     * This method invokes the undo operation on the command manager,
     * allowing the reversal of the last executed command in the command history.
     * If there are no commands to undo, this method has no effect.
     */
    public void undo() {
        commandManager.undoM();
    }

    /**
     * Redoes the previously undone command.
     * <p>
     * This method invokes the redo operation on the command manager,
     * allowing the execution of the last undone command in the command history.
     * If there are no commands to redo, this method has no effect.
     */
    public void redo() {
        commandManager.redoM();
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
