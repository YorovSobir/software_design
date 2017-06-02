package ru.spbau.mit.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * neighbors cells of current cell
     * @return List of cells
     */
    public List<Point> neighbors8() {
        List<Point> points = new ArrayList<>();

        for (int ox = -1; ox < 2; ox++) {
            for (int oy = -1; oy < 2; oy++) {
                if (ox == 0 && oy == 0)
                    continue;

                points.add(new Point(x + ox, y + oy));
            }
        }

        Collections.shuffle(points);
        return points;
    }
}
