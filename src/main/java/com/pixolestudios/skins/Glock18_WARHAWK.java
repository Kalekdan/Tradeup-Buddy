package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public class Glock18_WARHAWK extends Skin {
    // DATA FROM https://csgoitems.pro/en/skin/Glock-18_%7C_Warhawk
    public Glock18_WARHAWK() {
        super("Glock-18 Warhawk", 0f, 1f, WeaponCollection.HORIZON, Grade.MIL_SPEC, makeValueMap());
    }

    private static EnumMap<Condition, Float> makeValueMap() {
        EnumMap<Condition, Float> valMap = new EnumMap<>(Condition.class);
        valMap.put(Condition.FACTORY_NEW, 0.95f);
        valMap.put(Condition.MINIMAL_WEAR, 0.24f);
        valMap.put(Condition.FIELD_TESTED, 0.11f);
        valMap.put(Condition.WELL_WORN, 0.12f);
        valMap.put(Condition.BATTLE_SCARRED, 0.11f);
        return valMap;
    }
}
