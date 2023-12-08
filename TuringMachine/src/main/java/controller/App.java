package controller;

import model.GameFacade;
import model.validators.Validator;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import static view.View.*;

public class App {
    private final GameFacade gameFacade = new GameFacade();

    public void app() {
        displayTitle();

        int problemNumber = problemOfGame();
        gameFacade.startNewGame(problemNumber);

        //une fois la partie lancée
        boolean continueGame = true; //tant que continuer la partie
        while (continueGame) {
            continueGame = processOfGame();
        }
    }


    private boolean processOfGame() {
        displayValidators(gameFacade.getProblemValidators());
        displayScore(gameFacade.getScore(), gameFacade.getRounds().size());


        //l'utilisateur
        // entrer un code (uniquement si aucun validateur n’a été vérifié à cette manche) ;
        if (gameFacade.getCurRoundTestedValidators().size() == 0) {
            gameFacade.enterCode(enterCode());
        }

        System.out.println("Hello");
        //choisir un validateur (dans la limite de 3 validateurs par manche) ;
        int indexValidator = chooseValidator(gameFacade.getProblemValidators());
        int validatorNb = game
        boolean testResult = gameFacade.testValidator(nbValidatorToBeTested);
        if (testResult) {
            displayMessage("The test has passed!");
        } else {
            displayMessage("The test has failed!");
        }


        //choisir de passer à la manche suivante (pour tester un nouveau code) ;
        if (nextRound()) {
            gameFacade.nextRound();
            return true;
        }

        //vérifier si son code est le bon (ce qui met fin au jeu et lui dit s’il a gagné ou perdu) ;
        if(wantToGuessCode()){
            displayTestResult(gameFacade.guessCode());
            return false;
        } else {
            return true;
        }
    }

    public int problemOfGame() {
        displayProblems(gameFacade.getProblems());
        if (choseYorN("Do you want to chose the problem?")) { //the player wants to choose the problem
            return chooseProblem();
        } else {
            return getRandomProblem();
        }
    }

    private int chooseProblem() {
        return readNumber("Choose a problem.");
    }

    private int getRandomProblem() {
        Random random = new Random();
        return random.nextInt(gameFacade.getProblems().size());
    }


    private int enterCode() {
        Scanner keyboard = new Scanner(System.in);

        displayMessage("Enter a code of three digits between 1 and 5: ");

        String input = keyboard.nextLine().trim();
        String regex = "^[1-5]{3}$";   // code regex

        if (Pattern.matches(regex, input)) {
            return (Integer.parseInt(input));
        } else {
            displayInvalidInput("Invalid code format.");
            return enterCode(); //recursive call
        }
    }

    private int chooseValidator(List<Validator> validators) {
        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt();

        while (input < 0 || input >= validators.size()) {
            displayInvalidInput("You must choose a valid validator index");
            input = keyboard.nextInt();
        }

        return input;
    }

    private boolean nextRound(){
        return choseYorN("Do you want to test another code ?");
    }

    private boolean wantToGuessCode(){
        return choseYorN("Do you want to guess the secret code ?");
    }

    private boolean choseYorN(String message) {
        return stringRobustReading(message +
                " (y or n)").equalsIgnoreCase("y");
    }


    private int readNumber(String message) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);
        String input = keyboard.nextLine().trim();

        try {
            String[] detailInput = input.split("\\s+");
            return Integer.parseInt(detailInput[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            displayInvalidInput("Invalid input. Please try again.");
            return readNumber(message); // Recursive call to try again
        }
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


    // Méthode pour vérifier si un tableau d'entiers contient une valeur spécifique
    public static boolean contains(int[] array, int targetValue) {
        for (int value : array) {
            if (value == targetValue) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        App app = new App();
        app.app();
    }
}


