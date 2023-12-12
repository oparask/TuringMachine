package model.command;

import model.Code;
import model.Game;
import model.Round;
import model.validators.Validator;

public class NextRoundCommand implements Command {

    private final Game game;
    private Validator[]previousProblemValidators;


    public NextRoundCommand(Game game) {
        this.game = game;
        previousProblemValidators = game.getProblemValidators();
    }


    @Override
    public void execute() {
        game.nextRound();
    }

    @Override
    public void unexecute() {
        game.getRounds().remove(game.getRounds().size()-1); //remove the added round
        game.setCurrentRound( game.getRounds().get(game.getRounds().size()-1)); //change the current round
        game.setProblemValidators(previousProblemValidators);
    }
}
