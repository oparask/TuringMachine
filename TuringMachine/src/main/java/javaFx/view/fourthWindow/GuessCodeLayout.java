package javaFx.view.fourthWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GuessCodeLayout extends GridPane {
    private StyledButton guessCodeButton;
    private Label guessCodeResultLabel;

    private TextField guessCodeResult;

    public GuessCodeLayout() {
        this.setHgap(5);
        this.setVgap(50);
        this.setPadding(new Insets(0, 20, 0, 20));
        this.setStyle("-fx-background-color: white;");
        this.setAlignment(Pos.CENTER);


        guessCodeButton = new StyledButton("GUESS CODE");
        guessCodeButton.setMinWidth(200);


        guessCodeResultLabel = new Label("RESULT");
        guessCodeResultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));


        guessCodeResult = new TextField();

        this.add(guessCodeButton, 0, 0, 2, 1); // Couvre 2 colonnes
        this.add(guessCodeResultLabel, 0, 1);
        this.add(guessCodeResult, 1, 1);

    }
}








