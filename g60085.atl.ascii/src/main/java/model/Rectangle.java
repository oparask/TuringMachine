package model;

public class Rectangle extends ColoredShape{

    private Point bl; //bottom left corner
    private Point ur; //upper right corner

    public Rectangle(Point bottomLeft, Point upperRight, char color) {
        super(color);
        if (bottomLeft.getX() >= upperRight.getX() || bottomLeft.getY() >= upperRight.getY())
            throw new IllegalArgumentException(
                    "bottomLeft must be below and on the left of upperRight, received (bottomLeft - upperRight): "
                            + bottomLeft + "-" + upperRight);
        this.bl = new Point(bottomLeft);
        this.ur = new Point(upperRight);
    }

    @Override
    public boolean IsInside(Point p){

    }

    @Override
    public void move(double dx, double dy) {
        bl.move(dx, dy);
        ur.move(dx, dy);
    }




}
