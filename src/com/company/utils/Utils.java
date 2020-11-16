package com.company.utils;

import java.util.Random;

public class Utils {
    private static final int possibleAmountsOfGold[] = new int[] {5, 10, 15, 20};

    private static int generateRandomAmountOfGold() {
        Random random = new Random(possibleAmountsOfGold.length);
        int randomIndex = random.nextInt();
        return possibleAmountsOfGold[randomIndex];
    }
}
