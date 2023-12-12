package model;

import model.problems.Problem;
import model.validators.DefaultValidatorFactory;
import model.validators.Validator;
import model.validators.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Problem problem;
    private Validator[] problemValidators;
    private List<Round> rounds;
    private Round currentRound;

    public Game(Problem problem) {
        this.problem = problem;
        rounds = new ArrayList<>();
        currentRound = new Round();
        rounds.add(currentRound);

        problemValidators = initializeValidators();
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

    public Validator getLastValidatorTested(){
        return currentRound.getLastValidatorTested();
    }
    public Code getUserCode(){
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
        for(Validator validator: problemValidators){
            validator.setUserCode(code);
        }
        currentRound.setUserCode(code);
    }

    public boolean testValidator(int validatorIndex) {
        return currentRound.testValidator(this.problemValidators[validatorIndex]);
    }

    public void nextRound() {
        problemValidators = initializeValidators();
        currentRound = new Round();
        rounds.add(currentRound);
    }

    public boolean guessCode() {
        return currentRound.getUserCode().equals(problem.getSecretCode());
    }

    public int getScore() {
        int score = 0;
        for (Round round : rounds) {
            score += round.getTestedValidators().size();
        }

        return score;
    }


}
