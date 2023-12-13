package javaFxView;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.GameFacade;
import model.problems.Problem;
import model.validators.Validator;

import java.io.InputStream;
import java.util.List;
import java.util.Random;


public class AppJavafx extends Application {
    private GameFacade gameFacade;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameFacade = new GameFacade();

        primaryStage.setTitle("TURING MACHINE GAME");

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> openProblemChoiceWindow(primaryStage));

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(playButton);

        Scene scene = new Scene(layout, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openProblemChoiceWindow(Stage primaryStage) {
        primaryStage.close();

        Stage problemChoiceStage = new Stage();
        problemChoiceStage.setTitle("Choose Problem");

        Button chooseProblemButton = new Button("Choose Problem");
        chooseProblemButton.setOnAction(e -> displayProblems(problemChoiceStage));

        Button autoChooseButton = new Button("Auto Choose");
        autoChooseButton.setOnAction(e -> startNewGameAuto(problemChoiceStage));

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(chooseProblemButton, autoChooseButton);

        Scene scene = new Scene(layout, 400, 200);
        problemChoiceStage.setScene(scene);
        problemChoiceStage.show();
    }

    private void displayProblems(Stage problemChoiceStage) {
        problemChoiceStage.close();

        Stage displayProblems = new Stage();
        List<Problem> problems = gameFacade.getProblems();

        VBox problemButtonsLayout = new VBox(10);
        problemButtonsLayout.setAlignment(Pos.CENTER);

        for (Problem problem : problems) {
            Button problemButton = new Button("Problem " + problem.getProblemNumber() +
                    " (Difficulty: " + problem.getDifficultyLevel() +
                    ", Luck: " + problem.getLuckDegree() + ")");
            problemButton.setOnAction(e -> startNewGameWithChoice(displayProblems, problem));
            problemButtonsLayout.getChildren().add(problemButton);
        }

        ScrollPane scrollPane = new ScrollPane(problemButtonsLayout);

        Scene scene = new Scene(scrollPane, 400, 400);
        displayProblems.setScene(scene);
        displayProblems.show();
    }

    private void startNewGameWithChoice(Stage displayProblems, Problem selectedProblem) {
        gameFacade.startNewGame(selectedProblem.getProblemNumber());
        updateStatusLabel("Starting a new game with manual problem choice");
        displayGameProcess(displayProblems);
    }

    private void startNewGameAuto(Stage problemChoiceStage) {
        Random random = new Random();
        int autoChosenProblemIndex = random.nextInt(gameFacade.getProblems().size());
        gameFacade.startNewGame(autoChosenProblemIndex + 1);
        updateStatusLabel("Starting a new game with automatic problem choice");
        displayGameProcess(problemChoiceStage);
    }

    private void displayGameProcess(Stage displayProblems) {
        displayProblems.close();

        Stage GameProcessParts = new Stage();

        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: green;");

        ValidatorsLayout validatorsLayout = new ValidatorsLayout(gameFacade.getProblemValidators());
        root.getChildren().add(validatorsLayout);


        Label scoreLayout = new Label();
        scoreLayout.setTextFill(Color.WHITE);
        scoreLayout.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scoreLayout.setText("SCORE: " + gameFacade.getScore() + "    ROUNDS: " + gameFacade.getRounds().size());
        root.getChildren().add(scoreLayout);


        HBox codeLayout = new HBox(150);
        codeLayout.setAlignment(Pos.CENTER);
        root.getChildren().add(codeLayout);

        CodeVbox  codeVbox = new CodeVbox();
        codeLayout.getChildren().add(codeVbox);



        // Créer une grille pour testValidator
        GridPane testValidator = new GridPane();
        testValidator.setPadding(new Insets(0, 20, 0, 20)); // Remplacez ces valeurs par celles qui conviennent à votre mise en page

        testValidator.setAlignment(Pos.CENTER);
        testValidator.setHgap(5);
        testValidator.setVgap(50);
        testValidator.setStyle("-fx-background-color: white;");

     /*   ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        testValidator.getColumnConstraints().addAll(columnConstraints, columnConstraints);*/

        codeLayout.getChildren().add(testValidator);

        Label chooseValidator = new Label("Choose validator");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(validatorsLayout.getValidatorsLabels());
        choiceBox.setStyle("-fx-text-fill: black;");
        chooseValidator.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        testValidator.add(chooseValidator, 0, 0);
        testValidator.add(choiceBox, 1, 0);


        Button testButton = new Button("Test");
        testButton.setAlignment(Pos.CENTER);
        testButton.setMinWidth(200);
        testValidator.add(testButton, 0, 1, 2, 1); // Couvre 2 colonnes

        Label resultLabel = new Label("Result");
        GridPane.setHalignment(resultLabel, HPos.RIGHT);
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TextField testResult = new TextField();
        testResult.setMaxWidth(50);
        testValidator.add(resultLabel, 0, 2);
        testValidator.add(testResult, 1, 2);




        // Ajouter le ScrollPane à la VBox
        ScrollPane scrollPaneRoot = new ScrollPane(root);
        scrollPaneRoot.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneRoot.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu

        // Ajouter le ScrollPane à la scène
        Scene scene = new Scene(scrollPaneRoot, 500, 425);

        GameProcessParts.setScene(scene);
        GameProcessParts.show();
    }


    private void updateStatusLabel(String message) {
        System.out.println(message);
    }


}


