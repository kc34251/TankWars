package game;

public class Terrain {
    private int[] heights;

    public Terrain() {
        heights = new int[100]; // Example terrain size
        generateTerrain();
    }

    private void generateTerrain() {
        for (int i = 0; i < heights.length; i++) {
            heights[i] = (int) (Math.random() * 20) + 10; // Random height
        }
    }

    public int getHeightAt(int x) {
        return heights[x];
    }

    public void destroyArea(int x, int radius) {
        for (int i = x - radius; i <= x + radius; i++) {
            if (i >= 0 && i < heights.length) {
                heights[i] -= 5; // Example destruction
                if (heights[i] < 0) heights[i] = 0;
            }
        }
    }

    public int[] getHeights() {
        return heights;
    }
}
