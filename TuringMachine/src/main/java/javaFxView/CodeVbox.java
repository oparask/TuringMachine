package javaFxView;

import javafx.geometry.Insets;
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
    private  ImageView codeFormsImage;
    private GridPane codeButtons;

    private Button enterCodeButton;
    private List<Button> firstDigitButtons;
    private  List<Button> secondDigitButtons;
    private  List<Button> thirdDigitButtons;


    public CodeVbox() {
        this.setSpacing(15);
        this.setMinWidth(150);
        this.setMinHeight(300);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: white;");

        testCodeLabel = new Label("Test a code");
        testCodeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        codeFormsImage = new ImageView(new Image("/code_forms.png"));
        codeFormsImage.setFitHeight(20);
        codeFormsImage.setFitWidth(90);

        codeButtons = new GridPane();
        firstDigitButtons = new ArrayList<>();
        secondDigitButtons = new ArrayList<>();
        thirdDigitButtons = new ArrayList<>();
        codeButtonsGridPane(codeButtons);


        enterCodeButton = new Button("Enter this code");
        //this.setMargin(enterCodeButton, new Insets(10, 0, 10, 0));

        this.getChildren().addAll(testCodeLabel,codeFormsImage, codeButtons, enterCodeButton);
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
                if(col == 0){
                    firstDigitButtons.add(numberButton);
                } else if(col ==1){
                    secondDigitButtons.add(numberButton);
                } else {
                    thirdDigitButtons.add(numberButton);
                }
                gridPane.add(numberButton, col, row); // Commence à la ligne 3
            }
        }
    }
}
