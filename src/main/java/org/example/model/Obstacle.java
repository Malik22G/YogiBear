package org.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Represents an obstacle within the game level.
 * Obstacles can impede the player's movement.
 */
public class Obstacle {
    private int x, y;
    private int width, height;
    private String type;
    private Rectangle bounds;
    private BufferedImage obstacleImage;

    /**
     * Constructs a new Obstacle with the specified type and position.
     *
     * @param type the type of the obstacle
     * @param x    the x-coordinate of the obstacle
     * @param y    the y-coordinate of the obstacle
     */
    public Obstacle(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        loadObstacleImage(type);

        if (type.equals("Tree")) {
            width = 50;
            height = 50;
        } else if (type.equals("Mountain")) {
            width = 100;
            height = 100;
        }
        bounds = new Rectangle(x, y, width, height);
    }

    private void loadObstacleImage(String type) {
        String imagePath = null;

        if (type.equals("Tree")) {
            imagePath = "/images/tree1.png";
        } else if (type.equals("Mountain")) {
            imagePath = "/images/mountain.png";
        }

        if (imagePath != null) {
            try (InputStream is = getClass().getResourceAsStream(imagePath)) {
                if (is != null) {
                    obstacleImage = ImageIO.read(is);
                } else {
                    System.err.println("Error: Image not found for obstacle type: " + type);
                }
            } catch (IOException e) {
                System.err.println("Error loading image for obstacle type: " + type);
                e.printStackTrace();
            }
        }
    }
    /**
     * Draws the obstacle on the provided Graphics context.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        if (obstacleImage != null) {
            g.drawImage(obstacleImage, x, y, width, height, null);
        } else {
            // Fallback: draw a rectangle if the image fails to load
            if (type.equals("Tree")) {
                g.setColor(new Color(34, 139, 34)); // Forest Green
            } else if (type.equals("Mountain")) {
                g.setColor(new Color(139, 137, 137)); // Gray
            }
            g.fillRect(x, y, width, height);
        }
    }
    /**
     * Retrieves the bounding rectangle of the obstacle.
     *
     * @return the Rectangle representing the obstacle's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
