package model.validators;

public class ExtremeDigit implements Validator {
    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int extremumIndex;


    public ExtremeDigit(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 14 -> this.extremumIndex = -1;
            case 15 -> this.extremumIndex = 1;
        }
    }

    private int category(int code) {
        int firstDigit = code / 100;
        int secondDigit = (code / 10) % 10;
        int thirdDigit =  code % 10;

        int extremum;

        if (extremumIndex == -1) { //strictly the smallest
            extremum = Math.min(firstDigit, secondDigit);
            extremum = Math.min(extremum, thirdDigit);

        } else { //strictly the greatest
            extremum = Math.max(firstDigit, secondDigit);
            extremum = Math.max(extremum, thirdDigit);
        }

        return extremum;

    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

}
