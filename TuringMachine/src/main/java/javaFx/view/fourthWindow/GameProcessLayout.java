package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * Represents the layout for the game process in the Turing Machine game.
 * Extends HBox and contains UI components for entering and testing codes, moving to the next round, and guessing the code.
 */
public class GameProcessLayout extends HBox {
    private CodeVbox codeVbox;
    private StyledButton testValidator;
    private StyledButton nextRoundButton;
    private StyledButton guessCodeButton;

    /**
     * Constructs a new GameProcessLayout with buttons for testing, moving to the next round, and guessing the code.
     */
    public GameProcessLayout() {
        this.setSpacing(100);
        this.setAlignment(Pos.CENTER);

        codeVbox = new CodeVbox();

        testValidator = new StyledButton("TEST");
        testValidator.setMinWidth(200);

        nextRoundButton = new StyledButton("NEXT ROUND");
        nextRoundButton.setMinWidth(200);

        guessCodeButton = new StyledButton("GUESS");
        guessCodeButton.setMinWidth(200);

        VBox buttonsVbox = new VBox(15);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.getChildren().addAll(testValidator, nextRoundButton, guessCodeButton);

        this.getChildren().addAll(codeVbox, buttonsVbox);
    }

    /**
     * Gets the CodeVbox from the GameProcessLayout.
     *
     * @return The CodeVbox.
     */
    public CodeVbox getCodeVbox() {
        return codeVbox;
    }

    /**
     * Gets the Test Validator button from the GameProcessLayout.
     *
     * @return The Test Validator button.
     */
    public StyledButton getTestValidator() {
        return testValidator;
    }

    /**
     * Gets the Next Round button from the GameProcessLayout.
     *
     * @return The Next Round button.
     */
    public StyledButton getNextRoundButton() {
        return nextRoundButton;
    }

    /**
     * Gets the Guess Code button from the GameProcessLayout.
     *
     * @return The Guess Code button.
     */
    public StyledButton guessCodeButton() {
        return guessCodeButton;
    }
}
