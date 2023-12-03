package model.validators;

import model.Code;


public class CountEvenDigit  implements Validator{
    private Code secretCode;
    private Code userCode;

    public CountEvenDigit(Code secretCode, Code userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
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
}
