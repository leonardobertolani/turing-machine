package tapes;


public interface Tape {

    void load(String string);
    Character read();
    void write(Character symbol);
    void seek(Character seekMove);
    void rewind();
}
