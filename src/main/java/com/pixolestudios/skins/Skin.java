package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public abstract class Skin {

    private String name;                            // the name of the skin
    private float minFloat;                         // the minimum possible float of the skin
    private float maxFloat;                         // the maximum possible float of the skin
    private WeaponCollection collection;            // the collection the skin belongs to
    private Grade grade;                            // the grade of the skin
    private EnumMap<Condition, Float> valueMap;     // an EnumMap of condition against value
    private EnumMap<Condition, Float> valueMap_st;  // an EnumMap of condition against StatTrak value

    /**
     * Abstract class of a weapon skin
     *
     * @param name        the name of the skin
     * @param minFloat    the minimum possible float of the skin
     * @param maxFloat    the maximum possible float of the skin
     * @param collection  the collection the skin belongs to
     * @param grade       the grade of the skin
     * @param valueMap    an EnumMap of condition against value
     * @param valueMap_st an EnumMap of condition against StatTrak value
     */
    protected Skin(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade, EnumMap<Condition, Float> valueMap, EnumMap<Condition, Float> valueMap_st) {
        this.name = name;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.collection = collection;
        this.grade = grade;
        this.valueMap = valueMap;
        this.valueMap_st = valueMap_st;
    }

    /**
     * Abstract class of a weapon skin
     *
     * @param skin the skin to create an instance of
     */
    protected Skin(Skin skin) {
        name = skin.name;
        minFloat = skin.minFloat;
        maxFloat = skin.maxFloat;
        collection = skin.collection;
        grade = skin.grade;
        valueMap = skin.valueMap;
        valueMap_st = skin.valueMap_st;
    }

    /**
     * Returns the output float taking into account the maximum and minimum possible floats for the skin
     *
     * @param avgFloat the average float of the input skins
     * @return the float this skin would be as an output given the inputs
     */
    public float getOutputFloat(float avgFloat) {
        return (avgFloat * (maxFloat - minFloat)) + minFloat;
    }

    /**
     * Returns the output float taking into account the maximum and minimum possible floats for the skin
     *
     * @param inputFloats all the floats of the input skins
     * @return the float this skin would be as an output given the inputs
     */
    public float getOutputFloat(float... inputFloats) {
        float avgFloat = 0;
        for (float inFloat : inputFloats) {
            avgFloat += inFloat;
        }
        avgFloat /= inputFloats.length;
        return getOutputFloat(avgFloat);
    }

    /**
     * Retuns the weapon collection of the skin
     *
     * @return collection
     */
    public WeaponCollection getCollection() {
        return collection;
    }

    /**
     * Returns the string identifying the skin
     *
     * @return the skin name as a String
     */
    public String toString() {
        return name;
    }

    /**
     * Returns the grade of the skin
     *
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Returns the name of the skin
     *
     * @return the skin name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of the skin given the condition
     *
     * @param condition the condition to get the value of
     * @return the value of the skin at the given condition
     */
    public float getValue(Condition condition) {
        return valueMap.get(condition);
    }

    public float getMaxFloat() {
        return maxFloat;
    }

    public float getMinFloat() {
        return minFloat;
    }
}
