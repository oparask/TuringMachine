package DP;

import model.Drawing;
import model.Group;
import model.Shape;
/**
 * The ColorCommand class represents a command to change the color of a shape in a drawing.
 * It implements the Command interface and provides methods to execute and unexecute the color change operation.
 */
public class ColorCommand implements Command {
    /** The drawing in which the shape's color will be changed. */
    private final Drawing drawing;

    /** The index of the shape whose color will be changed. */
    private final int shapeIndex;

    /** The new color to be set for the shape. */
    private final char color;

    /** The previous color of the shape before the color change. */
    private final char previousColor;

    /**
     * Constructs a ColorCommand with the specified drawing, shape index, and new color.
     *
     * @param drawing    The drawing in which the shape's color will be changed.
     * @param shapeIndex The index of the shape whose color will be changed.
     * @param color      The new color to be set for the shape.
     */
    public ColorCommand(Drawing drawing, int shapeIndex, char color) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
        this.color = color;

        // Store the previous color of the shape
        if (drawing.getShape(shapeIndex) instanceof Group) {
            Shape firstElGroup = ((Group) drawing.getShape(shapeIndex)).getShapes().get(0);
            this.previousColor = firstElGroup.getColor();
        } else {
            this.previousColor = drawing.getShape(shapeIndex).getColor();
        }
    }

    /**
     * Executes the command by changing the color of the shape in the drawing.
     */
    @Override
    public void execute() {
        drawing.changeShapeColor(shapeIndex, color);
    }

    /**
     * Unexecutes the command by reverting the color of the shape to its previous color.
     */
    @Override
    public void unexecute() {
        drawing.changeShapeColor(shapeIndex, previousColor);
    }
}

