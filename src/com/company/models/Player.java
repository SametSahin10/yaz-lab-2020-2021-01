package com.company.models;

import com.company.user_interface.Cell;

public class Player {
    private int totalAmountOfGold;
    private Cell currentCell;
    private Cell targetCell;

    public Player(int totalAmountOfGold, Cell currentCell, Cell targetCell) {
        this.totalAmountOfGold = totalAmountOfGold;
        this.currentCell = currentCell;
        this.targetCell = targetCell;
    }

    public int getTotalAmountOfGold() {
        return totalAmountOfGold;
    }

    public void setTotalAmountOfGold(int totalAmountOfGold) {
        this.totalAmountOfGold = totalAmountOfGold;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(Cell targetCell) {
        this.targetCell = targetCell;
    }
}
