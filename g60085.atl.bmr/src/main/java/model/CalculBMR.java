package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class provides methods for calculating Basal Metabolic Rate (BMR) based on height, weight, age, and gender.
 * It follows the Observer design pattern to allow observers to receive notifications when BMR and calorie values change.
 */
public class CalculBMR { //MyObservable
    private final PropertyChangeSupport pcs;
    private double bmr;
    private double calories;


    /**
     * Constructs a new CalculBMR instance
     * by initializing the PropertyChangeSupport with a 'source' object.
     */
    public CalculBMR() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Registers a PropertyChangeListener to observe changes in BMR and calories.
     *
     * @param observer The PropertyChangeListener to be registered.
     */
    public void registerObserver(PropertyChangeListener observer) {
        pcs.addPropertyChangeListener(observer);
    }

    /**
     * Gets the current Basal Metabolic Rate (BMR) value.
     *
     * @return The current BMR value.
     */
    public double getBmr() {
        return bmr;
    }

    /**
     * Gets the current calories value, representing the number of calories required for the individual's daily needs.
     *
     * @return The current calories value in calories per day.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the new BMR and calories values and notifies observers of the change.
     *
     * @param newBmr      The new BMR value.
     * @param newCalories The new calories value.
     */
    public void setBmrAndCalories(double newBmr, double newCalories) {
        double oldBmr = this.bmr;
        double oldCalories = this.calories;

        this.bmr = newBmr;
        this.calories = newCalories;

        // Notify observers of the change in BMR and calories
        pcs.firePropertyChange("bmr", oldBmr, this.bmr);
        pcs.firePropertyChange("calories", oldCalories, this.calories);
    }

    /**
     * Calculates the Basal Metabolic Rate (BMR) based on a person's height, weight, age, and gender.
     *
     * @param height             The person's height in centimeters.
     * @param weight             The person's weight in kilograms.
     * @param age                The person's age in years.
     * @param sex                The person's gender, either "Female" or "Male."
     * @param activityLevelValue The person's activity level.
     */
    public void calculateBmr(int height, double weight, int age, String sex, LifeStyle activityLevelValue) {
        double bmrResult;
        if (sex.equals("Female")) {
            // BMR formula for females
            bmrResult =  9.6  * weight + 1.8 * height - 4.7 * age + 655;
        } else {
            // BMR formula for males
            bmrResult = 13.7  * weight + 5 * height -6.8 * age + 66;
        }
        double caloriesResult = bmrResult * activityLevelValue.getFactor();
        setBmrAndCalories(bmrResult, caloriesResult);
    }

}



