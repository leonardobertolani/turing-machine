package tapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractBiInfiniteTapeTest {

    @Test
    void load() {
        Tape tape = new BiInfiniteTape();
        StringBuilder appendingString = new StringBuilder();

        tape.load("ciao");
        tape.load(" a tutti");

        for(int i = 1; i <= 12; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "ciao a tutti");
    }

    @Test
    void read() {
        Tape tape = new BiInfiniteTape();
        tape.load("ciaociao");
        StringBuilder appendingString = new StringBuilder();

        for(int i = 1; i <= 10; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "ciaociao__");

        appendingString = new StringBuilder();
        tape.rewind();
        tape.seek('L');
        tape.seek('L');

        for(int i = 1; i <= 10; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "__ciaociao");
    }

    @Test
    void write() {
        Tape tape = new BiInfiniteTape();
        tape.load("ciaociao");
        StringBuilder appendingString = new StringBuilder();

        for(int i = 1; i <= 4; ++i) {
            tape.seek('L');
        }

        for(int i = 1; i <= 4; ++i) {
            tape.write('a');
            tape.seek('R');
        }

        for(int i = 1; i <= 4; ++i) {
            tape.seek('L');
        }

        for(int i = 1; i <= 12; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "aaaaciaociao");

    }

}