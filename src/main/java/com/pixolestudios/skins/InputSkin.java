package main.java.com.pixolestudios.skins;

import main.java.com.pixolestudios.skinUtils.Condition;

public class InputSkin extends Skin {
    private float floatValue;       // Float value of the skin
    private Condition condition;    // Condition of the skin
    private float value;            // Market value of the skin

    /**
     * Creates an instance of an input skin
     *
     * @param skin       the skin from the skindb
     * @param floatValue the float of the input
     */
    public InputSkin(Skin skin, float floatValue) {
        super(skin);
        this.floatValue = floatValue;
        condition = Condition.getCondition(floatValue);
        value = getValue(condition);
    }

    /**
     * Returns the condition of the skin
     *
     * @return condition
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Returns a string to display the skin
     *
     * @return String including data about the skin
     */
    @Override
    public String toString() {
        return super.toString() + " - Condition: " + condition + " - Float: " + floatValue + " - ~$" + value;
    }

    /**
     * Returns the float value of the skin
     *
     * @return float value of skin
     */
    public float getFloatValue() {
        return floatValue;
    }
}
