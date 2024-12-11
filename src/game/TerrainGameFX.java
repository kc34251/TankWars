package game;
import entities.Tank;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;

public class TerrainGameFX {
    private static Tank tank;
    private static int[] terrainHeights;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main JFrame
            JFrame frame = new JFrame("Tank Wars");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Create the JavaFX panel
            JFXPanel fxPanel = new JFXPanel();
            frame.add(fxPanel);

            // Initialize JavaFX content
            Platform.runLater(() -> {
                Pane root = new Pane();
                Canvas canvas = new Canvas(800, 600); // Canvas for drawing
                root.getChildren().add(canvas);

                terrainHeights = generateFlatTerrainWithMountain(800, 600, 10);
                tank = new Tank(600, terrainHeights[10], "file:src/resources/pixel-tank.png"); // Tank with sprite

                drawGame(canvas.getGraphicsContext2D());

                Scene scene = new Scene(root, 800, 600);

                // Handle keyboard input for tank movement
                scene.setOnKeyPressed(event -> handleKeyPress(event, canvas.getGraphicsContext2D()));

                fxPanel.setScene(scene);
            });

            frame.setVisible(true);
        });
    }

    // Handle key press events
    private static void handleKeyPress(KeyEvent event, GraphicsContext gc) {
        switch (event.getCode()) {
            case LEFT:
                tank.move(-10, terrainHeights, 10); // Move tank left
                break;
            case RIGHT:
                tank.move(10, terrainHeights, 10); // Move tank right
                break;
            default:
                break;
        }
        drawGame(gc); // Redraw the game
    }

    // Draw the game (terrain + tank)
    private static void drawGame(GraphicsContext gc) {
        // Clear the canvas
        gc.clearRect(0, 0, 800, 600);

        // Draw terrain
        gc.setFill(Color.CYAN);
        gc.fillRect(0, 0, 800, 600); // Sky

        gc.setFill(Color.GREEN);
        for (int i = 0; i < terrainHeights.length - 1; i++) {
            int x1 = i * 10;
            int y1 = terrainHeights[i];
            int x2 = (i + 1) * 10;
            int y2 = terrainHeights[i + 1];

            gc.fillPolygon(new double[]{x1, x2, x2, x1},
                           new double[]{y1, y2, 600, 600},
                           4); // Terrain segment
        }

        // Draw tank
        tank.draw(gc);
    }

    // Generate terrain heights with a flat base and mountain in the middle
    private static int[] generateFlatTerrainWithMountain(int width, int height, int segmentWidth) {
        int numSegments = width / segmentWidth;
        int[] heights = new int[numSegments];
        int baseHeight = height - 100; // Flat terrain height
        int peakHeight = height + 300; // Mountain peak height
        int mid = numSegments / 2;
    
        double baseWidth = 13.0; // Adjust this value to widen the mountain base
    
        for (int i = 0; i < numSegments; i++) {
            int distanceFromCenter = Math.abs(i - mid);
    
            if (distanceFromCenter > baseWidth) {
                // Flat terrain outside the mountain's range
                heights[i] = baseHeight;
            } else {
                // Slope inside the mountain's range
                heights[i] = baseHeight - (int) ((peakHeight - baseHeight) * (1.0 - (distanceFromCenter / baseWidth)));
            }
        }
        return heights;
    }
    
}
