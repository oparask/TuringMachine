package model;

/**
 * The Shape interface represents a geometric shape and defines the expected behaviors for all shapes.
 */
public interface Shape {

    /**
     * Moves the shape by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the shape.
     * @param dy The vertical distance to move the shape.
     */
    void move(double dx, double dy);

    /**
     * Checks whether a given point is inside the shape.
     *
     * @param p The Point object to be checked.
     * @return true if the point is inside the shape, false otherwise.
     */
    boolean isInside(Point p);

    /**
     * Retrieves the color representation of the shape.
     *
     * @return A character representing the color of the shape, e.g., 'c'.
     */
    char getColor();

    /**
     * Sets the color of the shape to the specified character.
     *
     * @param color The character to set as the color of the shape.
     */
    void setColor(char color);



}


