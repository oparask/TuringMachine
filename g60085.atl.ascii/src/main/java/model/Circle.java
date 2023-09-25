package model;

public class Circle implements Shape {

    private double radius;
    private Point center;

    public Circle(Point center, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive, received: " + radius);
        }

        this.center = new Point(center); //copie défensiveplus elegante
    }


    void move(double dx, double dy){

    }
    boolean isInside(Point p){

    }

    char getColor(); {

    }

    void setColor(char color){

    }
}
