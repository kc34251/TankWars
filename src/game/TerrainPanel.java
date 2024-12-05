package game;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TerrainPanel extends JPanel {
    private int[] terrainHeights; // Array to store terrain heights
    private final int WIDTH = 800;  // Panel width
    private final int HEIGHT = 600; // Panel height
    private final int SEGMENT_WIDTH = 10; // Width of each terrain segment

    public TerrainPanel() {
        terrainHeights = new int[WIDTH / SEGMENT_WIDTH];
        generateTerrain();
    }

    private void generateTerrain() {
        int mid = terrainHeights.length / 2;
        int peakHeight = HEIGHT / 2; // Height of the mountain peak
        int baseHeight = HEIGHT - 100; // Flat base height
    
        for (int i = 0; i < terrainHeights.length; i++) {
            if (i < mid - 10 || i > mid + 10) {
                // Flat base on either side of the mountain
                terrainHeights[i] = baseHeight;
            } else {
                // Create a triangular mountain
                int distanceFromPeak = Math.abs(i - mid);
                terrainHeights[i] = baseHeight - (int)((peakHeight * (10 - distanceFromPeak)) / 10.0);
            }
        }
    }
    

    // Destroy a circular area of terrain
    public void destroyTerrain(int x, int radius) {
        int center = x / SEGMENT_WIDTH; // Convert pixel x to segment index
    
        for (int i = center - radius / SEGMENT_WIDTH; i <= center + radius / SEGMENT_WIDTH; i++) {
            if (i >= 0 && i < terrainHeights.length) {
                // Calculate distance from the center of the destruction
                int distanceFromCenter = Math.abs(i - center);
                int destructionDepth = radius - distanceFromCenter * SEGMENT_WIDTH;
    
                // Only reduce height if it's within the destruction depth
                if (destructionDepth > 0) {
                    terrainHeights[i] = Math.max(terrainHeights[i] + destructionDepth, terrainHeights[i]); // Clamp to zero
                }
            }
        }
        repaint(); // Redraw the terrain
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Clear the panel
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the terrain
        g.setColor(Color.GREEN);
        for (int i = 0; i < terrainHeights.length; i++) {
            int x = i * SEGMENT_WIDTH;
            int y = terrainHeights[i];
            g.fillRect(x, y, SEGMENT_WIDTH, HEIGHT - y);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
