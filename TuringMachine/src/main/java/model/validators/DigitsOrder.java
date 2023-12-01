package model.validators;

public class DigitsOrder implements Validator {
    private int secretCode;
    private int userCode;

    public DigitsOrder(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;

        if (firstDigit < secondDigit && secondDigit < thirdDigit){
            return 1; //true -> twin
        } else if (firstDigit > secondDigit && secondDigit > thirdDigit){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
