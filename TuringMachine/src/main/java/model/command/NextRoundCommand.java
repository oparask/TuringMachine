package model.command;

import model.Game;
import model.validators.Validator;
/**
 * Represents a command to move to the next round in the Turing Machine Game.
 * This command is part of the Command design pattern and is used to encapsulate the logic
 * for advancing to the next round in the game.
 */
public class NextRoundCommand implements Command {

    /**
     * The game instance associated with the command.
     */
    private final Game game;

    /**
     * The array of problem validators from the previous round.
     */
    private Validator[] previousProblemValidators;

    /**
     * Constructs a new instance of the NextRoundCommand.
     *
     * @param game The game instance associated with the command.
     */
    public NextRoundCommand(Game game) {
        this.game = game;
        this.previousProblemValidators = game.getProblemValidators();
    }

    /**
     * Executes the command by moving to the next round in the game.
     */
    @Override
    public void execute() {
        game.nextRound();
    }

    /**
     * Undoes the command by reverting to the state of the previous round.
     */
    @Override
    public void unexecute() {
        game.getRounds().remove(game.getRounds().size() - 1); // Remove the added round
        game.setCurrentRound(game.getRounds().get(game.getRounds().size() - 1)); // Change the current round
        game.setProblemValidators(previousProblemValidators);
    }
}
