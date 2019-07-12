package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.EnumMap;

public abstract class Skin {

    private String name;
    private float minFloat;
    private float maxFloat;
    private WeaponCollection collection;
    private Grade grade;
    private EnumMap<Condition, Float> valueMap;
    private EnumMap<Condition, Float> valueMap_st;

    protected Skin(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade, EnumMap<Condition, Float> valueMap, EnumMap<Condition, Float> valueMap_st) {
        this.name = name;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.collection = collection;
        this.grade = grade;
        this.valueMap = valueMap;
        this.valueMap_st = valueMap_st;
    }

    protected Skin(Skin skin) {
        name = skin.name;
        minFloat = skin.minFloat;
        maxFloat = skin.maxFloat;
        collection = skin.collection;
        grade = skin.grade;
        valueMap = skin.valueMap;
        valueMap_st = skin.valueMap_st;
    }

    public float getOutputFloat(float avgFloat) {
        return (avgFloat * (maxFloat - minFloat)) + minFloat;
    }

    public float getOutputFloat(float... inputFloats) {
        float avgFloat = 0;
        for (float inFloat : inputFloats) {
            avgFloat += inFloat;
        }
        avgFloat /= 10;
        return getOutputFloat(avgFloat);
    }

    public WeaponCollection getCollection() {
        return collection;
    }

    public String toString() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public float getValue(Condition condition) {
        return valueMap.get(condition);
    }
}