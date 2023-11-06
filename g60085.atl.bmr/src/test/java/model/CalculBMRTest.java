package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculBMRTest {

    private CalculBMR calculBMR;
    private double delta = 0.001; // A small delta for double comparisons

    @Before
    public void setUp() {
        calculBMR = new CalculBMR();
    }

    @Test
    public void testCalculateBmrForFemale() {
        int height = 160;
        double weight = 60.0;
        int age = 30;
        String sex = "Female";
        LifeStyle activityLevelValue = LifeStyle.SEDENTARY;

        calculBMR.calculateBmr(height, weight, age, sex, activityLevelValue);
        double bmr = calculBMR.getBmr();
        double expectedBmr = 9.6  * weight + 1.8 * height - 4.7 * age + 655;
        assertEquals(expectedBmr, bmr, delta);
    }

    @Test
    public void testCalculateBmrForMale() {
        int height = 180;
        double weight = 80.0;
        int age = 35;
        String sex = "Male";
        LifeStyle activityLevelValue = LifeStyle.SEDENTARY;

        calculBMR.calculateBmr(height, weight, age, sex, activityLevelValue);
        double bmr = calculBMR.getBmr();
        double expectedBmr = 13.7  * weight + 5 * height -6.8 * age + 66;
        assertEquals(expectedBmr, bmr, delta);
    }

    @Test
    public void testSetBmrAndCalories() {
        double newBmr = 1500.0;
        double newCalories = 1800.0;

        calculBMR.setBmrAndCalories(newBmr, newCalories);

        assertEquals(newBmr, calculBMR.getBmr(), delta);
        assertEquals(newCalories, calculBMR.getCalories(), delta);
    }

    @Test
    public void testCaloriesForFemale() {
        int height = 160;
        double weight = 60.0;
        int age = 30;
        String sex = "Female";

        for(LifeStyle value : LifeStyle.values()){
            calculBMR.calculateBmr(height, weight, age, sex, value);
            double calories = calculBMR.getCalories();
            double expectedCalories  = (9.6  * weight + 1.8 * height - 4.7 * age + 655)* value.getFactor();
            assertEquals(expectedCalories, calories, delta);
        }
    }

    @Test
    public void testCaloriesForMale() {
        int height = 180;
        double weight = 80.0;
        int age = 35;
        String sex = "Male";

        for(LifeStyle value : LifeStyle.values()){
            calculBMR.calculateBmr(height, weight, age, sex, value);
            double calories = calculBMR.getCalories();
            double expectedCalories  = (13.7  * weight + 5 * height -6.8 * age + 66) * value.getFactor();
            assertEquals(expectedCalories, calories, delta);
        }
    }


}


