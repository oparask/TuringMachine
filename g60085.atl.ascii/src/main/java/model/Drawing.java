package model;

import java.util.ArrayList;
import java.util.List;
/**
 * The Drawing class represents a collection of shapes within a specified width and height.
 * It provides methods to add, retrieve, delete, move, and change the color of shapes within the drawing.
 */
public class Drawing {
    /** The list of shapes in the drawing. */
    private List<Shape> shapes;

    /** The height of the drawing area. */
    private int height;

    /** The width of the drawing area. */
    private int width;

    /**
     * Constructs a Drawing with default width and height (50x50).
     */
    public Drawing() {
        this(50, 50);
    }

    /**
     * Constructs a Drawing with the specified width and height for the drawing area.
     *
     * @param width  The width of the drawing area (must be between 10 and 100, inclusive).
     * @param height The height of the drawing area (must be between 10 and 100, inclusive).
     * @throws IllegalArgumentException if the width or height is outside the valid range.
     */
    public Drawing(int width, int height) throws IllegalArgumentException {
        if (width < 10 || height < 10 || width > 100 || height > 100) {
            throw new IllegalArgumentException("The width and height must be between 10 and 100, inclusive.");
        }
        this.shapes = new ArrayList<>();
        this.height = height;
        this.width = width;
    }

    /**
     * Adds a shape to the drawing.
     *
     * @param shape The shape to be added.
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Adds a shape to the drawing at a specified index.
     *
     * @param shapeIndex The index at which the shape is to be added.
     * @param shape The shape to be added.
     */
    public void addShape(int shapeIndex, Shape shape) {
        shapes.add(shapeIndex, shape);
    }

    /**
     * Retrieves the shape at the specified index.
     *
     * @param shapeIndex The index of the shape to retrieve.
     * @return The Shape at the given index.
     */
    public Shape getShape(int shapeIndex) {
        return shapes.get(shapeIndex);
    }

    /**
     * Deletes a shape from the drawing.
     *
     * @param shape The shape to be deleted.
     */
    public void deleteShape(Shape shape) {
        shapes.remove(shape);
    }

    /**
     * Moves a shape within the drawing by the specified delta values.
     *
     * @param shapeIndex The index of the shape to be moved.
     * @param dx The change in the x-coordinate (horizontal displacement).
     * @param dy The change in the y-coordinate (vertical displacement).
     */
    public void moveShape(int shapeIndex, double dx, double dy) {
        shapes.get(shapeIndex).move(dx, dy);
    }

    /**
     * Changes the color of a shape within the drawing.
     *
     * @param shapeIndex The index of the shape to have its color changed.
     * @param color The new color for the shape.
     */
    public void changeShapeColor(int shapeIndex, char color) {
        shapes.get(shapeIndex).setColor(color);
    }

    /**
     * Retrieves the shape at the specified point, if any.
     *
     * @param p The Point to check for shapes.
     * @return The Shape at the given Point, or null if no shape is found.
     */
    public Shape getShapeAt(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Gets the height of the drawing area.
     *
     * @return The height of the drawing area.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets the width of the drawing area.
     *
     * @return The width of the drawing area.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the list of shapes in the drawing.
     *
     * @return A List of Shape objects in the drawing.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

}

