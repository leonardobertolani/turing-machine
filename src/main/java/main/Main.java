package main;

import machines.KTapeTuringMachine;
import machines.TuringMachine;

public class Main {
    public static void main(String[] args) {

        TuringMachine turingMachine = new KTapeTuringMachine();
        turingMachine.loadConfiguration("D:\\Progetti\\Scienza\\turing-machine\\src\\main\\java\\configurations\\anbn.json");
        turingMachine.loadInputString("aaabbb");

        turingMachine.compute();

        System.out.println("TM is accepting string: ");
        System.out.println(turingMachine.isAccepting());
    }
}