package model.validators;

import model.Code;

public class RepetitionNumber extends Validator {

    public RepetitionNumber(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int repetition = 0;

        if(code.getFirstDigit() == code.getSecondDigit() && code.getFirstDigit() == code.getThirdDigit()){
            repetition = 3;
        } else if (code.getFirstDigit() == code.getSecondDigit()
                || code.getFirstDigit() == code.getThirdDigit()
                || code.getSecondDigit()  == code.getThirdDigit() ){
            repetition = 2;
        }

        return repetition;
    }

    @Override
    public String toString() {
        return "Determines if a digit in the code repeats and if so, how many times";
    }
}
