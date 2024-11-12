package machines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KTapeTuringMachineTest {

    @Test
    void compute() {
        TuringMachine kTape = new KTapeTuringMachine();
        kTape.loadConfiguration("D:\\Progetti\\Scienza\\automaton\\src\\main\\java\\configurations\\conf1.json");
        kTape.loadInputString("aab");

        kTape.compute();

        assertTrue(kTape.isBlocked());
        assertFalse(kTape.isAccepting());
    }

    @Test
    void oneStep() {
        TuringMachine kTape = new KTapeTuringMachine();
        kTape.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\main\\java\\configurations\\conf1.json");
        kTape.loadInputString("aab");

        kTape.oneStep();
        assertFalse(kTape.isBlocked());
        kTape.oneStep();
        assertTrue(kTape.isBlocked());
        assertFalse(kTape.isAccepting());

    }
}