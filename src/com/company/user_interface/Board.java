package com.company.user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final int numOfRows;
    private final int numOfColumns;

    private JPanel boardPanel;
    private final ArrayList<JLabel> cells = new ArrayList<>();

    public Board(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        initializeGUI();
    }

    public void initializeGUI() {
        boardPanel = new JPanel(new GridLayout(numOfRows, numOfColumns));

        ImageIcon aLetterIcon = new ImageIcon(getClass().getResource("assets/a_letter_icon.png"));
        ImageIcon bLetterIcon = new ImageIcon(getClass().getResource("assets/b_letter_icon.png"));
        ImageIcon cLetterIcon = new ImageIcon(getClass().getResource("assets/c_letter_icon.png"));
        ImageIcon dLetterIcon = new ImageIcon(getClass().getResource("assets/d_letter_icon.png"));
        ImageIcon goldIcon = new ImageIcon(getClass().getResource("assets/gold_icon.png"));
        ImageIcon chestIcon = new ImageIcon(getClass().getResource("assets/chest_icon.png"));

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                JLabel label = new JLabel();
                if (i == 0 && j == 0) {
                    // First cell
                    label.setIcon(aLetterIcon);
                } else if (i == 0 && j == numOfColumns - 1) {
                    // Last cell of the first row
                    label.setIcon(bLetterIcon);
                } else if (i == numOfRows - 1 && j == 0) {
                    // First cell of the last row
                    label.setIcon(cLetterIcon);
                } else if (i == numOfRows - 1 && j == numOfColumns - 1) {
                    // Last cell
                    label.setIcon(dLetterIcon);
                } else {
                    // A cell that is not a side.
                    if (i % 2 == 0) {
                        label.setIcon(goldIcon);
                    } else {
                        label.setIcon(chestIcon);
                    }
                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells.add(label);
                boardPanel.add(label);
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
