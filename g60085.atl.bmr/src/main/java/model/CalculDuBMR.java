package model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalculDuBMR extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calcul du MBR");
        VBox root = new VBox(10); // VBox for the main layout
        root.setPadding(new Insets(15));

        HBox hbox = new HBox(10); // HBox for containing two GridPanes

        GridPane gridPane1 = new GridPane(); // First GridPane
        gridPane1.setHgap(10);
        gridPane1.setVgap(5);

        Label titleGridPane1 = new Label("Données");
        titleGridPane1.setUnderline(true);

        Label height = new Label("Taille (cm)");
        TextField heightField = new TextField();

        Label weight = new Label("Poids (kg)");
        TextField weightField = new TextField();

        Label age = new Label("Age (années)");
        TextField ageField = new TextField();

        Label sexe = new Label("Sexe");
        RadioButton femaleRadioButton = new RadioButton("Femme");
        RadioButton maleRadioButton = new RadioButton("Homme");
        HBox radioBox = new HBox(10); // Créez une HBox pour les boutons radio avec un espacement de 10 pixels
        radioBox.getChildren().addAll(femaleRadioButton, maleRadioButton);
        ToggleGroup genderGroup = new ToggleGroup(); //'assurer qu'un seul d'entre eux peut être sélectionné à la fois.
        femaleRadioButton.setToggleGroup(genderGroup);
        maleRadioButton.setToggleGroup(genderGroup);

        Label lifeStyle = new Label("Style de vie");
        ChoiceBox<String> activityLevelChoice = new ChoiceBox<>();
        activityLevelChoice.getItems().addAll("Sédentaire", "Peu actif", "Actif", "Fort actif", "Extrêmement actif");
        activityLevelChoice.setValue("Sédentaire"); // Définir une valeur par défaut si nécessaire

        gridPane1.add(titleGridPane1, 0, 0);

        gridPane1.add(height, 0, 1);
        gridPane1.add(heightField, 1, 1);

        gridPane1.add(weight, 0, 2);
        gridPane1.add(weightField, 1, 2);

        gridPane1.add(age, 0, 3);
        gridPane1.add(ageField, 1, 3);

        gridPane1.add(sexe, 0, 4);
        gridPane1.add(radioBox, 1, 4);

        gridPane1.add(lifeStyle, 0, 5);
        gridPane1.add(activityLevelChoice, 1, 5);

        //2ème grid pane
        GridPane gridPane2 = new GridPane(); // Second GridPane
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);

        Label titleGridPane2 = new Label("Résultats");
        titleGridPane2.setUnderline(true);

        Label resultBMR = new Label("BMR");
        TextField resultBMRField = new TextField();

        Label calories = new Label("Calories");
        TextField caloriesField = new TextField();


        gridPane2.add(titleGridPane2, 0, 0);

        gridPane2.add(resultBMR, 0, 1);
        gridPane2.add(resultBMRField, 1, 1);

        gridPane2.add(calories, 0, 2);
        gridPane2.add(caloriesField, 1, 2);


        System.out.println(root.getWidth());
        // Créez un bouton avec l'étiquette "Calcul du BMR"
        Button calculateBMRButton = new Button("Calcul du BMR");

        int sizeButton = 470;
        calculateBMRButton.setMinWidth(sizeButton);

        hbox.getChildren().addAll(gridPane1, gridPane2);

        root.getChildren().addAll(hbox);
        root.getChildren().add(calculateBMRButton);
        // Faites en sorte que le bouton s'étire pour remplir la largeur de la VBox


        Scene scene = new Scene(root, 500, 250); // Augmentation de la largeur (600) et de la hauteur (400)

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
