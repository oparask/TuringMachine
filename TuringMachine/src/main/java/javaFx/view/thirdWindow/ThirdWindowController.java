package javaFx.view.thirdWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.GameFacade;
import model.problems.Problem;

public class ThirdWindowController {
    private final ThirdWindowView view;
    private final GameFacade gameFacade;

    public ThirdWindowController(ThirdWindowView view, GameFacade gameFacade) {
        this.view = view;
        this.gameFacade = gameFacade;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        for (Button problemButton : view.getProblemButtons()) {
            problemButton.addEventHandler(ActionEvent.ACTION, new ProblemButtonClickHandler());
        }
    }

    private class ProblemButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            //Retrieve the Problem object from the userData property.
            Problem selectedProblem = (Problem) clickedButton.getUserData();

            gameFacade.startNewGame(selectedProblem.getProblemNumber());
        }
    }
}
