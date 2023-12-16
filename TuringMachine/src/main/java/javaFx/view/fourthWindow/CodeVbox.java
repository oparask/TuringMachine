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

public class CodeVbox extends VBox {
    private Label testCodeLabel;
    private ImageView codeFormsImage;
    private GridPane codeButtons;

    private StyledButton enterCodeButton;
    private List<Button> firstDigitButtons, secondDigitButtons, thirdDigitButtons;
    private Button firstDigit, secondDigit, thirdDigit;


    public CodeVbox() {
        this.setSpacing(15);
        this.setMinWidth(170);
        this.setMinHeight(300);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: white;");


        firstDigitButtons = new ArrayList<>();
        secondDigitButtons = new ArrayList<>();
        thirdDigitButtons = new ArrayList<>();

        testCodeLabel = new Label("Test a code");
        testCodeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        codeFormsImage = new ImageView(new Image("/code_forms.png"));
        codeFormsImage.setFitHeight(20);
        codeFormsImage.setFitWidth(90);

        codeButtons = new GridPane();
        codeButtonsGridPane(codeButtons);

        enterCodeButton = new StyledButton("Enter this code");
        enterCodeButton.setMaxWidth(120);

        firstDigit = null;
        secondDigit = null;
        thirdDigit = null;

        this.getChildren().addAll(testCodeLabel, codeFormsImage, codeButtons, enterCodeButton);
    }


    private void codeButtonsGridPane(GridPane gridPane) {
        gridPane.setMaxWidth(150);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                int number = 5 - row; // Les chiffres décroissants de 5 à 1
                Button numberButton = new Button(Integer.toString(number));
                numberButton.addEventHandler(ActionEvent.ACTION,new digitButtonClickHandler());
                if (col == 0) {
                    firstDigitButtons.add(numberButton);
                } else if (col == 1) {
                    secondDigitButtons.add(numberButton);
                } else {
                    thirdDigitButtons.add(numberButton);
                }
                gridPane.add(numberButton, col, row); // Commence à la ligne 3
            }
        }
    }


    private class digitButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            // Réinitialiser tous les boutons de la liste à leur couleur d'origine
            resetAllButtonsFromList(clickedButton);

            // Rendre le bouton cliqué en gris et le stocker dans l'attribut correspondant
            setGrayedButton(clickedButton);
        }

        // Méthode pour réinitialiser tous les boutons de la liste à leur couleur d'origine
        private void resetAllButtonsFromList(Button clickedButton) {
            // Identifier de quelle liste de bouton il fait partie et réinitialiser uniquement cette liste
            if (firstDigitButtons.contains(clickedButton)) {
                resetButtons(firstDigitButtons);
            } else if (secondDigitButtons.contains(clickedButton)) {
                resetButtons(secondDigitButtons);
            } else if (thirdDigitButtons.contains(clickedButton)) {
                resetButtons(thirdDigitButtons);
            }
        }

        // Méthode pour réinitialiser une liste de boutons à leur couleur d'origine
        private void resetButtons(List<Button> buttons) {
            for (Button button : buttons) {
                button.setStyle(""); // Réinitialiser le style à la valeur par défaut // Remplacez defaultColor par la couleur d'origine
            }
        }



        // Méthode pour rendre un bouton en gris et le stocker dans l'attribut correspondant
        private void setGrayedButton(Button button) {
            button.setStyle("-fx-background-color: gray;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;");

            // Stocker le bouton dans l'attribut correspondant
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



    public StyledButton getEnterCodeButton() {
        return enterCodeButton;
    }

    public Button getFirstDigit() {
        return firstDigit;
    }

    public Button getSecondDigit() {
        return secondDigit;
    }

    public Button getThirdDigit() {
        return thirdDigit;
    }

    public void resetListButtons(List<Button> buttonList){
        for(Button button : buttonList){
            button.setStyle("");
        }
    }
    public void resetView (){
        resetListButtons(firstDigitButtons);
        resetListButtons(secondDigitButtons);
        resetListButtons(thirdDigitButtons);

        firstDigit = null;
        secondDigit = null;
        thirdDigit = null;

        enterCodeButton.setStyle("");

    }
}