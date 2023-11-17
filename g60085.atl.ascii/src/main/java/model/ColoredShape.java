package model;
/**
 * The ColoredShape class is an abstract implementation of the Shape interface and provides functionality for shapes with colors.
 * It defines methods to construct a ColoredShape object, retrieve the color representation, and set the color of the shape.
 */
public abstract class ColoredShape implements Shape {

    /** The character representing the color of the shape. */
    private char color;

    /**
     * Constructs a ColoredShape object with the specified color.
     *
     * @param color The character representing the color of the shape (e.g., 'c').
     * @throws IllegalArgumentException if the provided character is not a valid color identifier.
     */
    public ColoredShape(char color) throws IllegalArgumentException {
        if (!Character.isLetter(color)) {
            throw new IllegalArgumentException("Invalid color. The color must be a letter of the alphabet.");
        }

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
