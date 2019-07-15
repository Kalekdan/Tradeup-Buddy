package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumberException;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skinUtils.Condition;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;
import main.java.com.pixolestudios.skins.InputSkin;
import main.java.com.pixolestudios.skins.Skin;
import main.java.com.pixolestudios.skins.SkinDBItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TradeupCalculator {
    private Grade outputGrade;
    private float avgFloat;
    private float inputValue = 0;
    private ArrayList<Skin> outputSkins = new ArrayList<Skin>();

    // collection, num occurences
    private HashMap<WeaponCollection, Integer> inputCollections = new HashMap<>();
    private float sumOfCollections = 0;

    public TradeupCalculator(InputSkin... skins) throws IncorrectInputNumberException, MixedGradeException, NoSkinsFoundException {
        checkValidInput(skins);
        DisplayInputs(skins);

        outputGrade = Grade.nextGrade(skins[0].getGrade());
        avgFloat = calculateOutputFloat(skins);
        updateInputCollections(skins);

        calculateOutputSkins(skins);
        DisplayOutputs();
    }

    private void DisplayInputs(InputSkin[] skins) {
        for (InputSkin skin : skins) {
            System.out.println(skin.getName() + " " + skin.getFloatValue() + " - " + skin.getCondition());
        }
    }

    private void DisplayOutputs() throws NoSkinsFoundException {
        System.out.println("\nAverage input float = " + avgFloat + " - " + Condition.getCondition(avgFloat));
        System.out.println("Input value ~$" + inputValue);
        if (outputSkins.isEmpty()) {
            throw new NoSkinsFoundException();
        }
        for (Skin skin : outputSkins) {
            float outFloat = skin.getOutputFloat(avgFloat);
            Condition condition = Condition.getCondition(outFloat);
            System.out.println(skin.getName() + " " + outFloat + " - " + condition + " ~$" + skin.getValue(condition) + " - Chance = " + CalculateProbability(skin) * 100 + "%");
        }
    }

    private float CalculateProbability(Skin skin) {
        return inputCollections.get(skin.getCollection()) / (sumOfCollections * inputCollections.size());
    }

    private void checkValidInput(InputSkin[] skins) throws IncorrectInputNumberException, MixedGradeException {
        if (skins.length != 10) {
            throw new IncorrectInputNumberException(skins.length);
        }
        Grade grade = skins[0].getGrade();
        for (InputSkin skin : skins) {
            if (skin.getGrade() != grade) {
                throw new MixedGradeException();
            }
            inputValue += skin.getValue(skin.getCondition());
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
            sumOfCollections++;
        }
    }

    private void calculateOutputSkins(InputSkin[] skins) {
        for (Map.Entry<String, SkinDBItem> skin : TradeupBuddy.getSkinDB().entrySet()) {
            if (inputCollections.containsKey(skin.getValue().getCollection()) && skin.getValue().getGrade() == outputGrade) {
                if (!outputSkins.contains(skin)) {
                    outputSkins.add(skin.getValue());
                }
            }
        }
    }
}
