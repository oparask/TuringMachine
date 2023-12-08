package model.validators;

import model.Code;

public class CompSumTwoDigitsToAValue implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;
    private int value;

    public CompSumTwoDigitsToAValue(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
        this.value = 6;
    }

    public int getValidatorNumber() {
        return validatorNumber;
    }
    private int category(Code code) {
        int sum  = code.getFirstDigit() + code.getSecondDigit();

        return Integer.compare(sum, value); //returns -1, 0 or 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Compare the sum of the first of second digit of the code with the value 6";
    }
}
