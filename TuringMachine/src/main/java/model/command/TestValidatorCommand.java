package model.command;

import model.Game;

public class TestValidatorCommand implements Command {
    private final Game game;
    int validatorIndex;



    public TestValidatorCommand(Game game, int validatorIndex) {
        this.game = game;
        this.validatorIndex = validatorIndex;
    }


    @Override
    public void execute() {
        game.testValidator(validatorIndex);
    }

    @Override
    public void unexecute() {
        game.getCurrentRound().getTestedValidators().remove(game.getCurrentRound().getTestedValidators().size() -1);
        game.getProblemValidators()[validatorIndex].markAsTested(false);
    }

}

