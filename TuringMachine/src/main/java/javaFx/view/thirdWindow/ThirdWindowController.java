package javaFx.view.thirdWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.GameFacade;
import model.problems.Problem;
/**
 * Controller class for the third window in the Turing Machine Game application.
 * This class is responsible for handling user interactions with the displayed problems
 * and initiating the start of a new game based on the selected problem.
 */
public class ThirdWindowController {
    /**
     * The view associated with this controller, representing the third window.
     */
    private final ThirdWindowView view;

    /**
     * The game facade instance that manages the game state and logic.
     */
    private final GameFacade gameFacade;

    /**
     * Constructs a new instance of the ThirdWindowController.
     *
     * @param view       The view associated with this controller.
     * @param gameFacade The game facade instance.
     */
    public ThirdWindowController(ThirdWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    /**
     * Attaches event handlers to the problem buttons in the view.
     */
    private void attachEventHandlers() {
        for (Button problemButton : view.getProblemButtons()) {
            problemButton.addEventHandler(ActionEvent.ACTION, new ProblemButtonClickHandler());
        }
    }

    /**
     * Event handler class for handling clicks on problem buttons.
     */
    private class ProblemButtonClickHandler implements EventHandler<ActionEvent> {
        /**
         * Handles the action event triggered by clicking a problem button.
         *
         * @param event The action event.
         */
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            // Retrieve the Problem object from the userData property.
            Problem selectedProblem = (Problem) clickedButton.getUserData();

            // Start a new game based on the selected problem.
            gameFacade.startNewGame(selectedProblem.getProblemNumber());
        }
    }
}
