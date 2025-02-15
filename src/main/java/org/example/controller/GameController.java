/**
 * Contains classes responsible for handling game logic, user input, and controlling the game flow.
 * This includes the GameController which manages game updates, rendering, and interactions
 * between the model and view.
 */
package org.example.controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.example.model.*;
import org.example.view.GamePanel;
import org.example.view.GameFrame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controls the game logic, including game state updates, rendering, and user input.
 */
public class GameController implements KeyListener {
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Timer timer;
    private Player yogi;
    private Level currentLevel;
    private int levelNumber = 1;
    private int lives = 3;
    private long startTime;
    private int totalBasketsCollected = 0;
    private HighscoreManager highscoreManager;

    /**
     * Constructs a new GameController with the specified frame and panel.
     *
     * @param frame the GameFrame instance
     * @param panel the GamePanel instance
     */
    public GameController(GameFrame frame, GamePanel panel) {
        this.gameFrame = frame;
        this.gamePanel = panel;
        highscoreManager = new HighscoreManager();
        loadLevel(levelNumber);
        initTimer();
        startTime = System.currentTimeMillis();
    }

    /**
     * Loads the specified level.
     *
     * @param levelNum the level number to load
     */
    private void loadLevel(int levelNum) {
        System.out.println("Loading level " + levelNum + "...");
        currentLevel = new Level("./levels/level" + levelNum + ".txt");
        yogi = new Player(50, 50, currentLevel);
        resetTime();
    }

    /**
     * Initializes and starts the game loop timer.
     */
    private void initTimer() {
        System.out.println("Starting game timer...");
        timer = new Timer();
        timer.scheduleAtFixedRate(new GameLoop(), 0, 16);
    }

    /**
     * Resets the game timer to the current system time.
     */
    private void resetTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Restarts the game by resetting levels, lives, and basket counts.
     */
    public void restartGame() {
        System.out.println("Restarting game...");
        levelNumber = 1;
        lives = 3;
        totalBasketsCollected = 0;
        loadLevel(levelNumber);
        startTime = System.currentTimeMillis();
        timer.cancel();
        initTimer();
    }

    /**
     * Renders the current game state using the provided Graphics context.
     *
     * @param g the Graphics context
     */
    public void render(Graphics g) {
        if (currentLevel != null) {
            currentLevel.draw(g);
        }
        if (yogi != null) {
            yogi.draw(g);
        }
    }

    /**
     * Retrieves the current game status, including lives, baskets collected, and elapsed time.
     *
     * @return a string representing the game status
     */
    public String getStatus() {
        return "Lives: " + lives + " | Baskets Collected: " + totalBasketsCollected
                + " | Time Elapsed: " + getElapsedTime() + "s";
    }

    /**
     * Calculates the elapsed time since the game started or was last reset.
     *
     * @return the elapsed time in seconds
     */
    private int getElapsedTime() {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }

    /**
     * Represents the game loop task that updates game state and repaints the game panel.
     */
    private class GameLoop extends TimerTask {
        @Override
        public void run() {
            try {
                yogi.update();
                currentLevel.update();
                checkCollisions();
                gamePanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks for collisions between the player and game entities.
     */
    private void checkCollisions() {
        if (currentLevel.checkBasketCollision(yogi)) {
            yogi.collectBasket();
            totalBasketsCollected++;
            gameFrame.repaint();
            if (currentLevel.areAllBasketsCollected()) {
                loadNextLevel();
            }
        }

        if (currentLevel.checkRangerCollision(yogi)) {
            lives--;
            if (lives > 0) {
                yogi.resetPosition();
            } else {
                timer.cancel();
                endGame();
            }
        }
    }

    /**
     * Loads the next level or ends the game if all levels are completed.
     */
    private void loadNextLevel() {
        levelNumber++;
        if (levelNumber <= 10) {
            loadLevel(levelNumber);
            yogi.resetPosition();
        } else {
            timer.cancel();
            endGame();
        }
    }

    /**
     * Displays the highscores in a dialog.
     */
    public void showHighscores() {
        ArrayList<GameRecord> records = highscoreManager.getAllScores();
        StringBuilder sb = new StringBuilder("Highscores:\n");

        int rank = 1;
        for (GameRecord record : records) {
            sb.append(rank).append(". ").append(record.getPlayerName())
                    .append(" - ").append(record.getBasketsCollected()).append("\n");
            rank++;
        }

        JOptionPane.showMessageDialog(gameFrame, sb.toString());
    }

    /**
     * Ends the game by prompting the player to enter their name and saving the score.
     */
    public void endGame() {
        String playerName = JOptionPane.showInputDialog(gameFrame, "Game Over! Enter your name:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            highscoreManager.addScore(playerName, totalBasketsCollected, levelNumber - 1);
        }
        restartGame();
    }

    /**
     * Handles key pressed events by delegating to the player.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        yogi.keyPressed(e);
    }

    /**
     * Handles key released events by delegating to the player.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        yogi.keyReleased(e);
    }

    /**
     * Handles key typed events. Not used in this implementation.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Player getPlayer() {
        return yogi;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public int getLives() {
        return lives;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public HighscoreManager getHighscoreManager() {
        return highscoreManager;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    /**
     * Updates the game state to indicate whether the game is over.
     *
     * @param gameOver true if the game is over, false otherwise
     */
    public void setGameOver(boolean gameOver) {
        if (gameOver) {
            lives = 0; // Setting lives to zero indicates the game is over
            timer.cancel();
        } else {
            lives = 3; // Reset lives if game is not over
            initTimer(); // Restart the game timer
        }
    }

    /**
     * Sets the number of lives for the player.
     *
     * @param lives the number of lives to set
     */
    public void setLives(int lives) {
        this.lives = lives;
        if (this.lives <= 0) {
            setGameOver(true); // Automatically end the game if lives are zero
        }
    }



}
