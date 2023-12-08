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

    public int getValidatorNumber() {
        return validatorNumber;
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

    @Override
    public String toString() {
        String displayValidator = "";
        switch (this.validatorNumber) {
            case 8 -> displayValidator = "Count how many times the value 1 appears";
            case 9 -> displayValidator = "Count how many times the value 3 appears";
            case 10 -> displayValidator = "Count how many times the value 4 appears";
        }
        return displayValidator;
    }

}
