package model.command;

import model.Code;
import model.Game;

/**
 * Represents a command to guess the code in the Turing Machine Game.
 * This command is part of the Command design pattern and is used to encapsulate the logic
 * for guessing the code in the game.
 *
 * @author Your Name
 * @version 1.0
 */
public class GuessCodeCommand implements Command {

    /**
     * The game instance associated with the command.
     */
    private final Game game;

    /**
     * The user code for the guess.
     */
    private final Code userCode;

    /**
     * Constructs a new instance of the GuessCodeCommand.
     *
     * @param game     The game instance associated with the command.
     * @param userCode The user code for the guess.
     */
    public GuessCodeCommand(Game game, Code userCode) {
        this.game = game;
        this.userCode = userCode;
    }

    /**
     * Executes the command by entering the user's guess code in the game.
     */
    @Override
    public void execute() {
        game.enterCode(userCode);
    }

    /**
     * Undoes the command by clearing the entered user code in the game.
     */
    @Override
    public void unexecute() {
        game.enterCode(null);
    }
}
