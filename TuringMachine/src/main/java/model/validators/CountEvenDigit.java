package model.validators;

import model.Code;


public class CountEvenDigit  extends Validator{

    public CountEvenDigit(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
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
    public String toString() {
        return "Count how many digits in the code are even";
    }
}
