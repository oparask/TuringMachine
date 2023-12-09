package view;

import model.problems.Problem;
import model.validators.Validator;

import java.util.List;

public class View {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    /**
     * Displays the welcome message.
     */
    public static void displayTitle() {
        System.out.println(ANSI_PURPLE + "( T )( U )( R )( I )( N )( G )  ( M )( A )( C )( H )( I )( N )( E )" + ANSI_RESET);
        System.out.println();
    }

    public static void displayProblems(List<Problem> problems) {
        System.out.println("The problems of Turing Machine are: ");

        for (Problem problem : problems) {
            System.out.println("Problem " + problem.getProblemNumber()
                    + " (difficulty level: " + problem.getDifficultyLevel()
                    + ", luck degree: " + problem.getLuckDegree() + ")");

        }
        System.out.println();
    }

    public static void displayValidators(Validator[] validators) {
        System.out.println("The available validators for the chosen problem are: ");
        int i = 0;
        for (Validator validator : validators) {
            System.out.println(i + ": validator " + validator.getValidatorNumber() + " -> " + validator);
            i++;
        }

        System.out.println();

    }


    public static void displayScore(int testedValidatorsNb, int roundsNb) {
        //les scores (nombre de validateurs vérifiés et nombre de manches) sont affichés ;
        if (testedValidatorsNb == 1 && roundsNb == 1) {
            System.out.println("You've tested " + testedValidatorsNb + " validator in " + roundsNb + " round.");
        } else if (testedValidatorsNb == 1) {
            System.out.println("You've tested " + testedValidatorsNb + " validator in " + roundsNb + " rounds.");
        } else if (roundsNb == 1) {
            System.out.println("You've tested " + testedValidatorsNb + " validators in " + roundsNb + " round.");
        } else {
            System.out.println("You've tested " + testedValidatorsNb + " validators in " + roundsNb + " rounds.");
        }
        System.out.println();
    }

    public static void displayTestResult(boolean sameCharacteristics) {
        if (sameCharacteristics) {
            System.out.println("true -> the secret code has the same characteristic as your user code :)");
        } else {
            System.out.println("false -> the secret code doesn't have the same characteristic as your user code :(");
        }
        System.out.println();
    }

    public static void displayGuessedCode(boolean guessedCode) {
        if (guessedCode) {
            System.out.println("Congratulations! You guessed the code!");
        } else {
            System.out.println("Unfortunately, you didn't guessed...");
        }
    }


    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays a message to inform that the input is invalid.
     *
     * @param message The message to display.
     */
    public static void displayInvalidInput(String message) {
        System.out.println(ANSI_ORANGE + message + ANSI_RESET);
        System.out.println();
    }


}
