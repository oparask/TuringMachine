package model.validators;

import model.Code;
/**
 * The ExtremeDigit class extends the Validator class and represents a validator
 * that determines the extreme digit in a code.
 */
public class ExtremeDigit extends Validator {

    /**
     * Constructs a new ExtremeDigit validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode     The secret code used for validation.
     * @param userCode       The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public ExtremeDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests whether the extreme digit of the user code matches that of the secret code.
     *
     * @return {@code true} if the extreme digit matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the extreme digit of the provided code based on the validator number.
     *
     * @param code The code to be categorized.
     * @return The extreme digit (strictly the smallest or the greatest) of the code.
     */
    private int category(Code code) {
        int extremum;

        if (super.getValidatorNumber() == 14) { // strictly the smallest
            extremum = Math.min(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.min(extremum, code.getThirdDigit());

        } else { // strictly the greatest
            extremum = Math.max(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.max(extremum, code.getThirdDigit());
        }

        return extremum;
    }

    /**
     * Returns a human-readable description of the validator's purpose.
     *
     * @return A description of the validator's purpose.
     */
    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 14 -> displayValidator = "Determine which number is strictly the smaller";
            case 15 -> displayValidator = "Determine which number is strictly the bigger";
        }
        return displayValidator;
    }
}

