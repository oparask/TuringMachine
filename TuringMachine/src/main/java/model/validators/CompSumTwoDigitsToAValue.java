package model.validators;

import model.Code;
/**
 * The CompSumTwoDigitsToAValue class represents a validator that compares the sum of two specific digits in a code
 * to a predefined value. It checks if the sum of the specified digits in the user code matches the predefined value
 * in the secret code.
 */
public class CompSumTwoDigitsToAValue extends Validator {

    private final int value;

    /**
     * Constructs a CompSumTwoDigitsToAValue validator with the specified secret code, user code, and validator number.
     * The validator checks if the sum of the first and second digits in the code matches the predefined value 6.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public CompSumTwoDigitsToAValue(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
        this.value = 6;
    }

    /**
     * Tests if the sum of the first and second digits in the user code matches the predefined value in the secret code.
     *
     * @return {@code true} if the sum matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category (comparison result) of the sum of the first and second digits in the code.
     *
     * @param code The code from which to extract the digits.
     * @return The result of the comparison (negative, zero, or positive).
     */
    private int category(Code code) {
        int sum = code.getFirstDigit() + code.getSecondDigit();
        return Integer.compare(sum, value); // returns -1, 0, or 1; returns 0 for even and 1 for odd
    }

    /**
     * Returns a string representation of the validator, describing the comparison of the sum of digits.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        return "Compare the sum of the first and second digit of the code with the value 6";
    }
}
