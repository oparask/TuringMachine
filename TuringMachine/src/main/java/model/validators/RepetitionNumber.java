package model.validators;

import model.Code;

public class RepetitionNumber implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;

    public RepetitionNumber(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
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
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Determines if a digit in the code repeats and if so, how many times";
    }
}
