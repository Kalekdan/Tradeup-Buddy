package main.java.com.pixolestudios.skinUtils;

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
     */
    public static Grade nextGrade(Grade inputGrade) {
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
        return null; //TODO may want to throw an error instead
    }
}
