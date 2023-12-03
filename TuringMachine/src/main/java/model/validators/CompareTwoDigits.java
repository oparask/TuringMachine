package model.validators;

import model.Code;

public class CompareTwoDigits implements Validator {

    private Code secretCode;
    private Code userCode;
    private int validatorNumber;
    public CompareTwoDigits(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    private int category(Code code){
        int result = 0; //-1, 0 or 1
        switch (validatorNumber){
            case 11 -> result = Integer.compare(code.getFirstDigit(), code.getSecondDigit()); //returns -1, 0 or 1
            case 12 -> result = Integer.compare(code.getFirstDigit(), code.getThirdDigit()); //returns -1, 0 or 1
            case 13 -> result = Integer.compare(code.getSecondDigit(), code.getThirdDigit()); //returns -1, 0 or 1
        }
        return result;
    }
    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
