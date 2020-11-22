package com.company.user_interface;

import com.company.models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameOverScreen {
    private final Player playerA;
    private final Player playerB;
    private final Player playerC;
    private final Player playerD;

    private JPanel gameOverPanel;

    public GameOverScreen(Player playerA, Player playerB, Player playerC, Player playerD) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.playerC = playerC;
        this.playerD = playerD;
        initializeGUI();
    }

    public JPanel getGameOverPanel() {
        return gameOverPanel;
    }

    private void initializeGUI() {
        gameOverPanel = new JPanel(new BorderLayout());

        InputStream inputStream = Board.class.getResourceAsStream("../assets/fonts/SourceSansPro-Bold.ttf");
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font = font.deriveFont(48f);
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
            return;
        }
        JLabel gameOverLabel = new JLabel("Game over", JLabel.CENTER);
        gameOverLabel.setFont(font);
        gameOverPanel.add(gameOverLabel, BorderLayout.NORTH);

        JTable statsTable = createTableToHoldStats();
        JScrollPane scrollPane = new JScrollPane(statsTable);
        gameOverPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JTable createTableToHoldStats() {
        String[] columnNames = {
            "Player",
            "Total amount of steps",
            "Total amount of gold spent",
            "Current total amount of gold",
            "Total amount of gold collected"
        };
        String[][] data = {
            {
                "Player A",
                String.valueOf(playerA.getTotalAmountOfSteps()),
                String.valueOf(playerA.getTotalAmountOfGoldSpent()),
                String.valueOf(playerA.getTotalAmountOfGold()),
                String.valueOf(playerA.getTotalAmountOfGoldCollected())
            },
            {
                "Player B",
                String.valueOf(playerB.getTotalAmountOfSteps()),
                String.valueOf(playerB.getTotalAmountOfGoldSpent()),
                String.valueOf(playerB.getTotalAmountOfGold()),
                String.valueOf(playerB.getTotalAmountOfGoldCollected())
            },
            {
                "Player C",
                String.valueOf(playerC.getTotalAmountOfSteps()),
                String.valueOf(playerC.getTotalAmountOfGoldSpent()),
                String.valueOf(playerC.getTotalAmountOfGold()),
                String.valueOf(playerC.getTotalAmountOfGoldCollected())
            },
            {
                "Player D",
                String.valueOf(playerD.getTotalAmountOfSteps()),
                String.valueOf(playerD.getTotalAmountOfGoldSpent()),
                String.valueOf(playerD.getTotalAmountOfGold()),
                String.valueOf(playerD.getTotalAmountOfGoldCollected())
            },
        };
        return new JTable(data, columnNames);
    }
}
