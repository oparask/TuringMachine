package model.validators;

import java.util.ArrayList;

public class CountEvenDigit  implements Validator{
    private int secretCode;
    private int userCode;

    public CountEvenDigit(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(int code) {
        var codeDigits = new ArrayList<Integer>();
        codeDigits.add(code / 100); // firstDigit
        codeDigits.add((code / 10) % 10); // secondDigit
        codeDigits.add(code % 10); //thirdDigit
        int even = 0;

        for(int codeDigit : codeDigits){
            if(codeDigit % 2 ==0){
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
