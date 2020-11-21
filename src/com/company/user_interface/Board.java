package com.company.user_interface;

import com.company.models.Player;
import com.company.utils.Icons;
import com.company.utils.PlayerType;
import com.company.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    // Use the Frame instance below to set the GameOverScreen visible.
    private Frame frame;
    private final int numOfRows;
    private final int numOfColumns;
    private final double percentageOfCellsThatWillHaveGold;
    private final double percentageOfCellsThatWillHaveSecretGold;
    private final int totalAmountOfGoldEachUserWillHave;
    private final int numOfStepsToTakeOnEachMove;
    private final int costOfEachMoveForPlayerA;
    private final int costOfEachMoveForPlayerB;
    private final int costOfEachMoveForPlayerC;
    private final int costOfEachMoveForPlayerD;
    private final int costOfSettingTargetForPlayerA;
    private final int costOfSettingTargetForPlayerB;
    private final int costOfSettingTargetForPlayerC;
    private final int costOfSettingTargetForPlayerD;

    public Board(
        Frame frame,
        int numOfRows,
        int numOfColumns,
        double percentageOfCellsThatWillHaveGold,
        double percentageOfCellsThatWillHaveSecretGold,
        int totalAmountOfGoldEachUserWillHave,
        int numOfStepsToTakeOnEachMove,
        int costOfEachMoveForPlayerA,
        int costOfEachMoveForPlayerB,
        int costOfEachMoveForPlayerC,
        int costOfEachMoveForPlayerD,
        int costOfSettingTargetForPlayerA,
        int costOfSettingTargetForPlayerB,
        int costOfSettingTargetForPlayerC,
        int costOfSettingTargetForPlayerD
    ) {
        this.frame = frame;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.percentageOfCellsThatWillHaveGold = percentageOfCellsThatWillHaveGold;
        this.percentageOfCellsThatWillHaveSecretGold = percentageOfCellsThatWillHaveSecretGold;
        this.totalAmountOfGoldEachUserWillHave = totalAmountOfGoldEachUserWillHave;
        this.numOfStepsToTakeOnEachMove = numOfStepsToTakeOnEachMove;
        this.costOfEachMoveForPlayerA = costOfEachMoveForPlayerA;
        this.costOfEachMoveForPlayerB = costOfEachMoveForPlayerB;
        this.costOfEachMoveForPlayerC = costOfEachMoveForPlayerC;
        this.costOfEachMoveForPlayerD = costOfEachMoveForPlayerD;
        this.costOfSettingTargetForPlayerA = costOfSettingTargetForPlayerA;
        this.costOfSettingTargetForPlayerB = costOfSettingTargetForPlayerB;
        this.costOfSettingTargetForPlayerC = costOfSettingTargetForPlayerC;
        this.costOfSettingTargetForPlayerD = costOfSettingTargetForPlayerD;
        initializeGUI();
    }

    private JPanel boardPanel;
    private Cell[][] cells;
    private ArrayList<Integer> indicesOfCellsThatWillHaveGold;
    // The ArrayList below will get the values of the ArrayList above
    // after the assignment.
    // But the ArrayList below won't point to the ArrayList above.
    // They only will have the same values at first.
    private ArrayList<Integer> indicesOfCellsThatHaveGold;

    private int numOfPlayersInTheGame = 4;

    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

    private final boolean playerAselectedATarget = false;
    private final boolean playerBselectedATarget = false;
    private final boolean playerCSelectedATarget = false;
    private final boolean playerDSelectedATarget = false;

    private Timer timerToSelectTargetCellForA;
    private Timer timerToSelectTargetCellForB;
    private Timer timerToSelectTargetCellForC;
    private Timer timerToSelectTargetCellForD;

    private Timer timerToMoveToTargetCellForA;
    private Timer timerToMoveToTargetCellForB;
    private Timer timerToMoveToTargetCellForC;
    private Timer timerToMoveToTargetCellForD;

    public void initializeGUI() {
        setupBoard();
        setupGameLoop();
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

        indicesOfCellsThatWillHaveGold = Utils.selectCellsThatWillHaveGold(
                numOfRows,
                numOfColumns,
                numOfRows * numOfColumns,
                numOfCellsThatWillHaveGold
        );
        indicesOfCellsThatHaveGold = (ArrayList<Integer>) indicesOfCellsThatWillHaveGold.clone();

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
                    cell.setIcon(Icons.aLetterIcon);
                    playerA = new Player(
                        PlayerType.A,
                        costOfEachMoveForPlayerA,
                        costOfSettingTargetForPlayerA,
                        numOfStepsToTakeOnEachMove,
                        totalAmountOfGoldEachUserWillHave,
                        cell,
                        null
                    );
                } else if (i == 0 && j == numOfColumns - 1) {
                    // Last cell of the first row
                    cell.setIcon(Icons.bLetterIcon);
                    playerB = new Player(
                        PlayerType.B,
                        costOfEachMoveForPlayerB,
                        costOfSettingTargetForPlayerB,
                        numOfStepsToTakeOnEachMove,
                        totalAmountOfGoldEachUserWillHave,
                        cell,
                        null
                    );
                } else if (i == numOfRows - 1 && j == 0) {
                    // First cell of the last row
                    cell.setIcon(Icons.cLetterIcon);
                    playerC = new Player(
                        PlayerType.C,
                        costOfEachMoveForPlayerC,
                        costOfSettingTargetForPlayerC,
                        numOfStepsToTakeOnEachMove,
                        totalAmountOfGoldEachUserWillHave,
                        cell,
                        null
                    );
                } else if (i == numOfRows - 1 && j == numOfColumns - 1) {
                    // Last cell
                    cell.setIcon(Icons.dLetterIcon);
                    playerD = new Player(
                        PlayerType.D,
                        costOfEachMoveForPlayerD,
                        costOfSettingTargetForPlayerD,
                        numOfStepsToTakeOnEachMove,
                        totalAmountOfGoldEachUserWillHave,
                        cell,
                        null
                    );
                } else {
                    // A cell that is not a side.

                    // Do not add gold into the cell
                    // if all of the golds have already been added.
                    if (numOfCellsThatWillHaveGold != 0) {
                        boolean cellWillHaveGold = indicesOfCellsThatWillHaveGold.contains(currentIndex);
                        if (cellWillHaveGold) {
                            cell.setHasGold(true);
                            cell.setIcon(Icons.goldIcon);
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

    private void setupGameLoop() {
        timerToSelectTargetCellForA = new Timer(3000, e -> {
            if (playerA.getTargetCell() != null) {
                boolean targetCellHasGold = playerA.getTargetCell().isHasGold();
                if (targetCellHasGold) {
                    // Player A has already a target cell that has gold.
                    // Start moving to target.
                    timerToMoveToTargetCellForA.start();
                    return;
                }
            }

            int randomIndex = Utils.selectIndexOfTargetCellRandomly(
                    numOfRows, numOfColumns, indicesOfCellsThatHaveGold
            );
            int indexOfRow = randomIndex / numOfColumns;
            int indexOfColumn = randomIndex % numOfColumns;

            Cell randomCell = cells[indexOfRow][indexOfColumn];

            playerA.setTargetCell(randomCell);
            String randomCellText = randomCell.getText();
            if (randomCellText.isEmpty()) {
                randomCell.setText("A");
            } else {
                randomCell.setText(randomCellText + " " + "A");
            }
            timerToMoveToTargetCellForA.start();
        });

        timerToMoveToTargetCellForA = new Timer(3000, e -> {
            System.out.println("Player A is moving");
            playerA.moveToTargetCell();
            if (playerA.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player A");
                endGameForPlayer(playerA);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                endGameForEveryone();
                return;
            }

            for (int i = 0; i < cells[0].length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == null) {
                        System.out.println("The cell " + i + ", " + j + " is null");
                    }
                }
            }

            timerToSelectTargetCellForB.start();
        });

        timerToSelectTargetCellForB = new Timer(3000, e -> {
            if (playerB.getTargetCell() != null) {
                boolean targetCellHasGold = playerB.getTargetCell().isHasGold();
                if (targetCellHasGold) {
                    // Player B has already a target that has gold.
                    // Start moving to target.
                    timerToMoveToTargetCellForB.start();
                    return;
                }
            }

            int randomIndex = Utils.selectIndexOfTargetCellRandomly(
                    numOfRows, numOfColumns, indicesOfCellsThatHaveGold
            );
            int indexOfRow = randomIndex / numOfColumns;
            int indexOfColumn = randomIndex % numOfColumns;

            Cell randomCell = cells[indexOfRow][indexOfColumn];

            playerB.setTargetCell(randomCell);
            String randomCellText = randomCell.getText();
            if (randomCellText.isEmpty()) {
                randomCell.setText("B");
            } else {
                randomCell.setText(randomCellText + " " + "A");
            }
            timerToMoveToTargetCellForB.start();
        });

        timerToMoveToTargetCellForB = new Timer(3000, e -> {
            System.out.println("Player B is moving");
            playerB.moveToTargetCell();
            if (playerB.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player B");
                endGameForPlayer(playerB);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                endGameForEveryone();
                return;
            }

            for (int i = 0; i < cells[0].length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == null) {
                        System.out.println("The cell " + i + ", " + j + " is null");
                    }
                }
            }

            timerToSelectTargetCellForC.start();
        });

        timerToSelectTargetCellForC = new Timer(3000, e -> {
            if (playerC.getTargetCell() != null) {
                boolean targetCellHasGold = playerC.getTargetCell().isHasGold();
                if (targetCellHasGold) {
                    // Player C has already a target that has gold.
                    // Start moving to target.
                    timerToMoveToTargetCellForC.start();
                    return;
                }
            }

            int randomIndex = Utils.selectIndexOfTargetCellRandomly(
                    numOfRows, numOfColumns, indicesOfCellsThatHaveGold
            );
            int indexOfRow = randomIndex / numOfColumns;
            int indexOfColumn = randomIndex % numOfColumns;

            Cell randomCell = cells[indexOfRow][indexOfColumn];

            playerC.setTargetCell(randomCell);
            String randomCellText = randomCell.getText();
            if (randomCellText.isEmpty()) {
                randomCell.setText("C");
            } else {
                randomCell.setText(randomCellText + " " + "A");
            }
            timerToMoveToTargetCellForC.start();
        });

        timerToMoveToTargetCellForC = new Timer(3000, e -> {
            System.out.println("Player C is moving");
            playerC.moveToTargetCell();
            System.out.println("Amount of gold player C has: " + playerA.getTotalAmountOfGold());
            if (playerC.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player C");
                endGameForPlayer(playerC);
            }

            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                endGameForEveryone();
                return;
            }

            for (int i = 0; i < cells[0].length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == null) {
                        System.out.println("The cell " + i + ", " + j + " is null");
                    }
                }
            }

            timerToSelectTargetCellForD.start();
        });

        timerToSelectTargetCellForD = new Timer(3000, e -> {
            if (playerD.getTargetCell() != null) {
                boolean targetCellHasGold = playerD.getTargetCell().isHasGold();
                if (targetCellHasGold) {
                    // Player D has already a target that has gold.
                    // Start moving to target.
                    timerToMoveToTargetCellForD.start();
                    return;
                }
            }

            int randomIndex = Utils.selectIndexOfTargetCellRandomly(
                    numOfRows, numOfColumns, indicesOfCellsThatHaveGold
            );
            int indexOfRow = randomIndex / numOfColumns;
            int indexOfColumn = randomIndex % numOfColumns;

            Cell randomCell = cells[indexOfRow][indexOfColumn];

            playerD.setTargetCell(randomCell);
            String randomCellText = randomCell.getText();
            if (randomCellText.isEmpty()) {
                randomCell.setText("D");
            } else {
                randomCell.setText(randomCellText + " " + "A");
            }

            timerToMoveToTargetCellForD.start();
        });

        timerToMoveToTargetCellForD = new Timer(3000, e -> {
            System.out.println("Player D is moving");
            playerD.moveToTargetCell();
            if (playerD.getTotalAmountOfGold() <= 0) {
                System.out.println("Ending the game for player D");
                endGameForPlayer(playerD);
            }
            int numOfCellsThatHaveGold = getNumOfCellsThatHaveGold();
            if (numOfPlayersInTheGame == 0 || numOfCellsThatHaveGold == 0) {
                System.out.println("Game over");
                endGameForEveryone();
                return;
            }

            for (int i = 0; i < cells[0].length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == null) {
                        System.out.println("The cell " + i + ", " + j + " is null");
                    }
                }
            }

            // Start the loop again.
            timerToSelectTargetCellForA.start();
        });

        timerToSelectTargetCellForA.setRepeats(false);
        timerToSelectTargetCellForB.setRepeats(false);
        timerToSelectTargetCellForC.setRepeats(false);
        timerToSelectTargetCellForD.setRepeats(false);

        timerToMoveToTargetCellForA.setRepeats(false);
        timerToMoveToTargetCellForB.setRepeats(false);
        timerToMoveToTargetCellForC.setRepeats(false);
        timerToMoveToTargetCellForD.setRepeats(false);

        Timer timer = new Timer(1000, e -> timerToSelectTargetCellForA.start());
        timer.setRepeats(false);
        timer.start();
    }

    private void endGameForPlayer(Player player) {
        Cell currentCell = player.getCurrentCell();
        currentCell.clearText(player.getPlayerType());
        numOfPlayersInTheGame--;
    }

    private void endGameForEveryone() {
        GameOverScreen gameOverScreen = new GameOverScreen(playerA, playerB, playerC, playerD);
        frame.remove(boardPanel);
        frame.add(gameOverScreen.getGameOverPanel());
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
