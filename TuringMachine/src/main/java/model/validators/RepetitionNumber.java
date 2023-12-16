package model.validators;

import model.Code;
/**
 * The RepetitionNumber class extends the Validator class and represents a validator
 * that determines if a digit in the code repeats and, if so, how many times.
 */
public class RepetitionNumber extends Validator {

    /**
     * Constructs a new RepetitionNumber validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public RepetitionNumber(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether the repetition number (how many times a digit repeats) in the user code matches that of the secret code.
     *
     * @return {@code true} if the repetition number matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the repetition number in the provided code based on the occurrences of repeated digits.
     *
     * @param code The code to be categorized.
     * @return The repetition number (0, 2, or 3) indicating the occurrences of repeated digits.
     */
    private int category(Code code) {
        int repetition = 0;

        if (code.getFirstDigit() == code.getSecondDigit() && code.getFirstDigit() == code.getThirdDigit()) {
            repetition = 3;
        } else if (code.getFirstDigit() == code.getSecondDigit()
                || code.getFirstDigit() == code.getThirdDigit()
                || code.getSecondDigit() == code.getThirdDigit()) {
            repetition = 2;
        }

        return repetition;
    }

    /**
     * Returns a human-readable description of the validator's purpose.
     *
     * @return A description of the validator's purpose.
     */
    @Override
    public String toString() {
        return "Determines if a digit in the code repeats and if so, how many times";
    }
}
