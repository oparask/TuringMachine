package model.validators;

import model.Code;
/**
 * The TwinDigit class extends the Validator class and represents a validator
 * that determines if a digit of the code is found in exactly two copies in the code (but not three).
 */
public class TwinDigit extends Validator {

    /**
     * Constructs a new TwinDigit validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public TwinDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether a digit of the user code is found in exactly two copies in the code (but not three).
     *
     * @return {@code true} if a digit is found in exactly two copies, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines if a digit in the provided code is found in exactly two copies in the code (but not three).
     *
     * @param code The code for which to determine if a digit is found in exactly two copies.
     * @return {@code 1} if a digit is found in exactly two copies, {@code 0} otherwise.
     */
    private int category(Code code) {
        if (code.getFirstDigit() == code.getSecondDigit()
                || code.getFirstDigit() == code.getThirdDigit()
                || code.getSecondDigit() == code.getThirdDigit()) {
            return 1; // true -> twin
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
        return "Determines if a digit of the code is found in exactly two copies in the code (but not three)";
    }
}
