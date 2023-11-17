package model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsciiPaintTest {

    private AsciiPaint asciiPaint;

    @Before
    public void setUp() {
        // Initialize the AsciiPaint instance for testing
        asciiPaint = new AsciiPaint();
    }

    @org.junit.jupiter.api.Test
    void getDrawing() {
    }

    @Test
    public void testAsciiPaintWithValidSize() {
        assertDoesNotThrow(() -> new AsciiPaint(50, 50));
    }

    @Test
    public void testAsciiPaintWithInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(500, 50));
    }

    @Test
    public void testAsciiPaintWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(-500, 50));
    }

    @Test
    public void testNewCircle() {
        setUp();
        // Test adding a single circle
        asciiPaint.newCircle(5, 5, 3.0, 'O');
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());

        // Test adding multiple circles
        asciiPaint.newCircle(2, 2, 2.0, 'C');
        asciiPaint.newCircle(8, 8, 4.0, 'X');
        assertEquals(3, asciiPaint.getDrawing().getShapes().size());
    }


    @Test
    public void testNewCircleWithInvalidCoordinates() {
        setUp();
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(60, 60, 10.0, 'c'));
    }

    @Test
    public void testNewCircleWithNegativesCoordinates() {
        setUp();
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(-2, -2, 10.0, 'c'));
    }


    @Test
    public void testNewCircleWithZeroRadius() {
        setUp();
        // Test adding a circle with a zero radius
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(5, 5, 0.0, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }


    @Test
    public void testNewCircleWithNegativeRadius() {
        setUp();
        // Test adding a circle with a negative radius
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(5, 5, -2, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewCircleWithLargeRadius() {
        setUp();
        // Test adding a circle with a large radius
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(5, 5, 100, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewCircleWithValidColor() {
        setUp();
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(5, 5, 10, '0'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithValidParameters() {
        setUp();
        assertDoesNotThrow(() -> asciiPaint.newRectangle(10, 10, 20, 15, 'R'));
        // Test adding a rectangle with valid parameters
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithInvalidCoordinates() {
        setUp();
        // Test adding a rectangle with invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(-1, 10, 20, 15, 'R'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithInvalidDimensions() {
        setUp();
        // Test adding a rectangle with invalid dimensions
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(-5, 10, 30, 15, 'R'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithInvalidColor() {
        setUp();
        // Test adding a rectangle with an invalid color
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(10, 10, 20, 15, '1'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithValidParameters() {
        setUp();
        // Test adding a square with valid parameters
        assertDoesNotThrow(() -> asciiPaint.newSquare(10, 10, 15, 'S'));
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidCoordinates() {
        setUp();
        // Test adding a square with invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(0, -10, 15, 'S'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidSideLength() {
        setUp();
        // Test adding a square with an invalid side length
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(10, 10, -30, 'S'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidColor() {
        setUp();
        // Test adding a square with an invalid color
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(10, 10, 15, '1'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }


    //CREATE LINE
    @Test
    void validNewLine() {
        setUp();
        // Test adding a line
        asciiPaint.newLine(6, 6, 15, 15, 'L'); //droite croissante
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());

        // Test adding multiple lines
        asciiPaint.newLine(0, 0, 20, 20, 'C'); //limits
        asciiPaint.newLine(3, 8, 8, 3, 'X'); //droite décroissante
        assertEquals(3, asciiPaint.getDrawing().getShapes().size());

        assertDoesNotThrow(() -> asciiPaint.newLine(0, 0, 50, 50, 'R'));
    }

    @Test
    void invalidNewLine() {
        setUp();
        // Test adding a line with a negative X
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(-5, 5, 15, 15, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());

        // Test adding a line with a negative Y
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(5, -5, 15, 15, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());

        // Test adding a line with the coordinate X above drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(5, 5, 100, 10, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());


        // Test adding a line with the coordinate Y above drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(5, 5, 10, 100, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());

        // Test adding a line with the coordinate X above drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(5, 5, 51, 10, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());


        // Test adding a line with the coordinate Y above drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(5, 5, 10, 51, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());

        // Test adding a line with the coordinate X below drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(-1, 5, 50, 10, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());


        // Test adding a line with the coordinate Y below drawing limits
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newLine(-1, 5, 10, 50, 'O'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());


    }

    //MOVE A SHAPE
    @Test
    void validMoveRectangle() {
        setUp();

        // Test moving a rectangle
        Rectangle rectangle = new Rectangle(new Point(6, 6), 6, 4, 'R');
        asciiPaint.getDrawing().addShape(rectangle);
        asciiPaint.moveShape(0, 5, 5);
        assertEquals(11, rectangle.getUpperLeft().getX());
        assertEquals(11, rectangle.getUpperLeft().getY());
        asciiPaint.moveShape(0, -5, -5);
        assertEquals(6, rectangle.getUpperLeft().getX());
        assertEquals(6, rectangle.getUpperLeft().getY());
    }

    @Test
    void validMoveLine() {
        setUp();

        // Test moving a line
        Line line = new Line(new Point(6, 6), new Point(10, 10), 'l');
        asciiPaint.getDrawing().addShape(line);
        asciiPaint.moveShape(0, 5, 5);
        assertEquals(11, line.getA().getX());
        assertEquals(11, line.getA().getY());
        assertEquals(15, line.getB().getX());
        assertEquals(15, line.getB().getY());
        asciiPaint.moveShape(0, -5, -5);
        assertEquals(6, line.getA().getX());
        assertEquals(6, line.getA().getY());
        assertEquals(10, line.getB().getX());
        assertEquals(10, line.getB().getY());
    }

    @Test
    void validMoveSquare() {
        setUp();

        // Test moving a square
        Square square = new Square(new Point(6, 6), 6, 'S');
        asciiPaint.getDrawing().addShape(square);
        asciiPaint.moveShape(0, 5, 5);
        assertEquals(11, square.getUpperLeft().getX());
        assertEquals(11, square.getUpperLeft().getY());
        asciiPaint.moveShape(0, -5, -5);
        assertEquals(6, square.getUpperLeft().getX());
        assertEquals(6, square.getUpperLeft().getY());
    }

    @Test
    void validMoveCircle() {
        setUp();
        // Test moving a circle
        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        asciiPaint.getDrawing().addShape(circle);
        asciiPaint.moveShape(0, 5, 5);
        assertEquals(11, circle.getCenter().getX());
        assertEquals(11, circle.getCenter().getY());
        asciiPaint.moveShape(0, -5, -5);
        assertEquals(6, circle.getCenter().getX());
        assertEquals(6, circle.getCenter().getY());
    }

    //CHANGE THE COLOR OF A SHAPE
    @Test
    void changeShapeColor() {
        setUp();
        // Test moving a circle
        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        asciiPaint.getDrawing().addShape(circle);

        asciiPaint.changeShapeColor(0, 'a');
        assertEquals('a', circle.getColor());
    }


    //DELETE A SHAPE FROM THE DRAWING
    @Test
    void deleteShape() {
        setUp();

        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        Square square = new Square(new Point(6, 6), 6, 'S');
        Line line = new Line(new Point(6, 6), new Point(10, 10), 'l');
        Rectangle rectangle = new Rectangle(new Point(6, 6), 6, 4, 'R');

        asciiPaint.getDrawing().addShape(circle);
        asciiPaint.getDrawing().addShape(square);
        asciiPaint.getDrawing().addShape(line);
        asciiPaint.getDrawing().addShape(rectangle);

        asciiPaint.deleteShape(0);
        assertEquals(3, asciiPaint.getDrawing().getShapes().size());

    }

    //GROUP SHAPES
    @Test
    void groupShapes() {
        setUp();
        // Test moving a circle
        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        Square square = new Square(new Point(6, 6), 6, 'S');
        Line line = new Line(new Point(6, 6), new Point(10, 10), 'l');
        Rectangle rectangle = new Rectangle(new Point(6, 6), 6, 4, 'R');

        asciiPaint.getDrawing().addShape(circle);
        asciiPaint.getDrawing().addShape(square);
        asciiPaint.getDrawing().addShape(line);
        asciiPaint.getDrawing().addShape(rectangle);

        List<Integer> shapeIndexes = new ArrayList<>();
        shapeIndexes.add(0);
        shapeIndexes.add(1);
        shapeIndexes.add(2);
        shapeIndexes.add(3);

        asciiPaint.groupShapes(shapeIndexes);

        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    //UNGROUP SHAPES
    @Test
    void ungroupShapes() {
        setUp();
        // Test moving a circle
        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        Square square = new Square(new Point(6, 6), 6, 'S');
        Line line = new Line(new Point(6, 6), new Point(10, 10), 'l');
        Rectangle rectangle = new Rectangle(new Point(6, 6), 6, 4, 'R');

        asciiPaint.getDrawing().addShape(circle);
        asciiPaint.getDrawing().addShape(square);
        asciiPaint.getDrawing().addShape(line);
        asciiPaint.getDrawing().addShape(rectangle);

        List<Integer> shapeIndexes = new ArrayList<>();
        shapeIndexes.add(0);
        shapeIndexes.add(1);
        shapeIndexes.add(2);
        shapeIndexes.add(3);

        asciiPaint.groupShapes(shapeIndexes);

        assertEquals(1, asciiPaint.getDrawing().getShapes().size());

        asciiPaint.ungroupShapes(0);
        assertEquals(4, asciiPaint.getDrawing().getShapes().size());
    }

    //UNDO THE LAST COMMAND
    @Test
    void undo() {
        setUp();

        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        asciiPaint.getDrawing().addShape(circle);

        asciiPaint.changeShapeColor(0, 'a');
        asciiPaint.undo();
        assertEquals('c', circle.getColor());
    }

    //REDO THE LAST COMMAND
    @Test
    void redo() {
        setUp();

        Circle circle = new Circle(new Point(6, 6), 6, 'c');
        asciiPaint.getDrawing().addShape(circle);

        asciiPaint.changeShapeColor(0, 'a');
        asciiPaint.undo();
        asciiPaint.redo();
        assertEquals('a', circle.getColor());

    }
}