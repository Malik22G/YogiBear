package org.example;

import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BlackBoxTests {

    private Level level;
    private Player yogi;
    private HighscoreManager highscoreManager;

    @BeforeEach
    void setup() {
        level = new Level("./levels/testLevel.txt"); // Replace with mock or simple initialization if needed
        yogi = new Player(50, 50, level);
        highscoreManager = new HighscoreManager();
    }

    @Test
    void testBasketCollection() {
        level.getBaskets().add(new Item(100, 100));
        yogi.setPosition(100, 100);

        boolean collision = level.checkBasketCollision(yogi);

        assertTrue(collision, "Yogi should collect the basket when positioned at its location.");
        assertEquals(0, yogi.getBasketsCollected());
        assertTrue(level.getBaskets().isEmpty(), "The basket should be removed from the level.");
    }

    @Test
    void testRangerCollision() {
        level.getRangers().add(new Ranger(100, 100, "Horizontal", level));
        yogi.setPosition(110, 100); // Close enough for a collision

        boolean collision = level.checkRangerCollision(yogi);

        assertTrue(collision, "Yogi should collide with the ranger.");
        yogi.resetPosition();
        assertEquals(50, yogi.getX(), "Yogi should respawn at the starting X position.");
        assertEquals(50, yogi.getY(), "Yogi should respawn at the starting Y position.");
    }

    @Test
    void testGameOver() {
        int lives = 1; // Simulate one remaining life
        level.getRangers().add(new Ranger(100, 100, "Horizontal", level));
        yogi.setPosition(100, 100); // Simulate collision

        boolean collision = level.checkRangerCollision(yogi);

        assertTrue(collision, "Yogi should collide with the ranger.");
        lives--;
        assertEquals(0, lives, "Lives should be reduced to zero.");
        boolean gameOver = lives <= 0;
        assertTrue(gameOver, "Game should be over when lives reach zero.");
    }

    @Test
    void testHighscoreEntry() {
        String playerName = "Malik";
        int basketsCollected = 5;
        int levelsCleared = 2;

        highscoreManager.addScore(playerName, basketsCollected, levelsCleared);

        ArrayList<GameRecord> scores = highscoreManager.getAllScores();
        assertFalse(scores.isEmpty(), "Highscore manager should store scores.");
        assertEquals(playerName, scores.get(0).getPlayerName(), "Stored player's name should match.");
    }
}
