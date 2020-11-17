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

    public static ArrayList<Integer> selectCellsThatWillHaveGold(
        int numOfRows,
        int numOfColumns,
        int totalNumOfCells,
        int numOfCellsThatWillHaveGold
    ) {
        int indexThatPlayerAStarts = 0;
        int indexThatPlayerBStarts = numOfColumns - 1;
        int indexThatPlayerCStarts = (numOfRows - 1) * numOfColumns;
        int indexThatPlayerDStarts = (numOfRows * numOfColumns) - 1;

        ArrayList<Integer> indicesOfCellsThatHaveGold = new ArrayList<>();
        for (int i = 0; i < numOfCellsThatWillHaveGold; i++) {
            int randomIndex = 0;
            boolean isIndexValid = false;
            while (!isIndexValid) {
                randomIndex = random.nextInt(totalNumOfCells);
                boolean indexAlreadyGenerated = indicesOfCellsThatHaveGold.contains(randomIndex);
                isIndexValid = !indexAlreadyGenerated
                        && randomIndex != indexThatPlayerAStarts
                        && randomIndex != indexThatPlayerBStarts
                        && randomIndex != indexThatPlayerCStarts
                        && randomIndex != indexThatPlayerDStarts;
            }
            indicesOfCellsThatHaveGold.add(randomIndex);
        }
        return indicesOfCellsThatHaveGold;
    }

    public static ArrayList<Integer> selectCellsThatWillHaveSecretGold(
        int numOfRows,
        int numOfColumns,
        ArrayList<Integer> indicesOfCellsThatWillHaveGold,
        int numOfCellsThatWillHaveSecretGold
    ) {
        int indexThatPlayerAStarts = 0;
        int indexThatPlayerBStarts = numOfColumns;
        int indexThatPlayerCStarts = (numOfRows - 1) * numOfColumns;
        int indexThatPlayerDStarts = numOfRows * numOfColumns;

        ArrayList<Integer> indicesOfCellsThatWillHaveSecretGold = new ArrayList<>();
        for (int i = 0; i < numOfCellsThatWillHaveSecretGold; i++) {
            int randomIndex = 0;
            boolean isIndexValid = false;
            while (!isIndexValid) {
                randomIndex = random.nextInt(indicesOfCellsThatWillHaveGold.size());
                boolean indexAlreadyGenerated = indicesOfCellsThatWillHaveSecretGold.contains(randomIndex);
                isIndexValid = !indexAlreadyGenerated
                                && randomIndex != indexThatPlayerAStarts
                                && randomIndex != indexThatPlayerBStarts
                                && randomIndex != indexThatPlayerCStarts
                                && randomIndex != indexThatPlayerDStarts;
            }
            indicesOfCellsThatWillHaveSecretGold.add(indicesOfCellsThatWillHaveGold.get(randomIndex));
        }
        return indicesOfCellsThatWillHaveGold;
    }
}
