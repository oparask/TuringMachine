package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.LifeStyle;

/**
 * Represents a grid pane for inputting user data related to BMR calculation.
 * It provides fields for height, weight, age, gender, and activity level.
 */
public class GridPane1 extends GridPane{
    private Label gridPanetitle,height,weight,age, sex, lifeStyle;
    private RadioButton femaleRadioButton, maleRadioButton;
    private TextField heightField, weightField, ageField;
    private ChoiceBox<String> activityLevelChoice;
    private Label heightErrorLabel, weightErrorLabel, ageErrorLabel, sexErrorLabel;

    /**
     * Construct the GridPane1 and initialize its components.
     */
    public GridPane1() {
        this.setHgap(10);  // Set horizontal gap between elements
        this.setVgap(5);   // Set vertical gap between elements

        // Title for the data input section
        gridPanetitle = new Label("Données");
        gridPanetitle.setUnderline(true);
        this.add(gridPanetitle, 0, 0);

        // Height input section
        height = new Label("Taille (cm)");
        heightField = new TextField();
        heightField.setPromptText("Entrez la taille (en cm)");
        heightErrorLabel = new Label("");
        heightErrorLabel.setTextFill(Color.RED);
        GridPane.setMargin(this.getHeightErrorLabel(), new Insets(-8, 0, 6, 0)); // Ajustez les marges ici
        this.add(height, 0, 1);
        this.add(heightField, 1, 1);
        this.add(heightErrorLabel, 1, 2);

        // Weight input section
        weight = new Label("Poids (kg)");
        weightField = new TextField();
        weightField.setPromptText("Entrez le poids (en kg)");
        weightErrorLabel = new Label("");
        weightErrorLabel.setTextFill(Color.RED);
        GridPane.setMargin(this.getWeightErrorLabel(), new Insets(-8, 0, 6, 0)); // Ajustez les marges ici
        this.add(weight, 0, 3);
        this.add(weightField, 1, 3);
        this.add(weightErrorLabel, 1, 4);

        // Age input section
        age = new Label("Age (années)");
        ageField = new TextField();
        ageField.setPromptText("Entrez l'âge (en années)");
        ageErrorLabel = new Label("");
        ageErrorLabel.setTextFill(Color.RED);
        GridPane.setMargin(this.getAgeErrorLabel(), new Insets(-8, 0, 6, 0)); // Ajustez les marges ici
        this.add(age, 0, 5);
        this.add(ageField, 1, 5);
        this.add(ageErrorLabel, 1, 6);

        // Gender selection section
        sex = new Label("Sexe");
        sexErrorLabel = new Label("");
        sexErrorLabel.setTextFill(Color.RED);
        GridPane.setMargin(this.getSexErrorLabel(), new Insets(-8, 0, 6, 0)); // Ajustez les marges ici
        this.add(sex, 0, 7);
        this.add(sexErrorLabel, 1, 8);

        //Radio buttons for Gender selection section
        femaleRadioButton = new RadioButton("Femme");
        maleRadioButton = new RadioButton("Homme");

        HBox radioBox = new HBox(10); // Create an HBox for radio buttons with 10-pixel spacing
        radioBox.getChildren().addAll(femaleRadioButton, maleRadioButton);

        ToggleGroup genderGroup = new ToggleGroup(); // Ensure only one can be selected at a time
        femaleRadioButton.setToggleGroup(genderGroup);
        maleRadioButton.setToggleGroup(genderGroup);
        this.add(radioBox, 1, 7);

        // Activity level choice section
        lifeStyle = new Label("Style de vie");
        activityLevelChoice = new ChoiceBox<>();
        for (LifeStyle level : LifeStyle.values()) {
            activityLevelChoice.getItems().add(level.toString());
        }
        activityLevelChoice.setValue(LifeStyle.SEDENTARY.toString());
        this.add(lifeStyle, 0, 10);
        this.add(activityLevelChoice, 1, 10);
    }

    /**
     * @return The text field for entering the user's height in centimeters.
     */
    public TextField getHeightField() {
        return heightField;
    }

    /**
     * @return The text field for entering the user's weight in kilograms.
     */
    public TextField getWeightField() {
        return weightField;
    }

    /**
     * @return The text field for entering the user's age in years.
     */
    public TextField getAgeField() {
        return ageField;
    }

    /**
     * @return The radio button for selecting the female gender.
     */
    public RadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    /**
     * @return The radio button for selecting the male gender.
     */
    public RadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    /**
     * @return The choice box for selecting the user's activity level.
     */
    public ChoiceBox<String> getActivityLevelChoice() {
        return activityLevelChoice;
    }

    /**
     * @return The label for displaying error messages related to the height field.
     */
    public Label getHeightErrorLabel() {
        return heightErrorLabel;
    }

    /**
     * @return The label for displaying error messages related to the weight field.
     */
    public Label getWeightErrorLabel() {
        return weightErrorLabel;
    }

    /**
     * @return The label for displaying error messages related to the age field.
     */
    public Label getAgeErrorLabel() {
        return ageErrorLabel;
    }

    /**
     * @return The label for displaying error messages related to the gender selection.
     */
    public Label getSexErrorLabel() {
        return sexErrorLabel;
    }

    /**
     * Sets the error message related to the height field.
     *
     * @param heightErrorText The error message to display.
     */
    public void setHeightErrorLabel(String heightErrorText) {
        this.heightErrorLabel.setText(heightErrorText);
    }

    /**
     * Sets the error message related to the weight field.
     *
     * @param weightErrorText The error message to display.
     */
    public void setWeightErrorLabel(String weightErrorText) {
        this.weightErrorLabel.setText(weightErrorText);
    }

    /**
     * Sets the error message related to the age field.
     *
     * @param ageErrorText The error message to display.
     */
    public void setAgeErrorLabel(String ageErrorText) {
        this.ageErrorLabel.setText(ageErrorText);
    }

    /**
     * Sets the error message related to the gender selection.
     *
     * @param sexErrorText The error message to display.
     */
    public void setSexErrorLabel(String sexErrorText) {
        this.sexErrorLabel.setText(sexErrorText);
    }
}
