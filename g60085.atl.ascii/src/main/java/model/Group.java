package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Group class represents a grouped shape containing a collection of individual shapes.
 * It extends the ColoredShape class and provides methods to manage the group and perform operations on its shapes.
 */
public class Group extends ColoredShape {
    /** The list of shapes contained within the group. */
    private final List<Shape> shapes;

    /**
     * Constructs a Group with the specified color.
     *
     * @param color The color character representing the color of the group.
     */
    public Group(char color) {
        super(color);
        this.shapes = new ArrayList<>();
    }

    /**
     * Gets the list of shapes contained within the group.
     *
     * @return The list of shapes in the group.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * Adds a shape to the group.
     *
     * @param shape The shape to be added to the group.
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    /**
     * Moves the entire group by the specified displacement values.
     *
     * @param dx The change in the x-coordinate (horizontal displacement).
     * @param dy The change in the y-coordinate (vertical displacement).
     */
    @Override
    public void move(double dx, double dy) {
        for (Shape shape : this.shapes) {
            shape.move(dx, dy);
        }
    }

    /**
     * Checks if a point is inside any of the shapes in the group.
     *
     * @param p The point to be checked.
     * @return true if the point is inside any of the shapes in the group, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        for (Shape shape : this.shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the group, including its color and a list of contained shapes.
     *
     * @return A string representation of the group.
     */
    @Override
    public String toString() {
        String string = "Group: color: " + getColor() + "\n          shapes: - " + shapes.get(0);

        for (int i = 1; i < shapes.size(); i++) {
            string += "\n                  - " + shapes.get(i).toString();
        }
        return string;
    }
}

