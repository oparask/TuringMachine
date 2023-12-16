package javaFx.view.secondWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.GameFacade;

import java.util.Random;
/**
 * Controller class for the second window in the application.
 * Handles user interactions and events related to the SecondWindowView.
 */
public class SecondWindowController {
    private final SecondWindowView view;
    private final GameFacade gameFacade;

    /**
     * Constructs a new instance of SecondWindowController.
     * Initializes the controller with the associated view and game facade.
     *
     * @param view       The associated SecondWindowView.
     * @param gameFacade The GameFacade providing game-related functionality.
     */
    public SecondWindowController(SecondWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    /**
     * Attaches event handlers to the relevant UI components in the view.
     */
    private void attachEventHandlers() {
        view.getRandomProblemButton().addEventHandler(ActionEvent.ACTION, new AutoChooseButtonClickHandler());
    }

    /**
     * Event handler for the "Auto Choose" button click event.
     * Randomly selects a problem from the available list and starts a new game.
     */
    private class AutoChooseButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Random random = new Random();
            int autoChosenProblemIndex = random.nextInt(gameFacade.getProblems().size());
            gameFacade.startNewGame(autoChosenProblemIndex + 1);
        }
    }
}

