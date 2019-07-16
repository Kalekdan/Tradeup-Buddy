package main.java.com.pixolestudios.skinUtils;

import main.java.com.pixolestudios.exceptions.InvalidInputGradesException;

import java.util.Random;

/**
 * Enum of all the possible weapon grades
 * Output skins will alwasy be the grade above the inputs
 */
public enum Grade {
    COVERT,
    CLASSIFIED,
    RESTRICTED,
    MIL_SPEC,
    INDUSTRIAL,
    CONSUMER,
    CONTRABAND; // Included for consistency

    /**
     * Returns the grade of the output skins given the grade of the input skins
     *
     * @param inputGrade the grade of the input skins
     * @return the next grade i.e. the grade the output skins will be
     * @throws InvalidInputGradesException if the input grades do not allow output grades (i.e. COVERT/CONTRABAND)
     */
    public static Grade nextGrade(Grade inputGrade) throws InvalidInputGradesException {
        if (inputGrade == CONSUMER) {
            return INDUSTRIAL;
        }
        if (inputGrade == INDUSTRIAL) {
            return MIL_SPEC;
        }
        if (inputGrade == MIL_SPEC) {
            return RESTRICTED;
        }
        if (inputGrade == RESTRICTED) {
            return CLASSIFIED;
        }
        if (inputGrade == CLASSIFIED) {
            return COVERT;
        }
        throw new InvalidInputGradesException(inputGrade);
    }

    /**
     * Gets a random grade excluding CONTRABAND and COVERT
     * @return random grade
     */
    public static Grade getRandomGrade() {
        int randInt = new Random().nextInt(5) + 1;
        switch (randInt){
            case 1 : return CLASSIFIED;
            case 2 : return RESTRICTED;
            case 3 : return MIL_SPEC;
            case 4 : return INDUSTRIAL;
            case 5 : return CONSUMER;
            default: return null;
        }
    }
}
