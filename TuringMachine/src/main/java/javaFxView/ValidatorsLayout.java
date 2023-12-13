package javaFxView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.validators.Validator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ValidatorsLayout extends HBox {

    List<String> validatorsLabels;
    public ValidatorsLayout(Validator[] validators) {
        this.setStyle("-fx-background-color: green;");
        this.setAlignment(Pos.TOP_CENTER);

        validatorsLabels = new ArrayList<>();

        int indexValidator = 0;
        for (Validator validator : validators) {
            // Créer une VBox pour chaque validator et ajouter des éléments associés
            VBox validatorBox = new VBox();
            validatorBox.setAlignment(Pos.CENTER);
            validatorBox.setPadding(new Insets(10));

            createRobotImage(indexValidator, validatorBox);
            createValidatorImage(validator.getImagePath(), validatorBox);

            this.getChildren().add(validatorBox);

            indexValidator++;
        }

    }

    public List<String> getValidatorsLabels() {
        return validatorsLabels;
    }

    private void createRobotImage(int indexValidator, VBox validatorBox) {
        InputStream robotImageStream = null;
        Label validatorLabel = new Label();
        validatorLabel.setTextFill(Color.WHITE);
        validatorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Ajustez la police, le style et la taille selon vos besoins


        switch (indexValidator) {
            case 0 -> {
                robotImageStream = getClass().getResourceAsStream("/robotA.png");
                validatorLabel.setText("A");
            }
            case 1 -> {
                robotImageStream = getClass().getResourceAsStream("/robotB.png");
                validatorLabel.setText("B");
            }
            case 2 -> {
                robotImageStream = getClass().getResourceAsStream("/robotC.png");
                validatorLabel.setText("C");
            }
            case 3 -> {
                robotImageStream = getClass().getResourceAsStream("/robotD.png");
                validatorLabel.setText("D");
            }
            case 4 -> {
                robotImageStream = getClass().getResourceAsStream("/robotE.png");
                validatorLabel.setText("E");
            }
            case 5 -> {
                robotImageStream = getClass().getResourceAsStream("/robotF.png");
                validatorLabel.setText("F");
            }
        }

        validatorsLabels.add(validatorLabel.getText());

        ImageView robotImage = new ImageView(new Image(robotImageStream));


        robotImage.setFitHeight(60);
        robotImage.setFitWidth(60);

        validatorBox.getChildren().addAll(robotImage, validatorLabel);


    }

    private void createValidatorImage(String imagePath, VBox validatorBox) {
        InputStream validatorImageStream = getClass().getResourceAsStream(imagePath);
        if (validatorImageStream != null) {
            ImageView validatorImage = new ImageView(new Image(validatorImageStream));
            validatorImage.setFitWidth(190);
            validatorImage.setFitHeight(150);

            validatorBox.getChildren().add(validatorImage);
        } else {
            System.err.println("Could not load image: " + imagePath);
        }
    }
}
