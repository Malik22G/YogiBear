package org.example;

import org.example.model.*;
import org.example.controller.GameController;
import org.example.view.GamePanel;
import org.example.view.GameFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteBoxTests {

    private GameController controller;

    @BeforeEach
    void setup() {
        GameFrame frame = new GameFrame();
        GamePanel panel = frame.getGamePanel();
        controller = new GameController(frame, panel);
    }



    @Test
    void testCollisionDetection() {
        Player yogi = controller.getPlayer();
        Level level = controller.getLevel();
        level.getRangers().add(new Ranger(100, 100, "Horizontal", level));
        yogi.setPosition(100, 110);

        assertTrue(level.checkRangerCollision(yogi));
    }


    @Test
    void testResetGame() {
        controller.setGameOver(true);
        controller.restartGame();

        assertEquals(1, controller.getLevelNumber());
        assertEquals(3, controller.getLives());
    }



    @Test
    void testHighscoreDatabase() {
        HighscoreManager manager = new HighscoreManager();
        manager.addScore("Test Player", 10, 5);

        assertTrue(manager.getAllScores().stream()
                .anyMatch(record -> record.getPlayerName().equals("Test Player")));
    }

    @Test
    void testUpdateRangers() {
        Player yogi = controller.getPlayer();
        Level level = controller.getLevel();
        level.getRangers().add(new Ranger(100, 100, "Horizontal", level));
        yogi.setPosition(100, 110);

        assertTrue(level.checkRangerCollision(yogi));
    }

    @Test
    void testLives() {
        controller.setGameOver(true);
        controller.restartGame();

        assertEquals(3, controller.getLives());
        assertEquals(0, controller.getPlayer().getBasketsCollected());
    }
}
