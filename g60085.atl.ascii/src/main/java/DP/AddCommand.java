package DP;

import model.Drawing;
import model.Shape;
/**
 * The AddCommand class represents a command to add a shape to a drawing.
 * It implements the Command interface and provides methods to execute and unexecute the addition operation.
 */
public class AddCommand implements Command {
    /** The drawing to which the shape will be added. */
    private final Drawing drawing;

    /** The shape to be added to the drawing. */
    private final Shape shape;

    /**
     * Constructs an AddCommand with the specified drawing and shape.
     *
     * @param drawing The drawing to which the shape will be added.
     * @param shape   The shape to be added to the drawing.
     */
    public AddCommand(Drawing drawing, Shape shape) {
        this.drawing = drawing;
        this.shape = shape;
    }

    /**
     * Executes the command by adding the shape to the drawing.
     */
    @Override
    public void execute() {
        drawing.addShape(shape);
    }

    /**
     * Unexecutes the command by deleting the previously added shape from the drawing.
     */
    @Override
    public void unexecute() {
        drawing.deleteShape(shape);
    }
}
