package model;

import model.problems.Problem;

import java.util.ArrayList;
import java.util.List;

public class Game {

    Problem problem;
    List<Round> rounds;

    public Game(Problem problem) {
        this.problem = problem;
        this.rounds = new ArrayList<>();
        Round firstRound = new Round(problem, )
    }


    //creates a new round
    public void addRound(Code code) {
        Round round = new Round(code);
        rounds.add(round);
    }
}
