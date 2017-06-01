package ru.spbau.mit.mob;

/**
 * Coordinates of the object
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructor for creating Point object
     * @param x - coordinate on X axes
     * @param y - coordinate on Y axes
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get x coordinate
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * get y coordinate
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Moving an object
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
