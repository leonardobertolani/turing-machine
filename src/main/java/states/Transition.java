package states;

import java.util.List;

public class Transition {
    String outputState;
    Character inputChar_inputTape;
    Character outputChar_inputTape;
    List<Character> inputMemory;
    List<Character> outputMemory;
    Character outputChar_outputTape;
    List<Character> move;

    public void setOutputState(String outputState) {
        this.outputState = outputState;
    }

    public void setInputChar_InputTape(Character inputChar_inputTape) {
        this.inputChar_inputTape = inputChar_inputTape;
    }

    public void setOutputChar_inputTape(Character outputChar_inputTape) {
        this.outputChar_inputTape = outputChar_inputTape;
    }

    public void setInputMemory(List<Character> inputMemory) {
        this.inputMemory = inputMemory;
    }

    public void setOutputMemory(List<Character> outputMemory) {
        this.outputMemory = outputMemory;
    }

    public void setOutputChar_outputTape(Character outputChar_outputTape) {
        this.outputChar_outputTape = outputChar_outputTape;
    }

    public void setMove(List<Character> move) {
        this.move = move;
    }

    public String getOutputState() {
        return outputState;
    }

    public Character getInputChar_inputTape() {
        return this.inputChar_inputTape;
    }

    public Character getOutputChar_inputTape() {
        return this.outputChar_inputTape;
    }
    public List<Character> getInputMemory() {
        return inputMemory;
    }

    public List<Character> getOutputMemory() {
        return outputMemory;
    }

    public Character getOutputChar_outputTape() { return this.outputChar_outputTape; }

    public List<Character> getMove() {
        return move;
    }
}
