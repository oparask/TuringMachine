package model;

/**
 * This class provides methods for calculating Basal Metabolic Rate (BMR) based on height, weight, age, and gender.
 */
public class CalculBMR {

    /**
     * Calculates the Basal Metabolic Rate (BMR) based on a person's height, weight, age, and gender.
     *
     * @param height The person's height in centimeters.
     * @param weight The person's weight in kilograms.
     * @param age The person's age in years.
     * @param sex The person's gender, either "Female" or "Male."
     * @return The calculated Basal Metabolic Rate (BMR) in calories per day.
     */
    public static double bmr(int height, double weight, int age, String sex) {
        if (sex.equals("Female")) {
            // BMR formula for females
            return 9.6 * height + 1.8 * weight - 4.7 * age + 655;
        } else {
            // BMR formula for males
            return 13.7 * height + 5 * weight - 6.8 * age + 66;
        }
    }
}


