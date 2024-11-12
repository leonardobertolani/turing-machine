package tapes;

import java.util.ArrayList;

public class BiInfiniteTape extends AbstractBiInfiniteTape {

    public BiInfiniteTape() {
        tapeForward = new ArrayList<Character>();
        tapeBackward = new ArrayList<Character>();
        pointer = 0;
    }
}
