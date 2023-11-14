package model;

/**
 * The Square class represents a colored square and extends the Rectangle class.
 */
public class Square extends Rectangle {

    /**
     * Constructs a Square with the specified upper-left point, side length, and color.
     *
     * @param upperLeft The upper-left Point of the square.
     * @param side The length of each side of the square (must be positive).
     * @param color The character representing the color of the square, e.g., 'c'.
     * @throws IllegalArgumentException if the side length is not positive.
     */
    public Square(Point upperLeft, double side, char color) throws IllegalArgumentException{
        super(upperLeft, side, side, color);
    }

    /**
     * Returns a string representation of the square.
     *
     * @return The string "square".
     */
    @Override
    public String toString() {
        return "square: [upperLeft point: "+this.getUpperLeft()+", side: "+this.getHeight()+"]";
    }
}
