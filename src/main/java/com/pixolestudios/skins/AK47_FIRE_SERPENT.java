package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public class AK47_FIRE_SERPENT extends Skin { //TODO delete this class once data is in db
    // DATA FROM https://csgoitems.pro/en/skin/AK-47_%7C_Fire_Serpent
    public AK47_FIRE_SERPENT() {
        super("AK-47 Fire Serpent", 0.06f, 0.76f, WeaponCollection.BRAVO, Grade.COVERT, makeValueMap(), makeValueMap());
    }

    private static EnumMap<Condition, Float> makeValueMap() {
        EnumMap<Condition, Float> valMap = new EnumMap<>(Condition.class);
        valMap.put(Condition.FACTORY_NEW, 1133.64f);
        valMap.put(Condition.MINIMAL_WEAR, 407.14f);
        valMap.put(Condition.FIELD_TESTED, 278.89f);
        valMap.put(Condition.WELL_WORN, 272.83f);
        valMap.put(Condition.BATTLE_SCARRED, 165.25f);
        return valMap;
    }
}
