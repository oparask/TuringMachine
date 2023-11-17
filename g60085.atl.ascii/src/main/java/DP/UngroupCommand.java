package DP;

import model.Drawing;
import model.Group;
import model.Shape;
/**
 * The UngroupCommand class represents a command to ungroup a previously grouped shape (Group) within a drawing.
 * It implements the Command interface and provides methods to execute and unexecute the ungroup operation.
 */
public class UngroupCommand implements Command {
    /** The drawing on which the ungroup operation is performed. */
    private final Drawing drawing;

    /** The index of the grouped shape to be ungrouped. */
    private final int groupIndex;

    /** The group to be ungrouped. */
    private final Group group;

    /**
     * Constructs an UngroupCommand with the specified drawing and group index.
     *
     * @param drawing    The drawing on which the ungroup operation is performed.
     * @param groupIndex The index of the grouped shape to be ungrouped.
     */
    public UngroupCommand(Drawing drawing, int groupIndex) {
        this.drawing = drawing;
        this.groupIndex = groupIndex;
        this.group = (Group) drawing.getShape(groupIndex);
    }

    /**
     * Executes the command by ungrouping the specified group within the drawing.
     */
    @Override
    public void execute() {
        // Delete the grouped shape from the drawing
        drawing.deleteShape(group);

        // Add each shape from the group back to the drawing
        for (Shape shape : group.getShapes()) {
            drawing.addShape(shape);
        }
    }

    /**
     * Unexecutes the command by regrouping the shapes and adding the group back to the drawing.
     */
    @Override
    public void unexecute() {
        // Delete each shape from the group from the drawing
        for (Shape shape : group.getShapes()) {
            drawing.deleteShape(shape);
        }

        // Add the group back to the drawing
        drawing.addShape(groupIndex, group);
    }
}
