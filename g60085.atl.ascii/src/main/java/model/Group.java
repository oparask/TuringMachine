package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group extends ColoredShape {
    private List<Shape> shapes;

    public Group(char color) {
        super(color);
        this.shapes = new ArrayList<>();
    }

    public void addShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void ungroup() {
        this.shapes.clear();
    }

    @Override
    public void move(double dx, double dy) {
        for(Shape shape : this.shapes){
            shape.move(dx, dy);
        }
    }

    @Override
    public boolean isInside(Point p) {
        for(Shape shape : this.shapes){
            if(shape.isInside(p)){
                return true;
            }
        }
        return false;
    }


}
