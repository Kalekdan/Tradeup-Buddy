package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public class SkinDBItem extends Skin {
    public SkinDBItem(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade,
                      float fnValue,    float mwValue,    float ftValue,    float wwValue,    float bsValue,
                      float fnValue_st, float mwValue_st, float ftValue_st, float wwValue_st, float bsValue_st) {
        super(name, minFloat, maxFloat, collection, grade,
                makeValueMap(fnValue,    mwValue,    ftValue,    wwValue,    bsValue),
                makeValueMap(fnValue_st, mwValue_st, ftValue_st, wwValue_st, bsValue_st));
    }

    private static EnumMap<Condition, Float> makeValueMap(float fnValue, float mwValue, float ftValue, float wwValue, float bsValue) {
        EnumMap<Condition, Float> valMap = new EnumMap<>(Condition.class);
        valMap.put(Condition.FACTORY_NEW, fnValue);
        valMap.put(Condition.MINIMAL_WEAR, mwValue);
        valMap.put(Condition.FIELD_TESTED, ftValue);
        valMap.put(Condition.WELL_WORN, wwValue);
        valMap.put(Condition.BATTLE_SCARRED, bsValue);
        return valMap;
    }
}
