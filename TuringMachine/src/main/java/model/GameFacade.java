package model;

import model.problems.Problem;
import model.problems.ProblemReader;
import model.validators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameFacade {
    private List<Problem> problems;
    private Problem problem;
    private Game currentGame;

    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        problem = null;
        currentGame = null;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public Problem getProblem() {
        return problem;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public List<Validator> getValidators (){
        return currentGame.getValidators();
    }
    public List<Validator> getTestedValidators() {
        return currentGame.getTestedValidators();
    }

    public List<Validator> getCurRoundTestedValidators() {
        return currentGame.getCurRoundTestedValidators();
    }

    public List<Round> getRounds(){
        return currentGame.getRounds();
    }

    public void startNewGame(int problemNb) {
        if (problemNb < 1 || problemNb > problems.size()) {
            throw new IllegalArgumentException("The problem number must be between 1 and " + problems.size());
        }
        //Initialize the problem
        problem = problems.get(problemNb - 1);
        //Initialize the first game
        currentGame = new Game(problem);

    }

    //creates a new round
    public void enterCode(int code) {
        Code userCode = new Code(code);
        currentGame.addRound(userCode);
    }

    public boolean chooseValidator(int validatorNb) {
        if(validatorNb<1 || validatorNb > 22){
            throw new IllegalArgumentException("Invalid validator number");
        }
        return currentGame.testValidator(validatorNb);
    }

    public void nextRound(int code) {
        enterCode(code);
    }


    public boolean guessCode(int code) {
        Code userCode = new Code(code);

        return currentGame.guessCode(userCode);
    }

/*

    public void undoMove() {
        currentGame.undoMove();
    }

    public void redoMove() {
        currentGame.redoMove();
    }

    public void abandonGame() {
        currentGame.abandonGame();
        currentGame = null; // Réinitialiser le jeu courant après l'abandon
    }*/
}
