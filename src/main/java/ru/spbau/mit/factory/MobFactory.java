package ru.spbau.mit.factory;

import org.w3c.dom.Attr;
import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.inventory.Inventory;
import ru.spbau.mit.mob.*;
import ru.spbau.mit.world.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for creating mobs
 * */
public class MobFactory {
    private World world;
    private ArrayList<String> mobTypes =
            new ArrayList<>(Arrays.asList("orc", "troll", "dragon"));

    /**
     * Constructor for creating MobFactory
     * @param world - current world of game
     */
    public MobFactory(World world) {
        this.world = world;
    }

    /**
     * The method creates a mob of a certain type
     * @param name Type of mob
     * @return mob object or null if the object could not be created
     * */
    public Mob getMob(String name, ArrayList<String> messages) {
        Attributes attr;
        switch (name) {
            case "player":
                return getPlayer(messages);
            case "orc":
                attr = Attributes.orcAttributes();
                return new Orc(world.getEmptyLocation(), attr, messages, world);
            case "troll":
                attr = Attributes.trollAttributes();
                return new Troll(world.getEmptyLocation(), attr, messages, world);
            case "dragon":
                attr = Attributes.dragonAttributes();
                return new Dragon(world.getEmptyLocation(), attr, messages, world);
        }
        return null;
    }

    /**
     * The method returns the types of mobs
     * */
    public ArrayList<String> getMobTypes() {
        return mobTypes;
    }

    private Mob getPlayer(List<String> messages) {
        Attributes attr = Attributes.playerAttributes();
        return new Player(world.getEmptyLocation(), attr, new Inventory(), messages, world);

    }
}
