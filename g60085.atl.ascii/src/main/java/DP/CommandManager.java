package DP;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> undo;
    private Stack<Command> redo;

    public CommandManager() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public void execute(Command command){
        command.execute();
        undo.push(command);
        redo.clear();
    }

    public void undoM(){
        if (!undo.isEmpty()) {
            Command command = undo.pop();
            command.unexecute();
            redo.push(command);
        }
    }
    public void redoM() {
        if (!redo.isEmpty()) {
            Command command = redo.pop();
            command.execute();
            undo.push(command);
        }
    }
}
