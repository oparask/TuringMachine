package model.validators;

import model.Code;
/**
 * The ValidatorFactory interface defines a contract for creating Validator instances.
 * Implementations of this interface are responsible for creating specific types of Validators
 * based on the provided parameters.
 */
public interface ValidatorFactory {

    /**
     * Creates a new Validator instance based on the provided secret code, user code, and validator number.
     *
     * @param secretCode   The secret code used for validation.
     * @param userCode     The user code to be validated.
     * @param validatorNb  The number associated with the validator.
     * @return A new Validator instance.
     */
    Validator createValidator(Code secretCode, Code userCode, int validatorNb);
}
