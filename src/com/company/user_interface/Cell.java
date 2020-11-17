package com.company.user_interface;

import javax.swing.*;

public class Cell extends JLabel {
    private int indexOfRow;
    private int indexOfColumn;
    private boolean hasGold;
    private boolean hasSecretGold;
    private int amountOfGold;
    private int amountOfSecretGold;
    private boolean showSecretGold;

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
        this.showSecretGold = showSecretGold;
    }

    public int getIndexOfRow() {
        return indexOfRow;
    }

    public void setIndexOfRow(int indexOfRow) {
        this.indexOfRow = indexOfRow;
    }

    public int getIndexOfColumn() {
        return indexOfColumn;
    }

    public void setIndexOfColumn(int indexOfColumn) {
        this.indexOfColumn = indexOfColumn;
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

    public boolean isShowSecretGold() {
        return showSecretGold;
    }

    public void setShowSecretGold(boolean showSecretGold) {
        this.showSecretGold = showSecretGold;
    }

    public void clear() {
        setIcon(null);
//        oldCell.setText("0");
    }
}
