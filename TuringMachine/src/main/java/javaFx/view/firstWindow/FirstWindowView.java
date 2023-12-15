package javaFx.view.firstWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FirstWindowView extends VBox {
    private Label gameTitle;
    private StyledButton playButton;

    public FirstWindowView() {
        this.setSpacing(30);
        this.setAlignment(Pos.CENTER);

        gameTitle = new Label("TURING MACHINE");
        gameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));


        playButton = new StyledButton("Play");
        playButton.setMinWidth(200);

        this.getChildren().addAll(gameTitle, playButton);

    }

    public Label getGameTitle() {
        return gameTitle;
    }

    public StyledButton getPlayButton() {
        return playButton;
    }
}



