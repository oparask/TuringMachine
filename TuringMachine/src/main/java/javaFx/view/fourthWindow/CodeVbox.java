package javaFx.view.fourthWindow;

import javaFx.view.StyledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertical box (VBox) containing UI elements for entering a code in the Turing Machine game.
 * It includes buttons for digits, allowing users to create a three-digit code.
 */
public class CodeVbox extends VBox {
    private final StyledButton enterCodeButton;
    private final List<Button> firstDigitButtons;
    private final List<Button> secondDigitButtons;
    private final List<Button> thirdDigitButtons;
    private Button firstDigit, secondDigit, thirdDigit;

    /**
     * Constructs a new instance of the CodeVbox.
     * Initializes the layout, sets the spacing, alignment, style, and adds UI elements
     * like labels, image, digit buttons, and an "Enter Code" button.
     */
    public CodeVbox() {
        this.setSpacing(15);
        this.setMinWidth(170);
        this.setMinHeight(300);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10;");

        firstDigitButtons = new ArrayList<>();
        secondDigitButtons = new ArrayList<>();
        thirdDigitButtons = new ArrayList<>();

        Label testCodeLabel = new Label("Test a code");
        testCodeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        ImageView codeFormsImage = new ImageView(new Image("/code_forms.png"));
        codeFormsImage.setFitHeight(20);
        codeFormsImage.setFitWidth(90);

        GridPane codeButtons = new GridPane();
        codeButtonsGridPane(codeButtons);

        enterCodeButton = new StyledButton("Enter this code");
        enterCodeButton.setMaxWidth(120);

        firstDigit = null;
        secondDigit = null;
        thirdDigit = null;

        this.getChildren().addAll(testCodeLabel, codeFormsImage, codeButtons, enterCodeButton);
    }

    /**
     * Gets the "Enter Code" button from the view.
     *
     * @return The "Enter Code" button.
     */
    public StyledButton getEnterCodeButton() {
        return enterCodeButton;
    }

    /**
     * Gets the first digit button from the view.
     *
     * @return The first digit button.
     */
    public Button getFirstDigit() {
        return firstDigit;
    }

    /**
     * Gets the second digit button from the view.
     *
     * @return The second digit button.
     */
    public Button getSecondDigit() {
        return secondDigit;
    }

    /**
     * Gets the third digit button from the view.
     *
     * @return The third digit button.
     */
    public Button getThirdDigit() {
        return thirdDigit;
    }

    /**
     * Configures and populates a GridPane with buttons representing digits for entering a code.
     *
     * @param gridPane The GridPane to be configured.
     */
    private void codeButtonsGridPane(GridPane gridPane) {
        gridPane.setMaxWidth(150);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                int number = 5 - row;
                Button numberButton = new Button(Integer.toString(number));
                numberButton.addEventHandler(ActionEvent.ACTION, new CodeVbox.digitButtonClickHandler());

                if (col == 0) {
                    firstDigitButtons.add(numberButton);
                } else if (col == 1) {
                    secondDigitButtons.add(numberButton);
                } else {
                    thirdDigitButtons.add(numberButton);
                }

                gridPane.add(numberButton, col, row);
            }
        }
    }

    /**
     * Event handler for the digit buttons. Handles the button click event by resetting
     * other buttons in the same column and graying out the clicked button.
     */
    private class digitButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            resetAllButtonsFromList(clickedButton);
            setGrayedButton(clickedButton);
        }

        /**
         * Resets all buttons in the same column as the clicked button.
         *
         * @param clickedButton The button that was clicked.
         */
        private void resetAllButtonsFromList(Button clickedButton) {
            // Identify to which button list it belongs and reset only that list.
            if (firstDigitButtons.contains(clickedButton)) {
                resetButtons(firstDigitButtons);
            } else if (secondDigitButtons.contains(clickedButton)) {
                resetButtons(secondDigitButtons);
            } else if (thirdDigitButtons.contains(clickedButton)) {
                resetButtons(thirdDigitButtons);
            }
        }

        /**
         * Resets the styles of the given list of buttons to the default values.
         *
         * @param buttons The list of buttons to reset.
         */
        private void resetButtons(List<Button> buttons) {
            for (Button button : buttons) {
                button.setStyle(""); // Reset the style to the default value.
            }
        }

        /**
         * Sets the style of the clicked button to grayed out and stores the button
         * in the corresponding attribute based on its column index.
         *
         * @param button The clicked button.
         */
        private void setGrayedButton(Button button) {
            button.setStyle("-fx-background-color: gray;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;");

            // Store the button in the corresponding attribute.
            int buttonIndex = GridPane.getColumnIndex(button);
            if (buttonIndex == 0) {
                firstDigit = button;
                System.out.println(firstDigit);
            } else if (buttonIndex == 1) {
                secondDigit = button;
                System.out.println(secondDigit);
            } else if (buttonIndex == 2) {
                thirdDigit = button;
                System.out.println(thirdDigit);
            }
        }
    }
}