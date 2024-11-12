package tapes;

import java.util.ArrayList;

public class BiInfiniteTape extends AbstractBiInfiniteTape {

    public BiInfiniteTape() {
        tapeForward = new ArrayList<>();
        tapeBackward = new ArrayList<>();
        pointer = 0;
    }
}
