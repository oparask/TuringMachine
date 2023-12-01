package model;

import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class Game {

    Problem problem;
    List<Round> manches;

    public Game(Problem problem) {
        this.problem = problem;
        this.manches = new ArrayList<>();
    }
}
