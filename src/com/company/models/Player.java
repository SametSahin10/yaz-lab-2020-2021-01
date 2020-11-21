package com.company.models;

import com.company.user_interface.Cell;
import com.company.utils.Icons;
import com.company.utils.PlayerType;

public class Player {
    private final PlayerType playerType;
    private final int costOfEachMove;
    private final int costOfSettingTarget;
    private final int numOfStepsToTakeOnEachMove;
    private int totalAmountOfGold;
    private int totalAmountOfGoldSpent;
    private int totalAmountOfGoldCollected;
    private int totalAmountOfSteps;
    private Cell currentCell;
    private Cell targetCell;

    public Player(
        PlayerType playerType,
        int costOfEachMove,
        int costOfSettingTarget,
        int numOfStepsToTakeOnEachMove,
        int totalAmountOfGold,
        Cell currentCell,
        Cell targetCell
    ) {
        this.playerType = playerType;
        this.costOfEachMove = costOfEachMove;
        this.costOfSettingTarget = costOfSettingTarget;
        this.numOfStepsToTakeOnEachMove = numOfStepsToTakeOnEachMove;
        this.totalAmountOfGold = totalAmountOfGold;
        this.totalAmountOfGoldSpent = 0;
        this.totalAmountOfGoldCollected = 0;
        this.totalAmountOfSteps = 0;
        this.currentCell = currentCell;
        this.targetCell = targetCell;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getTotalAmountOfGold() {
        return totalAmountOfGold;
    }

    public void setTotalAmountOfGold(int totalAmountOfGold) {
        this.totalAmountOfGold = totalAmountOfGold;
    }

    public int getTotalAmountOfGoldSpent() {
        return totalAmountOfGoldSpent;
    }

    public void setTotalAmountOfGoldSpent(int totalAmountOfGoldSpent) {
        this.totalAmountOfGoldSpent = totalAmountOfGoldSpent;
    }

    public int getTotalAmountOfGoldCollected() {
        return totalAmountOfGoldCollected;
    }

    public void setTotalAmountOfGoldCollected(int totalAmountOfGoldCollected) {
        this.totalAmountOfGoldCollected = totalAmountOfGoldCollected;
    }

    public int getTotalAmountOfSteps() {
        return totalAmountOfSteps;
    }

    public void setTotalAmountOfSteps(int totalAmountOfSteps) {
        this.totalAmountOfSteps = totalAmountOfSteps;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell getTargetCell() {
        decreaseTheAmountOfGold(costOfSettingTarget);
        return targetCell;
    }

    public void setTargetCell(Cell targetCell) {
        decreaseTheAmountOfGold(costOfSettingTarget);
        this.targetCell = targetCell;
    }

    public void increaseTheAmountOfGold(int amount) {
        totalAmountOfGoldCollected += amount;
        totalAmountOfGold += amount;
    }

    public void decreaseTheAmountOfGold(int amount) {
        totalAmountOfGoldSpent += amount;
        totalAmountOfGold -= amount;
        // The minimum value of totalAmountOfGold is zero.
        // Do not set it to negative values;
        if (totalAmountOfGold < 0) {
            totalAmountOfGold = 0;
        }
    }

    public void move(Cell newCell) {
        System.out.println("Moving to " + newCell.getIndexOfRow() + ", " + newCell.getIndexOfColumn());
        totalAmountOfSteps++;
        decreaseTheAmountOfGold(costOfEachMove);
        if (newCell.isHasGold()) {
            increaseTheAmountOfGold(newCell.getAmountOfGold());
            newCell.setAmountOfGold(0);
            newCell.setHasGold(false);
            if (newCell.equals(targetCell)) {
                // Player has reached to its target.
                System.out.println("Player has reached to its target.");
                newCell.clearText(playerType);
                targetCell = null;
            }
        } else {
            // At this point, the cell does not have gold.
            // So, it is safe to check if there's secret gold.
            // Secret golds may only be at the cells where
            // their golds have already been taken.
            if (newCell.isHasSecretGold()) {
                // Player moved to a cell where
                // there is secret gold available to get.
                increaseTheAmountOfGold(newCell.getAmountOfSecretGold());
                newCell.setAmountOfSecretGold(0);
                newCell.setHasSecretGold(false);
            }
        }

        setPlayerIcon(newCell);

        if (currentCell.isHasSecretGold()) {
            // Player moved to a cell where there is secret gold hidden.
            // Set secret gold visible to other players.
            newCell.setSecretGoldVisible(true);
            currentCell.setIcon(Icons.chestIcon);
        } else {
            currentCell.clearIcon();
        }
        currentCell.clearText(playerType);
        currentCell = newCell;
    }

    public void moveToTargetCell() {
        move(targetCell);
    }

    public void setPlayerIcon(Cell cell) {
        switch (playerType) {
            case A:
                cell.setIcon(Icons.aLetterIcon);
                break;
            case B:
                cell.setIcon(Icons.bLetterIcon);
                break;
            case C:
                cell.setIcon(Icons.cLetterIcon);
                break;
            case D:
                cell.setIcon(Icons.dLetterIcon);
                break;
            default:
                cell.setIcon(null);
        }
    }
}
