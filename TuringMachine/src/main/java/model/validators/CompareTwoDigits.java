package model.validators;

import model.Code;
/**
 * The CompareTwoDigits class represents a validator that compares the values of two specific digits in a code.
 * It checks if the values of the specified digits in the user code match those of the secret code.
 */
public class CompareTwoDigits extends Validator {

    /**
     * Constructs a CompareTwoDigits validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator, indicating which digits' values to compare.
     */
    public CompareTwoDigits(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests if the values of the specified digits in the user code match those of the secret code.
     *
     * @return {@code true} if the values match, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category (comparison result) of the specified digits in the code.
     *
     * @param code The code from which to extract the digits.
     * @return The result of the comparison (negative, zero, or positive).
     */
    private int category(Code code) {
        int result = 0; // -1, 0, or 1

        switch (super.getValidatorNumber()) {
            case 11 -> result = Integer.compare(code.getFirstDigit(), code.getSecondDigit()); // returns -1, 0, or 1
            case 12 -> result = Integer.compare(code.getFirstDigit(), code.getThirdDigit()); // returns -1, 0, or 1
            case 13 -> result = Integer.compare(code.getSecondDigit(), code.getThirdDigit()); // returns -1, 0, or 1
        }

        return result;
    }

    /**
     * Returns a string representation of the validator, describing the comparison of the specified digits.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        String displayValidator = "";

        switch (super.getValidatorNumber()) {
            case 11 -> displayValidator = "Compare the first digit of the code with the second";
            case 12 -> displayValidator = "Compare the first digit of the code with the third";
            case 13 -> displayValidator = "Compare the second digit of the code with the third";
        }

        return displayValidator;
    }
}

