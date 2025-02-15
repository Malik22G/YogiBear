package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.example.controller.GameController;

/**
 * Represents the panel where the game is rendered.
 * Handles drawing the background and delegating rendering to the game controller.
 */
public class GamePanel extends JPanel {
    /**
     * The label displaying the game's status.
     */
    private JLabel statusBar;

    /**
     * The controller managing the game logic.
     */
    private GameController gameController;

    /**
     * The background image of the game.
     */
    private BufferedImage backgroundImage;

    /**
     * Constructs a new GamePanel, setting up the status bar and loading the background image.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        setLayout(new BorderLayout());
        statusBar = new JLabel("Status: Initializing...");
        add(statusBar, BorderLayout.NORTH);
        loadBackgroundImage();
        requestFocusInWindow();
    }

    /**
     * Loads the background image from the resources.
     */
    private void loadBackgroundImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/images/background.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Error: Background image not found!");
            }
        } catch (IOException e) {
            System.err.println("Error loading background image.");
            e.printStackTrace();
        }
    }

    /**
     * Sets the game controller and adds it as a key listener.
     *
     * @param controller the GameController instance
     */
    public void setGameController(GameController controller) {
        this.gameController = controller;
        addKeyListener(controller);
    }

    /**
     * Paints the component by drawing the background and delegating rendering to the controller.
     *
     * @param g the Graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(144, 238, 144));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (gameController != null) {
            gameController.render(g);
            updateStatusBar();
        }
    }

    /**
     * Updates the status bar with the current game status.
     */
    public void updateStatusBar() {
        if (gameController != null) {
            statusBar.setText(gameController.getStatus());
        }
    }
}
