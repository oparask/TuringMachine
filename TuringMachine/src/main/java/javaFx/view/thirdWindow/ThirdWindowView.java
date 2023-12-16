package javaFx.view.thirdWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * View class representing the third window in the Turing Machine Game application.
 * This class displays a list of problems as clickable buttons for the user to choose from.
 * Each button provides information about the associated problem's number, difficulty level,
 * and luck degree.
 */
public class ThirdWindowView extends VBox {
    /**
     * List of styled buttons, each representing a problem for the user to choose.
     */
    private final List<StyledButton> problemButtons;

    /**
     * Constructs a new instance of the ThirdWindowView.
     *
     * @param problems The list of problems to be displayed as buttons.
     */
    public ThirdWindowView(List<Problem> problems) {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        problemButtons = new ArrayList<>();
        for (Problem problem : problems) {
            StyledButton problemButton = new StyledButton(
                    "Problem " + problem.getProblemNumber() +
                            " (Difficulty: " + problem.getDifficultyLevel() +
                            ", Luck: " + problem.getLuckDegree() + ")");

            problemButton.setUserData(problem); // Stores the Problem object as userData.
            problemButtons.add(problemButton);
            this.getChildren().add(problemButton);
        }
    }

    /**
     * Gets the list of styled buttons representing the problems.
     *
     * @return The list of problem buttons.
     */
    public List<StyledButton> getProblemButtons() {
        return problemButtons;
    }
}




