package Model;

public class CompareOneDigitToAValue implements Validator {
    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int digit;
    private int value;


    public CompareOneDigitToAValue(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 1 -> {
                this.value = 1;
                this.digit = 1;
            }
            case 2 -> {
                this.value = 3;
                this.digit = 1;
            }
            case 3 -> {
                this.value = 3;
                this.digit = 2;
            }
            case 4 -> {
                this.value = 4;
                this.digit = 2;
            }
        }
    }

    private int category(int code){
        int firstDigit;
        if(digit == 1){
            firstDigit= userCode / 100;
        } else {
            firstDigit = (userCode / 10) % 10;
        }

        return Integer.compare(firstDigit, value); //returns -1, 0 or 1

    }
    @Override
    public boolean test() {
        int resultUserCategory = category(userCode);
        int resultSecretCategory = category(secretCode);

        return resultSecretCategory == resultUserCategory;
    }

}
