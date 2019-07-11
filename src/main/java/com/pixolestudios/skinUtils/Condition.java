package main.java.com.pixolestudios.skinUtils;

public enum Condition {
    FACTORY_NEW,
    MINIMAL_WEAR,
    FIELD_TESTED,
    WELL_WORN,
    BATTLE_SCARRED;

    public static Condition getCondition(float floatValue){    //TODO: get actual values for condition floats
        if (floatValue <= 0.06){
            return FACTORY_NEW;
        }
        if (floatValue <= 0.1f){
            return MINIMAL_WEAR;
        }
        if (floatValue <= 0.4){
            return FIELD_TESTED;
        }
        if (floatValue <= 0.6){
            return WELL_WORN;
        }
        return BATTLE_SCARRED;
    }
}
