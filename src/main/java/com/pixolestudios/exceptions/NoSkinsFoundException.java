package main.java.com.pixolestudios.exceptions;

public class NoSkinsFoundException extends Exception {
    public NoSkinsFoundException() {
        super("No output skins found - This is most likely due to an incomplete database of skins or if your input skins were covert");
    }
}
