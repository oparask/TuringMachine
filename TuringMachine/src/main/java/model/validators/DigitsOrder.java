package model.validators;

import model.Code;
/**
 * The DigitsOrder class extends the Validator class and represents a validator
 * that determines whether the three digits of the code are in ascending or descending order.
 */
public class DigitsOrder extends Validator {

    /**
     * Constructs a new DigitsOrder validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public DigitsOrder(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether the user code's digits are in the same order as the secret code's digits.
     *
     * @return {@code true} if the digits are in the same order, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category of the provided code based on the order of its digits.
     *
     * @param code The code to be categorized.
     * @return 1 if the digits are in ascending order, -1 if in descending order, and 0 otherwise.
     */
    private int category(Code code) {
        if (code.getFirstDigit() < code.getSecondDigit() && code.getSecondDigit() < code.getThirdDigit()) {
            return 1; // true -> twin
        } else if (code.getFirstDigit() > code.getSecondDigit() && code.getSecondDigit() > code.getThirdDigit()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Returns a human-readable description of the validator's purpose.
     *
     * @return A description of the validator's purpose.
     */
    @Override
    public String toString() {
        return "Determines whether the three digits of the code are in ascending or descending order";
    }
}
