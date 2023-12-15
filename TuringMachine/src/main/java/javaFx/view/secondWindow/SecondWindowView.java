package javaFx.view.secondWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SecondWindowView extends VBox {
    private StyledButton chooseProblemButton;

    private StyledButton autoChooseButton;

    public SecondWindowView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        chooseProblemButton = new StyledButton("Choose Problem");
        chooseProblemButton.setMinWidth(200);

        autoChooseButton = new StyledButton("Auto Choose");
        autoChooseButton.setMinWidth(200);
        this.getChildren().addAll(chooseProblemButton, autoChooseButton);
    }

    public Button getChooseProblemButton() {
        return chooseProblemButton;
    }

    public Button getAutoChooseButton() {
        return autoChooseButton;
    }
}