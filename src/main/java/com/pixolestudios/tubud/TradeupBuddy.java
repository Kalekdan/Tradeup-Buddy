package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.skins.AK47_FIRE_SERPENT;

public class TradeupBuddy {
    public static void main(String[] args){
        InputSkin skin = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        System.out.print(skin.toString());
        InputSkin skin1 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin2 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin3 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin4 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin5 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin6 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin7 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin8 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin9 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);

        try {
            TradeupCalculator tradeup = new TradeupCalculator(skin, skin1, skin2, skin3, skin4, skin5, skin7, skin3, skin6, skin9);
        } catch (IncorrectInputNumber incorrectInputNumber) {
            incorrectInputNumber.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        }

    }
}
