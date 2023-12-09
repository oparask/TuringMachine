package model.validators;

import model.Code;

public class MostFrequentParity extends Validator {

    public MostFrequentParity(Code secretCode, Code userCode, int validatorNumber) {
        super(validatorNumber, secretCode, userCode);
    }

    @Override
    public boolean test() {
        return category(super.getUserCode()) == category(super.getSecretCode());
    }

    private int category(Code code) {
        int even = 0;
        int odd = 0;

        for(Integer digit : code){
            if(digit % 2 ==0){
                even++;
            } else {
                odd++;
            }
        }

        return Math.max(even, odd);
    }

    @Override
    public String toString() {
        return "Determines if there are more even numbers or odd in code";
    }
}
