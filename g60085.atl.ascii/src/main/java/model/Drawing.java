package model;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private List<Shape> shapes;
    private int height;
    private int width;

    public Drawing() {
        this(50, 50);

    }

    public Drawing(int width, int height) {
        this.shapes = new ArrayList<>();
        this.height = height;
        this.width = width;

    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public Shape getShapeAt(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    int getHeight() {
        return this.height;
    }

    int getWidth() {
        return this.width;
    }
}
