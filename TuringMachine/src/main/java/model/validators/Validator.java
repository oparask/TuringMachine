package model.validators;

import model.Code;
/**
 * The Validator class is an abstract class representing a base validator for the game.
 * Validators are used to test characteristics of the user's code against the secret code.
 */
public abstract class Validator {

    private final int validatorNumber;
    private final Code secretCode;
    private Code userCode;
    private boolean tested = false;
    private boolean sameCharacteristic = false;

    /**
     * Constructs a new Validator with the specified validator number, secret code, and user code.
     *
     * @param validatorNumber The number associated with the validator.
     * @param secretCode      The secret code used for validation.
     * @param userCode        The user code to be validated.
     */
    public Validator(int validatorNumber, Code secretCode, Code userCode) {
        this.secretCode = secretCode;
        this.userCode = userCode;
        this.validatorNumber = validatorNumber;
    }

    /**
     * Gets the validator number associated with this validator.
     *
     * @return The validator number.
     */
    public int getValidatorNumber() {
        return validatorNumber;
    }

    /**
     * Gets the secret code used for validation.
     *
     * @return The secret code.
     */
    public Code getSecretCode() {
        return secretCode;
    }

    /**
     * Gets the user code to be validated.
     *
     * @return The user code.
     */
    public Code getUserCode() {
        return userCode;
    }

    /**
     * Checks if the validator has the same characteristic as the secret code.
     *
     * @return {@code true} if the validator has the same characteristic, {@code false} otherwise.
     */
    public boolean hasSameCharacteristic() {
        return sameCharacteristic;
    }

    /**
     * Checks if the validator has been tested.
     *
     * @return {@code true} if the validator has been tested, {@code false} otherwise.
     */
    public boolean isTested() {
        return tested;
    }

    /**
     * Abstract method that subclasses must implement to perform the validation test.
     *
     * @return {@code true} if the test passes, {@code false} otherwise.
     */
    public abstract boolean test();

    /**
     * Marks the validator as tested or not tested.
     *
     * @param isTested {@code true} to mark the validator as tested, {@code false} otherwise.
     */
    public void markAsTested(boolean isTested) {
        this.tested = isTested;
    }

    /**
     * Sets the result of the validation test.
     *
     * @param testResult {@code true} if the test result indicates the same characteristic, {@code false} otherwise.
     */
    public void setTestResult(boolean testResult) {
        this.sameCharacteristic = testResult;
    }

    /**
     * Sets the user code for the validator.
     *
     * @param code The user code to be set.
     */
    public void setUserCode(Code code) {
        this.userCode = code;
    }

    /**
     * Gets the file path of the image associated with the validator.
     *
     * @return The file path of the image.
     */
    public String getImagePath() {
        return "/card" + validatorNumber + ".png";
    }
}
