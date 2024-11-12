package machines;

/*
_ --> blank character on tape
$ --> Initial symbol on stacks
£ --> null string
 */

public interface TuringMachine {

    void loadConfiguration(String configurationFile);
    void loadInputString(String inputString);
    void compute();
    void oneStep();

    boolean isAccepting();
    boolean isBlocked();

    //TODO public void getInternalState();

}
