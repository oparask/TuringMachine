package DP;

import java.util.Stack;
/**
 * The CommandManager class manages the execution, undo, and redo of commands in the command pattern.
 * It keeps track of executed commands, allowing for undo and redo operations.
 */
public class CommandManager {
    /** Stack to store executed commands for undo operations. */
    private final Stack<Command> undo;

    /** Stack to store undone commands for redo operations. */
    private final Stack<Command> redo;

    /**
     * Constructs a CommandManager with empty undo and redo stacks.
     */
    public CommandManager() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    /**
     * Executes a command, adds it to the undo stack, and clears the redo stack.
     *
     * @param command The command to be executed.
     */
    public void execute(Command command) {
        command.execute();
        undo.push(command);
        redo.clear();
    }

    /**
     * Undoes the last executed command, adds it to the redo stack.
     */
    public void undoM() {
        if (!undo.isEmpty()) {
            Command command = undo.pop();
            command.unexecute();
            redo.push(command);
        }
    }

    /**
     * Redoes the last undone command, adds it to the undo stack.
     */
    public void redoM() {
        if (!redo.isEmpty()) {
            Command command = redo.pop();
            command.execute();
            undo.push(command);
        }
    }
}

