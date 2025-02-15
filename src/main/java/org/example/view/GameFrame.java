package org.example.view;

import javax.swing.*;
import org.example.controller.GameController;

/**
 * Represents the main game window frame.
 * Initializes the game panel, controller, and menu.
 */
public class GameFrame extends JFrame {
    /**
     * The panel where the game is rendered.
     */
    private GamePanel gamePanel;

    /**
     * The controller managing the game logic.
     */
    private GameController gameController;

    /**
     * Constructs a new GameFrame, setting up the game panel and controller.
     */
    public GameFrame() {
        gamePanel = new GamePanel();
        gameController = new GameController(this, gamePanel);
        gamePanel.setGameController(gameController);

        setResizable(false);

        add(gamePanel);
        createMenu();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates the game menu with options to restart the game and view highscores.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.addActionListener(e -> gameController.restartGame());
        gameMenu.add(restartItem);

        JMenuItem highscoreItem = new JMenuItem("Highscores");
        highscoreItem.addActionListener(e -> gameController.showHighscores());
        gameMenu.add(highscoreItem);

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Retrieves the game panel associated with this frame.
     *
     * @return the GamePanel instance
     */
    public GamePanel getGamePanel() {
        return gamePanel; 
    }
}
