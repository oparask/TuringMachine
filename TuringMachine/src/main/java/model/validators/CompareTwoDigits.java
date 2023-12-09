package model.validators;

import model.Code;

public class CompareTwoDigits extends Validator {

    public CompareTwoDigits(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code){
        int result = 0; //-1, 0 or 1
        switch (super.getValidatorNumber()){
            case 11 -> result = Integer.compare(code.getFirstDigit(), code.getSecondDigit()); //returns -1, 0 or 1
            case 12 -> result = Integer.compare(code.getFirstDigit(), code.getThirdDigit()); //returns -1, 0 or 1
            case 13 -> result = Integer.compare(code.getSecondDigit(), code.getThirdDigit()); //returns -1, 0 or 1
        }
        return result;
    }

    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()){
            case 11 -> displayValidator = "Compare the first digit of the code with the second";
            case 12 -> displayValidator = "Compare the first digit of the code with the third";
            case 13 -> displayValidator = "Compare the second digit of the code with the third";
        }
        return displayValidator;
    }
}
