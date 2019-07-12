package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.Skin;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;

import java.util.ArrayList;

public class TradeupCalculator {
    Grade outputGrade;
    float avgFloat;
    ArrayList<Skin> outputSkins = new ArrayList<Skin>();

    ArrayList<WeaponCollection> inputCollections = new ArrayList<WeaponCollection>();

    public TradeupCalculator(InputSkin... skins) throws IncorrectInputNumber, MixedGradeException {
        checkValidInput(skins);
        outputGrade = Grade.nextGrade(skins[0].getGrade());
        avgFloat = calculateOutputFloat(skins);
        updateInputCollections(skins);
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
            if(!inputCollections.contains(skin.getCollection())){
                inputCollections.add(skin.getCollection());
            }
        }
    }
}
