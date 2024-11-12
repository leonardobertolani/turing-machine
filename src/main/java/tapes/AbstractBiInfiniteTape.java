package tapes;

import java.util.ArrayList;
import java.util.stream.Stream;

public class AbstractBiInfiniteTape implements Tape{


    ArrayList<Character> tapeForward;
    ArrayList<Character> tapeBackward;
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
        if(pointer >= tapeForward.size() || (-pointer -1) >= tapeBackward.size()) {
            // The pointer is beyond the dimension of the strings, read a blank
            return '_';
        }

        if(pointer >= 0)
            return tapeForward.get(pointer);

        return tapeBackward.get(-pointer - 1);
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

        if ((-pointer -1) >= tapeBackward.size()) {
            int n_blanks = (-pointer -1) - tapeBackward.size() + 1;
            tapeBackward.addAll(Stream.generate(() -> '_').limit(n_blanks).toList());
        }

        if(pointer >= 0)
            tapeForward.set(pointer, symbol);
        else
            tapeBackward.set((-pointer -1), symbol);
    }

    /**
     * Moves the pointer of the tape to the right (R), to the left (L) or
     * keep it in the same position (S).
     * @param seekMove Character object describing the action to perform
     */
    @Override
    public void seek(Character seekMove) {
        if(seekMove.equals('R')) pointer += 1;
        if(seekMove.equals('L')) pointer -= 1;
    }

    @Override
    public void rewind() {
        pointer = 0;
    }

}
