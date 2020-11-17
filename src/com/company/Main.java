package com.company;

import com.company.user_interface.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static int i = 0;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            JFrame frame = new JFrame("Gold Hunt");

            Board board = new Board(
                20,
                20,
                20,
                10
            );
            frame.add(board.getBoardPanel());

            Timer timer = new Timer(3000, e -> {
                Timer localTimer = (Timer) (e.getSource());
                if (i == 2) {
                    localTimer.stop();
                    return;
                }
                board.movePlayerA();
                board.movePlayerB();
                board.movePlayerC();
                board.movePlayerD();
                i++;
            });
            timer.start();

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setSize(1400, 1000);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        };
        SwingUtilities.invokeLater(runnable);
    }
}
