package com.company;

import com.company.user_interface.Board;
import com.company.user_interface.IntroScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static IntroScreen introScreen;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            JFrame frame = new JFrame("Gold Hunt");

            JButton startGameButton = new JButton("Start");
            startGameButton.addActionListener(e -> {
                Board board = new Board(
                    frame,
                    Integer.parseInt(introScreen.getNumOfRowsTA().getText()),
                    Integer.parseInt(introScreen.getNumOfColumnsTA().getText()),
                    Integer.parseInt(introScreen.getPercentageOfCellsThatWillHaveGoldTA().getText()),
                    Integer.parseInt(introScreen.getPercentageOfCellsThatWillHaveSecretGoldTA().getText()),
                    Integer.parseInt(introScreen.getTotalAmountOfGoldEachUserWillHaveTA().getText()),
                    Integer.parseInt(introScreen.getNumOfStepsToTakeOnEachMoveTA().getText()),
                    Integer.parseInt(introScreen.getCostOfEachMoveForPlayerATA().getText()),
                    Integer.parseInt(introScreen.getCostOfEachMoveForPlayerBTA().getText()),
                    Integer.parseInt(introScreen.getCostOfEachMoveForPlayerCTA().getText()),
                    Integer.parseInt(introScreen.getCostOfEachMoveForPlayerDTA().getText()),
                    Integer.parseInt(introScreen.getCostOfSettingTargetForPlayerATA().getText()),
                    Integer.parseInt(introScreen.getCostOfSettingTargetForPlayerBTA().getText()),
                    Integer.parseInt(introScreen.getCostOfSettingTargetForPlayerCTA().getText()),
                    Integer.parseInt(introScreen.getCostOfSettingTargetForPlayerDTA().getText())
                );
                frame.add(board.getBoardPanel());
                frame.remove(introScreen.getIntroScreenPanel());
            });

            ActionListener gameOverActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };

            introScreen = new IntroScreen(startGameButton);
            frame.add(introScreen.getIntroScreenPanel());

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setSize(1400, 1000);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        };
        SwingUtilities.invokeLater(runnable);
    }
}
