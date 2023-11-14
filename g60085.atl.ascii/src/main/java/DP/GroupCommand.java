package DP;

import model.Drawing;
import model.Group;
import model.Shape;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements Command {
    Drawing drawing;
    Group group;
    List<Shape> shapes;
    List<Integer> shapeIndexes;

    public GroupCommand(Drawing drawing, List<Integer> shapeIndexes) {
        this.drawing = drawing;
        this.group = null;
        this.shapes = new ArrayList<>();
        this.shapeIndexes = shapeIndexes;
    }

    @Override
    public void execute() {
        //Initialise the shapes list
        for (Integer i : shapeIndexes) {
            shapes.add(drawing.getShape(i));
        }

        //Remove shapes that were grouped from the list
        for (Shape shape : shapes) {
            drawing.deleteShape(shape);
        }

        //Initialize the group
        this.group = new Group(shapes.get(0).getColor());
        group.addShapes(shapes);

        //Add the group to the drawing shapes list
        drawing.addShape(group);

    }

    @Override
    public void unexecute() {
        drawing.deleteShape(group);

        //Add the shapes in the shapes list
        for (int i = 0; i < shapeIndexes.size(); i++) {
            drawing.addShape(shapeIndexes.get(i), shapes.get(i));
        }
        group.getShapes().clear();
    }
}
