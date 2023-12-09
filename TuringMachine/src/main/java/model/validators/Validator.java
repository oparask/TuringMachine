package model.validators;

import model.Code;

public abstract class Validator {
    private final int validatorNumber;
    private final Code secretCode;
    private Code userCode;
    private boolean tested = false;
    private boolean testResult = false;

    public Validator(int validatorNumber, Code secretCode, Code userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    public int getValidatorNumber() {
        return validatorNumber;
    }

    public Code getSecretCode() {
        return secretCode;
    }

    public Code getUserCode() {
        return userCode;
    }
    public boolean isTestResult() {
        return testResult;
    }

    public boolean isTested() {
        return tested;
    }

    public abstract boolean test();

    public void markAsTested(boolean isTested)
    {
        this.tested = isTested;
    }

    public void setTestResult(boolean testResult){
        this.testResult = testResult;
    }

    public void setUserCode(Code code){
        this.userCode = code;
    }
}
