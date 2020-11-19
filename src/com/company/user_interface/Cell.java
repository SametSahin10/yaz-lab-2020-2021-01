package com.company.user_interface;

import com.company.utils.Icons;
import com.company.utils.PlayerType;

import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    private int indexOfRow;
    private int indexOfColumn;
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

    @Override
    public Font getFont() {
//        InputStream inputStream = Board.class.getResourceAsStream("../assets/fonts/SourceSansPro-Light.ttf");
//        try {
//            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
//            font = font.deriveFont(8f);
//            return font;
//        } catch (FontFormatException | IOException exception) {
//            exception.printStackTrace();
//            return super.getFont();
//        }
        // TODO: Use the code above when using the font somewhere else in the app.
        return super.getFont();
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

    public boolean isSecretGoldVisible() {
        return secretGoldVisible;
    }

    public void setSecretGoldVisible(boolean secretGoldVisible) {
        this.secretGoldVisible = secretGoldVisible;
    }

    public void clearText(PlayerType playerType) {
        String text = getText();
        if (text.isEmpty()) return;
        String newText = "";
        switch (playerType) {
            case A:
                newText = text.replace("A", "");
                setText(newText);
                break;
            case B:
                newText = text.replace("B", "");
                setText(newText);
                break;
            case C:
                newText = text.replace("C", "");
                setText(newText);
                break;
            case D:
                newText = text.replace("D", "");
                setText(newText);
                break;
            default:
                setText(newText);
        }
    }

    public void clearIcon() {
        setIcon(null);
    }
}
