package model.command;

import model.Code;
import model.Game;
import model.validators.Validator;

public class EnterUserCodeCommand implements Command {

    private final Game game;

    private final Code userCode;
    private Code previousUserCode;



    public EnterUserCodeCommand(Game game, Code userCode) {
        this.game = game;
        this.userCode = userCode;

        previousUserCode = game.getUserCode();


    }


    @Override
    public void execute() {
        game.enterCode(userCode);
    }

    @Override
    public void unexecute() {
        game.enterCode(previousUserCode);
    }
}
