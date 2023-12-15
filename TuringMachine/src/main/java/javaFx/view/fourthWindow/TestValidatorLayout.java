package javaFx.view.fourthWindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TestValidatorLayout extends GridPane {
        private Label chooseValidator;
    private ChoiceBox<String> choiceBox;

    private StyledButton testButton;

    private Label resultLabel;

    private TextField testResult;
    public TestValidatorLayout(ValidatorsLayout validatorsLayout) {
        this.setPadding(new Insets(0, 20, 0, 20));
        this.setAlignment(Pos.CENTER);
        this.setHgap(5);
        this.setVgap(50);
        this.setStyle("-fx-background-color: white;");

        chooseValidator = new Label("CHOOSE VALIDATOR");
        chooseValidator.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(validatorsLayout.getValidatorsLabels());
        choiceBox.setStyle("-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;");


       testButton = new StyledButton("TEST");
        testButton.setMinWidth(200);
        testButton.setAlignment(Pos.CENTER);

       resultLabel = new Label("RESULT");
        GridPane.setHalignment(resultLabel, HPos.RIGHT);
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));


        testResult = new TextField();
        testResult.setMaxWidth(50);


        this.add(chooseValidator, 0, 0);
        this.add(choiceBox, 1, 0);
        this.add(testButton, 0, 1, 2, 1); // Couvre 2 colonnes
        this.add(resultLabel, 0, 2);
        this.add(testResult, 1, 2);
    }
}


