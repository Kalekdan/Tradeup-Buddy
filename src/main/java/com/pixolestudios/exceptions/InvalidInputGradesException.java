package main.java.com.pixolestudios.exceptions;

import main.java.com.pixolestudios.skinUtils.Grade;

public class InvalidInputGradesException extends Exception {
    public InvalidInputGradesException(Grade inputGrade) {
        super("Tradeup contract cannot have input grade of " + inputGrade.toString());
    }
}
