package model.validators;

import model.Code;
/**
 * The CheckParityOfOneDigit class represents a validator that checks the parity of a specific digit in a code.
 * It compares the parity of the specified digit in the user code with that of the secret code.
 */
public class CheckParityOfOneDigit extends Validator {

    /**
     * Constructs a CheckParityOfOneDigit validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator, indicating which digit's parity to check.
     */
    public CheckParityOfOneDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests the parity of the specified digit in the user code against that of the secret code.
     *
     * @return {@code true} if the parity matches, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the parity category (even or odd) of the specified digit in the code.
     *
     * @param code The code from which to extract the digit.
     * @return 0 for even, 1 for odd.
     */
    private int category(Code code) {
        int digit = 0;

        switch (super.getValidatorNumber()) {
            case 5 -> digit = code.getFirstDigit();
            case 6 -> digit = code.getSecondDigit();
            case 7 -> digit = code.getThirdDigit();
        }
        return digit % 2 == 0 ? 0 : 1; // returns 0 for even and 1 for odd
    }

    /**
     * Returns a string representation of the validator, describing the parity check for the specified digit.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 5 -> displayValidator = "Checks the parity of the first digit";
            case 6 -> displayValidator = "Check the parity of the second digit";
            case 7 -> displayValidator = "Check the parity of the third digit";
        }
        return displayValidator;
    }
}

