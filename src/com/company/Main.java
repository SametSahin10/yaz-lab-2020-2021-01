package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            JFrame frame = new JFrame("Gold Hunt");

            Board board = new Board(20, 15);
            frame.add(board.getBoardPanel());

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setSize(1400, 1000);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        };
        SwingUtilities.invokeLater(runnable);
    }
}
