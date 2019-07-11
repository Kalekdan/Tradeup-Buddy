package main.java.com.pixolestudios.skinUtils;

import java.util.HashMap;

public abstract class Skin {

    private String name;
    private float minFloat, maxFloat;
    private WeaponCollection collection;
    private Grade grade;
    private HashMap<Condition, Float> valueMap = new HashMap<>();

    public Skin(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade, HashMap<Condition, Float> valueMap){
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

    public String toString(){
        return name;
    }

    public Grade getGrade(){
        return grade;
    }
}
