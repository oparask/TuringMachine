package model;

import model.problems.Problem;
import model.validators.*;

import java.util.ArrayList;
import java.util.List;

public class Round {
    Problem problem;
    Code userCode;
    List<Validator> testedValidators;
    List<Validator> validators;

    public Round(Code userCode, Problem problem) {
        this.userCode = userCode;
        this.problem = problem;
        this.testedValidators = new ArrayList<>();
        validators = new ArrayList<>();
        for (int validatorNb : problem.getValidators()) {
            validators.add(validatorConversion(validatorNb));
        }
    }

    public Code getUserCode() {
        return userCode;
    }
    public boolean testValidator(int validatorNb) {
        if (testedValidators.size() == 3) {
            throw new IllegalArgumentException("You already have tested 3 validators");
        }

        Validator validator = validatorConversion(validatorNb);
        testedValidators.add(validator);
        return validator.test();
    }

    public Problem getProblem() {
        return problem;
    }

    public List<Validator> getTestedValidators() {
        return testedValidators;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public Validator validatorConversion(int validatorNb){
        Validator validator = null;
        switch (validatorNb) {
            case 1, 2, 3, 4 -> validator = new CompareOneDigitToAValue(problem.getSecretCode(), userCode, validatorNb);
            case 5, 6, 7 -> validator = new CheckParityOfOneDigit(problem.getSecretCode(), userCode, validatorNb);
            case 8, 9, 10 -> validator = new CountDigitValue(problem.getSecretCode(), userCode, validatorNb);
            case 11, 12, 13 -> validator = new CompareTwoDigits(problem.getSecretCode(), userCode, validatorNb);
            case 14, 15 -> validator = new ExtremeDigit(problem.getSecretCode(), userCode, validatorNb);
            case 16 -> validator = new MostFrequentParity(problem.getSecretCode(), userCode);
            case 17 -> validator = new CountEvenDigit(problem.getSecretCode(), userCode);
            case 18 -> validator = new SumParity(problem.getSecretCode(), userCode);
            case 19 -> validator = new CompSumTwoDigitsToAValue(problem.getSecretCode(), userCode);
            case 20 -> validator = new RepetitionNumber(problem.getSecretCode(), userCode);
            case 21 -> validator = new TwinDigit(problem.getSecretCode(), userCode);
            case 22 -> validator = new DigitsOrder(problem.getSecretCode(), userCode);
        }

        return validator;
    }
}
