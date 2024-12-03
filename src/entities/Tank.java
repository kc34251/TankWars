package entities;

public class Tank {
    private int x, y;
    private int health;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
    }

    public void move(int distance) {
        x += distance;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

}