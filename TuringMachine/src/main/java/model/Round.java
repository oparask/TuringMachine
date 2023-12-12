package model;

import model.validators.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Round} class represents a single round in the game, storing the user's entered code and
 * the tested validators along with their results.
 */
public class Round {
    private Code userCode;
    private final List<Validator> testedValidators;

    /**
     * Constructs a Round object with no user code and an empty list of tested validators.
     */
    public Round() {
        this.userCode = null;
        this.testedValidators = new ArrayList<>();
    }

    /**
     * Gets the user's entered code for the current round.
     *
     * @return The user's entered code as a {@code Code} object.
     */
    public Code getUserCode() {
        return userCode;
    }

    /**
     * Gets the list of tested validator numbers in the current round.
     *
     * @return The list of tested validator numbers.
     */
    public List<Validator> getTestedValidators() {
        return testedValidators;
    }

    public Validator  getLastValidatorTested() {
        return testedValidators.get(testedValidators.size()-1);
    }


    /**
     * Tests a validator in the current round, marking it as tested and storing the result.
     *
     * @param validator The validator to test.
     * @return The result of the validator test.
     * @throws IllegalArgumentException if the validator is already tested or if three validators are already tested.
     */
    public boolean testValidator(Validator validator) {
        if (validator.isTested()) {
            throw new IllegalArgumentException("You've already tested this validator");
        }

        if (testedValidators.size() == 3) {
            throw new IllegalArgumentException("You have already tested 3 validators");
        }

        boolean testResult = validator.test();
        validator.markAsTested(true);
        validator.setTestResult(testResult);

        testedValidators.add(validator);

        return testResult;
    }

    /**
     * Sets the user's entered code for the current round.
     *
     * @param code The user's entered code.
     */
    public void setUserCode(Code code) {
        this.userCode = code;
    }

}
