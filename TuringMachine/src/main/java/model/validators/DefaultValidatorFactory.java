package model.validators;

import model.Code;
/**
 * The DefaultValidatorFactory class implements the ValidatorFactory interface and provides
 * a default implementation for creating validators based on the validator number.
 */
public class DefaultValidatorFactory implements ValidatorFactory {

    /**
     * Creates a validator based on the provided secret code, user code, and validator number.
     *
     * @param secretCode  The secret code used for validation.
     * @param userCode    The user code to be validated.
     * @param validatorNb The number associated with the validator to be created.
     * @return A validator instance based on the provided parameters.
     */
    @Override
    public Validator createValidator(Code secretCode, Code userCode, int validatorNb) {
        Validator validator = null;
        switch (validatorNb) {
            case 1, 2, 3, 4 -> validator = new CompareOneDigitToAValue(secretCode, userCode, validatorNb);
            case 5, 6, 7 -> validator = new CheckParityOfOneDigit(secretCode, userCode, validatorNb);
            case 8, 9, 10 -> validator = new CountDigitValue(secretCode, userCode, validatorNb);
            case 11, 12, 13 -> validator = new CompareTwoDigits(secretCode, userCode, validatorNb);
            case 14, 15 -> validator = new ExtremeDigit(secretCode, userCode, validatorNb);
            case 16 -> validator = new MostFrequentParity(secretCode, userCode, validatorNb);
            case 17 -> validator = new CountEvenDigit(secretCode, userCode, validatorNb);
            case 18 -> validator = new SumParity(secretCode, userCode, validatorNb);
            case 19 -> validator = new CompSumTwoDigitsToAValue(secretCode, userCode, validatorNb);
            case 20 -> validator = new RepetitionNumber(secretCode, userCode, validatorNb);
            case 21 -> validator = new TwinDigit(secretCode, userCode, validatorNb);
            case 22 -> validator = new DigitsOrder(secretCode, userCode, validatorNb);
        }

        return validator;
    }
}
