package javaFx.view.fourthWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorsLayout extends HBox {
    private Map<Button, Boolean> buttonClickedMap; // Utiliser une Map pour suivre l'état de chaque bouton
   private List<String> validatorsLabels;


    public ValidatorsLayout(Validator[] validators) {
        this.setStyle("-fx-background-color: green;");
        this.setAlignment(Pos.TOP_CENTER);

        buttonClickedMap = new HashMap<>();
        validatorsLabels = new ArrayList<>();


        int indexValidator = 0;
        for (Validator validator : validators) {
            // Créer une VBox pour chaque validator et ajouter des éléments associés
            VBox validatorBox = new VBox();
            validatorBox.setAlignment(Pos.CENTER);
            validatorBox.setPadding(new Insets(0, 10, 0, 10)); // Haut, Droite, Bas, Gauche


            createRobotImage(indexValidator, validatorBox);
            createValidatorImage(validator.getImagePath(), validatorBox);

            this.getChildren().add(validatorBox);

            indexValidator++;
        }

    }

    public Map<Button, Boolean> getButtonClickedMap() {
        return buttonClickedMap;
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

        Button robotButton = new Button();
        // Ajouter des données utilisateur au bouton
        robotButton.setUserData(indexValidator);

        ImageView robotImage = new ImageView(new Image(robotImageStream));
        robotImage.setFitHeight(60);
        robotImage.setFitWidth(60);
        robotButton.setGraphic(robotImage);
        // Désactiver les marges et le rembourrage du bouton
        robotButton.setStyle("-fx-padding: 0; -fx-margin: 0;");
        // Initialiser l'état du bouton
        buttonClickedMap.put(robotButton, false);

        // Ajouter un effet de luminosité lorsque la souris passe au-dessus du bouton
        robotButton.setOnMouseEntered(e -> {
            robotImage.setEffect(new ColorAdjust(0.2, 0, 0, 0)); // Ajustez la luminosité selon vos préférences
        });

        // Retirer l'effet uniquement si le bouton n'a pas été cliqué
        robotButton.setOnMouseExited(e -> {
            if (!buttonClickedMap.get(robotButton)) {
                robotImage.setEffect(null);
            }
        });


        // Ajouter un gestionnaire d'événements pour le clic
        robotButton.setOnAction(e -> {
            // Désactiver l'effet pour tous les boutons
            resetButtons();

            // Activer l'effet uniquement pour le bouton cliqué
            robotImage.setEffect(new ColorAdjust(0.2, 0, 0, 0));
            // Mettre à jour l'état pour le bouton cliqué
            buttonClickedMap.put(robotButton, true);

            System.out.println("Bouton robot " + validatorLabel.getText() + " cliqué !");
        });
        validatorBox.getChildren().addAll(robotButton, validatorLabel);


    }

    private void resetButtons(){
        // Désactiver l'effet pour tous les boutons
        for (Map.Entry<Button, Boolean> entry : buttonClickedMap.entrySet()) {
            Button button = entry.getKey();
            ImageView buttonImage = (ImageView) button.getGraphic();
            buttonImage.setEffect(null);
            // Mettre à jour l'état pour chaque bouton
            buttonClickedMap.put(button, false);
        }

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


    public void resetView(){
        resetButtons();
    }
}
