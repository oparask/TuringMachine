package model.command;

import model.Code;
import model.Game;

public class GuessCodeCommand implements Command {
        private final Game game;
        private final Code userCode;



        public GuessCodeCommand(Game game, Code userCode) {
            this.game = game;
            this.userCode = userCode;
        }


        @Override
        public void execute() {
            game.enterCode(userCode);
        }

        @Override
        public void unexecute() {
            game.enterCode(null);
        }

}
