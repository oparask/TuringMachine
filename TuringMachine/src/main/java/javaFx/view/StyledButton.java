package javaFx.view;
import javafx.scene.control.Button;
/**
 * Customized button with dynamic styling for a visually appealing user interface.
 * The button changes its appearance based on mouse interactions (hover, exit, and click).
 */
public class StyledButton extends Button {
    private boolean buttonPressed;

    /**
     * Constructs a new StyledButton with the specified button message.
     *
     * @param buttonMessage The text to be displayed on the button.
     */
    public StyledButton(String buttonMessage) {
        buttonPressed = false;
        setText(buttonMessage);
        updateStyle();

        setOnMouseEntered(e -> setStyleOnHover());
        setOnMouseExited(e -> setStyleOnExit());
        setOnMousePressed(e -> setStyleOnClick());
    }

    private void updateStyle() {
        setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: darkgreen;" +
                        "-fx-text-fill: darkgreen;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;"
        );
    }

    private void setStyleOnHover() {
        setStyle(
                "-fx-background-color: lightgrey;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: grey;" +
                        "-fx-text-fill: darkgreen;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;"
        );
    }

    private void setStyleOnExit() {
        updateStyle();
    }

    private void setStyleOnClick() {
        setStyle(
                "-fx-background-color: green;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: darkgreen;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;"
        );
    }
}
