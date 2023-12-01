package model;

import model.problems.Problem;
import model.problems.ProblemReader;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static view.View.displayInvalidInput;
import static view.View.displayMessage;


public class GameFacade {
    private List<Problem> problems;
    private Problem problem;
    private Game currentGame;

    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        problem = null;
        currentGame = null;
    }

    public void startNewGame() {
        problemInitialisation();
    }

    private void problemInitialisation() {
        if (choseProblem()) {
            int problemNb = readProblemNumber();
            problem = problems.get(problemNb - 1);
        } else { //chose a random problem
            problem = getRandomProblem();
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
            displayInvalidInput("Invalid input. The problem number must be between 1 and 16.");
            return readProblemNumber(); // Recursive call to try again
        }
    }

    private boolean choseProblem() {
        return stringRobustReading("Do you want to chose the problem?" +
                " (y or n)").equalsIgnoreCase("y");
    }

    private boolean validProblemNb(int problemNb) {
        return problemNb >= 1 && problemNb <= 16;
    }

    private Problem getRandomProblem() {
        Random random = new Random();
        int randomIndex = random.nextInt(problems.size());
        return problems.get(randomIndex);
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


    public void enterCode(Code code) {
        currentGame.enterCode(code);
    }

    public void chooseValidator(Validator validator) {
        currentGame.chooseValidator(validator);
    }

    public void nextRound() {
        currentGame.nextRound();
    }

    public boolean guessCode(Code code) {
        return currentGame.guessCode(code);
    }

    public void undoMove() {
        currentGame.undoMove();
    }

    public void redoMove() {
        currentGame.redoMove();
    }

    public void abandonGame() {
        currentGame.abandonGame();
        currentGame = null; // Réinitialiser le jeu courant après l'abandon
    }
}
