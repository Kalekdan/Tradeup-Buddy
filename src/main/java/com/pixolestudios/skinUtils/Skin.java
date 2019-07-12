package main.java.com.pixolestudios.skinUtils;

import java.util.EnumMap;

public abstract class Skin {

    private String name;
    private float minFloat, maxFloat;
    private WeaponCollection collection;
    private Grade grade;
    private EnumMap<Condition, Float> valueMap;

    public Skin(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade, EnumMap<Condition, Float> valueMap) {
        this.name = name;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.collection = collection;
        this.grade = grade;
        this.valueMap = valueMap;
    }

    public Skin(Skin skin) {
        name = skin.name;
        minFloat = skin.minFloat;
        maxFloat = skin.maxFloat;
        collection = skin.collection;
        grade = skin.grade;
        valueMap = skin.valueMap;
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
}
