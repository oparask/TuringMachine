package Model;

public class CountDigitValue implements Validator {
    private int secretCode;
    private int userCode;
    private int validatorNumber;
    private int value;


    public CountDigitValue(int secretCode, int userCode, int validatorNumber) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;

        switch (this.validatorNumber) {
            case 8 -> this.value = 1;
            case 9 -> this.value = 3;
            case 10 -> this.value = 4;
        }

    }

    private int category(int code) {
       int repetitionNb = 0;
       String codeString = Integer.toString(code);
       for(int i = 0; i<codeString.length(); i++){
           if(codeString.charAt(i) == value){
               repetitionNb++;
           }
       }

        return repetitionNb; //returns 0 for even ans 1 for odd

    }

    @Override
    public boolean test() {
        return category(userCode) == category(secretCode);
    }
}
