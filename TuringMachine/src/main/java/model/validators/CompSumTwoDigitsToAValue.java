package model.validators;

public class CompSumTwoDigitsToAValue implements Validator {
    private int secretCode;
    private int userCode;
    private int value;

    public CompSumTwoDigitsToAValue(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.value = 6;
    }

    private int category(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;

        int sum  = firstDigit + secondDigit;

        return Integer.compare(sum, value); //returns -1, 0 or 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
