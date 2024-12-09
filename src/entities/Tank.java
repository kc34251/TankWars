package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tank {
    private int x; // Tank's x-coordinate (top-left)
    private int y; // Tank's y-coordinate (top-left)
    private int width;
    private int height;
    private Image tankSprite;

    public Tank(int startX, int startY, String spritePath) {
        this.x = startX;
        this.tankSprite = new Image(spritePath);
        this.width = (int) tankSprite.getWidth();
        this.height = (int) tankSprite.getHeight();
        this.y = startY - height; // Adjust y to position the tank's bottom on the ground
    }

    // Draw the tank sprite
    public void draw(GraphicsContext gc) {
        gc.drawImage(tankSprite, x, y);
    }

    // Move the tank
    public void move(int dx, int[] terrainHeights, int segmentWidth) {
        x += dx;

        // Ensure tank stays within bounds
        if (x < 0) x = 0;
        if (x > terrainHeights.length * segmentWidth - width) x = terrainHeights.length * segmentWidth - width;

        // Update y based on terrain height (align bottom of tank with top of terrain)
        int segmentIndex = x / segmentWidth;
        y = terrainHeights[segmentIndex] - height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
