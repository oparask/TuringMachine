package model.validators;

import model.Code;

public class ExtremeDigit implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;



    public ExtremeDigit(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    private int category(Code code) {
        int extremum;

        if (validatorNumber == 14) { //strictly the smallest
            extremum = Math.min(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.min(extremum, code.getThirdDigit());

        } else { //strictly the greatest
            extremum = Math.max(code.getFirstDigit(), code.getSecondDigit());
            extremum = Math.max(extremum, code.getThirdDigit());
        }

        return extremum;

    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        String displayValidator = "";
        switch (this.validatorNumber) {
            case 14 -> displayValidator = "Determine which number is strictly the smaller";
            case 15 -> displayValidator = "Determine which number is strictly the bigger";
        }
        return displayValidator;
    }
}
