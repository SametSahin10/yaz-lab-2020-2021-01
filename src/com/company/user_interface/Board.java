package com.company.user_interface;

import com.company.models.Player;
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
//    private final ArrayList<Cell> cells = new ArrayList<>();
    private Cell cells[][];

    final ImageIcon aLetterIcon = new ImageIcon(getClass().getResource("../assets/a_letter_icon.png"));
    final ImageIcon bLetterIcon = new ImageIcon(getClass().getResource("../assets/b_letter_icon.png"));
    final ImageIcon cLetterIcon = new ImageIcon(getClass().getResource("../assets/c_letter_icon.png"));
    final ImageIcon dLetterIcon = new ImageIcon(getClass().getResource("../assets/d_letter_icon.png"));
    final ImageIcon goldIcon = new ImageIcon(getClass().getResource("../assets/gold_icon.png"));
    final ImageIcon chestIcon = new ImageIcon(getClass().getResource("../assets/chest_icon.png"));

    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

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
        setupBoard();
    }

    private void setupBoard() {
        boardPanel = new JPanel(new GridLayout(numOfRows, numOfColumns));
        cells = new Cell[numOfRows][numOfColumns];

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
                        false
                );
                if (i == 0 && j == 0) {
                    // First cell
                    cell.setIcon(aLetterIcon);
                    playerA = new Player(200, cell, null);
                } else if (i == 0 && j == numOfColumns - 1) {
                    // Last cell of the first row
                    cell.setIcon(bLetterIcon);
                    playerB = new Player(200, cell, null);
                } else if (i == numOfRows - 1 && j == 0) {
                    // First cell of the last row
                    cell.setIcon(cLetterIcon);
                    playerC = new Player(200, cell, null);
                } else if (i == numOfRows - 1 && j == numOfColumns - 1) {
                    // Last cell
                    cell.setIcon(dLetterIcon);
                    playerD = new Player(200, cell, null);
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
                cells[i][j] = cell;
                boardPanel.add(cell);
            }
        }
    }

    public void movePlayerA() {
        int indexOfRow = playerA.getCurrentCell().getIndexOfRow();
        int indexOfColumn = playerA.getCurrentCell().getIndexOfColumn();
        Cell oldCell = cells[indexOfRow][indexOfColumn];
        Cell newCell = cells[indexOfRow + 1][indexOfColumn + 1];
        newCell.setIcon(aLetterIcon);
        playerA.setCurrentCell(newCell);
        clearOldCell(oldCell);
    }

    public void movePlayerB() {
        int indexOfRow = playerB.getCurrentCell().getIndexOfRow();
        int indexOfColumn = playerB.getCurrentCell().getIndexOfColumn();
        Cell oldCell = cells[indexOfRow][indexOfColumn];
        Cell newCell = cells[indexOfRow + 1][indexOfColumn - 1];
        newCell.setIcon(bLetterIcon);
        playerB.setCurrentCell(newCell);
        clearOldCell(oldCell);
    }

    public void movePlayerC() {
        int indexOfRow = playerC.getCurrentCell().getIndexOfRow();
        int indexOfColumn = playerC.getCurrentCell().getIndexOfColumn();
        Cell oldCell = cells[indexOfRow][indexOfColumn];
        Cell newCell = cells[indexOfRow - 1][indexOfColumn + 1];
        newCell.setIcon(bLetterIcon);
        playerC.setCurrentCell(newCell);
        clearOldCell(oldCell);
    }

    public void movePlayerD() {
        int indexOfRow = playerD.getCurrentCell().getIndexOfRow();
        int indexOfColumn = playerD.getCurrentCell().getIndexOfColumn();
        Cell oldCell = cells[indexOfRow][indexOfColumn];
        Cell newCell = cells[indexOfRow - 1][indexOfColumn - 1];
        newCell.setIcon(bLetterIcon);
        playerD.setCurrentCell(newCell);
        clearOldCell(oldCell);
    }

    private void clearOldCell(Cell oldCell) {
        oldCell.setIcon(null);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
