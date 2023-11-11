package model;

public class Line extends ColoredShape {
    Point a;
    Point b;
    double coeffDir;

    public Line (Point a, Point b, char color){
        super(color);
        // Using defensive copying
        this.a = new Point(a);
        this.b = new Point(b);
        this.coeffDir = (b.getY()-a.getY())/(b.getX()-a.getX());
    }

    @Override
    public boolean isInside(Point p) {
        double distance = (Math.abs(coeffDir*p.getX() - p.getY()-coeffDir*a.getX()+a.getY()))
                /(Math.sqrt(Math.pow(coeffDir, 2) +1));
        return distance < 0.5;
    }

    @Override
    public void move(double dx, double dy) {
        this.a.move(dx, dy);
        this.b.move(dx, dy);
    }


    /**
     * Returns a string representation of the line.
     *
     * @return The string "line".
     */
    @Override
    public String toString() {
        return "line";
    }
}
