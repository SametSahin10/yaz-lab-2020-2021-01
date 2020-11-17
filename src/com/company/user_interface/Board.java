package com.company.user_interface;

import com.company.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final int numOfRows;
    private final int numOfColumns;
    private final double percentageOfCellsThatWillHaveGold;
    private final double percentageOfCellsThatWillHaveSecretGold;

    private JPanel boardPanel;
    private final ArrayList<JLabel> cells = new ArrayList<>();

    public Board(
        int numOfRows,
        int numOfColumns,
        double percentageOfCellsThatWillHaveGold,
        double percentageOfCellsThatWillHaveSecretGold
    ) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.percentageOfCellsThatWillHaveGold = percentageOfCellsThatWillHaveGold;
        this.percentageOfCellsThatWillHaveSecretGold = percentageOfCellsThatWillHaveSecretGold;
        initializeGUI();
    }

    public void initializeGUI() {
        boardPanel = new JPanel(new GridLayout(numOfRows, numOfColumns));

        ImageIcon aLetterIcon = new ImageIcon(getClass().getResource("../assets/a_letter_icon.png"));
        ImageIcon bLetterIcon = new ImageIcon(getClass().getResource("../assets/b_letter_icon.png"));
        ImageIcon cLetterIcon = new ImageIcon(getClass().getResource("../assets/c_letter_icon.png"));
        ImageIcon dLetterIcon = new ImageIcon(getClass().getResource("../assets/d_letter_icon.png"));
        ImageIcon goldIcon = new ImageIcon(getClass().getResource("../assets/gold_icon.png"));
        ImageIcon chestIcon = new ImageIcon(getClass().getResource("../assets/chest_icon.png"));

        int numOfCellsThatWillHaveGold = (int) (numOfRows * numOfColumns * percentageOfCellsThatWillHaveGold / 100);
        int numOfCellsThatWillHaveSecretGold = (int) (numOfCellsThatWillHaveGold * percentageOfCellsThatWillHaveSecretGold / 100);

        System.out.println("number of cells that will have gold: " + numOfCellsThatWillHaveGold);
        System.out.println("number of cells that will have secret gold: " + numOfCellsThatWillHaveSecretGold);

        final ArrayList<Integer> indicesOfCellsThatWillHaveGold = Utils.selectCellsThatWillHaveGold(
            numOfRows * numOfColumns,
            numOfCellsThatWillHaveGold
        );

        final ArrayList<Integer> indicesOfCellsThatWillHaveSecretGold = Utils.selectCellsThatWillHaveSecretGold(
            indicesOfCellsThatWillHaveGold, numOfCellsThatWillHaveSecretGold
        );

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                // currentIndex tells the index of the cell we're currently adding in.
                int currentIndex = i * numOfColumns + j;
                Cell cell = new Cell(
                    i,
                    j,
                    false,
                    false,
                    0,
                    0,
                    false,
                    null
                );
                if (i == 0 && j == 0) {
                    // First cell
                    cell.setIcon(aLetterIcon);
                } else if (i == 0 && j == numOfColumns - 1) {
                    // Last cell of the first row
                    cell.setIcon(bLetterIcon);
                } else if (i == numOfRows - 1 && j == 0) {
                    // First cell of the last row
                    cell.setIcon(cLetterIcon);
                } else if (i == numOfRows - 1 && j == numOfColumns - 1) {
                    // Last cell
                    cell.setIcon(dLetterIcon);
                } else {
                    // A cell that is not a side.

                    // Do not add gold into the cell
                    // if all of the golds have already been added.
                    if (numOfCellsThatWillHaveGold != 0) {
                        boolean cellWillHaveGold = indicesOfCellsThatWillHaveGold.contains(currentIndex);
                        if (cellWillHaveGold) {
                            cell.setHasGold(true);
                            cell.setIcon(goldIcon);
                            int amountOfGold = Utils.generateRandomAmountOfGold();
                            cell.setAmountOfGold(amountOfGold);
                            numOfCellsThatWillHaveGold--;

                            // Do not add secret gold into the cell
                            // if all of the secret golds have already been added.
                            if (numOfCellsThatWillHaveSecretGold != 0) {
                                boolean cellWillHaveSecretGold = indicesOfCellsThatWillHaveSecretGold.contains(
                                    currentIndex
                                );
                                if (cellWillHaveSecretGold) {
                                    cell.setHasSecretGold(true);
                                    int amountOfSecretGold = Utils.generateRandomAmountOfGold();
                                    cell.setAmountOfSecretGold(amountOfSecretGold);
                                    numOfCellsThatWillHaveSecretGold--;
                                }
                            }
                        } else {
                            cell.setHasGold(false);
                        }
                    }
                }
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setVerticalAlignment(SwingConstants.CENTER);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells.add(cell);
                boardPanel.add(cell);
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
