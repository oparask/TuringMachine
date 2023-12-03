package model;

import model.problems.Problem;
import model.problems.ProblemReader;
import model.validators.*;

import java.util.List;
import java.util.Random;


public class GameFacade {
    private List<Problem> problems;
    private Problem problem;
    private Game currentGame;
    private Code userCode;

    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        problem = null;
        currentGame = null;
        userCode = null;
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
        this.userCode = userCode;
        currentGame.addRound(userCode);
    }

    public void chooseValidator(int validatorNb) {
        Validator validator;
        switch (validatorNb){
            case 1, 2, 3, 4 -> validator = new CompareOneDigitToAValue(problem.getSecretCode(), userCode, validatorNb);
            case 5,6,7 -> validator = new CheckParityOfOneDigit(problem.getSecretCode(), userCode, validatorNb);
            case 8, 9, 10 -> validator = new CountDigitValue(problem.getSecretCode(), userCode, validatorNb);
            case 11, 12, 13 -> validator = new CompareTwoDigits(problem.getSecretCode(), userCode, validatorNb);
            case 14, 15 ->  validator = new ExtremeDigit(problem.getSecretCode(), userCode, validatorNb);
            case 16 -> validator = new MostFrequentParity(problem.getSecretCode(), userCode);
            case 17 -> validator = new CountEvenDigit(problem.getSecretCode(), userCode);
            case 18 -> validator = new SumParity(problem.getSecretCode(), userCode);
            case 19 -> validator = new CompSumTwoDigitsToAValue(problem.getSecretCode(), userCode);
            case 20 -> validator = new RepetitionNumber(problem.getSecretCode(), userCode);
            case 21 -> validator = new TwinDigit(problem.getSecretCode(), userCode);
            case 22 -> validator = new DigitsOrder(problem.getSecretCode(), userCode);
        }
        currentGame.chooseValidator(validator);
    }
/*
    public void nextRound() {
        currentGame.nextRound();
    }


    public void nextRound() {
        currentGame.nextRound();
    }

    public boolean guessCode(Code code) {
        return currentGame.guessCode(code);
    }

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
