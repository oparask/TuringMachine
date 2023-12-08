package model;

import model.problems.Problem;
import model.problems.ProblemReader;
import model.validators.*;

import java.util.ArrayList;
import java.util.List;


public class GameFacade {
    private final List<Problem> problems;
    private Game currentGame;

    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        currentGame = null;
    }

    public List<Problem> getProblems() {

        return problems;
    }


    public List<Validator> getProblemValidators(){
        List<Validator> problemValidators = new ArrayList<>();
        for(int validatorNb : currentGame.getProblemValidators()){
            problemValidators.add(validatorConversion(currentGame.getSecretCode(), currentGame.getUserCode(), validatorNb));
        }
        return problemValidators;
    }

    public int getScore() {
        return currentGame.getScore();
    }

    public List<Integer> getCurRoundTestedValidators() {
        return currentGame.getCurRoundTestedValidators();
    }

    public List<Round> getRounds() {
        return currentGame.getRounds();
    }


    //démarrer une partie en choisissant un problème parmi les problèmes connus. Le jeu
    //doit aussi proposer au joueur de choisir un problème au hasard. ;
    public void startNewGame(int problemNb) {
        if (problemNb < 1 || problemNb > problems.size()) {
            throw new IllegalArgumentException("The problem number must be between 1 and " + problems.size());
        }

        //Initialize the first game
        currentGame = new Game(problems.get(problemNb - 1));

    }

    //entrer un code
    public void enterCode(int code) {
        if(!validateCode(code)){
            throw new IllegalArgumentException("Le code doit être constitué de trois chiffre.\n" +
                    "Et chaque chiffre du code doit être compris entre 1 et 5 inclus.");
        }

        Code userCode = new Code(code);
        currentGame.enterCode(userCode);
    }

    // Méthode pour valider l'entier
    private boolean validateCode(int code) {
        // Convertit l'entier en une chaîne de caractères pour faciliter la manipulation des chiffres
        String codeStr = String.valueOf(code);

        // Vérifie si la chaîne a une longueur de 3
        if (codeStr.length() != 3) {
            return false;
        }

        // Vérifie si chaque chiffre est compris entre 1 et 5 inclus
        for (char digitChar : codeStr.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            if (digit < 1 || digit > 5) {
                return false;
            }
        }
        return true;
    }

    public boolean testValidator(int validatorNb) {
        if (getCurRoundTestedValidators().contains(validatorNb)) {
            throw new IllegalArgumentException("You've already tested this validator");
        }

        Validator validator = validatorConversion(currentGame.getSecretCode(), currentGame.getUserCode(), validatorNb);

        return currentGame.testValidator(validator);
    }

    public void nextRound() {
        currentGame.nextRound();
    }


    //Deviner un code (et donc vérifier s’il est correct)
    public boolean guessCode() {
        return currentGame.guessCode();
    }


    // Méthode pour vérifier si un tableau d'entiers contient une valeur spécifique
    public static boolean contains(int[] array, int targetValue) {
        for (int value : array) {
            if (value == targetValue) {
                return true;
            }
        }
        return false;
    }

    private Validator validatorConversion(Code secretCode, Code userCode, int validatorNb) {
        ValidatorFactory validatorFactory = new DefaultValidatorFactory();
        return validatorFactory.createValidator(secretCode, userCode, validatorNb);
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

