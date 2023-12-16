package model.validators;

import model.Code;
/**
 * The SumParity class extends the Validator class and represents a validator
 * that determines whether the sum of the digits of the code is even or odd.
 */
public class SumParity extends Validator {

    /**
     * Constructs a new SumParity validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public SumParity(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether the parity of the sum of the digits in the user code matches that of the secret code.
     *
     * @return {@code true} if the parity of the sum matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the parity of the sum of the digits in the provided code.
     *
     * @param code The code for which to determine the parity of the sum.
     * @return The parity of the sum (0 for even, 1 for odd).
     */
    private int category(Code code) {
        int sum = code.getFirstDigit() + code.getSecondDigit() + code.getThirdDigit();

        return sum % 2 == 0 ? 0 : 1; // returns 0 for even and 1 for odd
    }

    /**
     * Returns a human-readable description of the validator's purpose.
     *
     * @return A description of the validator's purpose.
     */
    @Override
    public String toString() {
        return "Determines whether the sum of the digits of the code is even or odd";
    }
}
