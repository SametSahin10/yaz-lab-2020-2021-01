package com.company.user_interface;

import com.company.models.Player;
import com.company.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board {
    private final int numOfRows;
    private final int numOfColumns;
    private final double percentageOfCellsThatWillHaveGold;
    private final double percentageOfCellsThatWillHaveSecretGold;
    private final int totalAmountOfGoldEachUserWillHave;
    private final int numOfCellsPlayersMoveEachTurn;

    public Board(
            int numOfRows,
            int numOfColumns,
            double percentageOfCellsThatWillHaveGold,
            double percentageOfCellsThatWillHaveSecretGold,
            int totalAmountOfGoldEachUserWillHave,
            int numOfCellsPlayersMoveEachTurn
    ) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.percentageOfCellsThatWillHaveGold = percentageOfCellsThatWillHaveGold;
        this.percentageOfCellsThatWillHaveSecretGold = percentageOfCellsThatWillHaveSecretGold;
        this.totalAmountOfGoldEachUserWillHave = totalAmountOfGoldEachUserWillHave;
        this.numOfCellsPlayersMoveEachTurn = numOfCellsPlayersMoveEachTurn;
        initializeGUI();
    }

    private JPanel boardPanel;
    private Cell[][] cells;

    final ImageIcon aLetterIcon = new ImageIcon(getClass().getResource("../assets/a_letter_icon.png"));
    final ImageIcon bLetterIcon = new ImageIcon(getClass().getResource("../assets/b_letter_icon.png"));
    final ImageIcon cLetterIcon = new ImageIcon(getClass().getResource("../assets/c_letter_icon.png"));
    final ImageIcon dLetterIcon = new ImageIcon(getClass().getResource("../assets/d_letter_icon.png"));
    final ImageIcon goldIcon = new ImageIcon(getClass().getResource("../assets/gold_icon.png"));
    final ImageIcon chestIcon = new ImageIcon(getClass().getResource("../assets/chest_icon.png"));

    private int numOfPlayersInTheGame = 4;

    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

    private boolean playerAselectedATarget = false;
    private boolean playerBselectedATarget = false;
    private boolean playerCSelectedATarget = false;
    private boolean playerDSelectedATarget = false;

    Timer timerToSelectTargetCellForA;
    Timer timerToSelectTargetCellForB;
    Timer timerToSelectTargetCellForC;
    Timer timerToSelectTargetCellForD;

    Timer timerToMoveToTargetCellForA;
    Timer timerToMoveToTargetCellForB;
    Timer timerToMoveToTargetCellForC;
    Timer timerToMoveToTargetCellForD;

    public void initializeGUI() {
        setupBoard();

        Cell targetCellForPlayerA = cells[playerA.getCurrentCell().getIndexOfRow() + 3][playerA.getCurrentCell().getIndexOfColumn() + 5];
        Cell targetCellForPlayerB = cells[playerB.getCurrentCell().getIndexOfRow() + 5][playerB.getCurrentCell().getIndexOfColumn()];
        Cell targetCellForPlayerC = cells[playerC.getCurrentCell().getIndexOfRow() - 2][playerC.getCurrentCell().getIndexOfColumn() + 2];
        Cell targetCellForPlayerD = cells[playerD.getCurrentCell().getIndexOfRow() - 3][playerD.getCurrentCell().getIndexOfColumn() - 3];

        timerToSelectTargetCellForA = new Timer(3000, e -> {
            playerA.setTargetCell(targetCellForPlayerA);
            targetCellForPlayerA.setText("Targeted by A");
            timerToMoveToTargetCellForA.start();
        });

        timerToMoveToTargetCellForA = new Timer(3000, e -> {
            playerA.move(targetCellForPlayerA);
            playerA.decreaseTheAmountOfGold(20);
            System.out.println("Amount of gold player A has: " + playerA.getTotalAmountOfGold());
            if (playerA.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player A");
                endGameForPlayer(playerA);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                return;
            }
            timerToSelectTargetCellForB.start();
        });

        timerToSelectTargetCellForB = new Timer(3000, e -> {
            playerB.setTargetCell(targetCellForPlayerB);
            targetCellForPlayerB.setText("Targeted by B");
            timerToMoveToTargetCellForB.start();
        });

        timerToMoveToTargetCellForB = new Timer(3000, e -> {
            playerB.move(targetCellForPlayerB);
            playerB.decreaseTheAmountOfGold(20);
            System.out.println("Amount of gold player B has: " + playerA.getTotalAmountOfGold());
            if (playerB.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player B");
                endGameForPlayer(playerB);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                return;
            }
            timerToSelectTargetCellForC.start();
        });

        timerToSelectTargetCellForC = new Timer(3000, e -> {
            playerC.setTargetCell(targetCellForPlayerC);
            targetCellForPlayerC.setText("Targeted by C");
            timerToMoveToTargetCellForC.start();
        });

        timerToMoveToTargetCellForC = new Timer(3000, e -> {
            playerC.move(targetCellForPlayerC);
            playerC.decreaseTheAmountOfGold(20);
            System.out.println("Amount of gold player C has: " + playerA.getTotalAmountOfGold());
            if (playerC.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player C");
                endGameForPlayer(playerC);
            }

            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                return;
            }
            timerToSelectTargetCellForD.start();
        });

        timerToSelectTargetCellForD = new Timer(3000, e -> {
            playerD.setTargetCell(targetCellForPlayerD);
            targetCellForPlayerD.setText("Targeted by D");
            timerToMoveToTargetCellForD.start();
        });

        timerToMoveToTargetCellForD = new Timer(3000, e -> {
            playerD.move(targetCellForPlayerD);
            playerD.decreaseTheAmountOfGold(20);
            System.out.println("Amount of gold player D has: " + playerA.getTotalAmountOfGold());
            if (playerD.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player D");
                endGameForPlayer(playerD);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                return;
            }
        });

        Timer timer = new Timer(2000, e -> timerToSelectTargetCellForA.start());
        timer.start();
    }

    private void setupBoard() {
        boardPanel = new JPanel(new GridLayout(numOfRows, numOfColumns));
        cells = new Cell[numOfRows][numOfColumns];

        int numOfCellsThatWillHaveGold = (int) (numOfRows * numOfColumns * percentageOfCellsThatWillHaveGold / 100);
        int numOfCellsThatWillHaveSecretGold = (int) (numOfCellsThatWillHaveGold * percentageOfCellsThatWillHaveSecretGold / 100);
        if (numOfCellsThatWillHaveSecretGold == 0) {
            // Number of cells that will have secret gold must be at least one.
            numOfCellsThatWillHaveSecretGold = 1;
        }

        System.out.println("number of cells that will have gold: " + numOfCellsThatWillHaveGold);
        System.out.println("number of cells that will have secret gold: " + numOfCellsThatWillHaveSecretGold);

        final ArrayList<Integer> indicesOfCellsThatWillHaveGold = Utils.selectCellsThatWillHaveGold(
                numOfRows,
                numOfColumns,
                numOfRows * numOfColumns,
                numOfCellsThatWillHaveGold
        );

        final ArrayList<Integer> indicesOfCellsThatWillHaveSecretGold = Utils.selectCellsThatWillHaveSecretGold(
                numOfRows, numOfColumns, indicesOfCellsThatWillHaveGold, numOfCellsThatWillHaveSecretGold
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
                    playerA = new Player(aLetterIcon, totalAmountOfGoldEachUserWillHave, cell, null);
                } else if (i == 0 && j == numOfColumns - 1) {
                    // Last cell of the first row
                    cell.setIcon(bLetterIcon);
                    playerB = new Player(bLetterIcon, totalAmountOfGoldEachUserWillHave, cell, null);
                } else if (i == numOfRows - 1 && j == 0) {
                    // First cell of the last row
                    cell.setIcon(cLetterIcon);
                    playerC = new Player(cLetterIcon, totalAmountOfGoldEachUserWillHave, cell, null);
                } else if (i == numOfRows - 1 && j == numOfColumns - 1) {
                    // Last cell
                    cell.setIcon(dLetterIcon);
                    playerD = new Player(dLetterIcon, totalAmountOfGoldEachUserWillHave, cell, null);
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
                                    indicesOfCellsThatWillHaveGold.removeIf(index -> index == currentIndex);
                                    indicesOfCellsThatWillHaveSecretGold.removeIf(index -> index == currentIndex);
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
                // Uncomment the two lines below to see how much gold the cell has.
//                cell.setText(String.valueOf(cell.getAmountOfGold()));
//                cell.setVerticalTextPosition(SwingConstants.BOTTOM);
                cells[i][j] = cell;
                boardPanel.add(cell);
            }
        }
    }

    private void endGameForPlayer(Player player) {
        Cell currentCell = player.getCurrentCell();
        currentCell.clear();
        numOfPlayersInTheGame--;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    private int getNumOfCellsThatHaveGold() {
        int numOfCellsThatHaveGold = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isHasGold() || cells[i][j].isHasSecretGold()) numOfCellsThatHaveGold++;
            }
        }
        System.out.println("The number of cells that have gold: " + numOfCellsThatHaveGold);
        return numOfCellsThatHaveGold;
    }
}
