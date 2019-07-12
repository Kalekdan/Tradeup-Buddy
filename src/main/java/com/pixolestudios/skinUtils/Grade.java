package main.java.com.pixolestudios.skinUtils;

public enum Grade {
    COVERT,
    CLASSIFIED,
    RESTRICTED,
    MIL_SPEC,
    INDUSTRIAL,
    CONSUMER,
    CONTRABAND;

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
