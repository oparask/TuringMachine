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
import javafx.event.EventHandler;
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

        SecondWindowView secondWindowView = new SecondWindowView();
        SecondWindowController secondWindowController = new SecondWindowController(secondWindowView, gameFacade);

        secondWindowView.getChooseProblemButton().setOnAction(e -> openThirdWindow(problemChoiceStage));
        secondWindowView.getAutoChooseButton().addEventHandler(ActionEvent.ACTION, e -> openFourthWindow(problemChoiceStage));


        Scene scene = new Scene(secondWindowView, 400, 200);
        problemChoiceStage.setScene(scene);
        problemChoiceStage.show();
    }

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
        Scene scene = new Scene(scrollPane, 400, 400);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        displayProblems.setScene(scene);
        displayProblems.show();
    }


    private void openFourthWindow(Stage displayProblems) {
        displayProblems.close();

        Stage GameProcessParts = new Stage();
        GameProcessParts.setTitle("GUESS CODE");


        FourthWindowView fourthWindowView = new FourthWindowView(gameFacade);

            fourthWindowView.getGuessCodeButton().addEventHandler(ActionEvent.ACTION, e -> openNewInstance(GameProcessParts));



        FourthWindowController FourthWindowController = new FourthWindowController(fourthWindowView, gameFacade);

        // Ajouter le ScrollPane à la VBox
        ScrollPane scrollPaneRoot = new ScrollPane(fourthWindowView);
        scrollPaneRoot.setFitToWidth(true);  // Permet au ScrollPane de s'adapter à la largeur du contenu
        scrollPaneRoot.setFitToHeight(true); // Permet au ScrollPane de s'adapter à la hauteur du contenu

        // Ajouter le ScrollPane à la scène
        Scene scene = new Scene(scrollPaneRoot, 500, 425);

        GameProcessParts.setScene(scene);
        GameProcessParts.show();
    }

    private void openNewInstance(Stage currentStage) {
        try {
            // Vérifier si le code utilisateur est défini
            if (gameFacade.getUserCode() != null) {
                currentStage.close();

                // Créer une nouvelle instance de votre application principale
                Main newAppInstance = new Main();
                // Lancer la nouvelle instance
                newAppInstance.start(new Stage());
            }
        } catch (Exception e) {
            // Gérer l'exception ici (affichage ou traitement supplémentaire si nécessaire)
            e.printStackTrace();
            PopOut popOut = new PopOut("ERROR", e.getMessage(), true);
            popOut.show();
        }
    }


}


