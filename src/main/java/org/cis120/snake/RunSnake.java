package org.cis120.snake;

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSnake implements Runnable {

    public void run() {
        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("The Snake Game");
        frame.setLocation(400, 400);

        // Game Instructions
        String instruction = "The Snake Game \nInstructions: \nUse the arrow keys to move up, " +
                "down, left or right.\nDon't run into your own body or touch the boundaries of the "
                +
                "screen.\nYou will die if this happens.\nPress OK to start the game!";
        JOptionPane.showMessageDialog(frame, instruction, "The Snake Game", JOptionPane.OK_OPTION);

        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Status
        final JPanel panel_status = new JPanel();
        frame.add(panel_status, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Score: ");
        panel_status.add(status);

        // Initializes the game court
        final GameCourt gamecourt = new GameCourt(status);
        frame.add(gamecourt, BorderLayout.CENTER);

        // Button to reset game
        final JPanel panel_control = new JPanel();
        frame.add(panel_control, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamecourt.reset();
            }
        });
        panel_control.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gamecourt.reset();

    }

}