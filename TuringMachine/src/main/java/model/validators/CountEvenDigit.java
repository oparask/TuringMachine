package model.validators;

import model.Code;
/**
 * The CountEvenDigit class represents a validator that counts the occurrences of even digits in a code.
 * It checks if the count of even digits in the user code matches the count in the secret code.
 */
public class CountEvenDigit extends Validator {

    /**
     * Constructs a CountEvenDigit validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public CountEvenDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests if the count of even digits in the user code matches the count in the secret code.
     *
     * @return {@code true} if the counts match, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category (count) of even digits in the code.
     *
     * @param code The code in which to count occurrences of even digits.
     * @return The count of even digits.
     */
    private int category(Code code) {
        int even = 0;

        for (Integer digit : code) {
            if (digit % 2 == 0) {
                even++;
            }
        }

        return even;
    }

    /**
     * Returns a string representation of the validator, describing the counting of even digits.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        return "Count how many digits in the code are even";
    }
}
