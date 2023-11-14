package DP;

import model.Drawing;
import model.Group;
import model.Shape;

public class ColorCommand implements Command{
    Drawing drawing;
    int shapeIndex;
    char color;
    char previousColor;

    public ColorCommand(Drawing drawing, int shapeIndex, char color) {
        this.drawing = drawing;
        this.shapeIndex = shapeIndex;
        this.color = color;

        if(drawing.getShape(shapeIndex) instanceof Group){
            Shape firstElGroup = ((Group) drawing.getShape(shapeIndex)).getShapes().get(0);
            this.previousColor = firstElGroup.getColor();
        } else {
            this.previousColor = drawing.getShape(shapeIndex).getColor();
        }
    }

    @Override
    public void execute() {
        drawing.changeShapeColor(shapeIndex, color);
    }

    @Override
    public void unexecute() {
        drawing.changeShapeColor(shapeIndex, previousColor);

    }
}
