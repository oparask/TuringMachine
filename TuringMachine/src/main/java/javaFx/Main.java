package javaFx;

import javaFx.view.firstWindow.FirstWindowView;
import javaFx.view.fourthWindow.FourthWindowView;
import javaFx.view.secondWindow.SecondWindowView;
import javaFx.view.thirdWindow.ThirdWindowView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.GameFacade;


public class Main extends Application {

    private GameFacade gameFacade;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gameFacade = new GameFacade();
        primaryStage.setTitle("TURING MACHINE GAME");

        FirstWindowView firstWindow = new FirstWindowView();
        firstWindow.getPlayButton().setOnAction(e -> openSecondWindow(primaryStage));

        Scene scene = new Scene(firstWindow, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openSecondWindow(Stage primaryStage) {
        primaryStage.close();

        Stage problemChoiceStage = new Stage();
        problemChoiceStage.setTitle("PROBLEM CHOICE");

        SecondWindowView SecondWindow = new SecondWindowView();
        SecondWindow.getChooseProblemButton().setOnAction(e -> openThirdWindow(problemChoiceStage));
        SecondWindow.getAutoChooseButton().setOnAction(e -> openFourthWindow(problemChoiceStage));


        Scene scene = new Scene(SecondWindow, 400, 200);
        problemChoiceStage.setScene(scene);
        problemChoiceStage.show();
    }

    private void openThirdWindow(Stage problemChoiceStage) {
        problemChoiceStage.close();

        Stage displayProblems = new Stage();

        ThirdWindowView problemButtonsLayout = new ThirdWindowView(gameFacade.getProblems());
        for (Button button : problemButtonsLayout.getProblemButtons()) {
            button.setOnAction(e -> openFourthWindow(displayProblems));
        }

        ScrollPane scrollPane = new ScrollPane(problemButtonsLayout);

        Scene scene = new Scene(scrollPane, 400, 400);
        displayProblems.setScene(scene);
        displayProblems.show();
    }


    private void openFourthWindow(Stage displayProblems) {
        displayProblems.close();
        Stage GameProcessParts = new Stage();

        FourthWindowView root = new FourthWindowView(gameFacade);

        // Ajouter le ScrollPane à la VBox
        ScrollPane scrollPaneRoot = new ScrollPane(root);
        scrollPaneRoot.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneRoot.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu

        // Ajouter le ScrollPane à la scène
        Scene scene = new Scene(scrollPaneRoot, 500, 425);

        GameProcessParts.setScene(scene);
        GameProcessParts.show();
    }

}


