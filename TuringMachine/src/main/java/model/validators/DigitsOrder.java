package model.validators;

import model.Code;

public class DigitsOrder implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;


    public DigitsOrder(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
    }
    private int category(Code code) {
        if (code.getFirstDigit() < code.getSecondDigit() && code.getSecondDigit() < code.getThirdDigit()){
            return 1; //true -> twin
        } else if (code.getFirstDigit() > code.getSecondDigit() && code.getSecondDigit() > code.getThirdDigit()){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Determines whether the three digits of the code are in ascending or descending order";
    }
}
