package Model;

public class RepetitionNumber implements Validator {
    private int secretCode;
    private int userCode;

    public RepetitionNumber(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;
        int repetition = 0;

        if(firstDigit == secondDigit && firstDigit == thirdDigit){
            repetition = 3;
        } else if (firstDigit == secondDigit || firstDigit == thirdDigit || secondDigit == thirdDigit ){
            repetition = 2;
        }

        return repetition;
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
