package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumberException;
import main.java.com.pixolestudios.exceptions.InvalidInputGradesException;
import main.java.com.pixolestudios.exceptions.MixedGradeException;
import main.java.com.pixolestudios.exceptions.NoHigherGradeInCollectionException;
import main.java.com.pixolestudios.exceptions.NoSkinsFoundException;
import main.java.com.pixolestudios.skinUtils.Condition;
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
import java.util.Map;
import java.util.Random;

@SuppressWarnings("MagicNumber")
public class TradeupBuddy {

    // PARAMETERS AND TARGETS FOR RUNNING TRADEUPS
    // TWEAK THESE TO AFFECT THE OUTPUTS

    // The desired chance for profit on the tradeup - new random tradeups will be generated until this is met
    private static final float desiredChanceForProfit = 0.0f;
    // The desired average profit on the tradeup - new random tradeups will be generated until this is met
    private static final float desiredMinAvgProfit = 0.7f;
    // Maximum number of tradeups to attempt before stopping - if set to zero, will continue until manually stopped
    private static final int maxTradeupsToPerform = 0;
    // With this flag set, will not perform tradeups using items where the market value is not known
    private static final boolean REQUIRE_KNOWN_MARKET_VALUE = true;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        TradeupCalculator tradeup = performRandomTradeup();
        float profitChance = tradeup.getChanceForProfit();
        float avgProfit = tradeup.getAvgProfit();

        int counter = 1;
        while ((profitChance <= desiredChanceForProfit || avgProfit <= desiredMinAvgProfit) && counter != maxTradeupsToPerform) {
            tradeup = performRandomTradeup();
            profitChance = tradeup.getChanceForProfit();
            avgProfit = tradeup.getAvgProfit();
            counter++;
        }
    }

    /**
     * Performs a tradeup with a manually provided list of skins and floats
     *
     * @return the chance for the tradeup to turn a profit
     */
    private static TradeupCalculator performManualTradeup() {
        InputSkin skin1 = new InputSkin(skinDB.get("AK-47 | First Class"), 0.5f);
        InputSkin skin2 = new InputSkin(skinDB.get("AK-47 | Emerald Pinstripe"), 0.2f);

        try {
            TradeupCalculator tradeup = new TradeupCalculator(skin1, skin1, skin1, skin1, skin2, skin2, skin2, skin2, skin2, skin2);
            return tradeup;
        } catch (IncorrectInputNumberException incorrectInputNumber) {
            incorrectInputNumber.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        } catch (NoSkinsFoundException e) {
            e.printStackTrace();
        } catch (InvalidInputGradesException e) {
            e.printStackTrace();
        } catch (NoHigherGradeInCollectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Performs a tradeup with a random selection of 10 skins with random floats
     *
     * @return the chance for the tradeup to turn a profit
     */
    private static TradeupCalculator performRandomTradeup() {
        InputSkin[] randomSkinsArr = getRandomSkinArr(REQUIRE_KNOWN_MARKET_VALUE);
        try {
            TradeupCalculator randomTradeup = new TradeupCalculator(randomSkinsArr[0], randomSkinsArr[1], randomSkinsArr[2], randomSkinsArr[3], randomSkinsArr[4], randomSkinsArr[5], randomSkinsArr[6], randomSkinsArr[7], randomSkinsArr[8], randomSkinsArr[9]);
            return randomTradeup;
        } catch (IncorrectInputNumberException e) {
            e.printStackTrace();
        } catch (MixedGradeException e) {
            e.printStackTrace();
        } catch (NoSkinsFoundException e) {
            e.printStackTrace();
        } catch (InvalidInputGradesException e) {
            e.printStackTrace();
        } catch (NoHigherGradeInCollectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns an array of 10 random skins
     *
     * @param requireKnownMarketValue if true, will not use skins where the db does not have a market value for the float level
     * @return InputSkin[] array of 10 random skins and floats
     */
    private static InputSkin[] getRandomSkinArr(boolean requireKnownMarketValue) {
        InputSkin[] skinsArr = new InputSkin[10];
        Grade randomGrade = Grade.getRandomGrade();
        for (int i = 0; i < 10; i++) {
            Skin randomSkin = null;
            try {
                randomSkin = getRandomSkin(randomGrade);
            } catch (InvalidInputGradesException e) {
                e.printStackTrace();
            }
            float randomFloat = getRandomFloat(randomSkin);
            while (randomSkin.getValue(Condition.getCondition(randomFloat)) == 0.0f && requireKnownMarketValue) {
                randomFloat = getRandomFloat(randomSkin);
            }
            skinsArr[i] = new InputSkin(randomSkin, randomFloat);
        }
        return skinsArr;
    }

    /**
     * Returns a random float within the range of the max and min floats of the given skin
     *
     * @param randomSkin skin to cap float value to
     * @return random float within max and min range of input skin
     */
    private static float getRandomFloat(Skin randomSkin) {
        float min = randomSkin.getMinFloat();
        float max = randomSkin.getMaxFloat();
        return min + new Random().nextFloat() * (max - min);
    }

    /**
     * Returns a random skin at the provided grade. Will keep generating a new random skin until it matches the grade provided
     *
     * @param randomGrade the grade for the skin to be
     * @return a random skin of the given grade
     */
    private static Skin getRandomSkin(Grade randomGrade) throws InvalidInputGradesException {
        Object[] skinDBArr = skinDB.values().toArray();
        Skin randomSkin = (Skin) skinDBArr[new Random().nextInt(skinDBArr.length)];
        while (!possibleOutputSkinExists(Grade.nextGrade(randomGrade), randomSkin.getCollection()) || randomSkin.getGrade() != randomGrade) {
            randomSkin = (Skin) skinDBArr[new Random().nextInt(skinDBArr.length)];
        }
        return randomSkin;
    }

    /**
     * Checks that a skin of the output grade exists in the input collection before performing a tradeup
     *
     * @param outputGrade the grade the output skin will be
     * @param collection  the collection to check
     * @return true if a possible output skin exists
     */
    private static boolean possibleOutputSkinExists(Grade outputGrade, WeaponCollection collection) {
        for (Map.Entry<String, SkinDBItem> skinEntry : skinDB.entrySet()) {
            if (skinEntry.getValue().getGrade() == outputGrade && skinEntry.getValue().getCollection() == collection) {
                return true;
            }
        }
        return false;
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
