package model.validators;

import model.Code;

public class TwinDigit implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;

    public TwinDigit(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }
    public int getValidatorNumber() {
        return validatorNumber;
    }

    private int category(Code code) {
        if (code.getFirstDigit() == code.getSecondDigit()
                || code.getFirstDigit() == code.getThirdDigit()
                || code.getSecondDigit()  == code.getThirdDigit() ){
           return 1; //true -> twin
        } else  {
            return 0;
        }
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Determines if a digit of the code is found in exactly two copies in the code (but not three)";
    }
}
