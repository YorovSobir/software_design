package ru.spbau.mit.mob;

import asciiPanel.AsciiPanel;
import org.junit.Test;
import ru.spbau.mit.attributes.Attributes;
import ru.spbau.mit.inventory.Inventory;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.Point;
import ru.spbau.mit.world.World;
import ru.spbau.mit.world.WorldBuilder;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    private World world;
    @Test
    public void moveByTest() throws Exception {
        createWorld();
        Attributes attr = new Attributes(
                "player",
                '@',
                AsciiPanel.brightWhite,
                100,
                20,
                5);

        Player pl = new Player(new Point(0,0), attr, new Inventory(), new ArrayList<String>(), world);
        pl.moveBy(0,0);
        int x = pl.getPosition().getY();
        int y = pl.getPosition().getX();
        assertEquals(x, 0);
        assertEquals(y, 0);
    }

    @Test
    public void addItemTest() throws Exception {
        createWorld();
        Attributes attr = new Attributes(
                "player",
                '@',
                AsciiPanel.brightWhite,
                100,
                20,
                5);

        Player pl = new Player(new Point(0,0), attr, new Inventory(), new ArrayList<String>(), world);
        pl.addItem(new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                new Point(0,0)));
        assertEquals(pl.getInventory().getSize(), 1);
    }

    @Test
    public void removeItemTest() throws Exception {
        createWorld();
        Attributes attr = new Attributes(
                "player",
                '@',
                AsciiPanel.brightWhite,
                100,
                20,
                5);

        Player pl = new Player(new Point(0,0), attr, new Inventory(), new ArrayList<String>(), world);
        pl.addItem(new Item("power",
                '^',
                AsciiPanel.brightRed,
                10,
                5,
                15,
                new Point(0,0)));
        pl.removeItem(0);
        assertEquals(pl.getInventory().getSize(), 0);
    }

    private void createWorld() {
        world = new WorldBuilder(90, 32)
                .makeCaves()
                .build();
    }
}