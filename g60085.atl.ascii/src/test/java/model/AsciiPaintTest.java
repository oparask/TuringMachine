package model;

import org.junit.Before;
import org.junit.jupiter.api.Test;


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
        assertThrows(InvalidColorException.class, () -> asciiPaint.newCircle(5, 5, 10, '0'));
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
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(0, 10, 20, 15, 'R'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithInvalidDimensions() {
        setUp();
        // Test adding a rectangle with invalid dimensions
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(10, 10, 30, 15, 'R'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangleWithInvalidColor() {
        setUp();
        // Test adding a rectangle with an invalid color
        assertThrows(InvalidColorException.class, () -> asciiPaint.newRectangle(10, 10, 20, 15, '1'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithValidParameters() {
        setUp();
        // Test adding a square with valid parameters
        assertDoesNotThrow(() ->  asciiPaint.newSquare(10, 10, 15, 'S'));
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidCoordinates() {
        setUp();
        // Test adding a square with invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(0, 10, 15, 'S'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidSideLength() {
        setUp();
        // Test adding a square with an invalid side length
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(10, 10, 30, 'S'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquareWithInvalidColor() {
        setUp();
        // Test adding a square with an invalid color
        assertThrows(InvalidColorException.class, () -> asciiPaint.newSquare(10, 10, 15, '1'));
        assertEquals(0, asciiPaint.getDrawing().getShapes().size());
    }

}