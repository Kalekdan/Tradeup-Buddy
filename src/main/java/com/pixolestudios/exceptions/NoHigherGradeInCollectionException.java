package main.java.com.pixolestudios.exceptions;

import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

public class NoHigherGradeInCollectionException extends Exception {
    public NoHigherGradeInCollectionException(WeaponCollection collection, Grade grade) {
        super("No " + grade.toString() + " skins exist in " + collection.toString() + " collection");
    }
}
