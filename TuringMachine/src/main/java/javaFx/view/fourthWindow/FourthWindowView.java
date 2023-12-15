package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.GameFacade;

public class FourthWindowView extends VBox {
    private HBox undoRedoButtons;
    private ValidatorsLayout validatorsLayout;
    private Label scoreLayout;
    private GameProcessLayout gameProcessLayout;

    public FourthWindowView(GameFacade gameFacade) {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: green;");



        //initialiser les boutons undo redo
        undoRedoButtons = new HBox(10);
        undoRedoButtons.setPadding(new Insets(10));
        //les bouton undo redo
        Button undoButton = new Button("<-");
       /* undoButton.setOnAction(event -> {
            System.out.println("Undo button pressed");
            gameFacade.undo();
        });*/
        Button redoButton = new Button("->");
       /* redoButton.setOnAction(event -> {
            System.out.println("Redo button pressed");
            gameFacade.redo();
        });*/
        undoRedoButtons.getChildren().addAll(undoButton, redoButton);

        validatorsLayout = new ValidatorsLayout(gameFacade.getProblemValidators());


        scoreLayout = new Label();
        scoreLayout.setTextFill(Color.WHITE);
        scoreLayout.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scoreLayout.setText("SCORE: " + gameFacade.getScore() + "    ROUNDS: " + gameFacade.getRounds().size());



        gameProcessLayout = new GameProcessLayout(validatorsLayout);

        //ajouter tous les enfants a son parent
        this.getChildren().add(undoRedoButtons);
        this.getChildren().add(validatorsLayout);
        this.getChildren().add(scoreLayout);
        this.getChildren().add(gameProcessLayout);
    }

    public StyledButton getEnterCodeButton() {
        return gameProcessLayout.getCodeVbox().getEnterCodeButton();
    }

    public CodeVbox getCodeVbox() {
        return gameProcessLayout.getCodeVbox();
    }
}


