package Model;

import java.util.ArrayList;

public class MostFrequentParity implements Validator {
    private int secretCode;
    private int userCode;


    public MostFrequentParity(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(int code) {
        var codeDigits = new ArrayList<Integer>();
        codeDigits.add(code / 100); // firstDigit
        codeDigits.add((code / 10) % 10); // secondDigit
        codeDigits.add(code % 10); //thirdDigit
        int even = 0;
        int odd = 0;

        for(int codeDigit : codeDigits){
            if(codeDigit % 2 ==0){
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
}
