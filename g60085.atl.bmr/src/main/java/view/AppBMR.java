package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BMRbuttonHandler;
import javafx.scene.input.KeyEvent;
import model.CalculBMR;

/**
 * A JavaFX application for calculating Basal Metabolic Rate (BMR).
 *
 * This application allows users to input their height, weight, age, and gender to calculate their BMR
 * and daily calorie needs based on their activity level.
 *
 *
 * @author [g60085]
 * @version 1.0
 * @since [03/11/2023]
 */
public class AppBMR extends Application {

    /**
     * The main entry point for launching the JavaFX application.
     * This method is called by the Java runtime when the application starts.
     * It initializes and launches the JavaFX Application Thread, which, in turn,
     * calls the overridden start method to start the JavaFX application.
     *
     * @param args An array of command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and launches the JavaFX application.
     * This method is called by the JavaFX framework when the application is started.
     * It sets up the main user interface components, including layout, buttons, event handlers,
     * and menu options. Additionally, it defines how the application responds to user input,
     * such as button clicks, text field changes, and menu selections.
     *
     * @param primaryStage The primary stage or window for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Application initialization and setup
        primaryStage.setTitle("Calcul du BMR");
        VBox root = new VBox(15); // VBox for the main layout
        root.setPadding(new Insets(15));

        HBox hbox = new HBox(15); // HBox for containing two GridPanes
        hbox.setAlignment(Pos.CENTER);

        GridPane1 gridPane1 = new GridPane1();
        GridPane2 gridPane2 = new GridPane2();

        CalculBMR calculBMR = new CalculBMR(); //Observable
        DisplayResultBMR DisplayResultBMR = new DisplayResultBMR(calculBMR, gridPane2); //Observer

        // Create and define behavior when button is clicked
        Button bmrButton = new Button("Calcul du BMR");
        bmrButton.setOnAction(new BMRbuttonHandler(gridPane1, gridPane2, calculBMR));

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event -> {
            // Clear all input fields and error labels
            gridPane1.getHeightField().clear();
            gridPane1.getWeightField().clear();
            gridPane1.getAgeField().clear();
            gridPane1.getFemaleRadioButton().setSelected(false);
            gridPane1.getMaleRadioButton().setSelected(false);
            gridPane1.setHeightErrorLabel("");
            gridPane1.setWeightErrorLabel("");
            gridPane1.setAgeErrorLabel("");
            gridPane1.setSexErrorLabel("");
            gridPane2.getResultBMRField().clear();
            gridPane2.getCaloriesField().clear();
        });

        // Define behavior for dynamic resizing of buttons
        root.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            double padding = root.getPadding().getLeft() + root.getPadding().getRight();
            bmrButton.setMinWidth(newWidth.doubleValue() - padding);
            clearButton.setMinWidth(newWidth.doubleValue() - padding);
        });

        // Event handler for height field
        gridPane1.getHeightField().textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane1.setHeightErrorLabel("");
            if (newValue.length() > 3) {
                // If the length exceeds the limit, truncate the text
                gridPane1.getHeightField().setText(newValue.substring(0, 3));
            }
        });

        // Filter for height field
        gridPane1.getHeightField().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); //Consume the event if the character is not a digit
            }
        });

        // Event handler for weight field
        gridPane1.getWeightField().textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane1.setWeightErrorLabel("");
            if (newValue.length() > 7) {
                gridPane1.getWeightField().setText(newValue.substring(0, 7));
            }
        });

        // Filter for weight field
        gridPane1.getWeightField().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9.]")) {
                event.consume();
            }
        });

        // Event handler for age field
        gridPane1.getAgeField().textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane1.setAgeErrorLabel("");
            if (newValue.length() > 3) {
                gridPane1.getAgeField().setText(newValue.substring(0, 3));
            }
        });

        // Filter for age field
        gridPane1.getAgeField().addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });

        // Event handler for radio button
        gridPane1.getFemaleRadioButton().selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected) {
                gridPane1.setSexErrorLabel("");
            }
        });

        // Event handler for radio button
        gridPane1.getMaleRadioButton().selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected) {
                gridPane1.setSexErrorLabel("");
            }
        });

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");

        //Add the exit option to the 'File' menu
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        // Event handler for exit menu item
        exitMenuItem.setOnAction(e -> {
            primaryStage.close();
        });

        hbox.getChildren().addAll(gridPane1, gridPane2);
        root.getChildren().addAll(menuBar, hbox, bmrButton, clearButton);

        // Application setup completed, launch the JavaFX application
        Scene scene = new Scene(root, 500, 425);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
