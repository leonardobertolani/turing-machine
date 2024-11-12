package states;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StateManagerTest {

    @Test
    void loadConfiguration() {

        StateManager stateManager = new StateManager();
        stateManager.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\main\\java\\configurations\\conf1.json");

        assertEquals("q0", stateManager.states.get(0).getName());
        assertTrue(stateManager.states.get(0).isInitial());
        assertTrue(stateManager.states.get(1).isFinal());
        assertEquals("q2", stateManager.states.get(2).getName());

        List<Character> initialStack = new ArrayList<>();
        initialStack.add('$');
        initialStack.add('$');
        assertArrayEquals(initialStack.toArray(), stateManager.states.get(0).transitions.get(1).getInputMemory().toArray());
    }

    @Test
    void oneStep() {
        StateManager stateManager = new StateManager();
        stateManager.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\main\\java\\configurations\\conf1.json");

        TransitionInput transitionInput = new TransitionInput('a', List.of('$', '$'));
        TransitionOutput transitionOutput = stateManager.oneStep(transitionInput);

        assertTrue(transitionOutput.isFinalState());
        assertEquals('a', transitionOutput.outputChar_inputTape());
        assertEquals(List.of('$', '$'), transitionOutput.memoryOutput());
        assertEquals(List.of('R', 'S', 'S'), transitionOutput.memoryMoves());
        assertFalse(transitionOutput.isMachineBlocked());

        assertEquals("q1", stateManager.currentState);

        transitionInput = new TransitionInput('b', List.of('$', '$'));
        transitionOutput = stateManager.oneStep(transitionInput);

        assertFalse(transitionOutput.isFinalState());
        assertEquals('b', transitionOutput.outputChar_inputTape());
        assertEquals(List.of('$', '$'), transitionOutput.memoryOutput());
        assertEquals(List.of('R', 'S', 'S'), transitionOutput.memoryMoves());
        assertFalse(transitionOutput.isMachineBlocked());

        assertEquals("q0", stateManager.currentState);

        transitionInput = new TransitionInput('c', List.of('$', '$'));
        transitionOutput = stateManager.oneStep(transitionInput);

        assertTrue(transitionOutput.isMachineBlocked());
    }
}