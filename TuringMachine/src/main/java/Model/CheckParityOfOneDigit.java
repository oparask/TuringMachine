package Model;

public class CheckParityOfOneDigit implements Validator {
    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int digitIndex;


    public CheckParityOfOneDigit(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 5 -> this.digitIndex = 1;
            case 6 -> this.digitIndex = 2;
            case 7 -> this.digitIndex = 3;
        }
    }

    private int category(int code) {
        int digit;
        if (digitIndex == 0) { //first number
            digit = code / 100;
        } else if (digitIndex == 1) { //second number
            digit = (code / 10) % 10;
        } else { //third number
            digit = code % 10;
        }

        return digit % 2 == 0 ? 0 : 1; //returns 0 for even and 1 for odd

    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
