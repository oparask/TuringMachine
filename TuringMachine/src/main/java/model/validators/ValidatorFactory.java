package model.validators;

import model.Code;

public interface ValidatorFactory {
    Validator createValidator(Code secretCode, Code userCode, int validatorNb);
}
