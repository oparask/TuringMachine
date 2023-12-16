package javaFx.view.secondWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.GameFacade;

import java.util.Random;

public class SecondWindowController {
    private final SecondWindowView view;
    private final GameFacade gameFacade;

    public SecondWindowController(SecondWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.getRandomProblemButton().addEventHandler(ActionEvent.ACTION, new SecondWindowController.AutoChooseButtonClickHandler());
    }

    private class AutoChooseButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Random random = new Random();
            int autoChosenProblemIndex = random.nextInt(gameFacade.getProblems().size());
            gameFacade.startNewGame(autoChosenProblemIndex + 1);
        }
    }
}
