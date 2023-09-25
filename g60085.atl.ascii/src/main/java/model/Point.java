package model;


public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) { //constructeur par copie
        this(p.x, p.y);
    }


    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }


    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow((other.x - this.x), 2) + Math.pow((other.y - this.y), 2));
    }
}


