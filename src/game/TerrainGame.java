package game;
import javax.swing.*;

public class TerrainGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank Wars");
        TerrainPanel terrainPanel = new TerrainPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(terrainPanel);
        frame.pack();
        frame.setVisible(true);

        // Simulate destruction
        new Timer(1000, e -> {
            terrainPanel.destroyTerrain((int) (Math.random() * 800), 30);
        }).start();
        
    }
}
