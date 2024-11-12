package tapes;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractUniInfiniteTape implements Tape {

    ArrayList<Character> tapeForward;
    int pointer;

    /**
     * Loads a string in the tape.
     * @param string the string to be loaded
     */
    @Override
    public void load(String string) {
        tapeForward.addAll(string.chars().mapToObj(c -> (char) c).toList());
    }

    /**
     * Reads the symbol in the current position of the tape.
     * @return Character object containing the symbol read
     */
    @Override
    public Character read() {
        if(pointer >= tapeForward.size()) {
            // The pointer is beyond the dimension of the string, read a blank
            return '_';
        }

        return tapeForward.get(pointer);
    }

    /**
     * Writes a symbol in the current cell of the tape.
     * @param symbol the symbol to be written
     */
    @Override
    public void write(Character symbol) {
        if (pointer >= tapeForward.size()) {
            int n_blanks = pointer - tapeForward.size() + 1;
            tapeForward.addAll(Stream.generate(() -> '_').limit(n_blanks).toList());
        }

        tapeForward.set(pointer, symbol);
    }

    /**
     * Moves the pointer of the tape to the right (R), to the left (L) or
     * keep it in the same position (S).
     * @param seekMove Character object describing the action to perform
     */
    @Override
    public void seek(Character seekMove) {
        if(seekMove.equals('R')) pointer += 1;
        if(seekMove.equals('L')) {
            if(pointer == 0)
                throw new IndexOutOfBoundsException("Tape pointer out of bounds.");
            else
                pointer  -= 1;
        }
    }

    @Override
    public void rewind() {
        pointer = 0;
    }
}
