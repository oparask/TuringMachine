package DP;

import model.Drawing;
import model.Shape;

public class DeleteCommand implements Command{
    Drawing drawing;
    Shape shape;
    int shapeIndex;

    public DeleteCommand(Drawing drawing, int shapeIndex) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
    }

    @Override
    public void execute() {
        shape = drawing.getShape(shapeIndex);
        drawing.deleteShape(shape);
    }

    @Override
    public void unexecute() {
        drawing.addShape(shapeIndex, shape);
    }
}
