package javaFx.view.secondWindow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SecondWindowView extends VBox {
    private Button chooseProblemButton;

    private Button autoChooseButton;

    public SecondWindowView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        chooseProblemButton = new Button("Choose Problem");

        autoChooseButton = new Button("Auto Choose");
        this.getChildren().addAll(chooseProblemButton, autoChooseButton);
    }

    public Button getChooseProblemButton() {
        return chooseProblemButton;
    }

    public Button getAutoChooseButton() {
        return autoChooseButton;
    }
}