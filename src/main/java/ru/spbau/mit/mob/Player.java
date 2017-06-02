package ru.spbau.mit.mob;


import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.inventory.Inventory;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.Point;
import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.List;
import java.util.Random;

/**
 * Class for representing our Hero in game
 */
public class Player extends Mob {
    private Inventory inventory;

    /**
     * Constructor for creating Player
     * @param pos - position of player on screen
     * @param attr - attributes of player
     * @param inv - inventory of player
     * @param mes - message
     * @param world - current world
     */
    public Player(Point pos, Attributes attr, Inventory inv,
                  List<String> mes, World world) {
        super(pos, attr, mes, world);
        inventory = inv;
    }

    /**
     * The method for determining the method of moving player from one point to another
     * @param dx - shift relative to the X axis
     * @param dy - shift relative to the Y axis
     */
    public void moveBy(int dx, int dy) {
        World world = getWorld();
        Point pos = getPosition();
        Mob other = world.checkMob(pos.getX() + dx, pos.getY() + dy);
        Item item = world.checkItem(pos.getX() + dx, pos.getY() + dy);
        if (dx == 0 && dy == 0) {
            if (item != null) {
                addMessage(item.getName() + "(dmg: " + item.getAttack() + "; def: " + item.getDefence() + "; hp: " + item.getHealth() + ")");
            }
            return;
        }
        if (other == null && item == null) {
            if (world.tile(pos.getX() + dx, pos.getY() + dy) == Tile.WALL) {
                world.dig(pos.getX() + dx, pos.getY() + dy);
                addMessage("The " + getAttr().getName() + " is digging");
            } else if (world.tile(pos.getX() + dx, pos.getY() + dy) == Tile.FLOOR) {
                pos.move(dx, dy);
            }
        } else if (other != null) {
            attack(other);
        } else {
            addMessage(item.getName() + "(dmg: " + item.getAttack() + "; def: " + item.getDefence() + "; hp: " + item.getHealth() + ")");
            pos.move(dx, dy);
        }
    }

    @Override
    public void update(Random rnd) {}

    /**
     * The method to add new Item to our inventory
     * @param item - new Item
     */
    public void addItem(Item item) {
        if (item != null && inventory.getSize() < inventory.getCapacity()) {
            inventory.add(item);
            Attributes attr = getAttr();
            attr.setAttackValue(attr.getAttackValue() + item.getAttack());
            attr.setDefenseValue(attr.getDefenseValue() + item.getDefence());
            attr.setCurrentHP(attr.getCurrentHP() + item.getHealth());
            if (attr.getMaxHP() < attr.getCurrentHP()) {
                attr.setMaxHP(attr.getMaxHP() + item.getHealth());
            }
            addMessage(item.getName() + " is inserted into the cell");
            getWorld().removeItem(item);
        } else {
            addMessage("No free cell for features");
        }
    }

    /**
     * to drop item from our inventory
     * @param item - item which we want to drop
     */
    public void dropItem(Item item) {
        inventory.drop(item);
        Attributes attr = getAttr();
        attr.setAttackValue(attr.getAttackValue() - item.getAttack());
        attr.setDefenseValue(attr.getDefenseValue() - item.getDefence());
        attr.setCurrentHP(attr.getCurrentHP() - item.getHealth());
        if (attr.getMaxHP() > 100) {
            attr.setMaxHP(attr.getMaxHP() - item.getHealth());
        }
        addMessage(item.getName() + " is dropped from inventories");
        getWorld().addAtEmptySpace(item, getPosition().getX(), getPosition().getY());
    }

    /**
     * The method to remove item from inventory
     * @param index index of item in inventory
     */
    public void removeItem(int index) {
        if (index < inventory.getSize()) {
            inventory.remove(index);
        }
    }

    /**
     * get current inventory of player
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
}
