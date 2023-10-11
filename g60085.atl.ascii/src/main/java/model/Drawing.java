package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Drawing class represents a collection of shapes within a specified width and height.
 */
public class Drawing {
    private List<Shape> shapes;
    private int height;
    private int width;

    /**
     * Constructs a Drawing with default width and height (50x50).
     */
    public Drawing() {
        this(50, 50);
    }

    /**
     * Constructs a Drawing with the specified width and height.
     *
     * @param width  The width of the drawing area.
     * @param height The height of the drawing area.
     */
    public Drawing(int width, int height) {
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
