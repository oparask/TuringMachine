package javaFx;

import javaFx.view.PopOut;
import javaFx.view.firstWindow.FirstWindowView;
import javaFx.view.fourthWindow.FourthWindowController;
import javaFx.view.fourthWindow.FourthWindowView;
import javaFx.view.secondWindow.SecondWindowController;
import javaFx.view.secondWindow.SecondWindowView;
import javaFx.view.thirdWindow.ThirdWindowController;
import javaFx.view.thirdWindow.ThirdWindowView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.GameFacade;

import static console.View.displayInvalidInput;
import static console.View.displayScore;

/**
 * The main class responsible for launching the Turing Machine Game application.
 * This class handles the initialization of the game facade and controls the
 * transition between different stages of the game.
 */
public class Main extends Application {
    /**
     * The game facade instance that manages the game state and logic.
     */
    private GameFacade gameFacade;

    /**
     * The entry point for the JavaFX application. It launches the JavaFX application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage with the first window when the application starts.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        gameFacade = new GameFacade();
        primaryStage.setTitle("TURING MACHINE GAME");

        FirstWindowView firstWindow = new FirstWindowView();
        firstWindow.getPlayButton().setOnAction(e -> openSecondWindow(primaryStage));

        Scene scene = new Scene(firstWindow, 1500, 600);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Closes the current stage and opens the second window for problem choice.
     *
     * @param primaryStage The current primary stage.
     */
    private void openSecondWindow(Stage primaryStage) {
        primaryStage.close();

        Stage problemChoiceStage = new Stage();
        problemChoiceStage.setTitle("PROBLEM CHOICE");

        SecondWindowView secondWindowView = new SecondWindowView();
        SecondWindowController secondWindowController = new SecondWindowController(secondWindowView, gameFacade);

        secondWindowView.getChooseProblemButton().setOnAction(e -> openThirdWindow(problemChoiceStage));
        secondWindowView.getRandomProblemButton().addEventHandler(ActionEvent.ACTION, e -> openFourthWindow(problemChoiceStage));


        Scene scene = new Scene(secondWindowView, 1500, 600);
        problemChoiceStage.setMaximized(true);
        problemChoiceStage.setScene(scene);
        problemChoiceStage.show();
    }

    /**
     * Closes the problem choice stage and opens the third window for displaying problems.
     *
     * @param problemChoiceStage The stage for problem choice.
     */
    private void openThirdWindow(Stage problemChoiceStage) {
        problemChoiceStage.close();

        Stage displayProblems = new Stage();
        displayProblems.setTitle("PROBLEMS");

        ThirdWindowView problemButtonsLayout = new ThirdWindowView(gameFacade.getProblems());
        ThirdWindowController thirdWindowController = new ThirdWindowController(problemButtonsLayout, gameFacade);
        for (Button button : problemButtonsLayout.getProblemButtons()) {
            button.addEventHandler(ActionEvent.ACTION, e -> openFourthWindow(displayProblems));
        }

        ScrollPane scrollPane = new ScrollPane(problemButtonsLayout);
        Scene scene = new Scene(scrollPane, 1500, 500);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        displayProblems.setMaximized(true);
        displayProblems.setScene(scene);
        displayProblems.show();
    }

    /**
     * Closes the problem display stage and opens the fourth window for the game process.
     *
     * @param displayProblems The stage for displaying problems.
     */
    private void openFourthWindow(Stage displayProblems) {
        displayProblems.close();

        Stage gameProcessParts = new Stage();
        gameProcessParts.setTitle("GUESS CODE");

        FourthWindowView fourthWindowView = new FourthWindowView(gameFacade);

        fourthWindowView.getGuessCodeButton().addEventHandler(ActionEvent.ACTION, e -> openNewInstance(gameProcessParts));
        fourthWindowView.getNextRoundButton().addEventHandler(ActionEvent.ACTION, e -> handleNextRoundButtonClick(gameProcessParts));
        FourthWindowController fourthWindowController = new FourthWindowController(fourthWindowView, gameFacade);

        ScrollPane scrollPaneRoot = new ScrollPane(fourthWindowView);
        scrollPaneRoot.setFitToWidth(true);
        scrollPaneRoot.setFitToHeight(true);

        Scene scene = new Scene(scrollPaneRoot, 1500, 600);

        gameProcessParts.setMaximized(true);
        gameProcessParts.setScene(scene);
        gameProcessParts.setScene(scene);
        gameProcessParts.show();
    }


    /**
     * Handles the logic when the "Next Round" button is clicked.
     *
     * @param gameProcessParts The stage for the game process.
     */
    private void handleNextRoundButtonClick(Stage gameProcessParts) {
        try {
            if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
                PopOut popOut = new PopOut("ERROR", "You haven't tested any validators for the round "
                        + gameFacade.getRounds().size() + "!", true);
                popOut.show();
                displayInvalidInput("You haven't yet chosen a validator for the round " + gameFacade.getRounds().size() + ".");
            } else {
                gameFacade.nextRound();

                gameProcessParts.close();
                openFourthWindow(new Stage());
                displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
            }
        }catch (Exception e) {
                e.printStackTrace();
                PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
                popOut.show();
            }
    }

    /**
     * Opens a new instance of the application.
     *
     * @param currentStage The current stage.
     */
    private void openNewInstance(Stage currentStage) {
        try {
            if (gameFacade.getUserCode() != null) {
                currentStage.close();

                Main newAppInstance = new Main();
                newAppInstance.start(new Stage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
            popOut.show();
        }
    }
}


