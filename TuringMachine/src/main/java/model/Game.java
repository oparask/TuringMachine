package model;

import model.problems.Problem;
import model.validators.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Problem problem;
    List<Round> rounds;
    Round currentRound;


    public Game(Problem problem) {
        this.problem = problem;
        this.rounds = new ArrayList<>();
        this.currentRound = new Round();
    }

    public int[] getProblemValidators(){
        return problem.getValidators();
    }
    public List<Integer> getTestedValidators() {
        List<Integer> testedValidators = new ArrayList<>();

        for (Round round : rounds) {
            testedValidators.addAll(round.getTestedValidators());
        }

        return testedValidators;
    }
    public List<Integer> getCurRoundTestedValidators() {
        return currentRound.getTestedValidators();
    }

    public Code getUserCode(){
        return currentRound.getUserCode();
    }

    public Code getSecretCode(){
        return problem.getSecretCode();
    }

    public Problem getProblem() {
        return problem;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }


    public void enterCode(Code code){
        currentRound.addUserCode(code);
    }

    //creates a new round
    public void nextRound() {
        Round round = new Round();
        currentRound = round;
        rounds.add(round);
    }

    public boolean testValidator(Validator validator) {
        return currentRound.testValidator(validator);
    }

    public boolean guessCode() {
        return currentRound.getUserCode().equals(problem.getSecretCode());
    }
}
