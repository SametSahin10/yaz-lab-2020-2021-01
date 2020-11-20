package com.company.user_interface;

import javax.swing.*;
import java.awt.*;

public class IntroScreen {
    private JPanel introScreenPanel;
    private JTextArea numOfRowsTA;
    private JTextArea numOfColumnsTA;
    private JTextArea percentageOfCellsThatWillHaveGoldTA;
    private JTextArea percentageOfCellsThatWillHaveSecretGoldTA;
    private JTextArea totalAmountOfGoldEachUserWillHaveTA;
    private JTextArea costOfEachMoveForPlayerATA;
    private JTextArea costOfSettingTargetForPlayerATA;
    private JTextArea costOfEachMoveForPlayerBTA;
    private JTextArea costOfSettingTargetForPlayerBTA;
    private JTextArea costOfEachMoveForPlayerCTA;
    private JTextArea costOfSettingTargetForPlayerCTA;
    private JTextArea costOfEachMoveForPlayerDTA;
    private JTextArea costOfSettingTargetForPlayerDTA;
    private JButton startGameButton;

    public IntroScreen(JButton startGameButton) {
        this.startGameButton = startGameButton;
        initializeGUI();
    }

    public JPanel getIntroScreenPanel() {
        return introScreenPanel;
    }

    public JTextArea getNumOfRowsTA() {
        return numOfRowsTA;
    }

    public JTextArea getNumOfColumnsTA() {
        return numOfColumnsTA;
    }

    public JTextArea getPercentageOfCellsThatWillHaveGoldTA() {
        return percentageOfCellsThatWillHaveGoldTA;
    }

    public JTextArea getPercentageOfCellsThatWillHaveSecretGoldTA() {
        return percentageOfCellsThatWillHaveSecretGoldTA;
    }

    public JTextArea getTotalAmountOfGoldEachUserWillHaveTA() {
        return totalAmountOfGoldEachUserWillHaveTA;
    }

    public JTextArea getCostOfEachMoveForPlayerATA() {
        return costOfEachMoveForPlayerATA;
    }

    public JTextArea getCostOfSettingTargetForPlayerATA() {
        return costOfSettingTargetForPlayerATA;
    }

    public JTextArea getCostOfEachMoveForPlayerBTA() {
        return costOfEachMoveForPlayerBTA;
    }

    public JTextArea getCostOfSettingTargetForPlayerBTA() {
        return costOfSettingTargetForPlayerBTA;
    }

    public JTextArea getCostOfEachMoveForPlayerCTA() {
        return costOfEachMoveForPlayerCTA;
    }

    public JTextArea getCostOfSettingTargetForPlayerCTA() {
        return costOfSettingTargetForPlayerCTA;
    }

    public JTextArea getCostOfEachMoveForPlayerDTA() {
        return costOfEachMoveForPlayerDTA;
    }

    public JTextArea getCostOfSettingTargetForPlayerDTA() {
        return costOfSettingTargetForPlayerDTA;
    }

    public void initializeGUI() {
        introScreenPanel = new JPanel(new GridLayout(8, 1));

        JLabel numOfRowsLabel = new JLabel("Number of rows: ");
        JLabel numOfColumnsLabel = new JLabel("Number of columns: ");
        JLabel percentageOfCellsThatWillHaveGoldLabel = new JLabel("percentage of cells that will have gold: ");
        JLabel percentageOfCellsThatWillHaveSecretGoldLabel = new JLabel("percentage of cells that will have secret gold: ");
        JLabel totalAmountOfGoldEachUserWillHaveLabel = new JLabel("Total amount of gold each user will have: ");
        JLabel costOfAMoveForPlayerALabel = new JLabel("Cost of a move for player A: ");
        JLabel costOfSettingTargetForPlayerALabel = new JLabel("Cost of setting target for player A: ");
        JLabel costOfAMoveForPlayerBLabel = new JLabel("Cost of a move for player B: ");
        JLabel costOfSettingTargetForPlayerBLabel = new JLabel("Cost of setting target for player B: ");
        JLabel costOfAMoveForPlayerCLabel = new JLabel("Cost of a move for player C: ");
        JLabel costOfSettingTargetForPlayerCLabel = new JLabel("Cost of setting target for player C: ");
        JLabel costOfAMoveForPlayerDLabel = new JLabel("Cost of a move for player D: ");
        JLabel costOfSettingTargetForPlayerDLabel = new JLabel("Cost of setting target for player D: ");

        // TA stands for TextArea
        numOfRowsTA = new JTextArea("20");
        numOfColumnsTA = new JTextArea("20");
        percentageOfCellsThatWillHaveGoldTA = new JTextArea("20");
        percentageOfCellsThatWillHaveSecretGoldTA = new JTextArea("10");
        totalAmountOfGoldEachUserWillHaveTA = new JTextArea("200");

        costOfEachMoveForPlayerATA = new JTextArea("5");
        costOfSettingTargetForPlayerATA = new JTextArea("3");

        costOfEachMoveForPlayerBTA = new JTextArea("5");
        costOfSettingTargetForPlayerBTA = new JTextArea("10");

        costOfEachMoveForPlayerCTA = new JTextArea("5");
        costOfSettingTargetForPlayerCTA = new JTextArea("15");

        costOfEachMoveForPlayerDTA = new JTextArea("5");
        costOfSettingTargetForPlayerDTA = new JTextArea("20");

        introScreenPanel.add(numOfRowsLabel);
        introScreenPanel.add(numOfRowsTA);

        introScreenPanel.add(numOfColumnsLabel);
        introScreenPanel.add(numOfColumnsTA);

        introScreenPanel.add(percentageOfCellsThatWillHaveGoldLabel);
        introScreenPanel.add(percentageOfCellsThatWillHaveGoldTA);

        introScreenPanel.add(percentageOfCellsThatWillHaveSecretGoldLabel);
        introScreenPanel.add(percentageOfCellsThatWillHaveSecretGoldTA);

        introScreenPanel.add(totalAmountOfGoldEachUserWillHaveLabel);
        introScreenPanel.add(totalAmountOfGoldEachUserWillHaveTA);


        introScreenPanel.add(costOfAMoveForPlayerALabel);
        introScreenPanel.add(costOfEachMoveForPlayerATA);

        introScreenPanel.add(costOfSettingTargetForPlayerALabel);
        introScreenPanel.add(costOfSettingTargetForPlayerATA);

        introScreenPanel.add(costOfAMoveForPlayerBLabel);
        introScreenPanel.add(costOfEachMoveForPlayerBTA);

        introScreenPanel.add(costOfSettingTargetForPlayerBLabel);
        introScreenPanel.add(costOfSettingTargetForPlayerBTA);

        introScreenPanel.add(costOfAMoveForPlayerCLabel);
        introScreenPanel.add(costOfEachMoveForPlayerCTA);

        introScreenPanel.add(costOfSettingTargetForPlayerCLabel);
        introScreenPanel.add(costOfSettingTargetForPlayerCTA);

        introScreenPanel.add(costOfAMoveForPlayerDLabel);
        introScreenPanel.add(costOfEachMoveForPlayerDTA);

        introScreenPanel.add(costOfSettingTargetForPlayerDLabel);
        introScreenPanel.add(costOfSettingTargetForPlayerDTA);

        introScreenPanel.add(startGameButton);
    }
}
