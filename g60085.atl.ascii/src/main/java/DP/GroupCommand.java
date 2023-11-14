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

    public GroupCommand(Drawing drawing, List<Shape> shapes) {
        this.drawing = drawing;
        this.group = null;
        this.shapes = shapes;
        this.shapeIndexes = new ArrayList<>();
        for(Shape shape : shapes){
            shapeIndexes.add(drawing.getShapes().indexOf(shape));
        }
    }

    @Override
    public void execute() {
        //Initialize the group
        this.group = new Group(shapes.get(0).getColor());
        for(Shape shape : shapes ){
            group.addShape(shape);
        }

        //Remove shapes that were grouped from the list
        for (Shape shape : shapes) {
            drawing.deleteShape(shape);
        }

        //Add the group to the drawing shapes list
        this.drawing.addShape(group);

    }

    @Override
    public void unexecute() {
        //Add the shapes in the shapes list
        for (int i = 0; i < shapes.size(); i++) {
            drawing.addShape(shapeIndexes.get(i), shapes.get(i));
        }

        drawing.deleteShape(group);
    }
}
