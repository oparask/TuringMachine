package consoleView.controller;

import model.GameFacade;

import java.util.Random;
import java.util.Scanner;

import static consoleView.View.*;

public class App {
    private GameFacade gameFacade = new GameFacade();

    public void app() {
        displayTitle();

        boolean anotherGame = true;

        while (anotherGame){
            //First part of the game
            while (true) {
                try {
                    gameFacade.startNewGame(chooseProblemOfGame());
                    break; // Exit the loop if gameFacade.startNewGame() is successful
                } catch (IllegalArgumentException e) {
                    displayInvalidInput(e.getMessage());
                }
            }

            //Second part of the game
            boolean continueGame = true;
            while (continueGame) {
                try {
                    continueGame = processGame();
                } catch (IllegalArgumentException e) {
                    displayInvalidInput(e.getMessage());
                }
            }

            if(anotherGame()){
                gameFacade = new GameFacade();
            } else {
                anotherGame = false;
                displayEnd();
            }
        }


    }

    private boolean processGame() {
        int passCommand = 0;
        displayGameInfo();

        if (gameFacade.getCurRoundTestedValidators().isEmpty()) {
            gameFacade.enterCode(enterCode());
        }

        if (testValidator()) {
            int validatorIndex = chooseValidatorIndex();
            displayTestResult(gameFacade.testValidator(validatorIndex));
        } else {
            passCommand++;
        }

        if (nextRound()) {
            gameFacade.nextRound();
            return true;
        } else {
            passCommand++;
        }

        if (wantToGuessCode()) {
            displayGuessedCode(gameFacade.guessCode());
            return false;
        } else {
            passCommand++;
        }

        if (passCommand == 3 && wantToQuit()) {
            gameFacade.abandonGame();
            return false;
        }

        return true;
    }

    public int chooseProblemOfGame() {
        displayProblems(gameFacade.getProblems());
        if (chooseYesOrNo("Do you want to choose the problem? Tap : y or n")) {
            return chooseProblem();
        } else {
            return getRandomProblem();
        }
    }

    private int chooseProblem() {
        return readNumber("Choose a problem from the list.");
    }

    private int getRandomProblem() {
        Random random = new Random();
        return random.nextInt(gameFacade.getProblems().size());
    }

    private void displayGameInfo() {
        displayValidators(gameFacade.getProblemValidators());
        displayScore(gameFacade.getScore(), gameFacade.getRounds().size());
    }

    private int enterCode() {
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            displayMessage("Enter a code of three digits between 1 and 5: ");
            displayEntrancePrompt();
            String input = keyboard.nextLine().trim();

            if (input.matches("^[1-5]{3}$")) {
                return Integer.parseInt(input);
            } else {
                displayInvalidInput("Invalid code format.");
            }
        }
    }

    private boolean testValidator() {
        return chooseYesOrNo("Do you want to test a validator? Tap : y or n");
    }

    private int chooseValidatorIndex() {
        return readNumber("Enter a validator index: ");
    }

    private boolean nextRound() {
        return chooseYesOrNo("Do you want to pass to the next round? Tap : y or n");
    }

    private boolean wantToGuessCode() {
        return chooseYesOrNo("Do you want to guess the secret code? Tap : y or n");
    }

    private boolean wantToQuit() {
        return chooseYesOrNo("Do you want to quit the game? Tap : y or n");
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
            displayEntrancePrompt();
            if (keyboard.hasNextInt()) {
                return keyboard.nextInt();
            } else {
                displayInvalidInput("Invalid input. Please enter a valid integer.");
                keyboard.next();
            }
        }
    }

    private static String stringRobustReading(String message) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);
        displayEntrancePrompt();
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


