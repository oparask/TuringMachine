package model;

import model.problems.Problem;
import model.validators.DefaultValidatorFactory;
import model.validators.Validator;
import model.validators.ValidatorFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Game { //MyObservable

    // Gestionnaire des observateurs
    private final PropertyChangeSupport pcs;

    private final Problem problem;
    private Validator[] problemValidators;
    private List<Round> rounds;
    private Round currentRound;

    public Game(Problem problem) {
        // Initialize le PropertyChangeSupport avec un objet "source"
        pcs = new PropertyChangeSupport(this);
        this.problem = problem;
        rounds = new ArrayList<>();
        currentRound = new Round();
        rounds.add(currentRound);

        problemValidators = initializeValidators();
    }

        /**
         * Registers a PropertyChangeListener to observe changes in BMR and calories.
         *
         * @param observer The PropertyChangeListener to be registered.
         */
    public void registerObserver(PropertyChangeListener observer) {
        pcs.addPropertyChangeListener(observer);
    }

    private Validator[] initializeValidators() {
        Validator[] validators = new Validator[problem.getValidators().length];
        for (int i = 0; i < problem.getValidators().length; i++) {
            validators[i] = validatorConversion(problem.getSecretCode(), currentRound.getUserCode(), problem.getValidators()[i]);
        }
        return validators;
    }

    private Validator validatorConversion(Code secretCode, Code userCode, int validatorNb) {
        ValidatorFactory validatorFactory = new DefaultValidatorFactory();
        return validatorFactory.createValidator(secretCode, userCode, validatorNb);
    }

    public Validator[] getProblemValidators() {
        return problemValidators;
    }

    public List<Validator> getCurRoundTestedValidators() {
        return currentRound.getTestedValidators();
    }

    public void setProblemValidators(Validator[] problemValidators) {
        this.problemValidators = problemValidators;
    }

    public Validator getLastValidatorTested() {
        return currentRound.getLastValidatorTested();
    }

    public Code getUserCode() {
        return currentRound.getUserCode();
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void enterCode(Code code) {
        for (Validator validator : problemValidators) {
            validator.setUserCode(code);
        }
        currentRound.setUserCode(code);
    }

    public void testValidator(int validatorIndex) {
        boolean testValidatorResult = currentRound.testValidator(this.problemValidators[validatorIndex]);
        int newScore = getScore();
        // Notify observers
        pcs.firePropertyChange("testValidatorResult",null, testValidatorResult);
        pcs.firePropertyChange("newScore",newScore-1, newScore);
    }

    public void nextRound() {
        problemValidators = initializeValidators();
        currentRound = new Round();
        rounds.add(currentRound);
    }

    public boolean guessCode() {
        boolean guessCodeResult = currentRound.getUserCode().equals(problem.getSecretCode());
        // Notify observers
        pcs.firePropertyChange("guessCodeResult",null, guessCodeResult);

        return guessCodeResult;
    }

    public int getScore() {
        int score = 0;
        for (Round round : rounds) {
            score += round.getTestedValidators().size();
        }

        return score;
    }


}
