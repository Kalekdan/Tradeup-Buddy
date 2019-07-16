package main.java.com.pixolestudios.tubud;

import main.java.com.pixolestudios.exceptions.IncorrectInputNumberException;
import main.java.com.pixolestudios.exceptions.InvalidInputGradesException;
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

@SuppressWarnings("MagicNumber")
public class TradeupCalculator {
    private Grade outputGrade;          // Grade the output skin will be
    private float avgFloat;             // Average float of the input skins
    private float inputValue = 0;       // Total value of the input skins
    private float avgOutputValue = 0;   // Average value of the output skins

    // Arraylist of all the possible output skins
    private ArrayList<Skin> outputSkins = new ArrayList<Skin>();

    private float maxProfit = -999999999;       // Maximum possible profit
    private float minProfit = 999999999;        // Minimum possible profit
    private float avgProfit = 0;                // Average profit taking probabilities into account
    private float chanceForProfit = 0;          // A sum of the probabilities of the outcomes where a profit is made

    // Hash Maps which count the number of skins in each collection for the inputs and outputs
    // collection, num occurences
    private HashMap<WeaponCollection, Integer> inputCollections = new HashMap<>();
    private HashMap<WeaponCollection, Integer> outputCollections = new HashMap<>();

    /**
     * The calculator which outputs the possible outcomes of the tradeup
     *
     * @param skins takes 10 inputs skins to perform the mock tradeup on
     * @throws IncorrectInputNumberException if more or less than 10 input skins are provided
     * @throws MixedGradeException           if input skins are not all of the same grade
     * @throws NoSkinsFoundException         if no output skins exist for the given inputs
     * @throws InvalidInputGradesException   if input grades do not support output
     */
    public TradeupCalculator(InputSkin... skins) throws IncorrectInputNumberException, MixedGradeException, NoSkinsFoundException, InvalidInputGradesException {
        DisplayInputs(skins);
        checkValidInput(skins);

        outputGrade = Grade.nextGrade(skins[0].getGrade());
        avgFloat = calculateAvgFloat(skins);
        updateInputCollections(skins);

        calculateOutputSkins(skins);
        countOutputSkinCollections();
        DisplayOutputs();
    }

    /**
     * Displays the input skins in a format with details about float, value etc.
     *
     * @param skins the array of input skins
     */
    private void DisplayInputs(InputSkin[] skins) {
        for (InputSkin skin : skins) {
            System.out.println(skin.getName() + " - (" + skin.getGrade() + ") (" + skin.getCollection() + ") - " + skin.getFloatValue() + " - " + skin.getCondition() + " ~$" + skin.getValue(skin.getCondition()));
        }
    }

    /**
     * Displays all the resulting data calculated by the calculator
     *
     * @throws NoSkinsFoundException if no output skins exist
     */
    private void DisplayOutputs() throws NoSkinsFoundException {
        System.out.println("\nAverage input float = " + avgFloat + " - " + Condition.getCondition(avgFloat));
        System.out.println("Input value ~$" + inputValue + "\n");
        System.out.println("===========================================================\n");

        if (outputSkins.isEmpty()) {
            throw new NoSkinsFoundException();
        }
        for (Skin skin : outputSkins) {
            float outFloat = skin.getOutputFloat(avgFloat);
            Condition condition = Condition.getCondition(outFloat);
            System.out.println(skin.getName() + " - (" + skin.getGrade() + ") (" + skin.getCollection() + ") - " + outFloat + " - " + condition + " ~$" + skin.getValue(condition) + " - " + CalculateProbability(skin) * 100 + "%");
            avgOutputValue += CalculateProbability(skin) * skin.getValue(condition);
            if (skin.getValue(condition) - inputValue > 0) { // i.e. profitable outcome
                chanceForProfit += CalculateProbability(skin);
            }
            if ((skin.getValue(condition) - inputValue) > maxProfit) { // profit is higher than previous max profit
                maxProfit = skin.getValue(condition) - inputValue;
            }
            if ((skin.getValue(condition) - inputValue) < minProfit) { // profit is lower than peviours min profit
                minProfit = skin.getValue(condition) - inputValue;
            }
        }
        System.out.println("\n===========================================================");
        System.out.println("\nAverage output value = ~$" + avgOutputValue);
        System.out.println("\nMax profit = " + maxProfit);
        System.out.println("Min profit = " + minProfit);
        avgProfit = avgOutputValue - inputValue;
        System.out.println("Average profit = " + avgProfit);
        System.out.println("\nChance for profit = " + (chanceForProfit * 100) + "%");
    }

    /**
     * Calculates the probabilty of each of the possible output skins occurring
     *
     * @param skin the output skin to get the probability for
     * @return the probabilty of the given output skin occuring as a decimal
     */
    private float CalculateProbability(Skin skin) {
        return (inputCollections.get(skin.getCollection()) / 10.0f) / outputCollections.get(skin.getCollection());
    }

    /**
     * Asserts that the skins provided are valid as inputs
     *
     * @param skins the array of input skins
     * @throws IncorrectInputNumberException if the number of input skins != 10
     * @throws MixedGradeException           if the input skins are not all of the same grade
     */
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

    /**
     * returns the average float of the input skins
     *
     * @param skins the array of input skins
     * @return the average float of the input skins
     */
    private float calculateAvgFloat(InputSkin[] skins) {
        float avg = 0;
        for (InputSkin skin : skins) {
            avg += skin.getFloatValue();
        }
        return avg / 10;
    }

    /**
     * Updates the inputCollections HashMap with the collections given in the inputs and the number of occurences of each
     *
     * @param skins the array of input skins
     */
    private void updateInputCollections(InputSkin[] skins) {
        for (InputSkin skin : skins) {
            if (inputCollections.containsKey(skin.getCollection())) {
                inputCollections.put(skin.getCollection(), inputCollections.get(skin.getCollection()) + 1);
            } else {
                inputCollections.put(skin.getCollection(), 1);
            }
        }
    }

    /**
     * Updates the output skins ArrayList with all the possible outcomes which match the collections of the inputs and the output grade
     *
     * @param skins the array of input skins
     */
    private void calculateOutputSkins(InputSkin[] skins) {
        for (Map.Entry<String, SkinDBItem> skin : TradeupBuddy.getSkinDB().entrySet()) {
            if (inputCollections.containsKey(skin.getValue().getCollection()) && skin.getValue().getGrade() == outputGrade) {
                if (!outputSkins.contains(skin)) {
                    outputSkins.add(skin.getValue());
                }
            }
        }
    }

    /**
     * Updates the outputCollections HashMap with the collections of the output skins and the number of output skins in each
     */
    private void countOutputSkinCollections() {
        for (Skin skin : outputSkins) {
            if (outputCollections.containsKey(skin.getCollection())) {
                outputCollections.put(skin.getCollection(), outputCollections.get(skin.getCollection()) + 1);
            } else {
                outputCollections.put(skin.getCollection(), 1);
            }
        }
    }

    /**
     * Retursn the average profit as calculated by the TradeupCalculator
     *
     * @return the average profit
     */
    public float getAvgProfit() {
        return avgProfit;
    }
}
