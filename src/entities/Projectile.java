package entities;

public class Projectile {
    private int x, y;
    private int velocityX, velocityY;

    public Projectile(int x, int y, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        velocityY -= 1; // Simulate gravity
    }

    public boolean hasHit(int targetX, int targetY) {
        return Math.abs(x - targetX) < 2 && Math.abs(y - targetY) < 2;
    }
}