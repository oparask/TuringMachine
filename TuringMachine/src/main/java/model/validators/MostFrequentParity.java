package model.validators;

import model.Code;
/**
 * The MostFrequentParity class extends the Validator class and represents a validator
 * that determines if there are more even or odd numbers in a code.
 */
public class MostFrequentParity extends Validator {

    /**
     * Constructs a new MostFrequentParity validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public MostFrequentParity(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether the most frequent parity (even or odd) in the user code matches that of the secret code.
     *
     * @return {@code true} if the most frequent parity matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the most frequent parity in the provided code based on the number of even and odd digits.
     *
     * @param code The code to be categorized.
     * @return The most frequent parity in the code (even or odd).
     */
    private int category(Code code) {
        int even = 0;
        int odd = 0;

        for (Integer digit : code) {
            if (digit % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return Math.max(even, odd);
    }

    /**
     * Returns a human-readable description of the validator's purpose.
     *
     * @return A description of the validator's purpose.
     */
    @Override
    public String toString() {
        return "Determines if there are more even numbers or odd in code";
    }
}

