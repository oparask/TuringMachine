package model.validators;

import model.Code;

public class SumParity  implements Validator {
    private Code secretCode;
    private Code userCode;

    public SumParity(Code secretCode, Code userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(Code code) {
        int sum  = code.getFirstDigit() + code.getSecondDigit() + code.getThirdDigit();

        return sum % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Determines whether the sum of the digits of the code is even or odd";
    }
}
