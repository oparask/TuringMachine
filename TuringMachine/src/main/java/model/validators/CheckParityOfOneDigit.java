package model.validators;

import model.Code;

public class CheckParityOfOneDigit extends Validator {

    public CheckParityOfOneDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int digit = 0;

        switch (super.getValidatorNumber()) {
            case 5 -> digit = code.getFirstDigit();
            case 6 -> digit = code.getSecondDigit();
            case 7 -> digit = code.getThirdDigit();
        }
        return digit % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd
    }

    @Override
    public String toString() {
        String displayValidator = "";
        switch (super.getValidatorNumber()) {
            case 5 -> displayValidator = "Checks the parity of the first digit";
            case 6 -> displayValidator = "Check the parity of the second digit";
            case 7 -> displayValidator = "Check the parity of the third digit";
        }
        return displayValidator;
    }
}
