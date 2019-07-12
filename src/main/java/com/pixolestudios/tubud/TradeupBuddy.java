package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skins.AK47_FIRE_SERPENT;
import main.java.com.pixolestudios.skins.Glock18_OFF_WORLD;
import main.java.com.pixolestudios.skins.Glock18_WARHAWK;

public class TradeupBuddy {
    public static void main(String[] args){
        InputSkin skin = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin1 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin2 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin3 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin4 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin5 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin6 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);
        InputSkin skin7 = new InputSkin(new Glock18_OFF_WORLD(), 0.04f);
        InputSkin skin8 = new InputSkin(new Glock18_WARHAWK(), 0.04f);
        InputSkin skin9 = new InputSkin(new AK47_FIRE_SERPENT(), 0.04f);

        try {
            TradeupCalculator tradeup = new TradeupCalculator(skin7, skin7, skin7, skin7, skin8, skin8, skin7, skin8, skin7, skin7);
        } catch (IncorrectInputNumber incorrectInputNumber) {
            incorrectInputNumber.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        } catch (NoSkinsFoundException e) {
            e.printStackTrace();
        }

    }
}
