package org.example.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents the player character in the game (Yogi Bear).
 * The player can move around the game board, collect baskets, and avoid obstacles or rangers.
 */
public class Player {

    /** The x-coordinate of the player on the game board. */
    private int x;

    /** The y-coordinate of the player on the game board. */
    private int y;

    /** The change in x-coordinate for player movement. */
    private int dx;

    /** The change in y-coordinate for player movement. */
    private int dy;

    /** The speed at which the player moves. */
    private int speed = 5;

    /** The number of baskets collected by the player. */
    private int basketsCollected = 0;

    /** The bounding rectangle representing the player's position and size. */
    private Rectangle bounds;

    /** The current game level the player is interacting with. */
    private Level level;

    /** The image used to visually represent the player. */
    private BufferedImage image;

    /**
     * Constructs a new Player at the specified starting position within the given level.
     *
     * @param x     the initial x-coordinate of the player
     * @param y     the initial y-coordinate of the player
     * @param level the game level in which the player is located
     */
    public Player(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
        bounds = new Rectangle(x, y, 50, 50); // Example size: 50x50 pixels
        loadImage();
    }

    /**
     * Loads the image representing the player character.
     */
    private void loadImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/images/yogi.png");
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                System.err.println("Image not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the player's position based on the current direction (dx, dy),
     * ensuring no collisions with obstacles or out-of-bounds movement.
     */
    public void update() {
        int nextX = x + dx;
        int nextY = y + dy;
        Rectangle nextBounds = new Rectangle(nextX, nextY, bounds.width, bounds.height);

        // Check collisions and boundaries
        if (!level.checkObstacleCollision(nextBounds) && withinBounds(nextBounds)) {
            x = nextX;
            y = nextY;
            bounds.setLocation(x, y);
        }
    }

    /**
     * Checks if the specified rectangle is within the boundaries of the game board.
     *
     * @param rect the rectangle to check
     * @return true if the rectangle is within bounds; false otherwise
     */
    private boolean withinBounds(Rectangle rect) {
        return rect.x >= 0 && rect.x + rect.width <= 800 && rect.y >= 0 && rect.y + rect.height <= 600;
    }

    /**
     * Draws the player on the game board.
     *
     * @param g the Graphics context used for drawing
     */
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, bounds.width, bounds.height, null);
        } else {
            // Fallback to an orange rectangle if the image is not loaded
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, bounds.width, bounds.height);
        }
    }

    /**
     * Handles key press events to set the player's movement direction.
     *
     * @param e the KeyEvent triggered by a key press
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> dy = -speed;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> dy = speed;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> dx = -speed;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> dx = speed;
        }
    }

    /**
     * Handles key release events to stop the player's movement in a specific direction.
     *
     * @param e the KeyEvent triggered by a key release
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_UP, KeyEvent.VK_DOWN -> dy = 0;
            case KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> dx = 0;
        }
    }

    /**
     * Gets the bounding rectangle representing the player's position and size.
     *
     * @return the Rectangle object representing the player's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Increments the player's basket collection count by one.
     */
    public void collectBasket() {
        basketsCollected++;
    }

    /**
     * Gets the number of baskets collected by the player.
     *
     * @return the total number of baskets collected
     */
    public int getBasketsCollected() {
        return basketsCollected;
    }

    /**
     * Resets the player's position to the starting point and updates the bounding rectangle.
     */
    public void resetPosition() {
        x = 50;
        y = 50;
        bounds.setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.setLocation(x, y);
    }

    /**
     * Gets the width of the player.
     *
     * @return the width of the player
     */
    public int getWidth() {
        return bounds.width;
    }

    /**
     * Gets the height of the player.
     *
     * @return the height of the player
     */
    public int getHeight() {
        return bounds.height;
    }

}
