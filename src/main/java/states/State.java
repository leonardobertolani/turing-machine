package states;

import java.util.List;

public class State {

    String name;
    boolean isFinal;
    boolean isInitial;
    List<Transition> transitions;

    public void setName(String name) {
        this.name = name;
    }

    public String getName()  {
        return this.name;
    }

    public void setIsFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void setIsInitial(boolean initial) {
        isInitial = initial;
    }

    public boolean isInitial() {
        return this.isInitial;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }
}
