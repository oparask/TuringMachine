package model.validators;

import model.Code;

public class DigitsOrder extends Validator {

    public DigitsOrder(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test(){
     return category(super.getUserCode()) == category(super.getSecretCode());
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
    public String toString() {
        return "Determines whether the three digits of the code are in ascending or descending order";
    }
}
