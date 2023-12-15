package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GameProcessLayout extends HBox {
    private CodeVbox codeVbox;
    private TestValidatorLayout testValidator;
    private StyledButton nextRoundButton;
    private GuessCodeLayout buttonsVbox;


    public GameProcessLayout(ValidatorsLayout validatorsLayout) {
        this.setSpacing(100);
        this.setAlignment(Pos.CENTER);

        codeVbox = new CodeVbox();

        testValidator = new TestValidatorLayout(validatorsLayout);

        nextRoundButton = new StyledButton("NEXT ROUND");
        nextRoundButton.setMinWidth(200);


        buttonsVbox = new GuessCodeLayout();

        this.getChildren().add(codeVbox);
        this.getChildren().add(testValidator);
        this.getChildren().add(nextRoundButton);
        this.getChildren().add(buttonsVbox);
    }

    public CodeVbox getCodeVbox() {
        return codeVbox;
    }

    public TestValidatorLayout getTestValidator() {
        return testValidator;
    }

    public StyledButton getNextRoundButton() {
        return nextRoundButton;
    }

    public GuessCodeLayout getButtonsVbox() {
        return buttonsVbox;
    }
}
