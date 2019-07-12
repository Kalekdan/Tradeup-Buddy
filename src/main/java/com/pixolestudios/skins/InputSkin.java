package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;

public class InputSkin extends Skin {
    private float floatValue;
    private Condition condition;

    public InputSkin(Skin skin, float floatValue) {
        super(skin);
        this.floatValue = floatValue;
        condition = Condition.getCondition(floatValue);
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return super.toString() + " - Condition: " + condition + " - Float: " + floatValue;
    }

    public float getFloatValue() {
        return floatValue;
    }
}
