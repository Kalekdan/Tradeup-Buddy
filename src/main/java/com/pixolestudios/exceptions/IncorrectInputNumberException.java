package main.java.com.pixolestudios.exceptions;

public class IncorrectInputNumberException extends Exception {
    public IncorrectInputNumberException(int numInputs) {
        super("Incorrect number of inputs - Expected 10 : Actual " + numInputs);
    }
}
