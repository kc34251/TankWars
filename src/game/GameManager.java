package game;

import entities.Tank;
import graphics.Renderer;

public class GameManager {
    private Renderer renderer;
    private Terrain terrain;
    private Tank player1, player2;
    private boolean isPlayer1Turn;

    public GameManager() {
        terrain = new Terrain();
        player1 = new Tank(10, terrain.getHeightAt(10)); // Example positions
        player2 = new Tank(90, terrain.getHeightAt(90));
        renderer = new Renderer(terrain, player1, player2);
        isPlayer1Turn = true;
    }

    public void start() {
        while (!gameOver()) {
            takeTurn(isPlayer1Turn ? player1 : player2);
            isPlayer1Turn = !isPlayer1Turn;
        }
        System.out.println("Game Over!");
    }

    private void takeTurn(Tank player) {
        renderer.displayGameState();
        // Handle player input and resolve actions
    }

    private boolean gameOver() {
        return player1.isDestroyed() || player2.isDestroyed();
    }
}
