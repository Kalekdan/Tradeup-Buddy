package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public class Glock18_OFF_WORLD extends Skin { //TODO delete this class once data is in db
    // DATA FROM https://csgoitems.pro/en/skin/Glock-18_%7C_Off_World
    public Glock18_OFF_WORLD() {
        super("Glock-18 Off World", 0f, 1f, WeaponCollection.SPECTRUM_2, Grade.MIL_SPEC, makeValueMap(), makeValueMap());
    }

    private static EnumMap<Condition, Float> makeValueMap() {
        EnumMap<Condition, Float> valMap = new EnumMap<>(Condition.class);
        valMap.put(Condition.FACTORY_NEW, 0.89f);
        valMap.put(Condition.MINIMAL_WEAR, 0.16f);
        valMap.put(Condition.FIELD_TESTED, 0.12f);
        valMap.put(Condition.WELL_WORN, 0.13f);
        valMap.put(Condition.BATTLE_SCARRED, 0.12f);
        return valMap;
    }
}
