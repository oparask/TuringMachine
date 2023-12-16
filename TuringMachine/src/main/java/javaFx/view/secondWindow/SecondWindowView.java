package javaFx.view.secondWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
/**
 * Represents the view for the second window in the application.
 * Extends VBox to organize UI components in a vertical box layout.
 */
public class SecondWindowView extends VBox {
    private final StyledButton chooseProblemButton;
    private final StyledButton randomProblemButton;

    /**
     * Constructs a new instance of the SecondWindowView.
     * Sets up the layout, spacing, and initializes UI components such as buttons.
     */
    public SecondWindowView() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        chooseProblemButton = new StyledButton("CHOOSE PROBLEM");
        chooseProblemButton.setMinWidth(200);

        randomProblemButton = new StyledButton("RANDOM PROBLEM");
        randomProblemButton.setMinWidth(200);

        this.getChildren().addAll(chooseProblemButton, randomProblemButton);
    }

    /**
     * Retrieves the "Choose Problem" button from the view.
     *
     * @return The "Choose Problem" button.
     */
    public Button getChooseProblemButton() {
        return chooseProblemButton;
    }

    /**
     * Retrieves the "Random Problem" button from the view.
     *
     * @return The "Random Problem" button.
     */
    public Button getRandomProblemButton() {
        return randomProblemButton;
    }
}
