package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GameProcessLayout extends HBox {
    private CodeVbox codeVbox;
    private StyledButton testValidator;
    private StyledButton nextRoundButton;
    private StyledButton guessCodeButton;


    public GameProcessLayout(ValidatorsLayout validatorsLayout) {
        this.setSpacing(100);
        this.setAlignment(Pos.CENTER);

        codeVbox = new CodeVbox();

        testValidator = new StyledButton("TEST");
        testValidator.setMinWidth(200);

        nextRoundButton = new StyledButton("NEXT ROUND");
        nextRoundButton.setMinWidth(200);


        guessCodeButton = new StyledButton("GUESS");
        guessCodeButton.setMinWidth(200);

        this.getChildren().add(codeVbox);
        this.getChildren().add(testValidator);
        this.getChildren().add(nextRoundButton);
        this.getChildren().add(guessCodeButton);
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
