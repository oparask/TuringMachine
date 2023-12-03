package controller;

import model.Game;
import model.GameFacade;
import model.problems.Problem;
import model.problems.ProblemReader;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static view.View.*;

public class App {

    private List<Game> games;
    Problem problem;
    GameFacade gameFacade = new GameFacade();

    List<Problem> problems = new ProblemReader().getProblems();
/*
    public void start() {
        displayTitle();
        //displayHelp();
        //games = new ArrayList<>();
       // problemInitialisation();
        System.out.println(problem.toString());


        boolean continueGame = true;

        while (continueGame) {
            continueGame = processCommand();
        }
    }
*/
  /*  private void problemInitialisation() {
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
*/

    /*private boolean processCommand() {
        Scanner keyboard = new Scanner(System.in);
        String invalidInputMessage = "Invalid input! Try again!";


    }*/

    public void startNewGame() {
        if (choseProblem()) { //the player wants to choose the problem
            gameFacade.startNewGame(readProblemNumber());
            displayInvalidInput();
        } else { //chose a random problem
            gameFacade.startNewGame(getRandomProblem());
        }
    }


    private int readProblemNumber() {
        Scanner keyboard = new Scanner(System.in);
        displayMessage("Enter the number of the problem: ");
        String input = keyboard.nextLine().trim();

        try {
            String[] detailInput = input.split("\\s+");
            return Integer.parseInt(detailInput[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            displayInvalidInput("Invalid input. Please try again.");
            return readProblemNumber(); // Recursive call to try again
        }
    }

    private boolean choseProblem() {
        return stringRobustReading("Do you want to chose the problem?" +
                " (y or n)").equalsIgnoreCase("y");
    }


    private int getRandomProblem() {
        Random random = new Random();
        int randomIndex = random.nextInt(problems.size());
        return randomIndex;
    }

    /**
     * Reads and validates a string input from the user based on a regular expression pattern.
     *
     * @param message The message to display to the user as a prompt.
     * @return The validated user input as a string.
     */
    private static String stringRobustReading(String message) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);

        String input = keyboard.nextLine();
        while (input.trim().isEmpty() || !input.trim().matches("(?i)[yn]")) {
            // Ensures that the user cannot enter only whitespace as a valid input.
            displayInvalidInput("Invalid input. " + message);
            input = keyboard.nextLine();
        }

        return input;
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}


