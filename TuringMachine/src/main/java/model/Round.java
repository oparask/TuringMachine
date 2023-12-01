package model;

import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class Round {
    Problem problem;
    Code userCode;
    List<Integer> testedValidators;

    public Round(Problem problem, Code userCode) {
        this.problem = problem;
        this.userCode = userCode;
        this.testedValidators = new ArrayList<>();
    }


}
