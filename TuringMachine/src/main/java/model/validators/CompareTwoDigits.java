package model.validators;

public class CompareTwoDigits implements Validator {

    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int firstDigitIndex;
    private int secondDigitIndex;

    public CompareTwoDigits(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 11 -> {
                this.firstDigitIndex = 0;
                this.secondDigitIndex = 1;
            }
            case 12 -> {
                this.firstDigitIndex = 0;
                this.secondDigitIndex = 2;
            }
            case 13 -> {
                this.firstDigitIndex = 1;
                this.secondDigitIndex = 2;
            }

        }
    }

    private int category(int code){
        int firstDigit;
        if(firstDigitIndex == 0){ //first digit
            firstDigit = code / 100;
        } else { //second digit
            firstDigit  = (code / 10) % 10;
        }

        int secondDigit;
        if(secondDigitIndex == 1){ //second digit
            secondDigit = (code / 10) % 10;
        } else { //third digit
            secondDigit  = code % 10;
        }

        return Integer.compare(firstDigit, secondDigit); //returns -1, 0 or 1

    }
    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
