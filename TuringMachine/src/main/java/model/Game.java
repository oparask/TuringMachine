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
    }

    public List<Validator> getValidators (){
        return currentRound.getValidators();
    }
    public List<Validator> getTestedValidators() {
        List<Validator> testedValidators = new ArrayList<>();

        for (Round round : rounds) {
            testedValidators.addAll(round.getTestedValidators());
        }

        return testedValidators;
    }
    public List<Validator> getCurRoundTestedValidators() {
        return currentRound.getTestedValidators();
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

    //creates a new round
    public void addRound(Code userCode) {
        Round round = new Round(userCode, problem);
        currentRound = round;
        rounds.add(round);
    }


    public boolean testValidator(int validatorNb) {
        int[] problemValidators = problem.getValidators();

        // Vérifier si le validateur se trouve dans le tableau de validateurs
        for (int problemValidator : problemValidators) {
            if (problemValidator == validatorNb) {

                return currentRound.testValidator(validatorNb); // Sortir de la méthode une fois que le validateur est trouvé
            }
        }
        throw new IllegalArgumentException("Chose a validator from the problem validators");
    }

    public boolean guessCode(Code userCode) {
        return userCode == problem.getSecretCode();
    }

}
