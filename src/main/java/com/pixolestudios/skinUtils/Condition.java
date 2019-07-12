package main.java.com.pixolestudios.skinUtils;

public enum Condition {
    FACTORY_NEW,
    MINIMAL_WEAR,
    FIELD_TESTED,
    WELL_WORN,
    BATTLE_SCARRED;

    public static Condition getCondition(float floatValue) {
        if (floatValue <= 0.07) {
            return FACTORY_NEW;
        }
        if (floatValue <= 0.15f) {
            return MINIMAL_WEAR;
        }
        if (floatValue <= 0.37) {
            return FIELD_TESTED;
        }
        if (floatValue <= 0.44) {
            return WELL_WORN;
        }
        return BATTLE_SCARRED;
    }
}
