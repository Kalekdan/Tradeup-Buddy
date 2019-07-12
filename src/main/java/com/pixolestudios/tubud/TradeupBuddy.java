package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumber;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;
import main.java.com.pixolestudios.skins.AK47_FIRE_SERPENT;
import main.java.com.pixolestudios.skins.Glock18_OFF_WORLD;
import main.java.com.pixolestudios.skins.Glock18_WARHAWK;
import main.java.com.pixolestudios.skins.SkinDBItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TradeupBuddy {

    private static String skindbpath = "scripts/skinsdb.csv";

    public static ArrayList<SkinDBItem> skinDB = new ArrayList<>();

    public static void main(String[] args) {
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

        loadSkinDB();

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

    /**
     * Loads all the skins from skinsdb.csv into array
     */
    private static void loadSkinDB() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(skindbpath));
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                List<String> vals = Arrays.asList(line.split(","));
                // Create a new SkinDBItem using the values provided in the .csv
                skinDB.add(new SkinDBItem(vals.get(0), Float.valueOf(vals.get(1)), Float.valueOf(vals.get(2)), WeaponCollection.valueOf(vals.get(3).toUpperCase(Locale.ENGLISH)), Grade.valueOf(vals.get(4).toUpperCase(Locale.ENGLISH)), Float.valueOf(vals.get(5)), Float.valueOf(vals.get(6)), Float.valueOf(vals.get(7)), Float.valueOf(vals.get(8)), Float.valueOf(vals.get(9)), Float.valueOf(vals.get(10)), Float.valueOf(vals.get(11)), Float.valueOf(vals.get(12)), Float.valueOf(vals.get(13)), Float.valueOf(vals.get(14))));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
