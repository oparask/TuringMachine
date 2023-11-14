package DP;

import model.Drawing;
import model.Group;
import model.Shape;

public class UngroupCommand implements Command{
    Drawing drawing;
    int groupIndex;
    Group group;

    public UngroupCommand(Drawing drawing, int groupIndex) {
        this.drawing = drawing;
        this.groupIndex = groupIndex;
        this.group = (Group) drawing.getShape(groupIndex);
    }

    @Override
    public void execute() {
        drawing.deleteShape(group);

        for(Shape shape : group.getShapes()){
            drawing.addShape(shape);
        }
    }

    @Override
    public void unexecute() {
        for(Shape shape : group.getShapes()){
            drawing.deleteShape(shape);
        }
        drawing.addShape(groupIndex, group);
    }
}
