package main.java.com.pixolestudios.exceptions;

public class IncorrectInputNumber extends Throwable {
    public IncorrectInputNumber(int numInputs) {
        super("Incorrect number of inputs - Expected 10 : Actual " + numInputs);
    }
}
