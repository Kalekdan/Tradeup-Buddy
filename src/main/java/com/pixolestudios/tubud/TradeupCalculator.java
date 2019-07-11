package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.skinUtils.Grade;

public class TradeupCalculator {
    public TradeupCalculator(InputSkin... skins) throws IncorrectInputNumber, MixedGradeException {
        checkValidInput(skins);
    }

    private void checkValidInput(InputSkin[] skins) throws IncorrectInputNumber, MixedGradeException {
        if (skins.length != 10){
            throw new IncorrectInputNumber(skins.length);
        }
        Grade grade = skins[0].getGrade();
        for (InputSkin skin: skins) {
            if (skin.getGrade() != grade){
                throw new MixedGradeException();
            }
        }
    }
}
