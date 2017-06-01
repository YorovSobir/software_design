package ru.spbau.mit.inventory;

import asciiPanel.AsciiPanel;
import org.junit.Test;
import ru.spbau.mit.world.World;
import ru.spbau.mit.world.WorldBuilder;

import static org.junit.Assert.*;

public class InventoryTest {
    private World world;

    @Test
    public void addTest() throws Exception {
        createWorld();
        Inventory inv = new Inventory();
        Item it = new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                world.getEmptyLocation());
        inv.add(it);
        assertTrue(inv.getSize() == 1);
    }

    @Test
    public void removeTest() throws Exception {
        createWorld();
        Inventory inv = new Inventory();
        Item it = new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                world.getEmptyLocation());
        inv.add(it);
        inv.remove(0);
        assertTrue(inv.getSize() == 0);
    }

    @Test
    public void getSizeTest() throws Exception {
        createWorld();
        Inventory inv = new Inventory();
        Item it = new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                world.getEmptyLocation());
        inv.add(it);
        Item it2 = new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                world.getEmptyLocation());
        inv.add(it2);
        assertTrue(inv.getSize() == 2);
    }

    @Test
    public void getCapacityTest() throws Exception {
        Inventory inv = new Inventory();
        assertTrue(inv.getCapacity() == 11);
    }

    private void createWorld() {
        world = new WorldBuilder(90, 32)
                .makeCaves()
                .build();
    }
}