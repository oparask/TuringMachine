package model.validators;

import model.Code;

public class CountDigitValue implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;


    public CountDigitValue(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

    }

    private int category(Code code) {
        int value = 0;
        switch (this.validatorNumber) {
            case 8 -> value = 1;
            case 9 -> value = 3;
            case 10 -> value = 4;
        }

        int repetitionNb = 0;
        for(Integer digit : code){
            if (digit == value) {
                repetitionNb++;
            }
        }

        return repetitionNb; //returns 0 for even ans 1 for odd

    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
