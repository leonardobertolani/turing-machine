package tapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractUniInfiniteTapeTest {

    @Test
    void load() {
        Tape tape = new UniInfiniteTape();
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
        Tape tape = new UniInfiniteTape();
        tape.load("ciaociao");
        StringBuilder appendingString = new StringBuilder();

        for(int i = 1; i <= 10; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "ciaociao__");

    }

    @Test
    void write() {
        Tape tape = new UniInfiniteTape();
        tape.load("ciaociao");
        StringBuilder appendingString = new StringBuilder();

        for(int i = 1; i <= 4; ++i) {
            tape.seek('R');
        }

        for(int i = 1; i <= 6; ++i) {
            tape.write('o');
            tape.seek('R');
        }

        tape.rewind();

        for(int i = 1; i <= 10; ++i) {
            appendingString.append(tape.read());
            tape.seek('R');
        }

        assertEquals(appendingString.toString(), "ciaooooooo");

    }

    @Test
    void seek() {
        Tape tape = new UniInfiniteTape();

        tape.seek('R');
        tape.seek('R');
        tape.seek('L');
        tape.seek('L');
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> tape.seek('L'));

        assertEquals("Tape pointer out of bounds.", exception.getMessage());
    }
}