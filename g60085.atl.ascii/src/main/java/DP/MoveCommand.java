package DP;

import model.Drawing;

/**
 * The MoveCommand class represents a command to move a shape within a drawing by a specified displacement.
 * It implements the Command interface and provides methods to execute and unexecute the move operation.
 */
public class MoveCommand implements Command {
    /**
     * The drawing on which the move operation is performed.
     */
    private final Drawing drawing;

    /**
     * The index of the shape to be moved.
     */
    private final int shapeIndex;

    /**
     * The horizontal displacement for the move operation.
     */
    private final double dx;

    /**
     * The vertical displacement for the move operation.
     */
    private final double dy;

    /**
     * Constructs a MoveCommand with the specified drawing, shape index, and displacement values.
     *
     * @param drawing    The drawing on which the move operation is performed.
     * @param shapeIndex The index of the shape to be moved.
     * @param dx         The horizontal displacement for the move operation.
     * @param dy         The vertical displacement for the move operation.
     */
    public MoveCommand(Drawing drawing, int shapeIndex, double dx, double dy) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Executes the command by moving the shape within the drawing by the specified displacement.
     */
    @Override
    public void execute() {
        drawing.moveShape(shapeIndex, dx, dy);
    }

    /**
     * Unexecutes the command by moving the shape in the opposite direction to undo the previous move.
     */
    @Override
    public void unexecute() {
        drawing.moveShape(shapeIndex, -dx, -dy);
    }
}

