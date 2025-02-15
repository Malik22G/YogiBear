package org.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a ranger enemy that moves within the game level.
 */
public class Ranger {
    private int x, y;
    private int width = 40, height = 40;
    private Rectangle bounds;
    private String movementPattern;
    private int speed = 2;
    private int direction = 1;
    private Level level;
    private BufferedImage rangerImage;

    /**
     * Constructs a new Ranger with the specified position and movement pattern.
     *
     * @param x                the initial x-coordinate
     * @param y                the initial y-coordinate
     * @param movementPattern  the movement pattern ("Horizontal" or "Vertical")
     * @param level            the current Level instance
     */
    public Ranger(int x, int y, String movementPattern, Level level) {
        this.x = x;
        this.y = y;
        this.movementPattern = movementPattern;
        this.level = level;
        bounds = new Rectangle(x, y, width, height);
        loadRangerImage();
    }

    /**
     * Loads the ranger image from the resources.
     */
    private void loadRangerImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/images/ranger.png");
            if (is != null) {
                rangerImage = ImageIO.read(is);
            } else {
                System.err.println("Error: Ranger image not found!");
            }
        } catch (IOException e) {
            System.err.println("Error loading ranger image.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the ranger's position based on its movement pattern and checks for collisions.
     */
    public void update() {
        int nextX = x;
        int nextY = y;

        if (movementPattern.equals("Horizontal")) {
            nextX += speed * direction;
            if (nextX <= 0 || nextX + width >= 800) {
                direction *= -1;
                nextX = Math.max(0, Math.min(800 - width, nextX));
            }
        } else if (movementPattern.equals("Vertical")) {
            nextY += speed * direction;
            if (nextY <= 0 || nextY + height >= 600) {
                direction *= -1;
                nextY = Math.max(0, Math.min(600 - height, nextY));
            }
        }

        Rectangle nextBounds = new Rectangle(nextX, nextY, width, height);

        if (level.checkObstacleCollision(nextBounds)) {
            direction *= -1;
        } else {
            x = nextX;
            y = nextY;
        }

        bounds.setLocation(x, y);
    }

    /**
     * Draws the ranger on the provided Graphics context.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        if (rangerImage != null) {
            g.drawImage(rangerImage, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    /**
     * Retrieves the bounding rectangle of the ranger.
     *
     * @return the Rectangle representing the ranger's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
