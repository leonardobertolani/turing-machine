package states;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class StateManager {

    String currentState;
    List<State> states;
    int k;

    public StateManager() {
        this.currentState = "";
        this.states = new ArrayList<>();
        this.k = 0;
    }

    public int loadConfiguration(String configurationFilePath) {

        try {
            loadData(configurationFilePath);
            setInitialState();
            checkConsistency();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return this.k;

    }

    public TransitionOutput oneStep(TransitionInput transitionInput) {

        // Find the current state object
        State currentState = states.stream()
                .filter(s -> s.getName().equals(this.currentState))
                .findFirst()
                .orElse(null);

        // Find the matching transition
        assert currentState != null;
        Transition matchingTransition = currentState
                .getTransitions()
                .stream()
                .filter(t -> t.getInputChar_inputTape().equals(transitionInput.inputChar())
                        && compareMemory(t.getInputMemory(), transitionInput.memoryInput()))
                .findFirst()
                .orElse(null);

        if (matchingTransition == null) {

            // may eventually add the possibility for epsilon move, but we will se

            return new TransitionOutput(currentState.isFinal(), null, null, null, null, true);
        }

        // Verify that the transition lead me somewhere defined
        State nextState = states.stream()
                .filter(s -> s.getName().equals(matchingTransition.getOutputState()))
                .findFirst()
                .orElse(null);

        if (nextState == null) {
            // Handle missing state
            throw new NoSuchElementException("Transition output state not found");
        }

        // Build the TrantistionOutput and update the current state
        this.currentState = nextState.getName();
        return new TransitionOutput(nextState.isFinal(),
                matchingTransition.getOutputChar_inputTape(),
                matchingTransition.getOutputChar_outputTape(),
                matchingTransition.getOutputMemory(),
                matchingTransition.getMove(),
                false);
    }

    private boolean compareMemory(List<Character> transitionInputMemory, List<Character> actualInputMemory) {

        for (int i = 0; i < transitionInputMemory.size(); ++i) {
            if (!transitionInputMemory.get(i).equals(actualInputMemory.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void loadData(String configurationFilePath) throws IOException{

        // read the json and put it in a string
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFilePath)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        // scan the json string
        JSONObject jsonObject = new JSONObject(content.toString());
        this.k = jsonObject.getInt("k");
        JSONArray configurationArray = jsonObject.getJSONArray("configuration");

        // set each state
        for (int i = 0; i < configurationArray.length(); i++) {
            JSONObject stateObject = configurationArray.getJSONObject(i);

            State state = new State();
            state.setName(stateObject.getString("inputState"));
            state.setIsFinal(stateObject.getBoolean("isFinal"));
            state.setIsInitial(stateObject.getBoolean("isInitial"));

            // set each transition for each state
            JSONArray transitionsArray = stateObject.getJSONArray("transitions");
            List<Transition> transitions = new ArrayList<>();
            for (int j = 0; j < transitionsArray.length(); j++) {
                JSONObject transitionObject = transitionsArray.getJSONObject(j);
                Transition transition = new Transition();

                transition.setOutputState(transitionObject.getString("outputState"));
                transition.setInputChar_InputTape(transitionObject.getString("inputChar_inputTape").charAt(0));
                transition.setOutputChar_inputTape(transitionObject.getString("outputChar_inputTape").charAt(0));
                transition.setInputMemory(
                        transitionObject.getJSONArray("inputMemory").toList()
                                .stream()
                                .map((obj) -> ((String)obj).charAt(0))
                                .toList());
                transition.setOutputMemory(
                        transitionObject.getJSONArray("outputMemory").toList()
                                .stream()
                                .map((obj) -> ((String)obj).charAt(0))
                                .toList());
                transition.setOutputChar_outputTape(transitionObject.getString("outputChar_outputTape").charAt(0));
                transition.setMove(
                        transitionObject.getJSONArray("move").toList()
                                .stream()
                                .map((obj) -> ((String)obj).charAt(0))
                                .toList());



                transitions.add(transition);
            }

            state.setTransitions(transitions);
            states.add(state);
        }

    }

    private void setInitialState() {
        this.currentState = states.stream()
                                .filter(State::isInitial)
                                .findFirst()
                                .orElseThrow(() -> new IllegalStateException("No initial state found"))
                                .getName();
    }

    private void checkConsistency() {
        for(State state : states) {
            for(Transition transition : state.transitions) {
                if( this.k != transition.getInputMemory().size() ||
                    this.k != transition.getOutputMemory().size() ||
                    this.k != transition.getMove().size() - 2)
                        throw new IllegalArgumentException("Some transaction makes use of a wrong k");
            }
        }
    }
}
