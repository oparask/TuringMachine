package DP;

public interface Command {
    void execute();
    void unexecute();
    boolean isReversible();
}
