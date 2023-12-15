package javaFx.view.fourthWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
    private HBox codeLayout;

    public FourthWindowView(GameFacade gameFacade) {
        //initialiser la racine
        //this.root = new VBox(10);

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



        codeLayout = new HBox(100);
        codeLayout.setAlignment(Pos.CENTER);
        //code machine
        CodeVbox codeVbox = new CodeVbox();
        // Créer une grille pour testValidator
        TestValidatorLayout testValidator = new TestValidatorLayout(validatorsLayout);
        //le bouton next
        StyledButton nextRoundButton = new StyledButton("NEXT ROUND");
        nextRoundButton.setMinWidth(200);
        //guesscode layout
        GuessCodeLayout buttonsVbox = new GuessCodeLayout();

        codeLayout.getChildren().add(codeVbox);
        codeLayout.getChildren().add(testValidator);
        codeLayout.getChildren().add(nextRoundButton);
        codeLayout.getChildren().add(buttonsVbox);


        //ajouter tous les enfants a son parent
        this.getChildren().add(undoRedoButtons);
        this.getChildren().add(validatorsLayout);
        this.getChildren().add(scoreLayout);
        this.getChildren().add(codeLayout);
    }
}


/* private void displayGameProcess(Stage displayProblems) {
        displayProblems.close();
        Stage GameProcessParts = new Stage();












        // Ajouter le ScrollPane à la VBox
        ScrollPane scrollPaneRoot = new ScrollPane(root);
        scrollPaneRoot.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneRoot.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu

        // Ajouter le ScrollPane à la scène
        Scene scene = new Scene(scrollPaneRoot, 500, 425);

        GameProcessParts.setScene(scene);
        GameProcessParts.show();
    }
*/