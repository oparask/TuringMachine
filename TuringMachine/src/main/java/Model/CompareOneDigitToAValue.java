package Model;

public class CompareOneDigitToAValue implements Validator {
    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int digitIndex;
    private int value;


    public CompareOneDigitToAValue(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 1 -> {
                this.value = 1;
                this.digitIndex = 0;
            }
            case 2 -> {
                this.value = 3;
                this.digitIndex = 0;
            }
            case 3 -> {
                this.value = 3;
                this.digitIndex = 1;
            }
            case 4 -> {
                this.value = 4;
                this.digitIndex = 1;
            }
        }
    }

    private int category(int code){
        int digit;
        if(digitIndex == 0){
            digit = userCode / 100;
        } else {
            digit  = (userCode / 10) % 10;
        }

        return Integer.compare(digit, value); //returns -1, 0 or 1

    }
    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }

}
