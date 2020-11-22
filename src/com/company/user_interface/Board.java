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

    private Timer timerToMoveToTargetCellForA;
    private Timer timerToMoveToTargetCellForB;
    private Timer timerToMoveToTargetCellForC;
    private Timer timerToMoveToTargetCellForD;

    // Global int to keep track of steps already taken;
    int count = 0;

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
        timerToMoveToTargetCellForA = new Timer(3000, actionEvent -> {
            System.out.println("timerToMoveToTargetCellForA has started");
            if (playerA.getTargetCell() == null || !(playerA.getTargetCell().isHasGold()) || !(playerA.getTargetCell().isHasSecretGold())) {
                System.out.println("Player A is selecting target");
                // Player A either does not have a target
                // or the gold on its target has been taken by some other player.
                Cell targetCell = findTargetCellForPlayerA(cells);
                playerA.setTargetCell(targetCell);
                String targetCellText = targetCell.getText();
                if (targetCellText.isEmpty()) {
                    targetCell.setText("A");
                } else {
                    targetCell.setText(targetCellText + " " + "A");
                }
            }

            int differenceBetweenIndicesOfRows = playerA.getCurrentCell().getIndexOfRow() - playerA.getTargetCell().getIndexOfRow();
            int differenceBetweenIndicesOfColumns = playerA.getCurrentCell().getIndexOfColumn() - playerA.getTargetCell().getIndexOfColumn();

            if (differenceBetweenIndicesOfRows > 0) {
                // Move player A up.
                playerA.moveUp(cells);
            } else if (differenceBetweenIndicesOfRows < 0) {
                // Move player A down.
                playerA.moveDown(cells);
            } else {
                // No need to go up or down.
                // See if there's need to move left or right.
                if (differenceBetweenIndicesOfColumns > 0) {
                    // Move left.
                    playerA.moveLeft(cells);
                } else if (differenceBetweenIndicesOfColumns < 0) {
                    // Move right.
                    playerA.moveRight(cells);
                }
            }

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

            count++;
            if (count == numOfStepsToTakeOnEachMove || playerA.getTargetCell() == null) {
                // Player A either used all of its steps or reached to its target.
                playerA.decreaseTheAmountOfGold(costOfEachMoveForPlayerA);
                System.out.println("Stopping timerToMoveToTargetCellForA");
                ((Timer) actionEvent.getSource()).stop();
                count = 0;
                timerToMoveToTargetCellForB.start();
            }
        });

        timerToMoveToTargetCellForB = new Timer(3000, actionEvent -> {
            System.out.println("timerToMoveToTargetCellForB has started");
            if (playerB.getTargetCell() == null || !(playerB.getTargetCell().isHasGold()) || !(playerB.getTargetCell().isHasSecretGold())) {
                System.out.println("Player B is selecting target");
                Cell targetCell = findTargetCellForPlayerB(cells);
                playerB.setTargetCell(targetCell);
                String targetCellText = targetCell.getText();
                if (targetCellText.isEmpty()) {
                    targetCell.setText("B");
                } else {
                    targetCell.setText(targetCellText + " " + "B");
                }
            }

            int differenceBetweenIndicesOfRows = playerB.getCurrentCell().getIndexOfRow() - playerB.getTargetCell().getIndexOfRow();
            int differenceBetweenIndicesOfColumns = playerB.getCurrentCell().getIndexOfColumn() - playerB.getTargetCell().getIndexOfColumn();

            if (differenceBetweenIndicesOfRows > 0) {
                // Move player B up.
                playerB.moveUp(cells);
            } else if (differenceBetweenIndicesOfRows < 0) {
                // Move player B down.
                playerB.moveDown(cells);
            } else {
                // No need to go up or down.
                // See if there's need to move left or right.
                if (differenceBetweenIndicesOfColumns > 0) {
                    // Move left.
                    playerB.moveLeft(cells);
                } else if (differenceBetweenIndicesOfColumns < 0) {
                    // Move right.
                    playerB.moveRight(cells);
                }
            }

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

            count++;
            if (count == numOfStepsToTakeOnEachMove || playerB.getTargetCell() == null) {
                // Player B either used all of its steps or reached to its target.
                playerB.decreaseTheAmountOfGold(costOfEachMoveForPlayerB);
                System.out.println("Stopping timerToMoveToTargetCellForB");
                ((Timer) actionEvent.getSource()).stop();
                count = 0;
                timerToMoveToTargetCellForC.start();
            }
        });

        timerToMoveToTargetCellForC = new Timer(3000, actionEvent -> {
            System.out.println("timerToMoveToTargetCellForC has started");
            if (playerC.getTargetCell() == null || !(playerC.getTargetCell().isHasGold()) || !(playerC.getTargetCell().isHasSecretGold())) {
                System.out.println("Player C is selecting target");
                Cell targetCell = findTargetCellForPlayerC(cells);
                playerC.setTargetCell(targetCell);
                String targetCellText = targetCell.getText();
                if (targetCellText.isEmpty()) {
                    targetCell.setText("C");
                } else {
                    targetCell.setText(targetCellText + " " + "C");
                }
            }

            int differenceBetweenIndicesOfRows = playerC.getCurrentCell().getIndexOfRow() - playerC.getTargetCell().getIndexOfRow();
            int differenceBetweenIndicesOfColumns = playerC.getCurrentCell().getIndexOfColumn() - playerC.getTargetCell().getIndexOfColumn();

            if (differenceBetweenIndicesOfRows > 0) {
                // Move player C up.
                playerC.moveUp(cells);
            } else if (differenceBetweenIndicesOfRows < 0) {
                // Move player C down.
                playerC.moveDown(cells);
            } else {
                // No need to go up or down.
                // See if there's need to move left or right.
                if (differenceBetweenIndicesOfColumns > 0) {
                    // Move left.
                    playerC.moveLeft(cells);
                } else if (differenceBetweenIndicesOfColumns < 0) {
                    // Move right.
                    playerC.moveRight(cells);
                }
            }

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

            count++;
            if (count == numOfStepsToTakeOnEachMove || playerC.getTargetCell() == null) {
                // Player C either used all of its steps or reached to its target.
                playerC.decreaseTheAmountOfGold(costOfEachMoveForPlayerC);
                System.out.println("Stopping timerToMoveToTargetCellForC");
                ((Timer) actionEvent.getSource()).stop();
                count = 0;
                timerToMoveToTargetCellForD.start();
            }
        });

        timerToMoveToTargetCellForD = new Timer(3000, actionEvent -> {
            System.out.println("timerToMoveToTargetCellForD has started");
            if (playerD.getTargetCell() == null || !(playerD.getTargetCell().isHasGold()) || !(playerD.getTargetCell().isHasSecretGold())) {
                System.out.println("Player D is selecting target");
                Cell targetCell = findTargetCellForPlayerD(cells);
                playerD.setTargetCell(targetCell);
                String targetCellText = targetCell.getText();
                if (targetCellText.isEmpty()) {
                    targetCell.setText("D");
                } else {
                    targetCell.setText(targetCellText + " " + "D");
                }
            }

            int differenceBetweenIndicesOfRows = playerD.getCurrentCell().getIndexOfRow() - playerD.getTargetCell().getIndexOfRow();
            int differenceBetweenIndicesOfColumns = playerD.getCurrentCell().getIndexOfColumn() - playerD.getTargetCell().getIndexOfColumn();

            if (differenceBetweenIndicesOfRows > 0) {
                // Move player D up.
                playerD.moveUp(cells);
            } else if (differenceBetweenIndicesOfRows < 0) {
                // Move player D down.
                playerD.moveDown(cells);
            } else {
                // No need to go up or down.
                // See if there's need to move left or right.
                if (differenceBetweenIndicesOfColumns > 0) {
                    // Move left.
                    playerD.moveLeft(cells);
                } else if (differenceBetweenIndicesOfColumns < 0) {
                    // Move right.
                    playerD.moveRight(cells);
                }
            }

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

            count++;
            if (count == numOfStepsToTakeOnEachMove || playerD.getTargetCell() == null) {
                // Player D either used all of its steps or reached to its target.
                playerD.decreaseTheAmountOfGold(costOfEachMoveForPlayerD);
                System.out.println("Stopping timerToMoveToTargetCellForD");
                ((Timer) actionEvent.getSource()).stop();
                count = 0;
                // Restart the loop.
                timerToMoveToTargetCellForA.start();
            }
        });

