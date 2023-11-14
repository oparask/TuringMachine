package DP;

import model.Drawing;
import model.Shape;

public class AddCommand implements Command {
    Drawing drawing;
    Shape shape;
    public AddCommand(Drawing drawing, Shape shape) {
        this.drawing = drawing;
        this.shape = shape;
    }

    @Override
    public void execute() {
        drawing.addShape(shape);
    }

    @Override
    public void unexecute() {
        drawing.deleteShape(shape);
    }

}
