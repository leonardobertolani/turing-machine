package machines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KTapeTuringMachineTest {

    @Test
    void compute() {
        TuringMachine kTape = new KTapeTuringMachine();
        kTape.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\test\\java\\configurations\\conf1.json");
        kTape.loadInputString("abb");

        kTape.compute();

        assertTrue(kTape.isBlocked());
        assertTrue(kTape.isAccepting());
    }

    @Test
    void oneStep() {
        TuringMachine kTape = new KTapeTuringMachine();
        kTape.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\test\\java\\configurations\\conf1.json");
        kTape.loadInputString("aab");

        kTape.oneStep();
        assertFalse(kTape.isBlocked());
        kTape.oneStep();
        assertTrue(kTape.isBlocked());
        assertTrue(kTape.isAccepting());

    }
}