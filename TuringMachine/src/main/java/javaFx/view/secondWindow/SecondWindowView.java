package javaFx.view.secondWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SecondWindowView extends VBox {
    private final StyledButton chooseProblemButton;

    private final StyledButton randomProblemButton;

    public SecondWindowView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        chooseProblemButton = new StyledButton("CHOOSE PROBLEM");
        chooseProblemButton.setMinWidth(200);

        randomProblemButton = new StyledButton("RANDOM PROBLEM");
        randomProblemButton.setMinWidth(200);
        this.getChildren().addAll(chooseProblemButton, randomProblemButton);
    }

    public Button getChooseProblemButton() {
        return chooseProblemButton;
    }

    public Button getRandomProblemButton() {
        return randomProblemButton;
    }
}