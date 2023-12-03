package model.validators;

import model.Code;

public class CompareOneDigitToAValue implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;

    public CompareOneDigitToAValue(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

    }

    private int category(Code code){
        int result = 0;

        switch (this.validatorNumber) {
            case 1 -> result = Integer.compare(code.getFirstDigit(), 1); //returns -1, 0 or 1
            case 2 -> result = Integer.compare(code.getFirstDigit(), 3); //returns -1, 0 or 1
            case 3 -> result = Integer.compare(code.getSecondDigit(), 3); //returns -1, 0 or 1
            case 4 -> result = Integer.compare(code.getSecondDigit(), 4); //returns -1, 0 or 1
        }

        return result;
    }
    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

}
