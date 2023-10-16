package model;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
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


    @org.junit.jupiter.api.Test
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



    @org.junit.jupiter.api.Test
    void newRectangle() {
    }

    @org.junit.jupiter.api.Test
    void newSquare() {
    }

    @org.junit.jupiter.api.Test
    void asASCII() {
    }
}