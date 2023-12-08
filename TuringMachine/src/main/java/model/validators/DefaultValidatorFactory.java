package model.validators;

import model.Code;

public class DefaultValidatorFactory implements ValidatorFactory {

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
