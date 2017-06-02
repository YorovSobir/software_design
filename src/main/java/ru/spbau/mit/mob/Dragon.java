package ru.spbau.mit.mob;

import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.world.Point;
import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for representing Dragon - type of Mobs
 */
public class Dragon extends Mob {

    /**
     * Constructor for creating Dragon
     * @param pos - position in screen
     * @param attr - attributes of Dragon
     * @param mes - message
     * @param world - current world
     */
    public Dragon(Point pos, Attributes attr, ArrayList<String> mes, World world) {
        super(pos, attr, mes, world);
    }

    /**
     * The method for determining an attack of Dragon to other Mobs
     * @param other - Mob whom we attack
     */
    public void attack(Mob other) {
        Attributes attr = getAttr();
        int amount = Math.max(0, attr.getAttackValue() - attr.getDefenseValue());
        amount = (int) (Math.random() * amount) + 1;
        Attributes otherAttr = other.getAttr();
        addMessage(this.getAttr().getName() + " attack the "
                + otherAttr.getName() + " for " + amount + " damage.");
        otherAttr.setCurrentHP(otherAttr.getCurrentHP() - amount);
        attr.setCurrentHP(attr.getCurrentHP() + amount);
        if (attr.getCurrentHP() > attr.getMaxHP()) {
            attr.setCurrentHP(attr.getMaxHP());
        }
        addMessage(this.getAttr().getName() + " regenerated " + amount + " hp.");
    }

    /**
     * The method for determining the method of moving Dragon from one point to another
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
                world.dig(pos.getX() + dx, pos.getY() + dy);
                addMessage("The " + getAttr().getName() + " is digging");
            } else if (world.tile(pos.getX() + dx, pos.getY() + dy) == Tile.FLOOR) {
                pos.move(dx, dy);
            }
        } else {
            attack(other);
        }
    }

    /**
     * Override method for updating dragon position
     */
    @Override
    public void update(Random rnd) {
        int dx = -2 + rnd.nextInt(7);
        int dy = -2 + rnd.nextInt(7);
        moveBy(dx, dy);
    }
}
