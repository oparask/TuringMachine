package javaFxView;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        displayValidators(displayProblems);
    }

    private void startNewGameAuto(Stage problemChoiceStage) {
        Random random = new Random();
        int autoChosenProblemIndex = random.nextInt(gameFacade.getProblems().size());
        gameFacade.startNewGame(autoChosenProblemIndex);
        updateStatusLabel("Starting a new game with automatic problem choice");
        displayValidators(problemChoiceStage);
    }

    private void displayValidators(Stage displayProblems) {
        displayProblems.close();

        Stage displayValidators = new Stage();
        VBox root = new VBox();
        root.setStyle("-fx-background-color: green;");

        Validator[] validators = gameFacade.getProblemValidators();

        HBox validatorsLayout = new HBox();
        validatorsLayout.setStyle("-fx-background-color: green;");

        validatorsLayout.setAlignment(Pos.TOP_CENTER);


        int indexValidator = 0;
        for (Validator validator : validators) {
            String validatorImagePath = validator.getImagePath();

            if (validatorImagePath != null) {
                InputStream validatorImageStream = getClass().getResourceAsStream(validatorImagePath);
                if (validatorImageStream != null) {
                    ImageView validatorImage = new ImageView(new Image(validatorImageStream));
                    validatorImage.setFitWidth(190);
                    validatorImage.setFitHeight(150);


                    // Créer une VBox pour chaque validator et ajouter des éléments associés
                    VBox validatorBox = new VBox();
                    validatorBox.setAlignment(Pos.CENTER);
                    validatorBox.setPadding(new Insets(10));

                    //
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
                    ImageView robotImage = new ImageView(new Image(robotImageStream));
                    robotImage.setFitHeight(60);
                    robotImage.setFitWidth(60);

                    validatorBox.getChildren().addAll(robotImage, validatorLabel, validatorImage);
                    validatorsLayout.getChildren().add(validatorBox);

                    // Récupérer les dimensions de l'image
                    Bounds bounds = validatorImage.getBoundsInLocal();

                    // Appliquer la hauteur de la première image à la HBox
                    if (validatorsLayout.getChildren().size() == 1) {
                        validatorsLayout.setMinHeight(bounds.getHeight());
                    }
                } else {
                    System.err.println("Could not load image: " + validatorImagePath);
                }
            } else {
                System.err.println("Image path is null for validator");
            }
            indexValidator++;
        }

        ScrollPane scrollPaneHbox = new ScrollPane(validatorsLayout);
        scrollPaneHbox.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneHbox.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu
        root.getChildren().add(scrollPaneHbox);


        Label scoreLabel = new Label();
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        // Mise à jour du texte du Label du score
        scoreLabel.setText("SCORE: " + gameFacade.getScore() + "    ROUNDS: " + gameFacade.getRounds().size());

        // Ajouter le Label du score à la VBox
        root.getChildren().add(scoreLabel);



        //code

        






        // Ajouter le ScrollPane à la VBox
        ScrollPane scrollPaneRoot = new ScrollPane(root);
        scrollPaneRoot.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneRoot.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu

        // Ajouter le ScrollPane à la scène ou à un autre conteneur si nécessaire
        Scene scene = new Scene(scrollPaneRoot, 500, 425);

        displayValidators.setScene(scene);
        displayValidators.show();
    }


    private void updateStatusLabel(String message) {
        System.out.println(message);
    }


}


