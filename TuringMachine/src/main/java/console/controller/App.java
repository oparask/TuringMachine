package console.controller;

import model.GameFacade;

import java.util.Random;
import java.util.Scanner;

import static console.View.*;

/**
 * The controller contains the classes responsible for
 * handling the console-based user interface and controlling the flow of the
 * interactive game.
 */
public class App {
    private GameFacade gameFacade = new GameFacade();

    /**
     * Starts and manages the console-based application, including the main game loop.
     */
    public void app() {
        displayTitle();

        boolean anotherGame = true;

        while (anotherGame) {
            //First part of the game
            problemInitialization();

            //Second part of the game
            displayHelp();

            while (gameFacade.getCurrentGame() != null) {
                processCommand();
            }

            if (anotherGame()) {
                gameFacade = new GameFacade();
            } else {
                anotherGame = false;
                displayEnd();
            }
        }
    }

    /**
     * Handles the initialization of the game by prompting the user to choose a problem.
     */
    private void problemInitialization() {
        while (true) {
            try {
                gameFacade.startNewGame(chooseProblemOfGame());
                break; // Exit the loop if gameFacade.startNewGame() is successful
            } catch (IllegalArgumentException e) {
                displayInvalidInput(e.getMessage());
            }
        }
    }

    /**
     * Processes user commands in the main game loop, handling various actions such
     * as entering user code, testing validators, advancing to the next round,
     * guessing the code, and managing undo/redo operations.
     */
    private void processCommand() {
        Scanner keyboard = new Scanner(System.in);
        String invalidInputMessage = "Invalid input! Try again!";
        while (gameFacade.getCurrentGame() != null) {
            displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
            try {
                displayEntrancePrompt();

                String input = keyboard.nextLine().trim();
                String[] detailInput = input.split("\\s+");
                String commandType = detailInput[0];

                switch (commandType.toLowerCase()) {
                    case "code" -> handleUserCode();
                    case "test" -> handleValidatorTest();
                    case "next" -> handleNextRound();
                    case "guess" -> handleCodeGuessing();
                    case "undo" -> gameFacade.undo();
                    case "redo" -> gameFacade.redo();
                    case "help" -> displayHelp();
                    case "exit" -> gameFacade.abandonGame();
                    default -> {
                        displayInvalidInput(invalidInputMessage);
                        displayHelp();
                    }
                }
            } catch (Exception e) {
                displayInvalidInput(e.getMessage());
                displayHelp();
            }
        }
    }

    /**
     * Handles the user's input for entering a code. If no validators have been tested in the current round,
     * prompts the user to enter a code using the {@link #enterCode()} method. Otherwise, displays an error
     * message and the current user code.
     */
    private void handleUserCode() {
        if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
            gameFacade.enterCode(enterCode());
        } else {
            displayInvalidInput("You must pass to the next round if you want to test another code.");
            displayMessage("Your current code is " + gameFacade.getUserCode() + ".");
        }
    }

    /**
     * Handles the user's input for testing a validator. If a user code has been set, displays the available
     * validators, the user code, and the tested validators. Then, prompts the user to choose a validator using
     * the {@link #chooseValidatorIndex()} method. After testing the chosen validator, displays the test result.
     * If no user code is set, displays an error message.
     */
    private void handleValidatorTest() {
        if (gameFacade.getUserCode() != null) {
            displayValidators(gameFacade.getProblemValidators());
            displayUserCode(gameFacade.getUserCode());
            displayTestedValidators(gameFacade.getCurRoundTestedValidators());
            gameFacade.testValidator(chooseValidatorIndex());
            displayTestResult(gameFacade.getLastValidatorTestResult());
        } else {
            displayInvalidInput("You must choose first your user code!");
        }
    }

    /**
     * Handles the user's input for moving to the next round. If at least one validator has been tested in the
     * current round, calls the {@link model.GameFacade#nextRound()} method. Otherwise, displays an error message.
     */
    private void handleNextRound() {
        if (!gameFacade.getCurRoundTestedValidators().isEmpty()) {
            gameFacade.nextRound();
        } else {
            displayInvalidInput("You haven't yet chosen a validator for the round " + gameFacade.getRounds().size() + ".");
        }
    }

    /**
     * Handles the user's input for guessing the code. If a user code has been set, displays the guessed code and
     * returns true. If no user code is set, displays an error message and returns false.
     *
     * @return {@code true} if a user code is set, {@code false} otherwise.
     */
    private boolean handleCodeGuessing() {
        if (gameFacade.getUserCode() != null) {
            displayGuessedCode(gameFacade.guessCode());
            return true;
        } else {
            displayInvalidInput("You must choose first your user code!");
            return false;
        }
    }

    /**
     * Allows the user to choose a problem for the game. Displays the available problems and prompts the user
     * to choose either by input or randomly. If the user chooses to input a problem number, calls the
     * {@link #readNumber(String)} method to read the input. If the user chooses randomly, generates a random
     * problem index.
     *
     * @return The chosen problem number.
     */
    public int chooseProblemOfGame() {
        displayProblems(gameFacade.getProblems());
        if (chooseYesOrNo("Do you want to choose the problem? Tap : y or n")) {
            return readNumber("Choose a problem from the list.");
        } else {
            Random random = new Random();
            return random.nextInt(gameFacade.getProblems().size() + 1);
        }
    }


    /**
     * Reads a three-digit code from the user, ensuring it meets the specified format.
     *
     * @return The entered three-digit code as an integer.
     */
    private int enterCode() {
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            displayMessage("Enter a code of three digits between 1 and 5: ");
            String input = keyboard.nextLine().trim();

            if (input.matches("^[1-5]{3}$")) {
                return Integer.parseInt(input);
            } else {
                displayInvalidInput("Invalid code format.");
            }
        }
    }

    /**
     * Reads a validator index from the user, displaying a prompt message.
     *
     * @return The entered validator index as an integer.
     */
    private int chooseValidatorIndex() {
        return readNumber("Enter a validator index from the list: ");
    }

    /**
     * Asks the user if they want to start a new game and reads their response.
     *
     * @return {@code true} if the user wants to start a new game, {@code false} otherwise.
     */
    private boolean anotherGame() {
        return chooseYesOrNo("Do you want to start a new game? Tap : y or n");
    }

    /**
     * Reads a yes or no answer from the user based on the provided message.
     *
     * @param message The message to display as a prompt.
     * @return {@code true} if the user answers 'y', {@code false} if the user answers 'n'.
     */
    private boolean chooseYesOrNo(String message) {
        return stringRobustReading(message).equalsIgnoreCase("y");
    }

    /**
     * Reads a number from the user, displaying a prompt message.
     *
     * @param message The message to display as a prompt.
     * @return The entered number as an integer.
     */
    private int readNumber(String message) {
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            displayMessage(message);
            if (keyboard.hasNextInt()) {
                return keyboard.nextInt();
            } else {
                displayInvalidInput("Invalid input. Please enter a valid number.");
                keyboard.next();
            }
        }
    }

    /**
     * Reads a string input from the user, handling invalid input and providing a valid response.
     *
     * @param message The message to display as a prompt.
     * @return The valid string input from the user.
     */
    private static String stringRobustReading(String message) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);
        String input = keyboard.nextLine();
        while (input.trim().isEmpty() || !input.trim().matches("(?i)[yn]")) {
            displayInvalidInput("Invalid input. " + message);
            input = keyboard.nextLine();
        }

        return input;
    }

    /**
     * The main entry point of the console-based application.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        App app = new App();
        app.app();
    }
}


