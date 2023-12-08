package model;

import model.problems.Problem;
import model.validators.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
   private Problem problem;
    private List<Round> rounds;
    private Round currentRound;



    public Game(Problem problem) {
        this.problem = problem;
        this.rounds = new ArrayList<>();
        this.currentRound = new Round();
        this.rounds.add(currentRound);
    }

    public int[] getProblemValidators(){
        return problem.getValidators();
    }
    public int getScore() {

        int score = 0;
        for (Round round : rounds) {
            score += round.getTestedValidators().size();
        }

        return score;
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
