package controller;

import model.Game;
import model.problems.Problem;
import model.problems.ProblemReader;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static view.View.*;

public class App {

    private List<Game> games;
    Problem problem;

    List<Problem> problems = new ProblemReader().getProblems();

    public void start() {
        displayTitle();
        //displayHelp();
        //games = new ArrayList<>();
        problemInitialisation();
        System.out.println(problem.toString());

/*
        boolean continueGame = true;

        while (continueGame) {
            continueGame = processCommand();
        }*/
    }

    private void problemInitialisation() {
        Scanner keyboard = new Scanner(System.in);

        int problemNb;

        while (true) {
            if (choseProblem()) {
                try {
                    displayProblems(problems);
                    displayMessage("Enter the number of the problem: ");
                    String input = keyboard.nextLine().trim();
                    String[] detailInput = input.split("\\s+");
                    problemNb = Integer.parseInt(detailInput[0]);

                    if (!validProblemNb(problemNb)) {
                        displayInvalidInput("The problem number must be between 1 and 16.");
                        continue;
                    }

                    problem = problems.get(problemNb - 1);
                    break;

                } catch (Exception e) {
                    displayInvalidInput(e.getMessage());
                }
            } else { //chose a random problem
                problem = getRandomProblem();
                break;
            }
        }
    }


    /*private boolean processCommand() {
        Scanner keyboard = new Scanner(System.in);
        String invalidInputMessage = "Invalid input! Try again!";


    }*/

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}


