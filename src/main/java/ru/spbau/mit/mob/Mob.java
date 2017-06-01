package ru.spbau.mit.mob;


import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for game characters
 */
public abstract class Mob {
    private Point pos;
    private Attributes attr;
    private List<String> messages;
    private World world;

    /**
     * Constructor for creating Mobs
     * @param pos - position on screen
     * @param attr - attributes
     * @param mes - message
     * @param world - current world
     */
    public Mob(Point pos, Attributes attr, List<String> mes, World world) {
        this.pos = pos;
        this.attr = attr;
        this.messages = mes;
        this.world = world;
    }

    /**
     * default attack method for mobs
     * @param other Mob whom we attack
     */
    public void attack(Mob other) {
        int amount = Math.max(0, this.attr.getAttackValue() - other.attr.getDefenseValue());
        amount = (int) (Math.random() * amount) + 1;
        messages.add(this.getAttr().getName() + " attack the " + other.attr.getName() + " for " + amount + " damage.");
        other.attr.setCurrentHP(other.attr.getCurrentHP() - amount);
    }

    /**
     * get position of mob
     * @return position of mob
     */
    public Point getPosition() {
        return pos;
    }

    /**
     * get attribute of mob
     * @return attribute of mob
     */
    public Attributes getAttr() {
        return attr;
    }

    /**
     * get current world
     * @return current world
     */
    public World getWorld() {
        return world;
    }

    /**
     * add message to List
     * @param message - message
     */
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * abstract method for updating mob's position
     */
    abstract public void update();

}