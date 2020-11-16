package com.company.user_interface;

import javax.swing.*;

public class Cell extends JLabel {
    private int amountOfGold;
    private int amountOfSecretGold;
    private boolean hasSecretGold;
    private boolean showSecretGold;
    private Cell targetCell;

    public Cell(
        int amountOfGold, int amountOfSecretGold, boolean hasSecretGold, boolean showSecretGold, Cell targetCell
    ) {
        this.amountOfGold = amountOfGold;
        this.amountOfSecretGold = amountOfSecretGold;
        this.hasSecretGold = hasSecretGold;
        this.showSecretGold = showSecretGold;
        this.targetCell = targetCell;
    }
}
