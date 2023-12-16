package model.command;

import model.Game;
/**
 * Represents a command to test a validator in the Turing Machine Game.
 * This command is part of the Command design pattern and is used to encapsulate the logic
 * for testing a validator in the game.
 *
 * @author Your Name
 * @version 1.0
 */
public class TestValidatorCommand implements Command {

    /**
     * The game instance associated with the command.
     */
    private final Game game;

    /**
     * The index of the validator to be tested.
     */
    private final int validatorIndex;

    /**
     * Constructs a new instance of the TestValidatorCommand.
     *
     * @param game           The game instance associated with the command.
     * @param validatorIndex The index of the validator to be tested.
     */
    public TestValidatorCommand(Game game, int validatorIndex) {
        this.game = game;
        this.validatorIndex = validatorIndex;
    }

    /**
     * Executes the command by testing the specified validator in the game.
     */
    @Override
    public void execute() {
        game.testValidator(validatorIndex);
    }

    /**
     * Undoes the command by reverting the state of the last tested validator.
     */
    @Override
    public void unexecute() {
        game.getCurrentRound().getTestedValidators().remove(
                game.getCurrentRound().getTestedValidators().size() - 1
        );
        game.getProblemValidators()[validatorIndex].markAsTested(false);
    }
}
