package model.validators;

public class SumParity  implements Validator {
    private int secretCode;
    private int userCode;

    public SumParity(int secretCode, int userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
    }

    private int category(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;

        int sum  = firstDigit + secondDigit + thirdDigit;

        return sum % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd
    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
