package DP;

import model.Drawing;

public class MoveCommand implements Command{
    Drawing drawing;
    int shapeIndex;
    double dx, dy;

    public MoveCommand(Drawing drawing, int shapeIndex, double dx, double dy) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        drawing.moveShape(shapeIndex, dx, dy);

    }

    @Override
    public void unexecute() {

    }
}
