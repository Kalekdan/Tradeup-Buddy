package main.java.com.pixolestudios.tubud;

public abstract class Skin {

    private String name;
    private float minFloat, maxFloat;
    private WeaponCollection collection;
    private Grade grade;


    public Skin(String name, float minFloat, float maxFloat, WeaponCollection collection, Grade grade){
        this.name = name;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.collection = collection;
        this.grade = grade;
    }

    public Skin(Skin skin) {
        name = skin.name;
        minFloat = skin.minFloat;
        maxFloat = skin.maxFloat;
        collection = skin.collection;
        grade = skin.grade;
    }

    public String toString(){
        return name;
    }
}
