package model.validators;

import model.Code;

public class CheckParityOfOneDigit implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;


    public CheckParityOfOneDigit(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
    }

    private int category(Code code) {
        int digit = 0;

        switch (this.validatorNumber) {
            case 5 -> digit = code.getFirstDigit();
            case 6 -> digit = code.getSecondDigit();
            case 7 -> digit = code.getThirdDigit();
        }
        return digit % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        String displayValidator = "";
        switch (this.validatorNumber) {
            case 5 -> displayValidator = "Checks the parity of the first digit";
            case 6 -> displayValidator = "Check the parity of the second digit";
            case 7 -> displayValidator = "Check the parity of the third digit";
        }
        return displayValidator;
    }
}
