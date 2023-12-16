package model;

import model.problems.Problem;
import model.validators.DefaultValidatorFactory;
import model.validators.Validator;
import model.validators.ValidatorFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * The Game class represents the core logic of the Turing Machine game. It manages
 * rounds, validators, and user interactions with the game.
 * <p>
 * This class implements the observer pattern, allowing external classes to register
 * as observers and receive notifications about changes in the game state.
 *
 * @author Your Name
 * @version 1.0
 */
public class Game {

    /**
     * The PropertyChangeSupport for handling property change events.
     */
    private final PropertyChangeSupport pcs;

    /**
     * The current problem to be solved in the game.
     */
    private final Problem problem;

    /**
     * The array of validators associated with the current problem.
     */
    private Validator[] problemValidators;

    /**
     * The list of rounds played in the game.
     */
    private List<Round> rounds;

    /**
     * The current round being played.
     */
    private Round currentRound;

    /**
     * Constructs a new Game instance with the specified problem.
     *
     * @param problem The problem to be solved in the game.
     */
    public Game(Problem problem) {
        pcs = new PropertyChangeSupport(this);
        this.problem = problem;
        rounds = new ArrayList<>();
        currentRound = new Round();
        rounds.add(currentRound);

        problemValidators = initializeValidators();
    }

    /**
     * Registers a PropertyChangeListener to observe changes in the game state.
     *
     * @param observer The PropertyChangeListener to be registered.
     */
    public void registerObserver(PropertyChangeListener observer) {
        pcs.addPropertyChangeListener(observer);
    }

    /**
     * Initializes the array of validators based on the current problem.
     *
     * @return The array of initialized validators.
     */
    private Validator[] initializeValidators() {
        Validator[] validators = new Validator[problem.getValidators().length];
        for (int i = 0; i < problem.getValidators().length; i++) {
            validators[i] = validatorConversion(problem.getSecretCode(), currentRound.getUserCode(), problem.getValidators()[i]);
        }
        return validators;
    }

    /**
     * Converts validator numbers to Validator objects.
     *
     * @param secretCode  The secret code of the problem.
     * @param userCode    The user-entered code.
     * @param validatorNb The validator number.
     * @return The corresponding Validator object.
     */
    private Validator validatorConversion(Code secretCode, Code userCode, int validatorNb) {
        ValidatorFactory validatorFactory = new DefaultValidatorFactory();
        return validatorFactory.createValidator(secretCode, userCode, validatorNb);
    }

    /**
     * Gets the array of validators associated with the current problem.
     *
     * @return The array of problem validators.
     */
    public Validator[] getProblemValidators() {
        return problemValidators;
    }

    /**
     * Gets the list of validators tested in the current round.
     *
     * @return The list of tested validators in the current round.
     */
    public List<Validator> getCurRoundTestedValidators() {
        return currentRound.getTestedValidators();
    }

    /**
     * Sets the array of problem validators.
     *
     * @param problemValidators The new array of problem validators.
     */
    public void setProblemValidators(Validator[] problemValidators) {
        this.problemValidators = problemValidators;
    }

    /**
     * Gets the last validator that was tested in the current round.
     *
     * @return The last tested validator in the current round.
     */
    public Validator getLastValidatorTested() {
        return currentRound.getLastValidatorTested();
    }

    /**
     * Gets the user-entered code in the current round.
     *
     * @return The user-entered code in the current round.
     */
    public Code getUserCode() {
        return currentRound.getUserCode();
    }

    /**
     * Gets the list of rounds played in the game.
     *
     * @return The list of rounds played in the game.
     */
    public List<Round> getRounds() {
        return rounds;
    }

    /**
     * Gets the current round being played.
     *
     * @return The current round.
     */
    public Round getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the current round to the specified round.
     *
     * @param currentRound The new current round.
     */
    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * Enters the provided code as the user's input for the current round.
     *
     * @param code The user-entered code.
     */
    public void enterCode(Code code) {
        for (Validator validator : problemValidators) {
            validator.setUserCode(code);
        }
        currentRound.setUserCode(code);
    }

    /**
     * Tests the validator at the specified index in the array of problem validators.
     * Notifies observers about the test result and the updated score.
     *
     * @param validatorIndex The index of the validator to be tested.
     */
    public void testValidator(int validatorIndex) {
        boolean testValidatorResult = currentRound.testValidator(this.problemValidators[validatorIndex]);
        int newScore = getScore();
        // Notify observers
        pcs.firePropertyChange("testValidatorResult", null, testValidatorResult);
        pcs.firePropertyChange("newScore", newScore - 1, newScore);
    }

    /**
     * Proceeds to the next round, initializing new problem validators and creating a new round.
     */
    public void nextRound() {
        problemValidators = initializeValidators();
        currentRound = new Round();
        rounds.add(currentRound);
    }

    /**
     * Checks if the user's entered code matches the secret code for the current problem.
     * Notifies observers about the guess code result.
     *
     * @return True if the user's code matches the secret code, false otherwise.
     */
    public boolean guessCode() {
        boolean guessCodeResult = currentRound.getUserCode().equals(problem.getSecretCode());
        // Notify observers
        pcs.firePropertyChange("guessCodeResult", null, guessCodeResult);

        return guessCodeResult;
    }

    /**
     * Calculates and returns the total score based on the number of tested validators in all rounds.
     *
     * @return The total score.
     */
    public int getScore() {
        int score = 0;
        for (Round round : rounds) {
            score += round.getTestedValidators().size();
        }

        return score;
    }
}
