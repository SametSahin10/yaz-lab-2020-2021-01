package com.company.utils;

import java.util.ArrayList;
import java.util.Random;

public class Utils {
    private static final int possibleAmountsOfGold[] = new int[] {5, 10, 15, 20};
    private static final Random random = new Random();

    public static int generateRandomAmountOfGold() {
        int randomIndex = random.nextInt(possibleAmountsOfGold.length);
        return possibleAmountsOfGold[randomIndex];
    }

    public static int generateOneOrZero() {
       return random.nextInt(2);
    }

    public static ArrayList<Integer> selectCellsThatWillHaveGold(int totalNumOfCells, int numOfCellsThatWillHaveGold) {
        // TODO: Do not add the same cell twice
        ArrayList<Integer> indicesOfCellsThatHaveGold = new ArrayList<>();
        for (int i = 0; i < numOfCellsThatWillHaveGold; i++) {
            int randomIndex = random.nextInt(totalNumOfCells);
            indicesOfCellsThatHaveGold.add(randomIndex);
        }
        return indicesOfCellsThatHaveGold;
    }

    public static ArrayList<Integer> selectCellsThatWillHaveSecretGold(
        ArrayList<Integer> indicesOfCellsThatWillHaveGold, int numOfCellsThatWillHaveSecretGold
    ) {
        // TODO: Do not add the same cell twice
        ArrayList<Integer> indicesOfCellsThatWillHaveSecretGold = new ArrayList<>();
        for (int i = 0; i < numOfCellsThatWillHaveSecretGold; i++) {
            int randomIndex = random.nextInt(indicesOfCellsThatWillHaveGold.size());
            indicesOfCellsThatWillHaveSecretGold.add(indicesOfCellsThatWillHaveGold.get(randomIndex));
        }
        return indicesOfCellsThatWillHaveGold;
    }
}
