package model.validators;

import model.Code;

public class CompSumTwoDigitsToAValue extends Validator {
    private final int value;

    public CompSumTwoDigitsToAValue(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
        this.value = 6;
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int sum  = code.getFirstDigit() + code.getSecondDigit();

        return Integer.compare(sum, value); //returns -1, 0 or 1; //returns 0 for even and 1 for odd
    }

    @Override
    public String toString() {
        return "Compare the sum of the first of second digit of the code with the value 6";
    }
}
