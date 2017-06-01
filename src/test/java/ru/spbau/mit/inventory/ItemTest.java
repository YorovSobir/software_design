package ru.spbau.mit.inventory;

import asciiPanel.AsciiPanel;
import org.junit.Test;
import ru.spbau.mit.mob.Point;

import static org.junit.Assert.*;

public class ItemTest {

    private  Item it = new Item("power",
            '^',
            AsciiPanel.brightRed,
            10,
            5,
            15,
            new Point(0,0));
    @Test
    public void getAttackTest() throws Exception {
        assertEquals(it.getAttack(), 10);
    }

    @Test
    public void getDefenceTest() throws Exception {
        assertEquals(it.getDefence(), 5);
    }

    @Test
    public void getHealthTest() throws Exception {
        assertEquals(it.getHealth(), 15);
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(it.getName(), "power");
    }

    @Test
    public void getColorTest() throws Exception {
        assertEquals(it.getColor(), AsciiPanel.brightRed);
    }

    @Test
    public void getGlyphTest() throws Exception {
        assertEquals(it.getGlyph(), '^');
    }

}