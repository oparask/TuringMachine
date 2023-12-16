package model;

import javaFx.view.fourthWindow.FourthWindowView;
import model.command.CommandManager;
import model.command.EnterUserCodeCommand;
import model.command.NextRoundCommand;
import model.command.TestValidatorCommand;
import model.problems.Problem;
import model.problems.ProblemReader;
import model.validators.Validator;

import java.util.List;
import java.util.Stack;

/**
 * The GameFacade class represents a facade for managing the game's functionality.
 * It provides a simplified interface for interacting with the game and managing its state.
 */
public class GameFacade {
    /**
     * The list of known problems available for the game.
     */
    private final List<Problem> problems;

    /**
     * The current game instance being managed by the facade.
     */
    private Game currentGame;

    /**
     * Stack to keep track of FourthWindowView instances for undo operations.
     */
    private Stack<FourthWindowView> undoFourthWindowViews;

    /**
     * Stack to keep track of FourthWindowView instances for redo operations.
     */
    private Stack<FourthWindowView> redoFourthWindowViews;

    /**
     * The CommandManager responsible for managing commands (undo and redo) in the game.
     */
    private CommandManager commandManager;

    /**
     * Constructs a GameFacade with a list of known problems and initializes other fields.
     */
    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        currentGame = null;
        commandManager = new CommandManager();
        undoFourthWindowViews = new Stack<>();
        redoFourthWindowViews = new Stack<>();
    }

    /**
     * Returns the list of known problems available for the game.
     *
     * @return The list of known problems.
     */
    public List<Problem> getProblems() {
        return problems;
    }

    /**
     * Returns the array of validators associated with the current problem in the game.
     *
     * @return The array of validators for the current problem.
     */
    public Validator[] getProblemValidators() {
        return currentGame.getProblemValidators();
    }

    /**
     * Returns the list of tested validators in the current round of the game.
     *
     * @return The list of tested validators in the current round.
     */
    public List<Validator> getCurRoundTestedValidators() {
        return currentGame.getCurRoundTestedValidators();
    }

    /**
     * Returns the list of rounds played in the game.
     *
     * @return The list of rounds played.
     */
    public List<Round> getRounds() {
        return currentGame.getRounds();
    }

    /**
     * Returns the user-entered code in the current round of the game.
     *
     * @return The user-entered code in the current round.
     */
    public Code getUserCode() {
        return currentGame.getUserCode();
    }

    /**
     * Returns the current score in the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return currentGame.getScore();
    }

    /**
     * Returns the result of the last tested validator in terms of having the same characteristic.
     *
     * @return The result of the last tested validator's characteristic comparison.
     */
    public boolean getLastValidatorTestResult() {
        return currentGame.getLastValidatorTested().hasSameCharacteristic();
    }

    /**
     * Returns the current game instance.
     *
     * @return The current game instance.
     */
    public Game getCurrentGame() {
        return currentGame;
    }


    /**
     * Starts a new game with the specified problem number.
     *
     * @param problemNb The number of the problem to be used in the new game.
     * @throws IllegalArgumentException If the problem number is not within the valid range (1 to the total number of problems).
     */
    public void startNewGame(int problemNb) {
        if (problemNb < 1 || problemNb > problems.size()) {
            throw new IllegalArgumentException("The problem number must be between 1 and " + problems.size());
        }

        // Initialize the first game
        currentGame = new Game(problems.get(problemNb - 1));
    }

    /**
     * Enters a user code into the current game.
     *
     * @param code The code to be entered.
     * @throws IllegalArgumentException If the provided code is invalid.
     */
    public void enterCode(int code) {
        if (!validateCode(code)) {
            throw new IllegalArgumentException("Le code doit être constitué de trois chiffres.\n" +
                    "Et chaque chiffre du code doit être compris entre 1 et 5 inclus.");
        }

        Code userCode = new Code(code);
        // Create the command and ask commandManager to execute it
        EnterUserCodeCommand command = new EnterUserCodeCommand(currentGame, userCode);
        commandManager.execute(command);
    }


    /**
     * Tests the specified validator in the current game.
     *
     * @param validatorIndex The index of the validator to be tested.
     * @throws IllegalArgumentException If the validator index is invalid (less than 0 or greater than the maximum index).
     */
    public void testValidator(int validatorIndex) {
        if (validatorIndex < 0 || validatorIndex > currentGame.getProblemValidators().length - 1) {
            throw new IllegalArgumentException("You must choose a valid validator index.");
        }

        // Create the command and ask commandManager to execute it
        TestValidatorCommand command = new TestValidatorCommand(currentGame, validatorIndex);
        commandManager.execute(command);
    }

    /**
     * Advances the game to the next round.
     * <p>
     * This method creates a command to execute the next round and asks the commandManager to execute it.
     */
    public void nextRound() {
        // Create the command and ask commandManager to execute it
        NextRoundCommand command = new NextRoundCommand(currentGame);
        commandManager.execute(command);
    }


    /**
     * Attempts to guess the secret code for the current round in the game.
     * If the guess is correct, the game is considered won, and the current game is abandoned.
     *
     * @return {@code true} if the guess is correct; {@code false} otherwise.
     */
    public boolean guessCode() {
        boolean result = currentGame.guessCode();

        // Abandon the current game after making a guess
        abandonGame();

        return result;
    }


    /**
     * Validates the given code, ensuring it meets the required criteria.
     *
     * @param code The code to be validated.
     * @return {@code true} if the code is valid; {@code false} otherwise.
     */
    private boolean validateCode(int code) {
        String codeStr = String.valueOf(code);

        // Check if the code has exactly three digits
        if (codeStr.length() != 3) {
            return false;
        }

        // Check if each digit is between 1 and 5 inclusive
        for (char digitChar : codeStr.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            if (digit < 1 || digit > 5) {
                return false;
            }
        }

        return true;
    }

    /**
     * Undoes the last executed command.
     * <p>
     * This method invokes the undo operation on the command manager,
     * allowing the reversal of the last executed command in the command history.
     * If there are no commands to undo, this method has no effect.
     */
    public void undo() {
        commandManager.undoM();
    }

    /**
     * Redoes the previously undone command.
     * <p>
     * This method invokes the redo operation on the command manager,
     * allowing the execution of the last undone command in the command history.
     * If there are no commands to redo, this method has no effect.
     */
    public void redo() {
        commandManager.redoM();
    }

    public void abandonGame() {
        currentGame = null; // Réinitialiser le jeu courant après l'abandon
    }


    /**
     * Adds a FourthWindowView to the undo stack and clears the redo stack.
     *
     * @param fourthWindowView The view to be added to the undo stack.
     */
    public void addViewToTheStack(FourthWindowView fourthWindowView) {
        this.undoFourthWindowViews.push(fourthWindowView);
        this.redoFourthWindowViews.clear();
    }

    /**
     * Undoes the last FourthWindowView action.
     *
     * @return The undone FourthWindowView or null if the stack is empty.
     */
    public FourthWindowView undoFourthWindowView() {
        if (!undoFourthWindowViews.isEmpty()) {
            FourthWindowView fourthWindowView = undoFourthWindowViews.pop();
            this.redoFourthWindowViews.push(fourthWindowView);
            return fourthWindowView;
        }
        return null;
    }

    /**
     * Redoes the previously undone FourthWindowView action.
     *
     * @return The redone FourthWindowView or null if the redo stack is empty.
     */
    public FourthWindowView redoFourthWindowView() {
        if (!redoFourthWindowViews.isEmpty()) {
            FourthWindowView fourthWindowView = redoFourthWindowViews.pop();
            this.undoFourthWindowViews.push(fourthWindowView);
            return fourthWindowView;
        }
        return null;
    }

}

