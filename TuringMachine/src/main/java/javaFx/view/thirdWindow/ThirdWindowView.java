package javaFx.view.thirdWindow;

import javaFx.view.StyledButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class ThirdWindowView  extends VBox{
    private List<StyledButton> problemButtons;

    public ThirdWindowView(List<Problem> problems) {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);


        problemButtons = new ArrayList<>();
        for (Problem problem : problems) {
            StyledButton problemButton = new StyledButton("Problem " + problem.getProblemNumber() +
                    " (Difficulty: " + problem.getDifficultyLevel() +
                    ", Luck: " + problem.getLuckDegree() + ")");

            problemButton.setUserData(problem); // Stocke l'objet Problem comme userData
            this.getChildren().add(problemButton);
            problemButtons.add(problemButton);

        }

    }

    public List<StyledButton> getProblemButtons() {
        return problemButtons;
    }
}




