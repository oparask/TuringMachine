package javaFx.view.fourthWindow;

import javaFx.view.PopOut;
import javaFx.view.StyledButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Game;
import model.GameFacade;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the view for the fourth window in the Turing Machine game.
 * Extends VBox and implements PropertyChangeListener to observe changes in the game state.
 * This class displays the UI components for the game process, including undo and redo buttons,
 * validators layout, score information, and game process layout.
 */
public class FourthWindowView extends VBox implements PropertyChangeListener {
    private final StyledButton undoButton;
    private final StyledButton redoButton;
    private final ValidatorsLayout validatorsLayout;
    private Label scoreLayout;
    private final GameProcessLayout gameProcessLayout;

    private GameFacade gameFacade;

    /**
     * Constructs a new FourthWindowView with the specified GameFacade.
     *
     * @param gameFacade The GameFacade representing the underlying game logic.
     */
    public FourthWindowView(GameFacade gameFacade) {
        gameFacade.getCurrentGame().registerObserver(this);

        this.gameFacade = gameFacade;

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: green;");

        undoButton = new StyledButton("<-");
        redoButton = new StyledButton("->");
        HBox undoRedoButtons = new HBox(5);
        undoRedoButtons.setPadding(new Insets(0, 10, 0, 10));
        undoRedoButtons.getChildren().addAll(undoButton, redoButton);

        validatorsLayout = new ValidatorsLayout(gameFacade.getProblemValidators());

        scoreLayout = new Label();
        scoreLayout.setTextFill(Color.WHITE);
        scoreLayout.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scoreLayout.setText("SCORE: " + gameFacade.getScore() + "    ROUND: " + gameFacade.getRounds().size());
        scoreLayout.setUserData(gameFacade.getRounds().size());

        gameProcessLayout = new GameProcessLayout();

        this.getChildren().add(undoRedoButtons);
        this.getChildren().add(validatorsLayout);
        this.getChildren().add(scoreLayout);
        this.getChildren().add(gameProcessLayout);
    }

    /**
     * Responds to property changes in the observed object.
     *
     * @param event The property change event containing information about the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "testValidatorResult": {
                boolean newState = (boolean) event.getNewValue();

                Node parent = validatorsLayout.getClickedButton().getParent();

                if (newState) {
                    // If validator test has passed.
                    parent.setStyle("-fx-background-color: lightgreen;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;");
                } else {
                    // If the test didn't pass.
                    parent.setStyle("-fx-background-color: lightcoral;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;");
                }
                break;
            }
            case "newScore": {
                int newState = (int) event.getNewValue();
                scoreLayout.setText("SCORE: " + newState + "    ROUND: " + gameFacade.getRounds().size());
                break;
            }
            case "guessCodeResult": {
                boolean newState = (boolean) event.getNewValue();
                PopOut popOut;
                if (newState) {
                    popOut = new PopOut("",  "Congratulations, YOU WON! \uD83C\uDF89", false);
                } else {
                    popOut = new PopOut("", "Unfortunately, you didn't guess the code \uD83D\uDE22 " +
                            "\nNext time \uD83D\uDE09 ", true);
                }
                popOut.show();
                break;
            }
        }
    }

    /**
     * Gets the Test Validator button from the GameProcessLayout.
     *
     * @return The Test Validator button.
     */
    public StyledButton getTestValidatorButton() {
        return gameProcessLayout.getTestValidator();
    }

    /**
     * Gets the Next Round button from the GameProcessLayout.
     *
     * @return The Next Round button.
     */
    public StyledButton getNextRoundButton() {
        return gameProcessLayout.getNextRoundButton();
    }

    /**
     * Gets the Guess Code button from the GameProcessLayout.
     *
     * @return The Guess Code button.
     */
    public StyledButton getGuessCodeButton() {
        return gameProcessLayout.guessCodeButton();
    }

    /**
     * Gets the CodeVbox from the GameProcessLayout.
     *
     * @return The CodeVbox.
     */
    public CodeVbox getCodeVbox() {
        return gameProcessLayout.getCodeVbox();
    }

    /**
     * Gets the Enter Code button from the GameProcessLayout.
     *
     * @return The Enter Code button.
     */
    public StyledButton getEnterCodeButton() {
        return gameProcessLayout.getCodeVbox().getEnterCodeButton();
    }

    /**
     * Gets the Undo button.
     *
     * @return The Undo button.
     */
    public Button getUndoButton() {
        return undoButton;
    }

    /**
     * Gets the Redo button.
     *
     * @return The Redo button.
     */
    public Button getRedoButton() {
        return redoButton;
    }

    /**
     * Gets the index of the clicked button in the ValidatorsLayout.
     *
     * @return The index of the clicked button.
     */
    public Integer getIndexOfClickedButton() {
        return validatorsLayout.getIndexOfClickedButton();
    }
}
