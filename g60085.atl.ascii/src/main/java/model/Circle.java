package model;

public class Circle extends ColoredShape {

    private double radius;
    private Point center;

    public Circle(Point center, double radius, char color) {
        super(color);
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive, received: " + radius);
        }

        this.center = new Point(center); //copie défensiveplus elegante
    }

    @Override
    public boolean isInside(Point p){
        return p.distanceTo(this.center)<=this.radius;
    }
    public void move(double dx, double dy){
        center.move(dx, dy);
    }

}
