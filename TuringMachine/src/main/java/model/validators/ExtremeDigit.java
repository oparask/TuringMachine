package model.validators;

import model.Code;

public class ExtremeDigit extends Validator {

    public ExtremeDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int extremum;

        if (super.getValidatorNumber() == 14) { //strictly the smallest
            extremum = Math.min(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.min(extremum, code.getThirdDigit());

        } else { //strictly the greatest
            extremum = Math.max(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.max(extremum, code.getThirdDigit());
        }

        return extremum;

    }

    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 14 -> displayValidator = "Determine which number is strictly the smaller";
            case 15 -> displayValidator = "Determine which number is strictly the bigger";
        }
        return displayValidator;
    }
}
