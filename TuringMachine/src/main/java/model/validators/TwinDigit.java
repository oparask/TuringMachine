package model.validators;

import model.Code;

public class TwinDigit extends Validator {

    public TwinDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
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
    public String toString() {
        return "Determines if a digit of the code is found in exactly two copies in the code (but not three)";
    }
}
