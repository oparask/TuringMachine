package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculBMRTest {
    @Test
    public void testBMRForFemale() {
        // Test the BMR calculation for a female
        double expectedBMR = 9.6 * 160 + 1.8 * 65 - 4.7 * 30 + 655;
        double actualBMR = CalculBMR.bmr(160, 65, 30, "Female");
        assertEquals(expectedBMR, actualBMR, 0.01);
    }

    @Test
    public void testBMRForMale() {
        // Test the BMR calculation for a male
        double expectedBMR = 13.7 * 175 + 5 * 75 - 6.8 * 35 + 66;
        double actualBMR = CalculBMR.bmr(175, 75, 35, "Male");
        assertEquals(expectedBMR, actualBMR, 0.01);
    }

    @Test
    public void testBMRForInvalidSex() {
        // Test for an invalid sex input
        try {
            CalculBMR.bmr(160, 65, 30, "InvalidSex");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid sex. Please provide 'Female' or 'Male'.", e.getMessage());
        }
    }

}