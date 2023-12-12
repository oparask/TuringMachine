package consoleView;

import model.Code;
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

    /**
     * Displays the help message with available commands and usage.
     */
    public static void displayHelp() {
        System.out.println();
        System.out.println(ANSI_PURPLE + """
                TURING MACHINE COMMANDS:
                                                                              
                 \u001B[35m- Enter user code:\u001B[0m code
                 \u001B[35m- Choose a validator:\u001B[0m test
                 \u001B[35m- Next round:\u001B[0m next
                 \u001B[35m- Guess round:\u001B[0m guess
                 \u001B[35m- Undo the command:\u001B[0m undo
                 \u001B[35m- Redo the command:\u001B[0m redo
                 \u001B[35m- Display commands:\u001B[0m help
                 \u001B[35m- Exit:\u001B[0m exit
                """ + ANSI_RESET);
    }


    public static void displayProblems(List<Problem> problems) {
        System.out.println();
        System.out.println(ANSI_PURPLE + "THE PROBLEMS OF TURING MACHINE GAME: " + ANSI_RESET);
        System.out.println();

        for (Problem problem : problems) {
            System.out.println(ANSI_PURPLE + "Problem " + ANSI_BLUE + problem.getProblemNumber() + ANSI_RESET
                    + " (difficulty: " + ANSI_PURPLE + problem.getDifficultyLevel() + ANSI_RESET +
                    ", luck: " + ANSI_PURPLE + problem.getLuckDegree() + ANSI_RESET + ")" + ANSI_RESET);

        }
    }

    public static void displayValidators(Validator[] validators) {
        System.out.println();
        System.out.println(ANSI_PURPLE + "AVAILABLE VALIDATORS: " + ANSI_RESET);
        int i = 0;
        for (Validator validator : validators) {
            System.out.println(ANSI_BLUE + i + ANSI_RESET + ": validator " + validator.getValidatorNumber() + ANSI_PURPLE + " -> " + ANSI_RESET + validator + ANSI_RESET);
            i++;
        }

    }

    public static void displayScore(int testedValidatorsNb, int roundsNb) {
        System.out.println();
        System.out.println(ANSI_PURPLE + "SCORE: " + ANSI_RESET + testedValidatorsNb + ANSI_RESET);
        //les scores (nombre de validateurs vérifiés et nombre de manches) sont affichés ;
        System.out.println(ANSI_PURPLE + "ROUNDS: " + ANSI_RESET + roundsNb);
    }

    public static void displayTestResult(boolean sameCharacteristics) {
        System.out.println();
        if (sameCharacteristics) {
            System.out.println(ANSI_GREEN + "true -> the secret code has the same characteristic as your user code :)" + ANSI_RESET);
        } else {
            System.out.println(ANSI_ORANGE + "false -> the secret code doesn't have the same characteristic as your user code :(" + ANSI_RESET);
        }
    }

    public static void displayGuessedCode(boolean guessedCode) {
        System.out.println();
        if (guessedCode) {
            System.out.println(ANSI_GREEN + "Congratulations! You guessed the code!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_ORANGE + "Unfortunately, you didn't guessed..." + ANSI_RESET);
        }
    }

    public static void displayUserCode(Code userCode) {
        System.out.println();
        System.out.println(ANSI_PURPLE+"USER CODE: " + userCode+ANSI_RESET);
    }

    public static void displayTestedValidators(List<Validator>testedValidators) {
        System.out.println();

        if(testedValidators.isEmpty()){
            System.out.println(ANSI_PURPLE+"You tested 0 validators in this round.");
        } else {
            System.out.print(ANSI_PURPLE+"You already have tested in the this round the validator: ");
            for (Validator validator : testedValidators) {
                System.out.print(validator.getValidatorNumber() + " ");
            }
        }
        System.out.println(ANSI_RESET);
    }

    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println();
        System.out.println(ANSI_PURPLE+message+ANSI_RESET);
    }
    /**
     * Prints a prompt symbol (">") and expects user input.
     */
    public static void displayEntrancePrompt() {
        System.out.println();
        System.out.print(ANSI_PURPLE + "Enter a command:\n> " + ANSI_RESET);
    }

    /**
     * Displays a message to inform that the input is invalid.
     *
     * @param message The message to display.
     */
    public static void displayInvalidInput(String message) {
        System.out.println();
        System.out.println(ANSI_ORANGE + message + ANSI_RESET);
    }

    /**
     * Displays the end message when exiting the application.
     */
    public static void displayEnd() {
        System.out.println();
        System.out.print(ANSI_PURPLE + "Bye, see you next time :)");
    }


}
