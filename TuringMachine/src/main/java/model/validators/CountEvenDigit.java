package model.validators;

import model.Code;


public class CountEvenDigit  implements Validator{
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;

    public CountEvenDigit(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
    }
    private int category(Code code) {
        int even = 0;

        for(Integer digit : code){
            if(digit % 2 == 0){
                even++;
            }
        }

        return even;
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Count how many digits in the code are even";
    }
}
