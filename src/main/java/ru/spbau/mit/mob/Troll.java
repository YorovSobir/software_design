package ru.spbau.mit.mob;


import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for representing Troll - type of Mobs
 */
public class Troll extends Mob {
    /**
     * Constructor for creating Troll
     * @param pos - position in screen
     * @param attr - attributes of Troll
     * @param mes - message
     * @param world - current world
     */
    public Troll(Point pos, Attributes attr,
                 List<String> mes, World world) {
        super(pos, attr, mes, world);
    }
    /**
     * The method for determining an attack of Troll to other Mobs
     * @param other - Mob whom we attack
     */
    public void attack(Mob other) {
        Attributes attr = getAttr();
        int amount = Math.max(0, attr.getAttackValue());
        amount = (int) (Math.random() * amount) + 1;
        Attributes otherAttr = other.getAttr();
        addMessage(this.getAttr().getName()
                + " attack the " + otherAttr.getName()
                + " for " + amount + " damage.");
        otherAttr.setCurrentHP(otherAttr.getCurrentHP() - amount);
    }

    /**
     * The method for determining the method of moving Troll from one point to another
     * @param dx - shift relative to the X axis
     * @param dy - shift relative to the Y axis
     */
    public void moveBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        World world = getWorld();
        Point pos = getPosition();
        Mob other = world.checkMob(pos.getX() + dx, pos.getY() + dy);
        if (other == null) {
            if (world.tile(pos.getX() + dx, pos.getY() + dy) == Tile.WALL) {
                addMessage("The " + getAttr().getName() + " hit the wall");
            } else if (world.tile(pos.getX() + dx, pos.getY() + dy) == Tile.FLOOR) {
                pos.move(dx, dy);
            }
        } else {
            attack(other);
        }
    }

    /**
     * Override method for updating troll position
     */
    @Override
    public void update() {
        final Random rnd = new Random();
        int dx = -2 + rnd.nextInt(4);
        int dy = -2 + rnd.nextInt(4);
        moveBy(dx, dy);
    }
}
