package model.validators;

import model.Code;
/**
 * The CompareOneDigitToAValue class represents a validator that compares the value of a specific digit in a code
 * with a predefined constant value. It checks if the value of the specified digit in the user code matches that of
 * the secret code.
 */
public class CompareOneDigitToAValue extends Validator {

    /**
     * Constructs a CompareOneDigitToAValue validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator, indicating which digit's value to compare.
     */
    public CompareOneDigitToAValue(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests if the value of the specified digit in the user code matches that of the secret code.
     *
     * @return {@code true} if the values match, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category (comparison result) of the specified digit in the code with a predefined constant value.
     *
     * @param code The code from which to extract the digit.
     * @return The result of the comparison (negative, zero, or positive).
     */
    private int category(Code code) {
        int result = 0;

        switch (super.getValidatorNumber()) {
            case 1 -> result = Integer.compare(code.getFirstDigit(), 1); // returns -1, 0, or 1
            case 2 -> result = Integer.compare(code.getFirstDigit(), 3); // returns -1, 0, or 1
            case 3 -> result = Integer.compare(code.getSecondDigit(), 3); // returns -1, 0, or 1
            case 4 -> result = Integer.compare(code.getSecondDigit(), 4); // returns -1, 0, or 1
        }

        return result;
    }

    /**
     * Returns a string representation of the validator, describing the comparison of the specified digit to a value.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 1 -> displayValidator = "Compare the first digit of the code with 1";
            case 2 -> displayValidator = "Compare the first number with 3";
            case 3 -> displayValidator = "Compare the second number with 3";
            case 4 -> displayValidator = "Compare the second number with 4";
        }
        return displayValidator;
    }
}
