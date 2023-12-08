package model;

import model.validators.*;

import java.util.ArrayList;
import java.util.List;

public class Round {
    Code userCode;
    List<Integer> testedValidators;
    List<Boolean> resultTests;


    public Round() {
        this.userCode = null;
        this.testedValidators = new ArrayList<>();
        this.resultTests = new ArrayList<>();
    }

    public List<Integer> getTestedValidators() {
        return testedValidators;
    }

    public Code getUserCode() {
        return userCode;
    }

    public List<Boolean> getResultTests() {
        return resultTests;
    }

    public boolean testValidator(Validator validator) {
        if (testedValidators.size() == 3) {
            throw new IllegalArgumentException("You already have tested 3 validators");
        }

        boolean testResult = validator.test();

        resultTests.add(testResult);
        testedValidators.add(validator.getValidatorNumber());


        return testResult;
    }


    public void addUserCode(Code code){
        this.userCode = code;
    }


}
