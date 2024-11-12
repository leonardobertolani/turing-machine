package machines;

import states.StateManager;
import states.TransitionInput;
import states.TransitionOutput;
import tapes.Tape;
import tapes.BiInfiniteTape;
import tapes.UniInfiniteTape;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KTapeTuringMachine implements TuringMachine {

    Tape inputTape;
    ArrayList<Tape> memoryTape;
    Tape outputTape;

    boolean isAccepting;
    boolean isBlocked;

    StateManager stateManager;

    public KTapeTuringMachine() {

        this.inputTape = new BiInfiniteTape();
        this.outputTape = new UniInfiniteTape();
        this.stateManager = new StateManager();

        this.isAccepting = false;
        this.isBlocked = false;
    }

    @Override
    public void loadConfiguration(String configurationFile) {

        int k = this.stateManager.loadConfiguration(configurationFile);
        if(k <= 0) throw new IllegalArgumentException("k must be greater than 0");

        memoryTape = new ArrayList<>();
        memoryTape.addAll(Stream.generate(UniInfiniteTape::new).limit(k).toList());
        memoryTape.forEach((tape) -> tape.write('$'));
    }

    @Override
    public void loadInputString(String inputString) {
        inputTape.load(inputString);
    }

    @Override
    public void compute() {
        while (!this.isBlocked) {
            oneStep();
        }
    }

    @Override
    public void oneStep() {

        TransitionInput transitionInput = new TransitionInput(
                this.inputTape.read(),
                this.memoryTape.stream()
                        .map(Tape::read)
                        .toList());

        try {
            TransitionOutput transitionOutput = this.stateManager.oneStep(transitionInput);

            this.isAccepting = transitionOutput.isFinalState();
            this.isBlocked = transitionOutput.isMachineBlocked();

            if(!this.isBlocked) {
                // Writes on tapes
                inputTape.write(transitionOutput.outputChar_inputTape());
                IntStream.range(0, memoryTape.size())
                        .forEach(i -> memoryTape.get(i).write(transitionOutput.memoryOutput().get(i)));
                outputTape.write(transitionOutput.outputChar_outputTape());

                // Moves the tapes
                inputTape.seek(transitionOutput.memoryMoves().get(0));
                outputTape.seek(transitionOutput.memoryMoves().get(1));
                IntStream.range(0, memoryTape.size())
                        .forEach(i -> memoryTape.get(i).seek(transitionOutput.memoryMoves().get(i+2)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.isAccepting = false;
            this.isBlocked = true;
        }

    }

    public boolean isAccepting() {
        return this.isAccepting;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }
}
