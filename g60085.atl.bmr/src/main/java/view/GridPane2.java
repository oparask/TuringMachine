package view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * This class represents a GridPane for displaying the results of BMR calculation.
 * It includes labels and text fields for displaying BMR and calorie values.
 */
public class GridPane2 extends GridPane {

    private Label gridPanetitle, resultBMR, calories;
    private TextField resultBMRField, caloriesField;

    /**
     * Constructs a GridPane2 for displaying BMR and calorie results.
     * It initializes various components including labels and text fields.
     */
    public GridPane2() {
        this.setHgap(10);  // Set horizontal gap between elements
        this.setVgap(5);   // Set vertical gap between elements

        // Title for the results section
        gridPanetitle = new Label("Résultats");
        gridPanetitle.setUnderline(true);
        this.add(gridPanetitle, 0, 0);

        // BMR result section
        resultBMR = new Label("BMR");
        resultBMRField = new TextField();
        resultBMRField.setPromptText("Résultat du BMR");
        resultBMRField.setEditable(false);
        this.add(resultBMR, 0, 1);
        this.add(resultBMRField, 1, 1);

        // Calories result section
        calories = new Label("Calories");
        caloriesField = new TextField();
        caloriesField.setPromptText("Dépense en calories");
        caloriesField.setEditable(false);
        this.add(calories, 0, 2);
        this.add(caloriesField, 1, 2);
    }
    /**
     * Get the TextField for BMR results.
     *
     * @return The TextField containing BMR results.
     */
    public TextField getResultBMRField() {
        return resultBMRField;
    }

    /**
     * Get the TextField for calorie results.
     *
     * @return The TextField containing calorie results.
     */
    public TextField getCaloriesField() {
        return caloriesField;
    }

    /**
     * Set the text of the BMR result field.
     *
     * @param resultBMRErrorText The text to be displayed in the BMR result field.
     */
    public void setResultBMRField(String resultBMRErrorText) {
        this.resultBMRField.setText(resultBMRErrorText);
    }

    /**
     * Set the text of the calorie result field.
     *
     * @param caloriesErrorText The text to be displayed in the calorie result field.
     */
    public void setCaloriesField(String caloriesErrorText) {
        this.caloriesField.setText(caloriesErrorText);
    }
}
