package view;

import model.problems.Problem;

import java.util.List;

public class View {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";


    private static final String FORMAT_HEADER = "n°  difficulty  luck";
    private static final String FORMAT_PROBLEM = "%-3d %-11d %-3d%n";

    /**
     * Displays the welcome message.
     */
    public static void displayTitle() {
        System.out.println(ANSI_PURPLE + "( T )( U )( R )( I )( N )( G )  ( M )( A )( C )( H )( I )( N )( E )( S )" + ANSI_RESET);
        System.out.println();
    }

    public static void displayProblems(List<Problem> problems) {
        System.out.println(FORMAT_HEADER);
        for (Problem problem : problems) {
            System.out.printf(FORMAT_PROBLEM,
                    problem.getProblemNumber(),
                    problem.getDifficultyLevel(),
                    problem.getLuckDegree());
        }
        System.out.println();
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
