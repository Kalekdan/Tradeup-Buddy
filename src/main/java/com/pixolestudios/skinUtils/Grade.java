package main.java.com.pixolestudios.skinUtils;

import main.java.com.pixolestudios.exceptions.InvalidInputGradesException;

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
}
