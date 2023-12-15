package javaFx.view.fourthWindow;
import javafx.scene.control.Button;


public class StyledButton extends Button {
    private boolean buttonPressed;

    public StyledButton(String buttonMessage) {
        buttonPressed = false;
        setText(buttonMessage);
        updateStyle();

        // Ajouter des gestionnaires d'événements pour les états de survol et de clic
        setOnMouseEntered(e -> setStyleOnHover());
        setOnMouseExited(e -> setStyleOnExit());
        setOnMousePressed(e -> {
            buttonPressed = !buttonPressed;
            updateStyle();
        });
    }

    private void updateStyle() {
        if (buttonPressed) {
            setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #C0C0C0, #C0C0C0);" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;" +
                            "-fx-border-color: darkgreen;" +
                            "-fx-text-fill: green;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 14px;"
            );
        } else {
            setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #FFFFFF, #FFFFFF);" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;" +
                            "-fx-border-color: darkgreen;" +
                            "-fx-text-fill: green;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 14px;"
            );
        }
    }

    private void setStyleOnHover() {
        setStyle(
                "-fx-background-color: linear-gradient(to bottom, #E0E0E0, #E0E0E0);" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: darkgreen;" +
                        "-fx-text-fill: green;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;"
        );
    }

    private void setStyleOnExit() {
        if (!buttonPressed) {
            setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #FFFFFF, #FFFFFF);" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;" +
                            "-fx-border-color: darkgreen;" +
                            "-fx-text-fill: green;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 14px;"
            );
        }
    }
}
