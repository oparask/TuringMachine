package model;

import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class Round {

    Code userCode;
    List<Integer> testedValidators;

    public Round(Code userCode) {
        this.userCode = userCode;
        this.testedValidators = new ArrayList<>();
    }


}
