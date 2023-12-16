package model.validators;

import model.Code;
/**
 * The CountDigitValue class represents a validator that counts the occurrences of a specific digit value in a code.
 * It checks if the count of the specified digit value in the user code matches the count in the secret code.
 */
public class CountDigitValue extends Validator {

    /**
     * Constructs a CountDigitValue validator with the specified secret code, user code, and validator number.
     *
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     * @param validatorNumber The number associated with the validator.
     */
    public CountDigitValue(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    /**
     * Tests if the count of a specific digit value in the user code matches the count in the secret code.
     *
     * @return {@code true} if the counts match, {@code false} otherwise.
     */
    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    /**
     * Determines the category (count) of a specific digit value in the code.
     *
     * @param code The code in which to count occurrences of the specified digit value.
     * @return The count of occurrences.
     */
    private int category(Code code) {
        int value = 0;
        switch (super.getValidatorNumber()) {
            case 8 -> value = 1;
            case 9 -> value = 3;
            case 10 -> value = 4;
        }

        int repetitionNb = 0;
        for (Integer digit : code) {
            if (digit == value) {
                repetitionNb++;
            }
        }

        return repetitionNb; // returns 0 for even and 1 for odd
    }

    /**
     * Returns a string representation of the validator, describing the counting of occurrences of a specific digit value.
     *
     * @return A string description of the validator.
     */
    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 8 -> displayValidator = "Count how many times the value 1 appears";
            case 9 -> displayValidator = "Count how many times the value 3 appears";
            case 10 -> displayValidator = "Count how many times the value 4 appears";
        }
        return displayValidator;
    }
}
