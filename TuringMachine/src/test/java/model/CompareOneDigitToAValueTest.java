package model;

import model.validators.CompareOneDigitToAValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompareOneDigitToAValueTest {

    //VALIDATOR 1
    //Compare le premier chiffre du code avec 1

    @Test
    public void validator1EqualTrue() {
        // Test case 1
        CompareOneDigitToAValue validator1 = new CompareOneDigitToAValue(143, 124, 1);
        assertTrue(validator1.test());
    }
    @Test
    public void validator1EqualFalse() {
        // Test case 1
        CompareOneDigitToAValue validator1 = new CompareOneDigitToAValue(243, 124,1);
        assertFalse(validator1.test());

        // Test case 2
        CompareOneDigitToAValue validator1Bis = new CompareOneDigitToAValue(143, 224,1);
        assertFalse(validator1Bis.test());
    }

    @Test
    public void validator1BiggerTrue() {
        // Test case 1
        CompareOneDigitToAValue validator1 = new CompareOneDigitToAValue(223, 324, 1);
        assertTrue(validator1.test());

        // Test case 2
        CompareOneDigitToAValue validator1Bis = new CompareOneDigitToAValue(423, 524, 1);
        assertTrue(validator1Bis.test());
    }

    @Test
    public void validator1BiggerFalse() {
        // Test case 1
        CompareOneDigitToAValue validator1 = new CompareOneDigitToAValue(423, 124, 1);
        assertFalse(validator1.test());

        // Test case 2
        CompareOneDigitToAValue validator1Bis = new CompareOneDigitToAValue(123, 524, 1);
        assertFalse(validator1Bis.test());
    }



    //VALIDATOR 2
    //Compare le premier chiffre avec 3
    @Test
    public void validator2SmallerTrue() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(155, 255, 2);
        assertTrue(validator2.test());

        // Test case 2
        CompareOneDigitToAValue validator2Bis = new CompareOneDigitToAValue(255, 255, 2);
        assertTrue(validator2Bis.test());
    }

    @Test
    public void validator2SmallerFalse() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(455, 255, 2);
        assertFalse(validator2.test());

        // Test case 2
        CompareOneDigitToAValue validator2Bis = new CompareOneDigitToAValue(255, 355, 2);
        assertFalse(validator2Bis.test());
    }


    @Test
    public void validator2EqualTrue() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(354, 384, 2);
        assertTrue(validator2.test());

    }

    @Test
    public void validator2EqualFalse() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(224, 334, 2);
        assertFalse(validator2.test());

        // Test case 2
        CompareOneDigitToAValue validator2Bis = new CompareOneDigitToAValue(324, 234, 2);
        assertFalse(validator2Bis.test());

    }

    @Test
    public void validator2BiggerTrue() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(453, 543, 2);
        assertTrue(validator2.test());

    }
    @Test
    public void validator2BiggerFalse() {
        // Test case 1
        CompareOneDigitToAValue validator2 = new CompareOneDigitToAValue(255, 545, 2);
        assertFalse(validator2.test());

    }

    //VALIDATOR 3
    //Compare le deuxième chiffre avec 3
    @Test
    public void validator3SmallerTrue() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(123, 414, 3);
        assertTrue(validator3.test());

    }

    @Test
    public void validator3SmallerFalse() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(153, 425, 3);
        assertFalse(validator3.test());

    }

    @Test
    public void validator3EqualTrue() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(133, 433, 3);
        assertTrue(validator3.test());

    }
    @Test
    public void validator3EqualFalse() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(123, 436, 3);
        assertFalse(validator3.test());

    }

    @Test
    public void validator3BiggerTrue() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(143, 455, 3);
        assertTrue(validator3.test());

    }

    @Test
    public void validator3BiggerFalse() {
        // Test case 3
        CompareOneDigitToAValue validator3 = new CompareOneDigitToAValue(123, 455, 3);
        assertFalse(validator3.test());

    }



    //VALIDATOR 4
    //Compare le deuxième chiffre avec 4
    @Test
    public void validator4SmallerTrue() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(111, 222, 4);
        assertTrue(validator4.test());

    }
    @Test
    public void validator4SmallerFalse() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(555, 222, 4);
        assertFalse(validator4.test());

    }

    @Test
    public void validator4EqualTrue() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(444, 444, 4);
        assertTrue(validator4.test());

    }

    @Test
    public void validator4EqualFalse() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(111, 444, 4);
        assertFalse(validator4.test());

    }


    @Test
    public void validator4BiggerTrue() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(555, 555, 4);
        assertTrue(validator4.test());
    }

    @Test
    public void validator4BiggerFalse() {
        // Test case 4
        CompareOneDigitToAValue validator4 = new CompareOneDigitToAValue(444, 555, 4);
        assertFalse(validator4.test());
    }


}
