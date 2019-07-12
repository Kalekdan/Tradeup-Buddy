package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;
import main.java.com.pixolestudios.skins.AK47_FIRE_SERPENT;
import main.java.com.pixolestudios.skins.Glock18_OFF_WORLD;
import main.java.com.pixolestudios.skins.Glock18_WARHAWK;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeupCalculator {
    Grade outputGrade;
    float avgFloat;
    ArrayList<Skin> outputSkins = new ArrayList<Skin>();

    ArrayList<Skin> allSkins = new ArrayList<Skin>();

    // collection, num occurences
    HashMap<WeaponCollection, Integer> inputCollections = new HashMap<>();

    public TradeupCalculator(InputSkin... skins) throws IncorrectInputNumber, MixedGradeException, NoSkinsFoundException {
        checkValidInput(skins);
        DisplayInputs(skins);
        makeSkinArray();

        outputGrade = Grade.nextGrade(skins[0].getGrade());
        avgFloat = calculateOutputFloat(skins);
        updateInputCollections(skins);

        calculateOutputSkins(skins);
        DisplayOutputs();
    }

    private void DisplayInputs(InputSkin[] skins) {
        for (InputSkin skin: skins) {
            System.out.println(skin.getName() + " " + skin.getFloatValue() + " - " + skin.getCondition());
        }
    }

    private void DisplayOutputs() throws NoSkinsFoundException {
        System.out.println("\nAverage input float = " + avgFloat + " - " + Condition.getCondition(avgFloat));
        if (outputSkins.size() == 0){
            throw new NoSkinsFoundException();
        }
        for (Skin skin: outputSkins) {
            float outFloat = skin.getOutputFloat(avgFloat);
            Condition condition = Condition.getCondition(outFloat);
            System.out.println(skin.getName() + " " + outFloat + " - " + condition + " ~$" + skin.getValue(condition));
        }
    }

    private void checkValidInput(InputSkin[] skins) throws IncorrectInputNumber, MixedGradeException {
        if (skins.length != 10) {
            throw new IncorrectInputNumber(skins.length);
        }
        Grade grade = skins[0].getGrade();
        for (InputSkin skin : skins) {
            if (skin.getGrade() != grade) {
                throw new MixedGradeException();
            }
        }
    }

    private float calculateOutputFloat(InputSkin[] skins) {
        float avg = 0;
        for (InputSkin skin : skins) {
            avg += skin.getFloatValue();
        }
        return avg / 10;
    }

    private void updateInputCollections(InputSkin[] skins) {
        for (InputSkin skin : skins) {
            if (inputCollections.containsKey(skin.getCollection())) {
                inputCollections.put(skin.getCollection(), inputCollections.get(skin.getCollection()) + 1);
            } else {
                inputCollections.put(skin.getCollection(), 1);
            }
        }
    }

    private void calculateOutputSkins(InputSkin[] skins) {
        for (Skin skin : allSkins) {
            if (inputCollections.containsKey(skin.getCollection()) && skin.getGrade() == outputGrade) {
                if (!outputSkins.contains(skin)) {
                    outputSkins.add(skin);
                }
            }
        }
    }

    private void makeSkinArray() { //TODO add all skins to this array
        allSkins.add(new AK47_FIRE_SERPENT());
        allSkins.add(new Glock18_OFF_WORLD());
        allSkins.add(new Glock18_WARHAWK());
    }
}
