package javaFx.view.thirdWindow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class ThirdWindowView  extends VBox{
    private List<Button> problemButtons;

    public ThirdWindowView(List<Problem> problems) {
        //VBox problemButtonsLayout = new VBox(10);
        this.setAlignment(Pos.CENTER);

        problemButtons = new ArrayList<>();
        for (Problem problem : problems) {
            Button problemButton = new Button("Problem " + problem.getProblemNumber() +
                    " (Difficulty: " + problem.getDifficultyLevel() +
                    ", Luck: " + problem.getLuckDegree() + ")");
            problemButton.setUserData(problem); // Stocke l'objet Problem comme userData
            problemButtons.add(problemButton);
           /* problemButton.setOnAction(e -> startNewGameWithChoice(displayProblems, problem));*/
            //il audrait y avoir deux gestionnaires un dans le controlleer qui va donner l'info problem a la game facad et lautre gestinon
            //qui va appeler la 4 eme fenetre
            this.getChildren().add(problemButton);
        }

    }

    public List<Button> getProblemButtons() {
        return problemButtons;
    }
}




