package model;

public class Rectangle extends ColoredShape {

    private Point upperLeft;
    private double width;
    private double height;

    public Rectangle(Point upperLeft, double width ,double height, char color) {
        super(color);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid width or height");
        }
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isInside(Point p) {
        if (p.getX() >= this.upperLeft.getX() && p.getX() < (this.upperLeft.getX() + width)
                && p.getY() >= this.upperLeft.getY() && p.getY() < (this.upperLeft.getY() + height)) {
            return true;
        }
        return false;
    }


    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }

    @Override
    public String toString() {
        return "rectangle";
    }
}
