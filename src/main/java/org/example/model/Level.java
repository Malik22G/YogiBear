package org.example.model;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Represents a game level, managing obstacles, baskets, and rangers.
 */
public class Level {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Item> baskets;
    private ArrayList<Ranger> rangers;

    /**
     * Constructs a new Level by loading level data from a file.
     *
     * @param levelFile the path to the level file
     */
    public Level(String levelFile) {
        obstacles = new ArrayList<>();
        baskets = new ArrayList<>();
        rangers = new ArrayList<>();
        loadLevelFromFile(levelFile);
    }

    /**
     * Loads level data from the specified file.
     *
     * @param levelFile the path to the level file
     */
    private void loadLevelFromFile(String levelFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(levelFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                switch (tokens[0]) {
                    case "O" -> obstacles.add(new Obstacle(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
                    case "B" -> baskets.add(new Item(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                    case "R" -> rangers.add(new Ranger(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], this));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the state of the level, including ranger movements.
     */
    public void update() {
        for (Ranger ranger : rangers) {
            ranger.update();
        }
    }

    /**
     * Draws all elements of the level onto the provided Graphics context.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        for (Item basket : baskets) {
            basket.draw(g);
        }

        for (Ranger ranger : rangers) {
            ranger.draw(g);
        }
    }

    /**
     * Checks for collision between the player and any baskets.
     * Removes the basket if a collision is detected.
     *
     * @param player the Player instance
     * @return true if a collision occurred, false otherwise
     */
    public boolean checkBasketCollision(Player player) {
        Rectangle playerBounds = player.getBounds();
        for (int i = 0; i < baskets.size(); i++) {
            if (playerBounds.intersects(baskets.get(i).getBounds())) {
                baskets.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if all baskets in the level have been collected.
     *
     * @return true if all baskets are collected, false otherwise
     */
    public boolean areAllBasketsCollected() {
        return baskets.isEmpty();
    }

    /**
     * Checks for collision between the player and any rangers.
     *
     * @param player the Player instance
     * @return true if a collision occurred, false otherwise
     */
    public boolean checkRangerCollision(Player player) {
        Rectangle playerBounds = player.getBounds();
        for (Ranger ranger : rangers) {
            if (playerBounds.intersects(ranger.getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given rectangle collides with any obstacles.
     *
     * @param rect the Rectangle to check
     * @return true if a collision is detected, false otherwise
     */
    public boolean checkObstacleCollision(Rectangle rect) {
        for (Obstacle obstacle : obstacles) {
            if (rect.intersects(obstacle.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getBaskets() {
        return baskets;
    }

    public ArrayList<Ranger> getRangers() {
        return rangers;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

}
