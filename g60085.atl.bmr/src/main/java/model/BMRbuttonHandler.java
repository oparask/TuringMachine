package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import view.GridPane1;
import view.GridPane2;

import model.CalculBMR;

/**
 * This class handles the action when the "Calcul du BMR" button is clicked, which calculates BMR and related information.
 */
public class BMRbuttonHandler implements EventHandler<ActionEvent> {
    private GridPane1 gridPane1;
    private GridPane2 gridPane2;
    private CalculBMR calculBMR;


    /**
     * Constructor for the BMRbuttonHandler class.
     *
     * @param gridPane1 The GridPane1 object containing user input data.
     * @param gridPane2 The GridPane2 object for displaying BMR and calories.
     */
    public BMRbuttonHandler(GridPane1 gridPane1, GridPane2 gridPane2, CalculBMR calculBMR) {
        this.gridPane1 = gridPane1;
        this.gridPane2 = gridPane2;
        this.calculBMR = calculBMR;

    }

    /**
     * Handles the action when the "Calcul du BMR" button is clicked, which calculates BMR and related information.
     *
     * @param event The ActionEvent triggered by clicking the "Calcul du BMR" button.
     */
    @Override
    public void handle(ActionEvent event) {
        // Retrieve data from GridPane1
        String heightText = gridPane1.getHeightField().getText();
        String weightText = gridPane1.getWeightField().getText();
        String ageText = gridPane1.getAgeField().getText();
        String activityLevelText = gridPane1.getActivityLevelChoice().getValue();

        boolean isFemaleSelected = gridPane1.getFemaleRadioButton().isSelected();
        boolean isMaleSelected = gridPane1.getMaleRadioButton().isSelected();

        int heightValue = -1;
        double weightValue = -1;
        int ageValue = -1;
        String sexValue;
        LifeStyle activityLevelValue = LifeStyle.SEDENTARY;


        // Check if any of the fields are empty
        if (heightText.isEmpty() || weightText.isEmpty() || ageText.isEmpty() ||
                (!isFemaleSelected && !isMaleSelected)) {
            // Handle empty fields and display an error message
            gridPane2.getResultBMRField().setStyle("-fx-text-fill: red;");
            gridPane2.setResultBMRField("Failed!");
            gridPane2.getCaloriesField().setStyle("-fx-text-fill: red;");
            gridPane2.setCaloriesField("Failed!");

            if (heightText.isEmpty()) {
                gridPane1.setHeightErrorLabel("La taille est requise.");
            }
            if (weightText.isEmpty()) {
                gridPane1.setWeightErrorLabel("Le poids est requis.");
            }
            if (ageText.isEmpty()) {
                gridPane1.setAgeErrorLabel("L'âge est requis.");
            }
            if (!isFemaleSelected && !isMaleSelected) {
                gridPane1.setSexErrorLabel("Veuillez sélectionner le sexe.");
            }
        } else {
            try {
                heightValue = Integer.parseInt(heightText);
                weightValue = Double.parseDouble(weightText);
                ageValue = Integer.parseInt(ageText);

            } catch (NumberFormatException e) {
                // Handle invalid input values and display an error message
                showError("Valeurs incorrectes",
                        "La taille, le poids et l'âge doivent être supérieurs à zéro.");
            }

            if (heightValue == 0) {
                // Handle zero height value and display an error message
                gridPane1.setHeightErrorLabel("Taille invalide!");
                showError("Valeur de la taille erronée", "La taille doit être supérieure à zéro.");
            }
            if (weightValue == 0) {
                // Handle zero weight value and display an error message
                gridPane1.setWeightErrorLabel("Poids invalide!");
                showError("Valeur du poids erronée", "Le poids doit être supérieur à zéro.");
            }
            if (ageValue == 0) {
                // Handle zero age value and display an error message
                gridPane1.setAgeErrorLabel("Age invalide!");
                showError("Valeur de l'âge erronée", "L'âge doit être supérieur à zéro.");
            }

            sexValue = gridPane1.getFemaleRadioButton().isSelected() ? "Femme" : "Homme";


            for (LifeStyle lifeStyleValue : LifeStyle.values()) {
                if (activityLevelText.equals(lifeStyleValue.toString())) {
                    activityLevelValue = lifeStyleValue;
                    break;
                }
            }

            // Call the function to calculate BMR and related information
            if (heightValue > 0 && weightValue > 0 && ageValue > 0) {
                calculBMR.calculateBmr(heightValue, weightValue,ageValue, sexValue, activityLevelValue);
            }
        }
    }

    /**
     * Displays an error dialog box with custom title, header, and content,
     * while also modifying the style of result fields in the user interface.
     *
     * @param headerText  The header of the error dialog box.
     * @param contentText The content of the error dialog box.
     */
    private void showError(String headerText, String contentText) {
        gridPane2.getResultBMRField().setStyle("-fx-text-fill: red;");
        gridPane2.setResultBMRField("Failed!");
        gridPane2.getCaloriesField().setStyle("-fx-text-fill: red;");
        gridPane2.setCaloriesField("Failed!");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}



