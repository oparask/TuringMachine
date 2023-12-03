package model.validators;

import model.Code;

public class CompSumTwoDigitsToAValue implements Validator {
    private Code secretCode;
    private Code userCode;
    private int value;

    public CompSumTwoDigitsToAValue(Code secretCode, Code userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.value = 6;
    }

    private int category(Code code) {
        int sum  = code.getFirstDigit() + code.getSecondDigit();

        return Integer.compare(sum, value); //returns -1, 0 or 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
