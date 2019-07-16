package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

/**
 * Class for each of the skins stored in the skinsdb.csv database
 */
public class SkinDBItem extends Skin {

    /**
     * Creates a skin database item
     *
     * @param name       the name of the skin
     * @param minFloat   the minimum possible float of the skin
     * @param maxFloat   the maximum possible float of the skin
     * @param collection the collection the skin is in
     * @param grade      the grade the skin is (i.e. Classified, Mil-Spec etc.)
     * @param fnValue    the market value of a factory new instance of this skin
     * @param mwValue    the market value of a minimal wear instance of this skin
     * @param ftValue    the market value of a field tested instance of this skin
     * @param wwValue    the market value of a well worn instance of this skin
     * @param bsValue    the market value of a battle scarred instance of this skin
     * @param fnValue_st the market value of a StatTrak factory new instance of this skin
     * @param mwValue_st the market value of a StatTrak minimal wear instance of this skin
     * @param ftValue_st the market value of a StatTrak field tested instance of this skin
     * @param wwValue_st the market value of a StatTrak well worn instance of this skin
     * @param bsValue_st the market value of a StatTrak battle scarred instance of this skin
     */
    public SkinDBItem(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade,
                      float fnValue, float mwValue, float ftValue, float wwValue, float bsValue,
                      float fnValue_st, float mwValue_st, float ftValue_st, float wwValue_st, float bsValue_st) {
        super(name, minFloat, maxFloat, collection, grade,
                makeValueMap(fnValue, mwValue, ftValue, wwValue, bsValue),
                makeValueMap(fnValue_st, mwValue_st, ftValue_st, wwValue_st, bsValue_st));
    }

    /**
     * Creates an EnumMap of condition against value of the skin at that condition
     *
     * @param fnValue the market value of a factory new instance of this skin
     * @param mwValue the market value of a minimal wear instance of this skin
     * @param ftValue the market value of a field tested instance of this skin
     * @param wwValue the market value of a well worn instance of this skin
     * @param bsValue the market value of a battle scarred instance of this skin
     * @return the EnumMap of conditions agains skin value
     */
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
