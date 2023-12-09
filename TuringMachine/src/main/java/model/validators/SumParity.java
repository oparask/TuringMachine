package model.validators;

import model.Code;

public class SumParity  extends Validator {

    public SumParity(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int sum  = code.getFirstDigit() + code.getSecondDigit() + code.getThirdDigit();

        return sum % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd
    }

    @Override
    public String toString() {
        return "Determines whether the sum of the digits of the code is even or odd";
    }
}
