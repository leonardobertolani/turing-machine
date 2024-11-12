package states;

import java.util.List;

public record TransitionOutput(boolean isFinalState, Character outputChar_inputTape, Character outputChar_outputTape, List<Character> memoryOutput, List<Character> memoryMoves, boolean isMachineBlocked) {
}
