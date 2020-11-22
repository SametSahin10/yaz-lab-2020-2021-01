package com.company.user_interface;

import javax.swing.*;

public class Cell extends JLabel {
    private final int indexOfRow;
    private final int indexOfColumn;
    private boolean hasGold;
    private boolean hasSecretGold;
    private int amountOfGold;
    private int amountOfSecretGold;
    private boolean secretGoldVisible;

    public Cell(
        int indexOfRow,
        int indexOfColumn,
        boolean hasGold,
        boolean hasSecretGold,
        int amountOfGold,
        int amountOfSecretGold,
        boolean showSecretGold
    ) {
        this.indexOfRow = indexOfRow;
        this.indexOfColumn = indexOfColumn;
        this.hasGold = hasGold;
        this.hasSecretGold = hasSecretGold;
        this.amountOfGold = amountOfGold;
        this.amountOfSecretGold = amountOfSecretGold;
        this.secretGoldVisible = showSecretGold;
    }

    public int getIndexOfRow() {
        return indexOfRow;
    }

    public int getIndexOfColumn() {
        return indexOfColumn;
    }

    public boolean isHasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public boolean isHasSecretGold() {
        return hasSecretGold;
    }

    public void setHasSecretGold(boolean hasSecretGold) {
        this.hasSecretGold = hasSecretGold;
    }

    public int getAmountOfGold() {
        return amountOfGold;
    }

    public void setAmountOfGold(int amountOfGold) {
        this.amountOfGold = amountOfGold;
    }

    public int getAmountOfSecretGold() {
        return amountOfSecretGold;
    }

    public void setAmountOfSecretGold(int amountOfSecretGold) {
        this.amountOfSecretGold = amountOfSecretGold;
    }

    public boolean isSecretGoldVisible() {
        return secretGoldVisible;
    }

    public void setSecretGoldVisible(boolean secretGoldVisible) {
        this.secretGoldVisible = secretGoldVisible;
    }

    public void clearIcon() {
        setIcon(null);
    }
}
