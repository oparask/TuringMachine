package console.controller;

import model.GameFacade;

import java.util.Random;
import java.util.Scanner;

import static console.View.*;

public class App {
    private GameFacade gameFacade = new GameFacade();

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
                    case "next" -> {
                        handleNextRound();
                    }
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

    private void handleUserCode() {
        if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
            gameFacade.enterCode(enterCode());
        } else {
            displayInvalidInput("You must pass to the next round if you want to test another code.");
            displayMessage("Your current code is " + gameFacade.getUserCode() + ".");
        }
    }

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

    private void handleNextRound() {
        if (!gameFacade.getCurRoundTestedValidators().isEmpty()) {
            gameFacade.nextRound();
        } else {
            displayInvalidInput("You haven't yet chosen a validator for the round " + gameFacade.getRounds().size() + ".");
        }
    }

    private boolean handleCodeGuessing() {
        if (gameFacade.getUserCode() != null) {
            displayGuessedCode(gameFacade.guessCode());
            return true;
        } else {
            displayInvalidInput("You must choose first your user code!");
            return false;
        }
    }

    public int chooseProblemOfGame() {
        displayProblems(gameFacade.getProblems());
        if (chooseYesOrNo("Do you want to choose the problem? Tap : y or n")) {
            return readNumber("Choose a problem from the list.");
        } else {
            Random random = new Random();
            return random.nextInt(gameFacade.getProblems().size() + 1);
        }
    }


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

    private int chooseValidatorIndex() {
        return readNumber("Enter a validator index from the list: ");
    }

    private boolean anotherGame() {
        return chooseYesOrNo("Do you want to start a new game? Tap : y or n");
    }

    private boolean chooseYesOrNo(String message) {
        return stringRobustReading(message).equalsIgnoreCase("y");
    }

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

    public static void main(String[] args) {
        App app = new App();
        app.app();
    }
}


