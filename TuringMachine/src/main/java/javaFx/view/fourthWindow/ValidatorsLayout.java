package javaFx.view.fourthWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.validators.Validator;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ValidatorsLayout extends HBox {
    private Map<Button, Boolean> buttonClickedMap;

    public ValidatorsLayout(Validator[] validators) {
        this.setStyle("-fx-background-color: green;");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(10);
        buttonClickedMap = new HashMap<>();

        int indexValidator = 0;
        for (Validator validator : validators) {
            // Create a VBox for each validator and add associated elements.
            VBox validatorBox = new VBox();
            validatorBox.setAlignment(Pos.CENTER);
            validatorBox.setPadding(new Insets(5));

            createRobotImage(indexValidator, validatorBox);
            createValidatorImage(validator.getImagePath(), validatorBox);

            this.getChildren().add(validatorBox);

            indexValidator++;
        }

    }

    private void createRobotImage(int indexValidator, VBox validatorBox) {
        InputStream robotImageStream = getRobotImageStream(indexValidator);
        Label validatorLabel = createValidatorLabel(indexValidator);
        Button robotButton = createRobotButton(indexValidator, robotImageStream, validatorLabel);

        validatorBox.getChildren().addAll(robotButton, validatorLabel);
    }

    private InputStream getRobotImageStream(int indexValidator) {
        String robotLetter = String.valueOf((char) ('A' + indexValidator));
        return getClass().getResourceAsStream("/robot" + robotLetter + ".png");
    }

    private Label createValidatorLabel(int indexValidator) {
        Label validatorLabel = new Label();
        validatorLabel.setTextFill(Color.WHITE);
        validatorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        validatorLabel.setText(String.valueOf((char) ('A' + indexValidator)));
        return validatorLabel;
    }

    private Button createRobotButton(int indexValidator, InputStream robotImageStream, Label validatorLabel) {
        Button robotButton = new Button();
        robotButton.setUserData(indexValidator);

        ImageView robotImage = new ImageView(new Image(robotImageStream));
        robotImage.setPreserveRatio(true);
        robotImage.setFitHeight(60);
        robotButton.setGraphic(robotImage);
        robotButton.setStyle("-fx-padding: 0; -fx-margin: 0;"+
                "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-color: red;");

        initializeButtonHandlers(robotButton, robotImage, validatorLabel);
        buttonClickedMap.put(robotButton, false);

        return robotButton;
    }

    private void createValidatorImage(String imagePath, VBox validatorBox) {
        InputStream validatorImageStream = getClass().getResourceAsStream(imagePath);
        if (validatorImageStream != null) {
            ImageView validatorImage = new ImageView(new Image(validatorImageStream));
            validatorImage.setFitWidth(190);
            validatorImage.setFitHeight(150);

            // Create a Rectangle with rounded corners
            Rectangle roundedRect = new Rectangle(190, 150);
            roundedRect.setArcWidth(10);
            roundedRect.setArcHeight(10);

            // Use the Rectangle as a clip for the ImageView
            validatorImage.setClip(roundedRect);

            validatorBox.getChildren().add(validatorImage);
        }
    }


    private void initializeButtonHandlers(Button robotButton, ImageView robotImage, Label validatorLabel) {
        robotButton.setOnMouseEntered(e -> robotImage.setEffect(new ColorAdjust(0.2, 0, 0, 0)));

        robotButton.setOnMouseExited(e -> {
            if (!buttonClickedMap.get(robotButton)) {
                robotImage.setEffect(null);
            }
        });

        robotButton.setOnAction(e -> {
            resetButtons();
            robotImage.setEffect(new ColorAdjust(0.2, 0, 0, 0));
            buttonClickedMap.put(robotButton, true);
            System.out.println("Robot button " + validatorLabel.getText() + " was selected !");
        });
    }

    private void resetButtons() {
        for (Map.Entry<Button, Boolean> entry : buttonClickedMap.entrySet()) {
            Button button = entry.getKey();
            ImageView buttonImage = (ImageView) button.getGraphic();
            buttonImage.setEffect(null);
            entry.setValue(false);
        }
    }

    public Integer getIndexOfClickedButton() {
        for (Map.Entry<Button, Boolean> entry : buttonClickedMap.entrySet()) {
            if (entry.getValue()) {
                // A button has been clicked, retrieve the button and retrieve its index.
                Button buttonSelected = entry.getKey();
                return (Integer) buttonSelected.getUserData();
            }
        }
        return null;
    }

    public Button getClickedButton() {
        for (Map.Entry<Button, Boolean> entry : buttonClickedMap.entrySet()) {
            if (entry.getValue()) {
                // A button has been clicked
                return entry.getKey();
            }
        }
        return null;
    }


}
