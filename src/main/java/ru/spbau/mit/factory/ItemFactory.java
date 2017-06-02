package ru.spbau.mit.factory;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class for creating game items
 * */
public class ItemFactory {
    private World world;
    private ArrayList<String> itemTypes = new ArrayList<>(Arrays.asList("Power", "Defense", "Health"));

    /**
     * Constructor for creating ItemFactory
     * @param world - current world of game
     */
    public ItemFactory(World world) {
        this.world = world;
    }

    /**
     * The method creates a feature of a certain type
     * @param name Type of feature
     * @return item object or null if the object could not be created
     * */
    public Item getItem(String name, Random rnd) {
        switch (name) {
            case "Power":
                return new Item("power", '^',
                        AsciiPanel.brightRed,
                        rnd.nextInt(100),
                        0, 0,
                        world.getEmptyLocation());
            case "Defense":
                return new Item("defense", '#',
                        AsciiPanel.brightCyan, 0,
                        rnd.nextInt(100), 0,
                        world.getEmptyLocation());
            case "Health":
                return new Item("health", '+',
                        AsciiPanel.brightGreen, 0, 0,
                        rnd.nextInt(100), world.getEmptyLocation());
        }
        return null;
    }
    /**
     * The method returns the types of features
     * */
    public ArrayList<String> getItemTypes() {
        return itemTypes;
    }
}
