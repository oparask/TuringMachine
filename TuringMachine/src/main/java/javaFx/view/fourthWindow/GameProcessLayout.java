package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameProcessLayout extends HBox {
    private CodeVbox codeVbox;
    private StyledButton testValidator;
    private StyledButton nextRoundButton;
    private StyledButton guessCodeButton;


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
        buttonsVbox.getChildren().addAll(testValidator, nextRoundButton, guessCodeButton );

        this.getChildren().addAll(codeVbox, buttonsVbox);

    }

    public CodeVbox getCodeVbox() {
        return codeVbox;
    }

    public StyledButton getTestValidator() {
        return testValidator;
    }

    public StyledButton getNextRoundButton() {
        return nextRoundButton;
    }

    public StyledButton guessCodeButton() {
        return guessCodeButton;
    }
}
