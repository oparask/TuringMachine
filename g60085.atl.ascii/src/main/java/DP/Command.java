package DP;
/**
 * The Command interface defines the contract for command objects in the command pattern.
 * It declares two methods, execute and unexecute, to perform the main and reverse operations, respectively.
 */
public interface Command {
    /**
     * Executes the main operation associated with the command.
     */
    void execute();

    /**
     * Unexecutes or reverses the main operation associated with the command.
     */
    void unexecute();
}

