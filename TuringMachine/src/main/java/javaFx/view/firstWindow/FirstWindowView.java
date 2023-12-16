package javaFx.view.firstWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents the view for the first window in the Turing Machine game.
 * This window typically contains the game title and a "Play" button.
 */
public class FirstWindowView extends VBox {
    private final StyledButton playButton;

    /**
     * Constructs a new instance of the FirstWindowView.
     * Initializes the layout, sets the spacing and alignment, and adds
     * a title label and a "Play" button to the view.
     */
    public FirstWindowView() {
        this.setSpacing(30);
        this.setAlignment(Pos.CENTER);

        Label gameTitle = new Label("TURING MACHINE");
        gameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        playButton = new StyledButton("PLAY");
        playButton.setMinWidth(200);

        this.getChildren().addAll(gameTitle, playButton);
    }

    /**
     * Gets the "Play" button from the view.
     *
     * @return The "Play" button.
     */
    public StyledButton getPlayButton() {
        return playButton;
    }
}
