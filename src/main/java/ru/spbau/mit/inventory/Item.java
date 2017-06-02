package ru.spbau.mit.inventory;


import ru.spbau.mit.world.Point;
import java.awt.*;


/**
 * Class for implementing game's items
 */
public class Item {
    private Point pos;
    private int attack;
    private int defence;
    private int health;
    private String name;
    private Color color;
    private char glyph;

    /**
     * Constructor for creating Item object
     * @param name - name of item
     * @param glyph - symbol to show in screen
     * @param color - color of symbol
     * @param attack - attack value
     * @param defence - defence value
     * @param health - health value
     * @param pos - position in screen
     */
    public Item(String name, char glyph, Color color, int attack, int defence, int health, Point pos) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.glyph = glyph;
        this.color = color;
        this.pos = pos;
    }

    /**
     * get attack value
     * @return return attack value of item
     */
    public int getAttack() {
        return attack;
    }

    /**
     * get defence value
     * @return return defence value of item
     */
    public int getDefence() {
        return defence;
    }


    /**
     * get health value
     * @return return health value of item
     */
    public int getHealth() {
        return health;
    }

    /**
     * get name of item
     * @return return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * get color of item
     * @return return color of item
     */
    public Color getColor() {
        return color;
    }

    /**
     * get symbol of item
     * @return return symbol of item
     */
    public char getGlyph() {
        return glyph;
    }

    /**
     * get position of item
     * @return return position of item
     */
    public Point getPos() {
        return pos;
    }

    /**
     * set new position
     * @param pos - new position
     */
    public void setPos(Point pos) {
        this.pos = pos;
    }
}
