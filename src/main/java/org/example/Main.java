package org.example;

import javax.swing.*;

import org.example.view.GameFrame;
/**
 * The entry point for the Yogi game application.
 * Initializes and displays the main game window.
 */
public class Main {
    /**
     * The main method that launches the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

            GameFrame frame = new GameFrame();
            frame.setTitle("Yogi Bear Adventure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

    }
}
