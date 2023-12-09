package consoleView;

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
        System.out.println(ANSI_PURPLE + "THE PROBLEMS OF TURING MACHINE GAME: " + ANSI_RESET);
        System.out.println();

        for (Problem problem : problems) {
            System.out.println(ANSI_PURPLE + "Problem " + ANSI_BLUE + problem.getProblemNumber() + ANSI_RESET
                    + " (difficulty: " + ANSI_PURPLE + problem.getDifficultyLevel() + ANSI_RESET +
                    ", luck: " + ANSI_PURPLE + problem.getLuckDegree() + ANSI_RESET + ")" + ANSI_RESET);

        }
        System.out.println();
    }

    public static void displayValidators(Validator[] validators) {
        System.out.println(ANSI_PURPLE + "AVAILABLE VALIDATORS: " + ANSI_RESET);
        int i = 0;
        for (Validator validator : validators) {
            System.out.println(ANSI_BLUE + i + ANSI_RESET + ": validator " + validator.getValidatorNumber() + ANSI_PURPLE + " -> " + ANSI_RESET + validator + ANSI_RESET);
            i++;
        }

        System.out.println();
    }


    public static void displayScore(int testedValidatorsNb, int roundsNb) {
        System.out.println(ANSI_PURPLE + "SCORE: " + ANSI_RESET + testedValidatorsNb + ANSI_RESET);
        //les scores (nombre de validateurs vérifiés et nombre de manches) sont affichés ;
        System.out.println(ANSI_PURPLE + "ROUNDS: " + ANSI_RESET + roundsNb);

        System.out.println();
    }

    public static void displayTestResult(boolean sameCharacteristics) {
        if (sameCharacteristics) {
            System.out.println(ANSI_GREEN + "true -> the secret code has the same characteristic as your user code :)" + ANSI_RESET);
        } else {
            System.out.println(ANSI_ORANGE + "false -> the secret code doesn't have the same characteristic as your user code :(" + ANSI_RESET);
        }
        System.out.println();
    }

    public static void displayGuessedCode(boolean guessedCode) {
        if (guessedCode) {
            System.out.println(ANSI_GREEN + "Congratulations! You guessed the code!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_ORANGE + "Unfortunately, you didn't guessed..." + ANSI_RESET);
        }
    }


    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(ANSI_PURPLE+message+ANSI_RESET);
    }

    /**
     * Prints a prompt symbol (">") and expects user input.
     */
    public static void displayEntrancePrompt() {
        System.out.print(ANSI_PURPLE + "> " + ANSI_RESET);
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

    /**
     * Displays the end message when exiting the application.
     */
    public static void displayEnd() {
        System.out.print(ANSI_PURPLE + "Bye, see you next time :)");
    }


}
