package ru.spbau.mit.attributes;

import asciiPanel.AsciiPanel;

import java.awt.*;

/**
 * Class for representing game's characteristic of mobs
 */
public class Attributes {
    private String name;
    private char glyph;
    private Color color;
    private int maxHP;
    private int currentHP;
    private int attackValue;
    private int defenseValue;

    /**
     * Default Attributes for player
     * @return default attributes of player
     */
    public static Attributes playerAttributes() {
        return new Attributes(
                "player",
                '@',
                AsciiPanel.brightWhite,
                100,
                20,
                5);
    }

    /**
     * default attributes for Orc
     * @return default attributes for Orc
     */
    public static Attributes orcAttributes() {
        return new Attributes(
                "orc",
                'o',
                AsciiPanel.green,
                200,
                40,
                20);
    }

    /**
     * default attributes for Troll
     * @return default attributes for Troll
     */
    public static Attributes trollAttributes() {
        return new Attributes(
                "troll",
                't',
                AsciiPanel.brightBlue,
                70,
                20,
                15);
    }

    /**
     * default attributes for Dragon
     * @return default attributes for Dragon
     */
    public static Attributes dragonAttributes() {
        return new Attributes(
                "dragon",
                'd',
                AsciiPanel.red,
                500,
                70,
                50);
    }

    /**
     * Constructor for creating new Attributes
     * @param name - name of attributes
     * @param glyph - symbol for representing attribute in screen
     * @param color - color of @glyph
     * @param maxHP - max health
     * @param attackValue - attack value
     * @param defenseValue - defence value
     */
    public Attributes(String name, char glyph, Color color, int maxHP, int attackValue, int defenseValue) {
        this.name = name;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.currentHP = this.maxHP;
    }

    /**
     * get glyph of attribute
     * @return glyph of attribute
     */
    public char getGlyph() {
        return glyph;
    }

    /**
     * get color of attribute's glyph
     * @return color of attribute's glyph
     */
    public Color getColor() {
        return color;
    }

    /**
     * get max health of attribute
     * @return max health of attribute
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * set max health of attribute
     */
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * get attack value of attribute
     * @return attack value of attribute
     */
    public int getAttackValue() {
        return attackValue;
    }

    /**
     * set attack value of attribute
     */
    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    /**
     * get defence value of attribute
     * @return defence value of attribute
     */
    public int getDefenseValue() {
        return defenseValue;
    }

    /**
     * set defence value of attribute
     */
    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    /**
     * get current health of attribute
     * @return current health of attribute
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * set current health of attribute
     */
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * get name of attribute
     * @return name of attribute
     */
    public String getName() {
        return name;
    }
}
