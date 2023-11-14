package model;

import java.util.ArrayList;
import java.util.List;

public class Group extends ColoredShape {
    private List<Shape> shapes;

    public Group(char color) {
        super(color);
        this.shapes = new ArrayList<>();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    @Override
    public void move(double dx, double dy) {
        for (Shape shape : this.shapes) {
            shape.move(dx, dy);
        }
    }

    @Override
    public boolean isInside(Point p) {
        for (Shape shape : this.shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "";
        for (Shape shape : shapes) {
            string += shape.toString() + " ";
        }
        return "Group: [ " + string + "]";
    }
}
