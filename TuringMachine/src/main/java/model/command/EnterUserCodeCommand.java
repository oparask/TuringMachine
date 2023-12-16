package model.command;

import model.Code;
import model.Game;
/**
 * Represents a command to enter a user code in the Turing Machine Game.
 * This command is part of the Command design pattern and is used to encapsulate the logic
 * for entering a user code in the game. It stores the previous user code to enable undo functionality.
 */
public class EnterUserCodeCommand implements Command {

    /**
     * The game instance associated with the command.
     */
    private final Game game;

    /**
     * The user code to be entered.
     */
    private final Code userCode;

    /**
     * The previous user code stored before executing the command (for undo functionality).
     */
    private Code previousUserCode;

    /**
     * Constructs a new instance of the EnterUserCodeCommand.
     *
     * @param game     The game instance associated with the command.
     * @param userCode The user code to be entered.
     */
    public EnterUserCodeCommand(Game game, Code userCode) {
        this.game = game;
        this.userCode = userCode;
        this.previousUserCode = game.getUserCode();
    }

    /**
     * Executes the command by entering the user code in the game.
     */
    @Override
    public void execute() {
        game.enterCode(userCode);
    }

    /**
     * Undoes the command by entering the previous user code in the game.
     */
    @Override
    public void unexecute() {
        game.enterCode(previousUserCode);
    }
}
