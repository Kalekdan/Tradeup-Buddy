package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.HashMap;

public class AK47_FIRE_SERPENT extends Skin { //TODO put correct values in here


    public AK47_FIRE_SERPENT() {
        super("AK-47 Fire Serpent", 0, 1, WeaponCollection.OVERPASS, Grade.COVERT, makeValueMap());
    }

    private static HashMap<Condition, Float> makeValueMap() {
        HashMap<Condition, Float> valMap = new HashMap<>();
        valMap.put(Condition.FACTORY_NEW, 400f);
        valMap.put(Condition.MINIMAL_WEAR, 200f);
        valMap.put(Condition.FIELD_TESTED, 60f);
        valMap.put(Condition.WELL_WORN, 50f);
        valMap.put(Condition.WELL_WORN, 45f);
        return valMap;
    }
}
