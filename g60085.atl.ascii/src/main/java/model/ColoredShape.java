package model;

/**
 * The ColoredShape class is an abstract implementation of the Shape interface and provides functionality for shapes with colors.
 */
public abstract class ColoredShape implements Shape {

    private char color;

    /**
     * Constructs a ColoredShape object with the specified color.
     *
     * @param color The character representing the color of the shape, e.g., 'c'.
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * Retrieves the color representation of the shape.
     *
     * @return A character representing the color of the shape, e.g., 'c'.
     */
    @Override
    public char getColor() {
        return this.color;
    }

    /**
     * Sets the color of the shape to the specified character.
     *
     * @param color The character to set as the color of the shape.
     */
    @Override
    public void setColor(char color) {
        this.color = color;
    }
}
