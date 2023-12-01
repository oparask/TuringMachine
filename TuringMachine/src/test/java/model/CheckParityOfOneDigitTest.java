package model;

import model.validators.CheckParityOfOneDigit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckParityOfOneDigitTest {

    //VALIDATOR 5
    @Test
    public void testValidator5PairTrue() {
        // Test case 1
        CheckParityOfOneDigit validator1 = new CheckParityOfOneDigit(123, 124, 5);
        assertTrue(validator1.test());
    }
    @Test
    public void testValidator5PairFalse() {
        // Test case 1
        CheckParityOfOneDigit validator1 = new CheckParityOfOneDigit(123, 124, 5);
        assertTrue(validator1.test());
    }

    @Test
    public void testValidator5ImpairTrue() {
        // Test case 1
        CheckParityOfOneDigit validator1 = new CheckParityOfOneDigit(123, 124, 5);
        assertTrue(validator1.test());
    }

    @Test
    public void testValidator5ImpairFalse() {
        // Test case 1
        CheckParityOfOneDigit validator1 = new CheckParityOfOneDigit(123, 124, 5);
        assertTrue(validator1.test());
    }


    //VALIDATOR 6
    @Test
    public void testValidator6Impair() {
        // Test case 2
        CheckParityOfOneDigit validator2 = new CheckParityOfOneDigit(456, 789, 6);
        assertFalse(validator2.test());
    }

    @Test
    public void testValidator6Pair() {
        // Test case 2
        CheckParityOfOneDigit validator2 = new CheckParityOfOneDigit(456, 789, 6);
        assertFalse(validator2.test());
    }


    //VALIDATOR 7
    @Test
    public void testValidator7() {
        // Test case 3
        CheckParityOfOneDigit validator3 = new CheckParityOfOneDigit(123, 456, 7);
        assertTrue(validator3.test());
    }
}