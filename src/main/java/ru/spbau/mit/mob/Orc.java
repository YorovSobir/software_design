package ru.spbau.mit.mob;


import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for representing Orc - type of Mobs
 */
public class Orc extends Mob {
    /**
     * Constructor for creating Orc
     * @param pos - position in screen
     * @param attr - attributes of Orc
     * @param mes - message
     * @param world - current world
     */
    public Orc(Point pos, Attributes attr,
               List<String> mes, World world) {
        super(pos, attr, mes, world);
    }

    /**
     * The method for determining the method of moving Orc from one point to another
     * @param dx - shift relative to the X axis
     * @param dy - shift relative to the Y axis
     */
    public void moveBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        World world = getWorld();
        Point position = getPosition();
        Mob other = world.checkMob(position.getX() + dx, position.getY() + dy);
        if (other == null) {
            if (world.tile(position.getX() + dx, position.getY() + dy) == Tile.WALL) {
                world.dig(position.getX() + dx, position.getY() + dy);
                addMessage("The " + getAttr().getName() + " hit the wall");
            } else if (world.tile(position.getX() + dx, position.getY() + dy) == Tile.FLOOR) {
                position.move(dx, dy);
            }
        } else {
            attack(other);
        }
    }
    /**
     * Override method for updating orc position
     */
    @Override
    public void update() {
        final Random rnd = new Random();
        int dx = -1 + rnd.nextInt(3);
        int dy = -1 + rnd.nextInt(3);
        moveBy(dx, dy);
    }
}