//        Timer timer = new Timer(3000, e -> timerToMoveToTargetCellForA.start());
//        timer.setRepeats(false);
//        timer.start();
        timerToMoveToTargetCellForA.setInitialDelay(2000);
        timerToMoveToTargetCellForA.start();
    }

    private Cell findTargetCellForPlayerA(Cell[][] cells) {
        Cell targetCell = null;
        int shortestDistanceBetweenCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (playerA.getCurrentCell().equals(cells[i][j])) {
                    // Do not take currentCell into account while finding target cell.
                    continue;
                }
                boolean isSecretGoldAvailable = cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible();
                if (cells[i][j].isHasGold() || isSecretGoldAvailable) {
                    // First cell that has gold is the target cell.
                    // The initial cell to start comparison.
                    if (targetCell == null) {
                        targetCell = cells[i][j];
                        shortestDistanceBetweenCells = calculateDistanceBetweenTwoCells(
                            playerA.getCurrentCell(), targetCell
                        );
                    }
                    int distance = calculateDistanceBetweenTwoCells(playerA.getCurrentCell(), cells[i][j]);
                    if (distance < shortestDistanceBetweenCells) {
                        shortestDistanceBetweenCells = distance;
                        targetCell = cells[i][j];
                    }
                }
            }
        }
        return targetCell;
    }

    private Cell findTargetCellForPlayerB(Cell[][] cells) {
        Cell targetCell = null;
        // Profit point is the product of (1 / distance) and the amount of gold.
        double highestProfitPoint = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (playerB.getCurrentCell().equals(cells[i][j])) {
                    // Do not take currentCell into account while finding target cell.
                    continue;
                }
                boolean isSecretGoldAvailable = cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible();
                if (cells[i][j].isHasGold() || isSecretGoldAvailable) {
                    // First cell that has gold is the target cell.
                    // The initial cell to start comparison.
                    if (targetCell == null) {
                        targetCell = cells[i][j];
                    }
                    int distance = calculateDistanceBetweenTwoCells(playerB.getCurrentCell(), cells[i][j]);
                    double profitPoint;
                    if (cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible()) {
                        // Use the amount of secret gold if secret gold is present and available.
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfSecretGold();
                    } else {
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfGold();
                    }
                    if (profitPoint > highestProfitPoint) {
                        highestProfitPoint = profitPoint;
                        targetCell = cells[i][j];
                    }
                }
            }
        }
        return targetCell;
    }

    private Cell findTargetCellForPlayerC(Cell[][] cells) {
        Cell targetCell = null;
        // Profit point is the product of (1 / distance) and the amount of gold.
        double highestProfitPoint = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (playerC.getCurrentCell().equals(cells[i][j])) {
                    // Do not take currentCell into account while finding target cell.
                    continue;
                }
                boolean isSecretGoldAvailable = cells[i][j].isHasSecretGold();
                if (cells[i][j].isHasGold() || isSecretGoldAvailable) {
                    // First cell that has gold is the target cell.
                    // The initial cell to start comparison.
                    if (targetCell == null) {
                        targetCell = cells[i][j];
                    }
                    int distance = calculateDistanceBetweenTwoCells(playerC.getCurrentCell(), cells[i][j]);
                    double profitPoint;
                    if (cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible()) {
                        // Use the amount of secret gold if secret gold is present and available.
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfSecretGold();
                    } else {
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfGold();
                    }
                    if (profitPoint > highestProfitPoint) {
                        highestProfitPoint = profitPoint;
                        targetCell = cells[i][j];
                    }
                }
            }
        }
        return targetCell;
    }

    private Cell findTargetCellForPlayerD(Cell[][] cells) {
        Cell targetCell = null;
        // Profit point is the product of (1 / distance) and the amount of gold.
        double highestProfitPoint = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (playerD.getCurrentCell().equals(cells[i][j])) {
                    // Do not take currentCell into account while finding target cell.
                    continue;
                }
                if (cells[i][j].equals(playerA.getTargetCell())) {
                    // The target cell of player A.
                    // Check if player A can reach to its target before player D
                    int numOfVerticalStepsPlayerANeedsToTake = Math.abs(
                        playerA.getTargetCell().getIndexOfRow() - playerA.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerANeedsToTake = Math.abs(
                        playerA.getTargetCell().getIndexOfColumn() - playerA.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerANeedsToTake = numOfHorizontalStepsPlayerANeedsToTake +
                                                            numOfVerticalStepsPlayerANeedsToTake;

                    int numOfVerticalStepsPlayerDNeedsToTake = Math.abs(
                        playerA.getTargetCell().getIndexOfRow() - playerD.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerDNeedsToTake = Math.abs(
                        playerA.getTargetCell().getIndexOfColumn() - playerD.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerDNeedsToTake = numOfHorizontalStepsPlayerDNeedsToTake +
                                                            numOfVerticalStepsPlayerDNeedsToTake;

                    if (numOfTotalStepsPlayerDNeedsToTake > numOfTotalStepsPlayerANeedsToTake) {
                        // Player D cannot reach to this cell before player A.
                        continue;
                    }
                }
                if (cells[i][j].equals(playerB.getTargetCell())) {
                    // The target cell of player B.
                    // Check if player A can reach to its target before player D
                    int numOfVerticalStepsPlayerANeedsToTake = Math.abs(
                            playerB.getTargetCell().getIndexOfRow() - playerB.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerANeedsToTake = Math.abs(
                            playerB.getTargetCell().getIndexOfColumn() - playerB.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerANeedsToTake = numOfHorizontalStepsPlayerANeedsToTake +
                            numOfVerticalStepsPlayerANeedsToTake;

                    int numOfVerticalStepsPlayerDNeedsToTake = Math.abs(
                            playerB.getTargetCell().getIndexOfRow() - playerD.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerDNeedsToTake = Math.abs(
                            playerB.getTargetCell().getIndexOfColumn() - playerD.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerDNeedsToTake = numOfHorizontalStepsPlayerDNeedsToTake +
                            numOfVerticalStepsPlayerDNeedsToTake;

                    if (numOfTotalStepsPlayerDNeedsToTake > numOfTotalStepsPlayerANeedsToTake) {
                        // Player D cannot reach to this cell before player B.
                        continue;
                    }
                }
                if (cells[i][j].equals(playerC.getTargetCell())) {
                    // The target cell of player C.
                    // Check if player C can reach to its target before player D
                    int numOfVerticalStepsPlayerANeedsToTake = Math.abs(
                            playerC.getTargetCell().getIndexOfRow() - playerC.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerANeedsToTake = Math.abs(
                            playerC.getTargetCell().getIndexOfColumn() - playerC.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerANeedsToTake = numOfHorizontalStepsPlayerANeedsToTake +
                            numOfVerticalStepsPlayerANeedsToTake;

                    int numOfVerticalStepsPlayerDNeedsToTake = Math.abs(
                            playerC.getTargetCell().getIndexOfRow() - playerD.getCurrentCell().getIndexOfRow()
                    );
                    int numOfHorizontalStepsPlayerDNeedsToTake = Math.abs(
                            playerC.getTargetCell().getIndexOfColumn() - playerD.getCurrentCell().getIndexOfColumn()
                    );
                    int numOfTotalStepsPlayerDNeedsToTake = numOfHorizontalStepsPlayerDNeedsToTake +
                            numOfVerticalStepsPlayerDNeedsToTake;

                    if (numOfTotalStepsPlayerDNeedsToTake > numOfTotalStepsPlayerANeedsToTake) {
                        // Player D cannot reach to this cell before player C.
                        continue;
                    }
                }
                boolean isSecretGoldAvailable = cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible();
                if (cells[i][j].isHasGold() || isSecretGoldAvailable) {
                    // First cell that has gold is the target cell.
                    // The initial cell to start comparison.
                    if (targetCell == null) {
                        targetCell = cells[i][j];
                    }
                    int distance = calculateDistanceBetweenTwoCells(playerD.getCurrentCell(), cells[i][j]);
                    double profitPoint;
                    if (cells[i][j].isHasSecretGold() && cells[i][j].isSecretGoldVisible()) {
                        // Use the amount of secret gold if secret gold is present and available.
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfSecretGold();
                    } else {
                        profitPoint = (1 / (double) distance) * cells[i][j].getAmountOfGold();
                    }
                    if (profitPoint > highestProfitPoint) {
                        highestProfitPoint = profitPoint;
                        targetCell = cells[i][j];
                    }
                }
            }
        }
        return targetCell;
    }

    private int calculateDistanceBetweenTwoCells(Cell firstCell, Cell secondCell) {
        int absDifferenceBetweenIndicesOfRows = Math.abs(firstCell.getIndexOfRow() - secondCell.getIndexOfRow());
        int absDifferenceBetweenIndicesOfColumns = Math.abs(firstCell.getIndexOfColumn() - secondCell.getIndexOfColumn());
        return absDifferenceBetweenIndicesOfRows + absDifferenceBetweenIndicesOfColumns;
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
