package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumberException;
import main.java.com.pixolestudios.exceptions.InvalidInputGradesException;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skinUtils.Grade;
import main.java.com.pixolestudios.skinUtils.WeaponCollection;
import main.java.com.pixolestudios.skins.InputSkin;
import main.java.com.pixolestudios.skins.Skin;
import main.java.com.pixolestudios.skins.SkinDBItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SuppressWarnings("MagicNumber")
public class TradeupBuddy {

    // The location of the skinsdb.csv which contains all the data about the skins
    private static String skindbpath = "data/skinsdb.csv";

    // The skins HashMap database which is loaded from the .csv
    // name, skin
    private static HashMap<String, SkinDBItem> skinDB = new HashMap<>();

    private TradeupBuddy() {
    }

    public static void main(String[] args) {
        loadSkinDB();

//        performManualTradeup();
        performRandomTradeup();
    }

    private static void performManualTradeup() {
        InputSkin skin1 = new InputSkin(skinDB.get("AK-47 | First Class"), 0.5f);
        InputSkin skin2 = new InputSkin(skinDB.get("AK-47 | Emerald Pinstripe"), 0.2f);

        try {
            TradeupCalculator tradeup = new TradeupCalculator(skin1, skin1, skin1, skin1, skin2, skin2, skin2, skin2, skin2, skin2);
        } catch (IncorrectInputNumberException incorrectInputNumber) {
            incorrectInputNumber.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        } catch (NoSkinsFoundException e) {
            e.printStackTrace();
        } catch (InvalidInputGradesException e) {
            e.printStackTrace();
        }
    }

    private static void performRandomTradeup() {
        InputSkin[] randomSkinsArr = getRandomSkinArr();
        try {
            TradeupCalculator randomTradeup = new TradeupCalculator(randomSkinsArr[0], randomSkinsArr[1], randomSkinsArr[2], randomSkinsArr[3], randomSkinsArr[4], randomSkinsArr[5], randomSkinsArr[6], randomSkinsArr[7], randomSkinsArr[8], randomSkinsArr[9]);
        } catch (IncorrectInputNumberException e) {
            e.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        } catch (NoSkinsFoundException e) {
            e.printStackTrace();
        } catch (InvalidInputGradesException e) {
            e.printStackTrace();
        }
    }

    private static InputSkin[] getRandomSkinArr() {
        InputSkin[] skinsArr = new InputSkin[10];
        Grade randomGrade = Grade.getRandomGrade();
        for (int i = 0; i < 10; i++) {
            Skin randomSkin = getRandomSkin(randomGrade);
            float randomFloat = getRandomFloat(randomSkin);
            skinsArr[i] = new InputSkin(randomSkin, randomFloat);
        }
        return skinsArr;
    }

    private static float getRandomFloat(Skin randomSkin) {
        float min = randomSkin.getMinFloat();
        float max = randomSkin.getMaxFloat();
        return min + new Random().nextFloat() * (max - min);
    }

    private static Skin getRandomSkin(Grade randomGrade) {
        Object[] skinDBArr = skinDB.values().toArray();
        Skin randomSkin = (Skin) skinDBArr[new Random().nextInt(skinDBArr.length)];
        while (randomSkin.getGrade() != randomGrade){
            randomSkin = (Skin) skinDBArr[new Random().nextInt(skinDBArr.length)];
        }
        return randomSkin;
    }

    /**
     * Loads all the skins and their data from skinsdb.csv into array
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
                getSkinDB().put(vals.get(0), new SkinDBItem(vals.get(0), Float.valueOf(vals.get(1)), Float.valueOf(vals.get(2)), WeaponCollection.valueOf(vals.get(3).toUpperCase(Locale.ENGLISH)), Grade.valueOf(vals.get(4).toUpperCase(Locale.ENGLISH)), Float.valueOf(vals.get(5)), Float.valueOf(vals.get(6)), Float.valueOf(vals.get(7)), Float.valueOf(vals.get(8)), Float.valueOf(vals.get(9)), Float.valueOf(vals.get(10)), Float.valueOf(vals.get(11)), Float.valueOf(vals.get(12)), Float.valueOf(vals.get(13)), Float.valueOf(vals.get(14))));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the skin DB as calculated from the file
     *
     * @return the skinDB HashMap
     */
    public static HashMap<String, SkinDBItem> getSkinDB() {
        return skinDB;
    }
}
