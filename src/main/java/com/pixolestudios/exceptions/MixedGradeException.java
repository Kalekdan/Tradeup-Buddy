package main.java.com.pixolestudios.exceptions;

public class MixedGradeException extends Throwable {
    public MixedGradeException() {
        super("Input skins must all be same grade");
    }
}
