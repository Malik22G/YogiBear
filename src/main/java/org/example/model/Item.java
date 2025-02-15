package org.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents an item (basket) that the player can collect.
 */
public class Item {
    private int x, y;
    private int width = 30, height = 30;
    private Rectangle bounds;
    private BufferedImage image;

    /**
     * Constructs a new Item at the specified position.
     *
     * @param x the x-coordinate of the item
     * @param y the y-coordinate of the item
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, width, height);
        loadImage();
    }

    /**
     * Loads the basket image from the resources.
     */
    private void loadImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/images/basket.png");
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                System.err.println("Basket image not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the basket on the provided Graphics context.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
        }
    }

    /**
     * Retrieves the bounding rectangle of the basket.
     *
     * @return the Rectangle representing the basket's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
