package controller;

import model.GameFacade;
import model.problems.Problem;
import model.problems.ProblemReader;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import static view.View.*;

public class App {
    GameFacade gameFacade = new GameFacade();
    List<Problem> problems = new ProblemReader().getProblems();

    public void start() {
        displayTitle();
        //displayHelp();
        startNewGame();
        boolean continueGame = true;

        while (continueGame) {
            continueGame = processCommand();
        }
    }


    private boolean processCommand() {
        Scanner keyboard = new Scanner(System.in);
        String invalidInputMessage = "Invalid input! Try again!";

        //les validateurs du probleme sont affichés ;
        displayValidators(gameFacade.getValidators());

        //les scores (nombre de validateurs vérifiés et nombre de manches) sont affichés ;
        displayScore(gameFacade.getTestedValidators().size(), gameFacade.getRounds().size());

        // entrer un code (uniquement si aucun validateur n’a été vérifié à cette manche) ;
        if (gameFacade.getCurRoundTestedValidators().size() == 0) {
            enterCode();
        }

/*

        . l’utilisateur peut alors :
. entrer un code (uniquement si aucun validateur n’a été vérifié à cette manche) ;
. choisir un validateur (dans la limite de 3 validateurs par manche) ;
. choisir de passer à la manche suivante (pour tester un nouveau code) ;
. vérifier si son code est le bon (ce qui met fin au jeu et lui dit s’il a gagné ou
                perdu) ;

*/
    }

    public void startNewGame() {
        displayProblems(problems);
        if (choseProblem()) { //the player wants to choose the problem
            gameFacade.startNewGame(readProblemNumber());
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


    private void enterCode() {
        Scanner keyboard = new Scanner(System.in);
        displayMessage("Enter a code of three digits between 1 and 5: ");
        String input = keyboard.nextLine().trim();
        // code regex
        String regex = "^[1-5]{3}$";
        if (Pattern.matches(regex, input)) {
            int userCode = Integer.parseInt(input);
            gameFacade.enterCode(userCode);
        } else {
            displayInvalidInput("Invalid code format. Please enter a code of three digits between 1 and 5.");
        }
    }

    private boolean isValidCode(int code) {
        return code >= 1 && code <= 5;
    }


    private boolean isValidCode(int code) {
        return code >= 1 && code <= 5;
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


