package com.company.models;

import com.company.user_interface.Cell;

import javax.swing.*;

public class Player {
    private ImageIcon icon;
    private int totalAmountOfGold;
    private Cell currentCell;
    private Cell targetCell;

    public Player(ImageIcon icon, int totalAmountOfGold, Cell currentCell, Cell targetCell) {
        this.icon = icon;
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

    public void increaseTheAmountOfGold(int amount) {
        this.totalAmountOfGold += amount;
    }

    public void decreaseTheAmountOfGold(int amount) {
        this.totalAmountOfGold -= amount;
    }

    public void move(Cell newCell) {
        if (newCell.isHasGold()) {
            increaseTheAmountOfGold(newCell.getAmountOfGold());
            System.out.println("Player A received " + newCell.getAmountOfGold() + " gold");
            System.out.println("Amount of gold player A has: " + getTotalAmountOfGold());
            newCell.setAmountOfGold(0);
            newCell.setHasGold(false);
            if (newCell.equals(targetCell)) {
                // Player has reached to its target.
                System.out.println("Player has reached to its target.");
                targetCell.setText("");
                targetCell = null;
            }
        }
        newCell.setIcon(icon);
        currentCell.clear();
        currentCell = newCell;
    }

    public void moveToTargetCell() {
        move(targetCell);
    }
}
