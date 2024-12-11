package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Tank {
    private int x; // Tank's x-coordinate (top-left)
    private int y; // Tank's y-coordinate (top-left)
    private int width;
    private int height;
    private Image tankSprite;
    private double rotationAngle; // Angle of rotation based on terrain slope

    public Tank(int startX, int startY, String spritePath) {
        this.x = startX;
        this.tankSprite = new Image(spritePath);
        this.width = (int) tankSprite.getWidth();
        this.height = (int) tankSprite.getHeight();
        this.y = startY - height; // Adjust y to position the tank's bottom on the ground
        this.rotationAngle = 0; // Initial angle
    }

    // Draw the tank sprite with rotation
    public void draw(GraphicsContext gc) {
        gc.save(); // Save the current state
        rotate(gc, rotationAngle, x + width / 2, y + height); // Rotate around tank's bottom center
        gc.drawImage(tankSprite, x, y); // Draw the tank
        gc.restore(); // Restore the original state
    }

    // Update the tank's position and rotation angle
    public void move(int dx, int[] terrainHeights, int segmentWidth) {
        x += dx;

        // Ensure tank stays within bounds
        if (x < 0) x = 0;
        if (x > terrainHeights.length * segmentWidth - width) x = terrainHeights.length * segmentWidth - width;

        // Update y based on terrain height (align bottom of tank with top of terrain)
        int segmentIndex = x / segmentWidth;
        y = terrainHeights[segmentIndex] - height;

        // Calculate rotation angle based on slope
        if (segmentIndex > 0 && segmentIndex < terrainHeights.length - 1) {
            int prevHeight = terrainHeights[segmentIndex - 1];
            int nextHeight = terrainHeights[segmentIndex + 1];
            rotationAngle = Math.toDegrees(Math.atan2(nextHeight - prevHeight, segmentWidth));
        }
    }

    // Utility method to apply rotation to the graphics context
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        gc.translate(px, py); // Move pivot point to rotation center
        gc.rotate(angle); // Rotate the graphics context
        gc.translate(-px, -py); // Move back the pivot point
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
