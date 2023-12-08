package model.validators;

import model.Code;

import java.util.ArrayList;

public class MostFrequentParity implements Validator {
    private Code secretCode;
    private Code userCode;
    private int validatorNumber;


    public MostFrequentParity(Code secretCode, Code userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
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
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

    @Override
    public String toString() {
        return "Determines if there are more even numbers or odd in code";
    }
}
