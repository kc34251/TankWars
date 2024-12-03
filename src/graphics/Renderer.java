package graphics;

import entities.Tank;
import game.Terrain;

public class Renderer {
    private Terrain terrain;
    private Tank player1, player2;

    public Renderer(Terrain terrain, Tank player1, Tank player2) {
        this.terrain = terrain;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void displayGameState() {
        System.out.println("Rendering game...");
        // Code to render the game state (ASCII art or graphics library)
    }
}
