package view;

import model.CalculBMR;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class represents a listener that observes changes in a `CalculBMR` object and updates a `GridPane2` accordingly.
 * It is part of the Observer design pattern implementation for updating the user interface when BMR and calorie values change.
 */
public class DisplayResultBMR implements PropertyChangeListener {

    private GridPane2 gridPane2;

    /**
     * Constructs a new `DisplayResultBMR` instance with the provided `CalculBMR` object and `GridPane2` object.
     *
     * @param observable The `CalculBMR` object to observe for property changes.
     * @param gridPane2  The `GridPane2` object for displaying BMR and calories.
     */
    public DisplayResultBMR(CalculBMR observable, GridPane2 gridPane2) {
        observable.registerObserver(this);
        this.gridPane2 = gridPane2;
    }

    /**
     * Responds to property changes in the observed object.
     *
     * @param evt The property change event containing information about the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "bmr": {
                double newState = (double) evt.getNewValue();
                // Update the result BMR field in the user interface
                gridPane2.getResultBMRField().setStyle(null);
                double roundedResultBMR = Math.round(newState * 100.0) / 100.0;
                gridPane2.setResultBMRField(String.valueOf(roundedResultBMR));
                break;
            }
            case "calories": {
                double newState = (double) evt.getNewValue();
                // Update the calories field in the user interface
                gridPane2.getCaloriesField().setStyle(null);
                double roundedResultCalories = Math.round(newState * 100.0) / 100.0;
                gridPane2.setCaloriesField(String.valueOf(roundedResultCalories));
                break;
            }
        }
    }
}
