package DP;

import model.Drawing;
import model.Shape;
/**
 * The DeleteCommand class represents a command to delete a shape from a drawing.
 * It implements the Command interface and provides methods to execute and unexecute the delete operation.
 */
public class DeleteCommand implements Command {
    /** The drawing from which the shape will be deleted. */
    private final Drawing drawing;

    /** The index of the shape to be deleted. */
    private final int shapeIndex;

    /** The shape that was deleted during the execute operation. */
    private Shape shape;

    /**
     * Constructs a DeleteCommand with the specified drawing and shape index.
     *
     * @param drawing    The drawing from which the shape will be deleted.
     * @param shapeIndex The index of the shape to be deleted.
     */
    public DeleteCommand(Drawing drawing, int shapeIndex) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
    }

    /**
     * Executes the command by deleting the shape from the drawing.
     */
    @Override
    public void execute() {
        shape = drawing.getShape(shapeIndex);
        drawing.deleteShape(shape);
    }

    /**
     * Unexecutes the command by adding the deleted shape back to the drawing.
     */
    @Override
    public void unexecute() {
        drawing.addShape(shapeIndex, shape);
    }
}

