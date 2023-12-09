package model;

import model.problems.Problem;
import model.validators.DefaultValidatorFactory;
import model.validators.Validator;
import model.validators.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Problem problem;
    private final Validator[] problemValidators;
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

    public List<Integer> getCurRoundTestedValidators() {
        return currentRound.getTestedValidators();
    }

    public List<Round> getRounds() {
        return rounds;
    }


    public void enterCode(Code code) {
        for(Validator validator: problemValidators){
            validator.setUserCode(code);
            validator.markAsTested(false);
        }
        currentRound.setUserCode(code);
    }

    public boolean testValidator(int validatorIndex) {
        return currentRound.testValidator(this.problemValidators[validatorIndex]);
    }

    public void nextRound() {
        Round round = new Round();
        currentRound = round;
        rounds.add(round);
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
