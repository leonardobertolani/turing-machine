package tapes;

import java.util.ArrayList;

public class UniInfiniteTape extends AbstractUniInfiniteTape {

    public UniInfiniteTape() {
        tapeForward = new ArrayList<>();
        pointer = 0;
    }
}
