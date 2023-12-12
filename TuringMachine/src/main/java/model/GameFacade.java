package model;

import model.command.CommandManager;
import model.command.EnterUserCodeCommand;
import model.command.NextRoundCommand;
import model.command.TestValidatorCommand;
import model.problems.Problem;
import model.problems.ProblemReader;
import model.validators.Validator;

import java.util.List;


public class GameFacade {
    private final List<Problem> problems;
    private Game currentGame;

    /**
     * The CommandManager responsible for managing commands (undo and redo) in the AsciiPaint instance.
     */
    private CommandManager commandManager;


    public GameFacade() {
        this.problems = new ProblemReader().getProblems();
        currentGame = null;
        commandManager = new CommandManager();
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public Validator[] getProblemValidators() {
        return currentGame.getProblemValidators();
    }

    public List<Validator> getCurRoundTestedValidators() {
        return currentGame.getCurRoundTestedValidators();
    }

    public List<Round> getRounds() {
        return currentGame.getRounds();
    }
    public Code getUserCode(){
        return currentGame.getUserCode();
    }
    public int getScore() {
        return currentGame.getScore();
    }

    public boolean getLastValidatorTestResult(){
        return currentGame.getLastValidatorTested().hasSameCharacteristic();
    }

    public Game getCurrentGame() {
        return currentGame;
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
        if (!validateCode(code)) {
            throw new IllegalArgumentException("Le code doit être constitué de trois chiffre.\n" +
                    "Et chaque chiffre du code doit être compris entre 1 et 5 inclus.");
        }

        Code userCode = new Code(code);
        // Create the command and ask commandManager to execute it
        EnterUserCodeCommand command = new EnterUserCodeCommand(currentGame, userCode);
        commandManager.execute(command);
    }

    // Méthode pour valider l'entier


    public void testValidator(int validatorIndex) {
        if(validatorIndex < 0 || validatorIndex > currentGame.getProblemValidators().length - 1){
            throw new IllegalArgumentException("You must choose an valid validator index.");
        }

        // Create the command and ask commandManager to execute it
        TestValidatorCommand command = new TestValidatorCommand(currentGame, validatorIndex);
        commandManager.execute(command);
    }

    public void nextRound() {
        // Create the command and ask commandManager to execute it
        NextRoundCommand command = new NextRoundCommand(currentGame);
        commandManager.execute(command);

    }

    //Deviner un code (et donc vérifier s’il est correct)
    public boolean guessCode() {
        boolean result = currentGame.guessCode();
        abandonGame();
        return result;

    }


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


    /**
     * Undoes the last executed command.
     * <p>
     * This method invokes the undo operation on the command manager,
     * allowing the reversal of the last executed command in the command history.
     * If there are no commands to undo, this method has no effect.
     */
    public void undo() {
        commandManager.undoM();
    }

    /**
     * Redoes the previously undone command.
     * <p>
     * This method invokes the redo operation on the command manager,
     * allowing the execution of the last undone command in the command history.
     * If there are no commands to redo, this method has no effect.
     */
    public void redo() {
        commandManager.redoM();
    }



    public void abandonGame() {
        currentGame = null; // Réinitialiser le jeu courant après l'abandon
    }
}

