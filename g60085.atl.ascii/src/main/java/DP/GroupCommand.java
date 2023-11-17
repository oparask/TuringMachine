package DP;

import model.Drawing;
import model.Group;
import model.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * The GroupCommand class represents a command to group a set of shapes into a new group.
 * It implements the Command interface and provides methods to execute and unexecute the group operation.
 */
public class GroupCommand implements Command {
    /**
     * The drawing on which the group operation is performed.
     */
    private final Drawing drawing;

    /**
     * The newly created group during the execute operation.
     */
    private Group group;

    /**
     * The list of shapes to be grouped.
     */
    private final List<Shape> shapes;

    /**
     * The list of shape indexes before the group operation for unexecution.
     */
    private final List<Integer> shapeIndexes;

    /**
     * Constructs a GroupCommand with the specified drawing and list of shapes to be grouped.
     *
     * @param drawing The drawing on which the group operation is performed.
     * @param shapes  The list of shapes to be grouped.
     */
    public GroupCommand(Drawing drawing, List<Shape> shapes) {
        this.drawing = drawing;
        this.group = null;
        this.shapes = shapes;
        this.shapeIndexes = new ArrayList<>();
        for (Shape shape : shapes) {
            shapeIndexes.add(drawing.getShapes().indexOf(shape));
        }
    }

    /**
     * Executes the command by creating a new group and adding the shapes to it.
     */
    @Override
    public void execute() {
        // Initialize the group
        this.group = new Group(shapes.get(0).getColor());
        for (Shape shape : shapes) {
            group.addShape(shape);
        }

        // Remove shapes that were grouped from the list
        for (Shape shape : shapes) {
            drawing.deleteShape(shape);
        }

        // Add the group to the drawing shapes list
        this.drawing.addShape(group);
    }

    /**
     * Unexecutes the command by removing the group and adding the individual shapes back to the drawing.
     */
    @Override
    public void unexecute() {
        // Add the shapes back to the drawing
        for (int i = 0; i < shapes.size(); i++) {
            drawing.addShape(shapeIndexes.get(i), shapes.get(i));
        }

        // Delete the group from the drawing
        drawing.deleteShape(group);
    }
}
